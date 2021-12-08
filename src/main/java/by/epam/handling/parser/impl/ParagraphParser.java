package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComponentType;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractTextParser {//todo second parser
    static Logger logger = LogManager.getLogger();
    private static final String SENTENCE_DELIMITER_REGEX = ".+?[.!?…]+(\\s+|$)";

    public ParagraphParser(){
        successor = new SentenceParser();
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);

        Pattern pattern = Pattern.compile(SENTENCE_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String sentence = matcher.group();
            logger.log(Level.DEBUG, "Sentence: {}", sentence);
            TextComponent sentenceComponent = successor.parse(sentence);
            paragraphComponent.add(sentenceComponent);
        }

        return paragraphComponent;
    }
}
