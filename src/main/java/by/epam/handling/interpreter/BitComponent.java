package by.epam.handling.interpreter;

public enum BitComponent {
    NOT("~", 6),
    AND("&", 4),
    OR("|", 2),
    XOR("^", 3),
    SHIFT_LEFT("<<", 5),
    SHIFT_RIGHT(">>", 5),
    SHIFT_RIGHT_UNSIGNED(">>>", 5),
    OPEN_BRACKET("(", 1),
    CLOSE_BRACKET(")", -1),
    NUMBER("\\d+", 0);

    private String component;
    private int priority;

    BitComponent(String component, int priority){
        this.component = component;
        this.priority = priority;
    }

    public String getComponent() {
        return component;
    }

    public int getPriority(){
        return priority;
    }

    public static BitComponent parse(String component){
        switch (component){
            case "~": return NOT;
            case "&": return AND;
            case "|": return OR;
            case "^": return XOR;
            case "<<": return SHIFT_LEFT;
            case ">>": return SHIFT_RIGHT;
            case ">>>": return SHIFT_RIGHT_UNSIGNED;
            case "(": return OPEN_BRACKET;
            case ")": return CLOSE_BRACKET;
            default: return NUMBER;
        }
    }
}
