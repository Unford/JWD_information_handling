package by.epam.handling.parser;

import by.epam.handling.entity.TextComponent;

public abstract class AbstractTextParser {

    protected static final String LETTER_DELIMITER_REGEX = "";
    protected static final String PARAGRAPH_DELIMITER_REGEX = "\\n";
    protected static final String SENTENCE_DELIMITER_REGEX = ".+?[.!?…]+(\\s+|$)";
    protected static final String LEXEME_DELIMITER_REGEX = "\\s+";

    protected static final String PUNCTUATION_REGEX = "\\p{Punct}";
    protected static final String LETTER_REGEX = "\\p{L}";
    protected static final String DIGIT_REGEX = "\\d";
    protected static final String VOWEL_REGEX = "[aeiouAEIOUауоыиэяюёеАУОЫИЭЯЮЁЕ]";

    protected static final String WORD_REGEX = "\\p{L}+";
    protected static final String EXPRESSION_REGEX = "[^\\p{L}]+";
    protected static final String BIT_EXPRESSION_TOKEN_REGEX = "\\d+|[~&^|()]|>{2,3}|<<";

    protected static final String PUNCTUATION_WORD_REGEX = PUNCTUATION_REGEX + "|" + WORD_REGEX;
    protected static final String SYMBOL_OR_WORD_REGEX = "[" + PUNCTUATION_REGEX + LETTER_REGEX + "]+";

    protected AbstractTextParser nextParser;

    protected AbstractTextParser(){}

    protected AbstractTextParser(AbstractTextParser nextParser){
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);

}
