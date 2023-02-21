package itmo.kxrxh.lab5.utils.xml;

import itmo.kxrxh.lab5.collection.ProductCollector;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.types.builders.Builder;
import itmo.kxrxh.lab5.utils.strings.StringUtils;
import itmo.kxrxh.lab5.utils.xml.exceptions.FileNameIsNullException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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

    public ProductCollector parse() {
        ProductCollector productCollector;
        productCollector = readXML(xml.getXmlFile());
        return productCollector;
    }

    public ProductCollector parse(File file) {
        ProductCollector productCollector;
        productCollector = readXML(file);
        return productCollector;
    }

    private ProductCollector readXML(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        doc.normalizeDocument();
        Element root = doc.getDocumentElement();
        NodeList rootTree = root.getChildNodes();
        ProductCollector productCollector = new ProductCollector();
        for (int i = 0; i < rootTree.getLength(); i++) {
            Node node = rootTree.item(i);
            // Getting only Products
            if (node.getNodeName().equals("Product")) {
                Product pr;
                try {
                    pr = (Product) parseItem(node);
                } catch (ClassNotFoundException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                productCollector.add(pr);
            }
        }
        return productCollector;
    }

    private Object parseItem(Node node) throws ClassNotFoundException {
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

    @SuppressWarnings("unchecked")
    protected <T> void setValueToField(Object item, String fieldName, T value) {
        if (item instanceof Collection<?>) {
            ((Collection<T>) item).add(value);
            return;
        }
        Field field = findField(item, toCamelCase(fieldName));
        if (field == null) {
            System.err.println("Field " + fieldName + " not found");
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
    private <T> void setEnumValue(Field field, Object item, T value) {
        try {
            field.set(item, Enum.valueOf((Class<Enum>) field.getType(), (String) value));
        } catch (IllegalAccessException e) {
            System.out.println("Error while parsing enum. Check if value is enum or enum has this value: " + value);
        }
    }

    private <T> void setFieldValue(Field field, Object item, T value) {
        try {
            Class<?> type = field.getType();
            if (value == "null") {
                field.set(item, null);
                return;
            }
            field.set(item, switch (type.getSimpleName()) {
                case "LocalDateTime" -> LocalDateTime.parse((String) value);
                case "Integer", "int" -> Integer.parseInt((String) value);
                case "Long", "long" -> Long.parseLong((String) value);
                case "Float", "float" -> Float.parseFloat((String) value);
                case "Double", "double" -> Double.parseDouble((String) value);
                default -> value;
            });
        } catch (IllegalAccessException e) {
            System.err.printf("Error while parsing %s. Check if value is %s%n", field.getType().getSimpleName(), field.getType().getSimpleName());
        }
    }
}