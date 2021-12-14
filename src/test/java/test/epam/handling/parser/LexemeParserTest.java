package test.epam.handling.parser;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.impl.LexemeParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LexemeParserTest {
    private LexemeParser parser = LexemeParser.getInstance();

    @Test(dataProvider = "dataForParse")
    public void testParse(String text, String expected) {
        TextComponent actual = parser.parse(text);
        assertEquals(actual.toString(), expected);
    }

    @DataProvider(name = "dataForParse")
    public Object[][] dataForParse(){
        return new Object[][]{
                {"5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1).", "5."},
                {"popularised", "popularised"},
                {"(five)", "(five)"}

        };
    }
}