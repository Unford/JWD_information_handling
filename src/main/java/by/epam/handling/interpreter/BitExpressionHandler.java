package by.epam.handling.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BitExpressionHandler {
    private Context context = new Context();

    private List<String> parseToPolishNotation(List<String> tokens){
        List<String> polishNotation = new ArrayList<>();
        ArrayDeque<String> stack = new ArrayDeque<>();

        tokens.forEach(token -> {
            BitComponent current = BitComponent.parse(token);
            switch (current){
                case NUMBER -> polishNotation.add(token);
                case OPEN_BRACKET -> stack.push(token);
                case CLOSE_BRACKET -> {
                    while (!BitComponent.parse(stack.peek()).equals(BitComponent.OPEN_BRACKET)){
                        polishNotation.add(stack.pop());
                    }
                    stack.pop();
                }
                default -> {
                    while (!stack.isEmpty()){
                        BitComponent component = BitComponent.parse(stack.peek());
                        if (component.getPriority() >= current.getPriority()){
                            polishNotation.add(stack.pop());
                        }else {
                            break;
                        }
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
        BitExpressionInterpreter interpreter = new BitExpressionInterpreter();
        List<String> polishNotation = parseToPolishNotation(tokens);
        interpreter.interpret(polishNotation)
                .forEach(terminal -> terminal.interpret(context));
        return context.pop();
    }
}
