package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;


import static by.epam.handling.entity.TextComponentType.TEXT;

public class TextParser extends AbstractTextParser {
    private static TextParser instance;

    private TextParser(){
        nextParser = ParagraphParser.getInstance();
    }

    public static TextParser getInstance(){
        if (instance == null){
            instance = new TextParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent textComponent = new TextComposite(TEXT);

        for (String paragraph : text.split(PARAGRAPH_DELIMITER_REGEX)) {
            logger.log(Level.DEBUG, "Paragraph: {}", paragraph);
            TextComponent paragraphComponent = nextParser.parse(paragraph.trim());
            textComponent.add(paragraphComponent);
        }
        logger.log(Level.INFO, "Parsing text is successful: {}", textComponent);
        return textComponent;
    }
}
