package by.epam.handling.service;

import by.epam.handling.entity.TextComponent;

public interface InfoHandlingService {
   TextComponent paragraphSort(TextComponent text);
   TextComponent findSentencesWithLongestWord(TextComponent text);
   TextComponent deleteSentences(TextComponent text , int sizeBound);
   int countSameWords(TextComponent text , String word);
   int countVowel(TextComponent text);
   int countConsonant(TextComponent text);
}
