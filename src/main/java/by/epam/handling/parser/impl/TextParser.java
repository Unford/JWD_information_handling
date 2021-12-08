package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComponentType;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends AbstractTextParser {//todo first parser
    static Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_DELIMITER_REGEX = "\\n";

    public TextParser(){
        successor = new ParagraphParser();
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent textComponent = new TextComposite(TextComponentType.TEXT);

        for (String paragraph : text.split(PARAGRAPH_DELIMITER_REGEX)) {
            logger.log(Level.DEBUG, "Paragraph: {}", paragraph);
            TextComponent paragraphComponent = successor.parse(paragraph.trim());
            textComponent.add(paragraphComponent);
        }
        return textComponent;
    }
}
