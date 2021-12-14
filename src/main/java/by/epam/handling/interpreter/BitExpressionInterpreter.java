package by.epam.handling.interpreter;


import java.util.ArrayList;
import java.util.List;

public class BitExpressionInterpreter {
    public List<BitExpression> interpret(List<String> tokens){
        List<BitExpression> expression = new ArrayList<>();
        tokens.forEach(token -> {
            switch (BitComponent.parse(token)){
                case NOT -> expression.add(c -> c.push(~c.pop()));
                case AND -> expression.add(c -> c.push(c.pop() & c.pop()));
                case OR -> expression.add(c -> c.push(c.pop() | c.pop()));
                case XOR -> expression.add(c -> c.push(c.pop() ^ c.pop()));
                case SHIFT_LEFT -> expression.add(c -> {
                    Integer right = c.pop();
                    Integer left = c.pop();
                    c.push(left << right);
                });
                case SHIFT_RIGHT -> expression.add(c -> {
                    Integer right = c.pop();
                    Integer left = c.pop();
                    c.push(left >> right);
                });
                case SHIFT_RIGHT_UNSIGNED -> expression.add(c -> {
                    Integer right = c.pop();
                    Integer left = c.pop();
                    c.push(left >>> right);
                });
                default -> expression.add(c -> c.push(Integer.parseInt(token)));
            }
        });
        return expression;
    }
}
