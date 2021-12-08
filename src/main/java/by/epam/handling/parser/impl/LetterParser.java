package by.epam.handling.parser.impl;

import by.epam.handling.entity.*;
import by.epam.handling.parser.AbstractTextParser;

public class LetterParser extends AbstractTextParser {
    private static final String VOWEL_REGEX = "[aeiouAEIOUауоыиэяюёеАУОЫИЭЯЮЁЕ]";

    private static LetterParser instance;

    private LetterParser(){}

    public static LetterParser getInstance(){
        if (instance == null){
            instance = new LetterParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent symbolComponent = new TextComposite(TextComponentType.SYMBOL);

        SymbolType symbolType = text.matches(VOWEL_REGEX) ? SymbolType.VOWEL : SymbolType.CONSONANT;
        symbolComponent.add(new Symbol(text.charAt(0), symbolType));

        return symbolComponent;
    }
}
