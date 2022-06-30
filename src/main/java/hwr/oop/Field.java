package hwr.oop;

import java.util.Objects;

class Field {
    private FieldType fieldType;
    public Field(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    FieldType getFieldType() {
    return fieldType;
    }

    void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Field field = (Field) obj;
        return Objects.equals(fieldType, field.getFieldType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldType);
    }
}
