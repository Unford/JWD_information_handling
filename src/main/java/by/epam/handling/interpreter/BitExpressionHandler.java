package by.epam.handling.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static by.epam.handling.interpreter.BitComponent.OPEN_BRACKET;

public class BitExpressionHandler {
    private Context context = new Context();
    private BitExpressionInterpreter interpreter = new BitExpressionInterpreter();

    private List<String> parseToPolishNotation(List<String> tokens){
        List<String> polishNotation = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        tokens.forEach(token -> {
            BitComponent current = BitComponent.parse(token);
            switch (current){
                case NUMBER -> polishNotation.add(token);
                case OPEN_BRACKET -> stack.push(token);
                case CLOSE_BRACKET -> {
                    while (!BitComponent.parse(stack.peek()).equals(OPEN_BRACKET)){
                        polishNotation.add(stack.pop());
                    }
                    stack.pop();
                }
                default -> {
                    while (!stack.isEmpty()){
                        BitComponent component = BitComponent.parse(stack.peek());
                        if (component.getPriority() < current.getPriority()){
                            break;
                        }
                        polishNotation.add(stack.pop());
                    }
                    stack.push(token);
                }
            }
        });

        while (!stack.isEmpty()){
            polishNotation.add(stack.pop());
        }
        return polishNotation;
    }

    public int handleExpression(List<String> tokens){
        List<String> polishNotation = parseToPolishNotation(tokens);
        interpreter.interpret(polishNotation)
                .forEach(terminal -> terminal.interpret(context));
        return context.pop();
    }
}
