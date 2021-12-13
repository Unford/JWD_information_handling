package by.epam.handling.parser.impl;

import by.epam.handling.entity.*;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.handling.entity.TextComponentType.LEXEME;

public class LexemeParser extends AbstractTextParser {
    static Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        TextComponent lexemeComponent = new TextComposite(LEXEME);

        TextComponent textComponent;
                if (text.matches(SYMBOL_OR_WORD_REGEX)){
                    logger.log(Level.DEBUG, "Lexeme-Word-Symbol: {}", text);
                    Pattern pattern = Pattern.compile(PUNCTUATION_WORD_REGEX);
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()){
                        String textPart = matcher.group();
                        nextParser = textPart.length() == 1 ? SymbolParser.getInstance() : WordParser.getInstance();
                        textComponent = nextParser.parse(textPart);
                        lexemeComponent.add(textComponent);
                    }
                }else if (text.matches(EXPRESSION_REGEX)){
                    logger.log(Level.DEBUG, "Lexeme-Expression: {}", text);
                    nextParser = ExpressionParser.getInstance();
                    textComponent = nextParser.parse(text);
                    lexemeComponent.add(textComponent);
                }

        return lexemeComponent;
    }
}
