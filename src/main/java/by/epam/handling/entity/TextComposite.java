package by.epam.handling.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextComposite implements TextComponent{
    private TextComponentType type;
    private List<TextComponent> components;

    public TextComposite(TextComponentType type) {
        this(type, new ArrayList<>());
    }

    public TextComposite(TextComponentType type, List<TextComponent> components) {
        this.type = type;
        this.components = components;
    }

    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getComponents() {
        return List.copyOf(components);
    }

    @Override
    public int getSize() {
        return components.size();
    }

    public void setType(TextComponentType type) {
        this.type = type;
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    public boolean addAll(Collection<? extends TextComponent> c) {
        return components.addAll(c);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
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
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent element : components) {
            stringBuilder.append(type.getPrefix())
                         .append(element.toString())
                         .append(type.getPostfix());
        }
        return stringBuilder.toString();
    }
}
