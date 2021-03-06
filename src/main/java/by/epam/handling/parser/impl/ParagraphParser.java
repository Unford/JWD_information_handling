package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.handling.entity.TextComponentType.PARAGRAPH;


public class ParagraphParser extends AbstractTextParser {
    private static ParagraphParser instance;

    private ParagraphParser(){
        nextParser = SentenceParser.getInstance();
    }

    public static ParagraphParser getInstance(){
        if (instance == null){
            instance = new ParagraphParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent paragraphComponent = new TextComposite(PARAGRAPH);

        Pattern pattern = Pattern.compile(SENTENCE_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String sentence = matcher.group();
            logger.log(Level.DEBUG, "Sentence: {}", sentence);
            TextComponent sentenceComponent = nextParser.parse(sentence);
            paragraphComponent.add(sentenceComponent);
        }

        return paragraphComponent;
    }
}
