package test.epam.handling.parser;

import by.epam.handling.entity.Symbol;
import by.epam.handling.entity.TextComponent;
import by.epam.handling.parser.impl.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static by.epam.handling.entity.SymbolType.*;

public class SymbolParserTest {
    private SymbolParser parser = SymbolParser.getInstance();

    @Test(dataProvider = "dataForParse")
    public void testParse(String text, Symbol expected) {
        TextComponent actual = parser.parse(text);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForParse")
    public Object[][] dataForParse(){
        return new Object[][]{
                {"a", new Symbol('a', VOWEL)},
                {"b", new Symbol('b', CONSONANT)},
                {"0", new Symbol('0', DIGIT)},
                {"±", new Symbol('±', UNDEFINED)},
                {",", new Symbol(',', PUNCTUATION)}
        };
    }
}