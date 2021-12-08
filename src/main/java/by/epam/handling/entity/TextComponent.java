package by.epam.handling.entity;

import java.util.List;

public interface TextComponent {
    boolean add(TextComponent component);
    boolean remove(TextComponent component);
    TextComponentType getType();
    List<TextComponent> getComponents();
    int getSize();
    String toString();
}
