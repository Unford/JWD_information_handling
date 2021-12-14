package test.epam.handling.parser;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.impl.WordParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WordParserTest {
    private WordParser parser = WordParser.getInstance();

    @Test(dataProvider = "dataForParse")
    public void testParse(String text, String expected) {
        TextComponent actual = parser.parse(text);
        assertEquals(actual.toString(), expected);
        assertEquals(actual.getComponents().size(), expected.length());

    }

    @DataProvider(name = "dataForParse")
    public Object[][] dataForParse(){
        return new Object[][]{
                {"five", "five"},
                {"centuries", "centuries"}
        };
    }
}