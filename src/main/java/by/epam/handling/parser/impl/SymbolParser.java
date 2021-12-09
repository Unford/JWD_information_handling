package by.epam.handling.parser.impl;

import by.epam.handling.entity.*;
import by.epam.handling.parser.AbstractTextParser;

import static by.epam.handling.entity.TextComponentType.SYMBOL;
import static by.epam.handling.entity.SymbolType.*;

public class SymbolParser extends AbstractTextParser {

    private static final String VOWEL_REGEX = "[aeiouAEIOUауоыиэяюёеАУОЫИЭЯЮЁЕ]";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";
    private static final String LETTER_REGEX = "\\p{L}";
    private static final String DIGIT_REGEX = "\\d";

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
        TextComponent symbolComponent = new TextComposite(SYMBOL);
        char symbol = text.charAt(0);

        if (text.matches(PUNCTUATION_REGEX)){
            symbolComponent.add(new Symbol(symbol, PUNCTUATION));
        } else if (text.matches(DIGIT_REGEX)) {
            symbolComponent.add(new Symbol(symbol, DIGIT));
        } else if (text.matches(LETTER_REGEX)){
            SymbolType symbolType = text.matches(VOWEL_REGEX) ? VOWEL : CONSONANT;
            symbolComponent.add(new Symbol(symbol, symbolType));
        }

        return symbolComponent;
    }
}
