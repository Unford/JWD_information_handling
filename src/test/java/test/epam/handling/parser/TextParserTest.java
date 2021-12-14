package test.epam.handling.parser;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.impl.TextParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextParserTest {
    private TextParser parser = TextParser.getInstance();
    private String textExample = "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1).\n" +
            "    It is a long established fact that a reader will be distracted by the distribution readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78.\n";

    @Test(dataProvider = "dataForParse")
    public void testParse(String text, int expected) {
       TextComponent actual = parser.parse(text);
       assertEquals(actual.getComponents().size(), expected);
    }

    @DataProvider(name = "dataForParse")
    public Object[][] dataForParse(){
        return new Object[][]{
                {textExample, 2}
        };
    }
}