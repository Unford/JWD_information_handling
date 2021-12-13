package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;

import static by.epam.handling.entity.TextComponentType.WORD;


public class WordParser extends AbstractTextParser {

    private static WordParser instance;

    private WordParser(){
        nextParser = SymbolParser.getInstance();
    }

    public static WordParser getInstance(){
        if (instance == null){
            instance = new WordParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent wordComponent = new TextComposite(WORD);

        for (String letter : text.split(LETTER_DELIMITER_REGEX)) {
            TextComponent letterComponent = nextParser.parse(letter);
            wordComponent.add(letterComponent);
        }
        return wordComponent;
    }
}
