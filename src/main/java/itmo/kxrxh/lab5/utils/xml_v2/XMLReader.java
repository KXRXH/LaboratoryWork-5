package itmo.kxrxh.lab5.utils.xml_v2;

import itmo.kxrxh.lab5.collection.ProductCollector;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.types.builders.Builder;
import itmo.kxrxh.lab5.utils.strings.StringUtils;
import itmo.kxrxh.lab5.utils.xml_v2.exceptions.FileNameIsNullException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static itmo.kxrxh.lab5.utils.strings.StringUtils.toCamelCase;

public class XMLReader extends XmlAction {
    public XMLReader(XML xml) {
        super(xml);
    }

    public ProductCollector parse() throws Exception {
        ProductCollector productCollector;
        String filename = xml.getXmlFileName();
        if (filename != null) {
            productCollector = readXML(filename);
        } else {
            throw new FileNameIsNullException();
        }
        return productCollector;
    }

    private static ProductCollector readXML(String file) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = dbf.newDocumentBuilder().parse(file);
        doc.normalizeDocument();
        Element root = doc.getDocumentElement();
        NodeList rootTree = root.getChildNodes();
        ProductCollector productCollector = new ProductCollector();
        for (int i = 0; i < rootTree.getLength(); i++) {
            Node node = rootTree.item(i);
            // Getting only Products
            if (node.getNodeName().equals("Product")) {
                Product pr = (Product) parseItem(node);
                productCollector.add(pr);
            }
        }
        return productCollector;
    }

    private static Object parseItem(Node node) throws ClassNotFoundException {
        NodeList innerNodes = node.getChildNodes();
        // Getting builder's clazz
        Class<?> builderClass = Class.forName("%s.%sBuilder".formatted("itmo.kxrxh.lab5.types.builders", StringUtils.capitalize(node.getNodeName())));
        Builder builder;
        try {
            builder = (Builder) builderClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Can't create instance of class");
            return null;
        } catch (InvocationTargetException | NoSuchMethodException e) {
            System.err.printf("Class %s doesn't have default constructor%n", node.getNodeName());
            return null;
        }
        for (int j = 0; j < innerNodes.getLength(); j++) {
            Node item = innerNodes.item(j);
            // Skipping #text lines
            if (item instanceof Text) {
                continue;
            }
            // Checking if item is an object
            if (item.getChildNodes().getLength() > 1) {
                String fieldName = item.getNodeName().toLowerCase(); // fields of objects are in lower case
                setValueToField(builder, fieldName, parseItem(item));
            } else {
                setValueToField(builder, item.getNodeName(), item.getFirstChild().getNodeValue());
            }
        }
        return builder.build();
    }

    protected static <T> void setValueToField(Object item, String fieldName, T value) {
        if (item instanceof Collection<?>) {
            ((Collection<T>) item).add(value);
            return;
        }
        Field field = findField(item, toCamelCase(fieldName));
        if (field == null) {
            System.out.println("Field " + fieldName + " not found");
            return;
        }
        field.setAccessible(true);
        if (field.getType().isEnum()) {
            setEnumValue(field, item, value);
        } else {
            setFieldValue(field, item, value);
        }
    }

    private static Field findField(Object item, String fieldName) {
        return Arrays.stream(item.getClass().getDeclaredFields()).filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> void setEnumValue(Field field, Object item, T value) {
        try {
            field.set(item, Enum.valueOf((Class<Enum>) field.getType(), (String) value));
        } catch (IllegalAccessException e) {
            System.out.println("Error while parsing enum. Check if value is enum or enum has this value: " + value);
        }
    }

    private static <T> void setFieldValue(Field field, Object item, T value) {
        try {
            Class<?> type = field.getType();
            if (value == "null") {
                field.set(item, null);
                return;
            }
            switch (type.getSimpleName()) {
                case "LocalDateTime" -> field.set(item, LocalDateTime.parse((String) value));
                case "Integer", "int" -> field.set(item, Integer.parseInt((String) value));
                case "Long", "long" -> field.set(item, Long.parseLong((String) value));
                case "Float", "float" -> field.set(item, Float.parseFloat((String) value));
                case "Double", "double" -> field.set(item, Double.parseDouble((String) value));
                default -> field.set(item, value);
            }
        } catch (IllegalAccessException e) {
            System.out.println("Error while parsing " + field.getType().getSimpleName() + ". Check if value is " + field.getType().getSimpleName());
        }
    }
}