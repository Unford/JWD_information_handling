package by.epam.handling.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{
    private TextComponentType type;
    private List<TextComponent> components;

    public TextComposite(TextComponentType type) {
        this.type = type;
        this.components = new ArrayList<>();
    }

    public TextComponentType getType() {
        return type;
    }

    public void setType(TextComponentType type) {
        this.type = type;
    }

    @Override
    public boolean add(TextComponent component) {
        return component.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return component.remove(component);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        if (type != that.type) return false;
        return components != null ? components.equals(that.components) : that.components == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {//todo change
        return "TextComposite{" +
                "type=" + type +
                ", components=" + components +
                '}';
    }
}
