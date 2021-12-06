package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.AbstractTextParser;

public class SentenceParser extends AbstractTextParser {//todo third parser

    public SentenceParser(){
        successor = new LexemeParser();
    }

    @Override
    public TextComponent parse(String text) {
        return null;
    }
}
