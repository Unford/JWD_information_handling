package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComponentType;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SentenceParser extends AbstractTextParser {//todo third parser
    static Logger logger = LogManager.getLogger();
    private static final String LEXEME_DELIMITER_REGEX = "\\s+";
    public SentenceParser(){
        successor = new LexemeParser();
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);

        for (String lexeme : text.split(LEXEME_DELIMITER_REGEX)) {
            logger.log(Level.DEBUG, "lexeme: {}", lexeme.trim());
            TextComponent lexemeComponent = successor.parse(lexeme.trim());
            sentenceComponent.add(lexemeComponent);
        }

        return sentenceComponent;
    }
}
