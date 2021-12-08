package by.epam.handling.parser.impl;

import by.epam.handling.entity.*;
import by.epam.handling.parser.AbstractTextParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LexemeParser extends AbstractTextParser {//todo fourth parser
    static Logger logger = LogManager.getLogger();

    private static final String WORD_REGEX = "\\p{L}+";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";
    private static final String LETTER_REGEX = "\\p{L}";
    private static final String DIGIT_REGEX = "\\d";

    @Override
    public TextComponent parse(String text) {
        TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);//todo need refactoring

            TextComponent textComponent = null;
            if (text.length() == 1){
                if (text.matches(LETTER_REGEX)) {
                    logger.log(Level.DEBUG, "Lexeme-Letter: {}", text);//todo

                    successor = LetterParser.getInstance();
                    textComponent = successor.parse(text);
                    lexemeComponent.add(textComponent);

                }else if (text.matches(PUNCTUATION_REGEX)){
                    logger.log(Level.DEBUG, "Lexeme-Punctuation: {}", text);

                    textComponent = new Symbol(text.charAt(0), SymbolType.PUNCTUATION);
                    lexemeComponent.add(textComponent);

                }
            }else {
                if (text.matches(WORD_REGEX)){
                    logger.log(Level.DEBUG, "Lexeme-Word: {}", text);

                    successor = WordParser.getInstance();
                    textComponent = successor.parse(text);
                    lexemeComponent.add(textComponent);

                }else {
                    logger.log(Level.DEBUG, "Lexeme-Word-Punctuation: {}", text);//todo extract to symbol parser

                    for (String letter : text.split(LETTER_DELIMITER_REGEX)) {
                        if (letter.matches(LETTER_REGEX)){
                            successor = LetterParser.getInstance();
                            lexemeComponent.add(successor.parse(letter));
                        }else {
                            if (letter.matches(DIGIT_REGEX)){
                                lexemeComponent.add(new Symbol(letter.charAt(0), SymbolType.DIGIT));
                            }else {
                                lexemeComponent.add(new Symbol(letter.charAt(0), SymbolType.PUNCTUATION));
                            }
                        }
                    }

                }
            }

        return lexemeComponent;
    }
}
