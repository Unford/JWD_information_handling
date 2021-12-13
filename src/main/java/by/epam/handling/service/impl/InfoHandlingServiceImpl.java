package by.epam.handling.service.impl;

import by.epam.handling.entity.*;
import by.epam.handling.service.InfoHandlingService;

import java.util.*;
import java.util.stream.Collectors;

import static by.epam.handling.entity.TextComponentType.*;
import static by.epam.handling.entity.SymbolType.*;

public class InfoHandlingServiceImpl implements InfoHandlingService {

    @Override
    public TextComponent paragraphSort(TextComponent text) {
        TextComposite sortedText = new TextComposite(TEXT);
        List<TextComponent> paragraphs = findAllComponents(text, PARAGRAPH);

        List<TextComponent> sortedParagraphs = paragraphs.stream()
                .sorted(Comparator.comparing(paragraph -> paragraph.getComponents().size()))
                .toList();

        sortedText.addAll(sortedParagraphs);

        return sortedText;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) {
        List<TextComponent> sentences = findAllComponents(text, SENTENCE);

        int maxLength = findLongestWordLength(text);

        List<TextComponent> result = sentences.stream()
                .filter(sentence -> sentence.getComponents()
                        .stream()
                        .flatMap(lexeme -> lexeme.getComponents().stream())
                        .anyMatch(word -> word.getSize() == maxLength))
                .toList();

        return result;
    }

    private List<TextComponent> findAllComponents(TextComponent component, TextComponentType type){
        List<TextComponent> result = new ArrayList<>();
        Queue<TextComponent> queue = new ArrayDeque<>();
        queue.add(component);

        if (component.getType() == type){
            result.add(component);
        }

        while (!queue.isEmpty()) {
            TextComponent current = queue.poll();
            if (current.getType() != SYMBOL){

                current.getComponents().forEach(child -> {
                    if (child.getType() == type) {
                        result.add(child);
                    }
                    queue.add(child);
                });
            }
        }
        return result;
    }

    private int findLongestWordLength(TextComponent text){
        int max = findAllComponents(text, WORD)
                .stream()
                .mapToInt(TextComponent::getSize)
                .max().orElse(0);
        return max;
    }

    @Override
    public List<TextComponent> deleteSentences(TextComponent text, int sizeBound) {
        List<TextComponent> sentences = findAllComponents(text, SENTENCE);

        sentences = sentences.stream().filter(sentence -> sentence.getComponents().stream()
                .flatMap(lexeme -> lexeme.getComponents().stream()
                        .filter(word -> word.getType() == WORD))
                .count() > sizeBound).toList();

        return sentences;
    }

    @Override
    public Map<String, Long> countSameWords(TextComponent text) {
        List<TextComponent> words = findAllComponents(text, WORD);
        Map<String, Long> sameWords = words.stream()
                .collect(Collectors.groupingBy(word -> word.toString().toLowerCase(), Collectors.counting()));
        sameWords.entrySet().removeIf(count -> count.getValue() == 1);
        return sameWords;
    }

    @Override
    public long countVowel(TextComponent text) {
        return countSymbolType(text, VOWEL);
    }

    @Override
    public long countConsonant(TextComponent text) {
        return countSymbolType(text, CONSONANT);
    }

    private long countSymbolType(TextComponent text, SymbolType type){
        List<TextComponent> symbols = findAllComponents(text, SYMBOL);
        long amount = symbols.stream()
                .flatMap(sym -> sym.getComponents().stream()
                        .filter(symbol -> ((Symbol) symbol).getSymbolType() == type))
                .count();
        return amount;
    }
}
