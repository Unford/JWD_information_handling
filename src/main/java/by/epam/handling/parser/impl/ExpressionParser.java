package by.epam.handling.parser.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.interpreter.BitExpressionHandler;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;

import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.handling.entity.TextComponentType.NUMBER;

public class ExpressionParser extends AbstractTextParser {

    private static ExpressionParser instance;

    private ExpressionParser(){
        nextParser = SymbolParser.getInstance();
    }

    public static ExpressionParser getInstance(){
        if (instance == null){
            instance = new ExpressionParser();
        }
        return instance;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent numberComponent = new TextComposite(NUMBER);

        Pattern pattern = Pattern.compile(BIT_EXPRESSION_TOKEN_REGEX);
        Matcher matcher = pattern.matcher(text);
        List<String> expressionTokens = matcher.results().map(MatchResult::group).toList();

        BitExpressionHandler bitExpressionHandler = new BitExpressionHandler();
        String result = Integer.toString(bitExpressionHandler.handleExpression(expressionTokens));
        logger.log(Level.DEBUG, "Number: {}", result);

        Arrays.stream(result.split(LETTER_DELIMITER_REGEX))
                .forEach(digit -> numberComponent.add(nextParser.parse(digit)));
        return numberComponent;
    }
}
