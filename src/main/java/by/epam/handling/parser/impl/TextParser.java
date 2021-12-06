package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.AbstractTextParser;

public class TextParser extends AbstractTextParser {//todo first parser

    public TextParser(){
        successor = new ParagraphParser();
    }

    @Override
    public TextComponent parse(String text) {
        return null;
    }
}
