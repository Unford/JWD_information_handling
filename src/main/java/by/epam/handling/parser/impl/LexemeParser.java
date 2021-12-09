package by.epam.handling.parser.impl;

import by.epam.handling.entity.*;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.handling.entity.TextComponentType.LEXEME;

public class LexemeParser extends AbstractTextParser {//todo fourth parser
    static Logger logger = LogManager.getLogger();

    private static final String WORD_REGEX = "\\p{L}+";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";//todo double
    private static final String PUNCTUATION_WORD_REGEX = PUNCTUATION_REGEX + "|" + WORD_REGEX;
    private static final String EXPRESSION_REGEX = "[^\\p{L}]+";

    @Override
    public TextComponent parse(String text) {
        TextComponent lexemeComponent = new TextComposite(LEXEME);

        TextComponent textComponent;
            if (text.length() == 1){
                    logger.log(Level.DEBUG, "Lexeme-Letter: {}", text);//todo
                    nextParser = SymbolParser.getInstance();
                    textComponent = nextParser.parse(text);
                    lexemeComponent.add(textComponent);
            }else {
                if (text.matches(EXPRESSION_REGEX)){
                    logger.log(Level.DEBUG, "Lexeme-Expression: {}", text);
                    nextParser = new ExpressionParser();
                    textComponent = nextParser.parse(text);
                    lexemeComponent.add(textComponent);
                }else {
                    logger.log(Level.DEBUG, "Lexeme-Word-Punctuation: {}", text);
                    Pattern pattern = Pattern.compile(PUNCTUATION_WORD_REGEX);
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()){
                        String textPart = matcher.group();
                        nextParser = textPart.matches(PUNCTUATION_REGEX) ? SymbolParser.getInstance() : WordParser.getInstance();
                        textComponent = nextParser.parse(textPart);
                        lexemeComponent.add(textComponent);
                    }
                }
            }
        return lexemeComponent;
    }
}
