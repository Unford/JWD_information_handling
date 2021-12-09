package by.epam.handling.service.impl;

import by.epam.handling.entity.TextComponent;
import by.epam.handling.entity.TextComponentType;
import by.epam.handling.entity.TextComposite;
import by.epam.handling.service.InfoHandlingService;

import java.util.Comparator;
import java.util.List;

import static by.epam.handling.entity.TextComponentType.*;

public class InfoHandlingServiceImpl implements InfoHandlingService {//todo implement methods
    @Override
    public TextComponent paragraphSort(TextComponent text) {//fixme
        TextComposite sortedText = null;
        if (text.getType() == TEXT){
            List<TextComponent> components = text.getComponents()
                    .stream()
                    .filter(component -> component.getType() == SENTENCE)
                    .sorted(Comparator.comparingInt(TextComponent::getSize))
                    .toList();

            sortedText = new TextComposite(TEXT , components);
        }
        return sortedText;
    }

    @Override
    public TextComponent findSentencesWithLongestWord(TextComponent text) {//fixme
        TextComposite longestSentence = null;
        if (text.getType() == PARAGRAPH){
            List<TextComponent> sentences = text.getComponents();
            TextComponent longestWord = findLongestWord(text);
            longestSentence = new TextComposite(PARAGRAPH);
            longestSentence.addAll(sentences.stream()
                    .filter(component -> component.getComponents()
                                                  .stream()
                                                  .filter(word -> word.getType() == WORD)
                            .anyMatch(word -> word.getSize() == longestWord.getSize())).toList());
        }
        return longestSentence;
    }

    private TextComponent findLongestWord(TextComponent sentences){//fixme
        TextComposite longestWord = new TextComposite(TextComponentType.WORD);
        TextComponent component = sentences.getComponents().stream()
                .max(Comparator.comparingInt(sentence -> sentence.getComponents()
                                         .stream()
                                         .max(Comparator.comparingInt(TextComponent::getSize))
                                         .get()
                                         .getSize()))
                .get();
        longestWord.add(component);
        return longestWord;
    }

    @Override
    public TextComponent deleteSentences(TextComponent text, int sizeBound) {
        return null;
    }

    @Override
    public int countSameWords(TextComponent text, String word) {
        return 0;
    }

    @Override
    public int countVowel(TextComponent text) {
        return 0;
    }

    @Override
    public int countConsonant(TextComponent text) {
        return 0;
    }

}
