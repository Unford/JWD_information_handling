package by.epam.handling.parser;

import by.epam.handling.entity.TextComponent;

public abstract class AbstractTextParser {
    protected static final String LETTER_DELIMITER_REGEX = "";

    protected AbstractTextParser successor = DefaultTextParser.getInstance();

    protected AbstractTextParser(){}

    protected AbstractTextParser(AbstractTextParser successor){
        this.successor = successor;
    }

    public abstract TextComponent parse(String text);

    private static class DefaultTextParser extends AbstractTextParser {
        private static DefaultTextParser instance = new DefaultTextParser();

        private DefaultTextParser(){}

        public static DefaultTextParser getInstance() {
            return instance;
        }

        @Override
        public TextComponent parse(String text) {
            return null;
        }
    }
}
