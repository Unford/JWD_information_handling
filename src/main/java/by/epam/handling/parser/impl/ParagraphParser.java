package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.AbstractTextParser;

public class ParagraphParser extends AbstractTextParser {//todo second parser

    public ParagraphParser(){
        successor = new SentenceParser();
    }

    @Override
    public TextComponent parse(String text) {
        return null;
    }
}
