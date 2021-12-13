package by.epam.handling.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Integer> contextValue = new ArrayDeque<>();

    public void push(Integer number) {
        contextValue.push(number);
    }

    public Integer pop() {
        return contextValue.pop();
    }
    public Integer peek() {
        return contextValue.peek();
    }
}
