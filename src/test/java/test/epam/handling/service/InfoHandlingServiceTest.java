package test.epam.handling.service;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComponentType;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.impl.ParagraphParser;
import by.epam.handling.parser.impl.TextParser;
import by.epam.handling.service.InfoHandlingService;
import by.epam.handling.service.impl.InfoHandlingServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class InfoHandlingServiceTest {
    private InfoHandlingService service = new InfoHandlingServiceImpl();
    private TextComponent textComponent;

    @BeforeClass
    public void init(){
        TextParser parser = new TextParser();
        textComponent = parser.parse("    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "    It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "    Bye.");
    }


    @Test
    public void testParagraphSort() {
        ParagraphParser paragraphParser = new ParagraphParser();
        TextComponent actual = service.paragraphSort(textComponent);

        TextComposite expected = new TextComposite(TextComponentType.TEXT);
        expected.add(paragraphParser.parse("It is a 1202 established fact that a reader will be of a page when looking at its layout."));
        expected.add(paragraphParser.parse("Bye."));
        expected.add(paragraphParser.parse("It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
        expected.add(paragraphParser.parse("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English." ));

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindSentencesWithLongestWord() {
    }

    @Test
    public void testDeleteSentences() {
    }

    @Test
    public void testCountSameWords() {
    }

    @Test
    public void testCountVowel() {
    }

    @Test
    public void testCountConsonant() {
    }
}
