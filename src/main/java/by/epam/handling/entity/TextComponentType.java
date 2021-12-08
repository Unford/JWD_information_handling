package by.epam.handling.entity;

public enum TextComponentType {//todo
    TEXT("\t", "\n"),
    PARAGRAPH("", ""),
    SENTENCE("", " "),
    LEXEME("", ""),
    WORD("", ""),
    SYMBOL("", "");

    private String prefix;
    private String postfix;

    TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
