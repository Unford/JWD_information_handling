package by.epam.handling.entity;

public enum TextComponentType {
    TEXT("\t", "\n"),
    PARAGRAPH(""),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    NUMBER(""),
    SYMBOL("");

    private String prefix;
    private String postfix;

    TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }
    TextComponentType(String postfix) {
        this("", postfix);
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
