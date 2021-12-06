package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.AbstractTextParser;

public class LexemeParser extends AbstractTextParser {//todo fourth parser

    public LexemeParser(){
        successor = new WordParser();
    }

    @Override
    public TextComponent parse(String text) {
        return null;
    }
}
