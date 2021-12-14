package by.epam.handling.parser.impl;

import by.epam.handling.entity.*;
import by.epam.handling.parser.AbstractTextParser;

import static by.epam.handling.entity.SymbolType.*;

public class SymbolParser extends AbstractTextParser {

    private static SymbolParser instance;

    private SymbolParser(){}

    public static SymbolParser getInstance(){
        if (instance == null){
            instance = new SymbolParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {

        char symbol = text.charAt(0);
        SymbolType symbolType = UNDEFINED;

        if (text.matches(PUNCTUATION_REGEX)) {
            symbolType = PUNCTUATION;
        } else if (text.matches(DIGIT_REGEX)) {
            symbolType = DIGIT;
        } else if (text.matches(LETTER_REGEX)) {
            symbolType = text.matches(VOWEL_REGEX) ? VOWEL : CONSONANT;
        }

        return new Symbol(symbol, symbolType);
    }
}
