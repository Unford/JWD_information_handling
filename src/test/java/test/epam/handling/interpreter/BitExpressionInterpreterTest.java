package test.epam.handling.interpreter;

import by.epam.handling.parser.impl.ExpressionParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BitExpressionInterpreterTest {
    ExpressionParser expressionParser = ExpressionParser.getInstance();

    @Test(description = "interpret bit expression",
            dataProvider = "dataForBitInterpreter")

    public void testBitInterpreter(String expression, int expected){
        int actual = Integer.parseInt(expressionParser.parse(expression).toString());
        Assert.assertEquals(actual, expected);

    }

    @DataProvider(name = "dataForBitInterpreter")
    public Object[][] dataForBitInterpreter(){
        return new Object[][]{
                {"(7^5|~1&2<<(2|5>>2&71))|1200", (7^5|~1&2<<(2|5>>2&71))|1200},
                {"5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)", 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)},
                {"4&(1^5|6&47)|3|(~89&4|42&7)", 4&(1^5|6&47)|3|(~89&4|42&7)}
        };
    }

}
