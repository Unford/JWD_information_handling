package test.epam.handling.parser;

import by.epam.handling.parser.impl.ExpressionParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ExpressionParserTest {

    private ExpressionParser parser = ExpressionParser.getInstance();

    @Test(dataProvider = "dataForParse")
    public void testParse(String text, int expected) {
        int actual = Integer.parseInt(parser.parse(text).toString());
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForParse")
    public Object[][] dataForParse(){
        return new Object[][]{
                {"(7^5|~1&2<<(2|5>>2&71))|1200", (7^5|~1&2<<(2|5>>2&71))|1200},
                {"5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)", 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)},
                {"4&(1^5|6&47)|3|(~89&4|42&7)", 4&(1^5|6&47)|3|(~89&4|42&7)}
        };
    }
}