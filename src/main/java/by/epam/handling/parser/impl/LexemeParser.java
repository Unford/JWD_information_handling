package by.epam.handling.parser.impl;

import by.epam.handling.entity.*;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.handling.entity.TextComponentType.LEXEME;

public class LexemeParser extends AbstractTextParser {
    private static LexemeParser instance;

    private LexemeParser(){}

    public static LexemeParser getInstance(){
        if (instance == null){
            instance = new LexemeParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent lexemeComponent = new TextComposite(LEXEME);

        TextComponent textComponent;
                if (text.matches(PUNCTUATION_WORD_REGEX)){
                    logger.log(Level.DEBUG, "Lexeme-Word-Symbol: {}", text);
                    Pattern pattern = Pattern.compile(SYMBOL_OR_WORD_REGEX);
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()){
                        String textPart = matcher.group();
                        nextParser = textPart.length() == 1 ? SymbolParser.getInstance() : WordParser.getInstance();
                        textComponent = nextParser.parse(textPart);
                        lexemeComponent.add(textComponent);
                    }
                }else if (text.matches(EXPRESSION_REGEX)){
                    logger.log(Level.DEBUG, "Lexeme-Expression: {}", text);
                    Pattern pattern = Pattern.compile(EXPRESSION_OR_SYMBOL_REGEX);
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()){
                        String textPart = matcher.group();
                        nextParser = textPart.length() == 1 ? SymbolParser.getInstance() : ExpressionParser.getInstance();
                        textComponent = nextParser.parse(textPart);
                        lexemeComponent.add(textComponent);
                    }
                }

        return lexemeComponent;
    }
}
