package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;

import static by.epam.handling.entity.TextComponentType.NUMBER;

public class ExpressionParser extends AbstractTextParser {//todo interpreter

    @Override
    public TextComponent parse(String text) {
        TextComponent numberComponent = new TextComposite(NUMBER);

        return numberComponent;
    }
}
