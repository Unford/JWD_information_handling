package test.epam.handling.service;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComponentType;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.parser.impl.ParagraphParser;
import by.epam.handling.parser.impl.SentenceParser;
import by.epam.handling.parser.impl.TextParser;
import by.epam.handling.service.InfoHandlingService;
import by.epam.handling.service.impl.InfoHandlingServiceImpl;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InfoHandlingServiceTest {
    private InfoHandlingService service = new InfoHandlingServiceImpl();
    private TextComponent textComponent;
    private String text = "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1).\n" +
            "    It is a long established fact that a reader will be distracted by the distribution readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78.\n" +
            "    It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.";

    @BeforeClass
    public void init(){
        TextParser parser = TextParser.getInstance();
        textComponent = parser.parse(text);
    }


    @Test
    public void testParagraphSort() {
        ParagraphParser paragraphParser = ParagraphParser.getInstance();
        TextComponent actual = service.paragraphSort(textComponent);

        TextComposite expected = new TextComposite(TextComponentType.TEXT);
        expected.add(paragraphParser.parse("It is a 1202 established fact that a reader will be of a page when looking at its layout."));
        expected.add(paragraphParser.parse("It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)."));
        expected.add(paragraphParser.parse("It is a long established fact that a reader will be distracted by the distribution readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78." ));

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindSentencesWithLongestWord() {
        List<TextComponent> actual = service.findSentencesWithLongestWord(textComponent);
        SentenceParser parser = SentenceParser.getInstance();
        List<TextComponent> expected = List.of(parser.parse("It is a long established fact that a reader will be distracted by the distribution readable content of a page when looking at its layout."));
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test(dataProvider = "dataForDeleteSentencesLess")
    public void testDeleteSentencesLess(TextComponent component, int bound, List<TextComponent> expected) {
        List<TextComponent> actual = service.deleteSentencesLess(component, bound);
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @DataProvider(name = "dataForDeleteSentencesLess")
    public Object[][] dataForDeleteSentencesLess(){
        return new Object[][]{
                {textComponent, 20, List.of(SentenceParser.getInstance()
                        .parse("It is a long established fact that a reader will be distracted by the distribution readable content of a page when looking at its layout."))},
                {textComponent, 50, List.of()}

        };
    }

    @Test(dataProvider = "dataForCountSameWords")
    public void testCountSameWords(TextComponent component, Map<String, Long> expected) {
        Map<String, Long> actual = service.countSameWords(component);
        assertThat(actual).containsExactlyEntriesOf(expected);
    }
    @DataProvider(name = "dataForCountSameWords")
    public Object[][] dataForCountSameWords(){
        return new Object[][]{
                {SentenceParser.getInstance().parse("same alOne sAme more same More"), Map.of("more", 2L, "same", 3L)},
                {SentenceParser.getInstance().parse("qwerty asdfgh"), Map.of()}
        };
    }

    @Test(dataProvider = "dataForCountVowel")
    public void testCountVowel(TextComponent component, long expected) {
        long actual = service.countVowel(component);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForCountVowel")
    public Object[][] dataForCountVowel(){
        return new Object[][]{
                {textComponent, 126},
                {SentenceParser.getInstance().parse("qwerty asdfgh"), 2}
        };
    }

    @Test(dataProvider = "dataForCountConsonant")
    public void testCountConsonant(TextComponent component, long expected) {
        long actual = service.countConsonant(component);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForCountConsonant")
    public Object[][] dataForCountConsonant(){
        return new Object[][]{
                {textComponent, 189},
                {SentenceParser.getInstance().parse("qwerty asdfgh"), 10}
        };
    }
}
