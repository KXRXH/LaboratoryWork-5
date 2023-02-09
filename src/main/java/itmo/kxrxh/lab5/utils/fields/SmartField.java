package itmo.kxrxh.lab5.utils.fields;

import java.lang.reflect.Field;

public class SmartField {
    private final Field field;
    private final Object object;

    public SmartField(Field field, Object object) {
        this.field = field;
        this.object = object;
    }

    public Field getField() {
        return field;
    }

    public Object getObject() {
        return object;
    }
}
