package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;

import static by.epam.handling.entity.TextComponentType.SENTENCE;


public class SentenceParser extends AbstractTextParser {
    private static SentenceParser instance;

    private SentenceParser(){
        nextParser = LexemeParser.getInstance();
    }

    public static SentenceParser getInstance(){
        if (instance == null){
            instance = new SentenceParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent sentenceComponent = new TextComposite(SENTENCE);

        for (String lexeme : text.split(LEXEME_DELIMITER_REGEX)) {
            logger.log(Level.DEBUG, "lexeme: {}", lexeme);
            TextComponent lexemeComponent = nextParser.parse(lexeme.trim());
            sentenceComponent.add(lexemeComponent);
        }

        return sentenceComponent;
    }
}
