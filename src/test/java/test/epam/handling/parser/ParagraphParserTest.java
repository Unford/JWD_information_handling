package test.epam.handling.parser;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.impl.ParagraphParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ParagraphParserTest {
    private ParagraphParser parser = ParagraphParser.getInstance();
    private String textExample = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1).";

    @Test(dataProvider = "dataForParse")
    public void testParse(String text, int expected) {
        TextComponent actual = parser.parse(text);
        assertEquals(actual.getComponents().size(), expected);
    }

    @DataProvider(name = "dataForParse")
    public Object[][] dataForParse(){
        return new Object[][]{
                {textExample, 2},
                {"It has survived - not only (five) centuries, but also the leap into 13<<2.", 1}
        };
    }
}