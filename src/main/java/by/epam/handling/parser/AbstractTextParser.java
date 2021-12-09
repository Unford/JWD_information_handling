package by.epam.handling.parser;

import by.epam.handling.entity.TextComponent;

public abstract class AbstractTextParser {

    protected AbstractTextParser nextParser = DefaultTextParser.getInstance();

    protected AbstractTextParser(){}

    protected AbstractTextParser(AbstractTextParser nextParser){
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);

    private static class DefaultTextParser extends AbstractTextParser {//todo
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
