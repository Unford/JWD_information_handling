package by.epam.handling.entity;

import java.util.List;

public class Symbol implements TextComponent{
    private char symbol;
    private SymbolType type;

    public Symbol(char symbol, SymbolType type){
        this.symbol = symbol;
        this.type = type;
    }

    public SymbolType getSymbolType() {
        return type;
    }

    @Override
    public boolean add(TextComponent component) {
        throw new UnsupportedOperationException("Method add is unsupported for class " + getClass());
    }

    @Override
    public boolean remove(TextComponent component) {
        throw new UnsupportedOperationException("Method remove is unsupported for class " + getClass());
    }

    @Override
    public TextComponentType getType() {
        return TextComponentType.SYMBOL;
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("Method getComponents is unsupported for class " + getClass());
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Method getSize is unsupported for class " + getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol1 = (Symbol) o;

        if (symbol != symbol1.symbol) return false;
        return type == symbol1.type;
    }

    @Override
    public int hashCode() {
        int result = symbol;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
