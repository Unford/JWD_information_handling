package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComponentType;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends AbstractTextParser {//todo fifth parser

    private static WordParser instance;

    private WordParser(){
        successor = LetterParser.getInstance();
    }

    public static WordParser getInstance(){
        if (instance == null){
            instance = new WordParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent wordComponent = new TextComposite(TextComponentType.WORD);

        for (String letter : text.split(LETTER_DELIMITER_REGEX)) {
            TextComponent letterComponent = successor.parse(letter);
            wordComponent.add(letterComponent);
        }
        return wordComponent;
    }
}
