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
                case SHIFT_LEFT -> expression.add(this::interpretLeftShift);
                case SHIFT_RIGHT -> expression.add(this::interpretRightShift);
                case SHIFT_RIGHT_UNSIGNED -> expression.add(this::interpretRightShiftUnsigned);
                default -> expression.add(c -> c.push(Integer.parseInt(token)));
            }
        });
        return expression;
    }

    private void interpretLeftShift(Context context){
        Integer right = context.pop();
        Integer left = context.pop();
        context.push(left << right);
    }
    private void interpretRightShift(Context context){
        Integer right = context.pop();
        Integer left = context.pop();
        context.push(left >> right);
    }
    private void interpretRightShiftUnsigned(Context context){
        Integer right = context.pop();
        Integer left = context.pop();
        context.push(left >>> right);

    }


}
