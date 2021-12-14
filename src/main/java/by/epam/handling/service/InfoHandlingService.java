package by.epam.handling.service;

import by.epam.handling.entity.TextComponent;

import java.util.List;
import java.util.Map;

public interface InfoHandlingService {
   TextComponent paragraphSort(TextComponent text);
   List<TextComponent> findSentencesWithLongestWord(TextComponent text);
   List<TextComponent> deleteSentencesLess(TextComponent text , int sizeBound);
   Map<String, Long> countSameWords(TextComponent text);
   long countVowel(TextComponent text);
   long countConsonant(TextComponent text);
}
