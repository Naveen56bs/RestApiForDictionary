package com.dictionary.businesslogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dictionary.utilities.DictionaryIoOperations;

/**
 * DictionarySimilarWords class contains the logic required for sorting and
 * ordering the words based on similarity.
 *
 */
public class DictionarySimilarWords {

	private static final Logger logger = LoggerFactory.getLogger(DictionarySimilarWords.class);

	/**
	 * @param searchWord:
	 *            word that needs to be searched and retrieved from the
	 *            dictionary
	 * @return ArrayList of similar words ordered according to the order of
	 *         similarity
	 */
	public static ArrayList<String> similarWords(String searchWord) {
		ArrayList<String> wordsList = DictionaryIoOperations.listOfWordsInDict();
		ArrayList<String> similarWordsList = new ArrayList<String>();

		int searchWordLength = searchWord.length();
		logger.info("Search WORD:" + searchWord);
		logger.info("Search word length:" + searchWordLength);
		char s1Array[] = searchWord.toLowerCase().toCharArray();
		Arrays.sort(s1Array);
		for (int i = 0; i < wordsList.size(); i++) {
			if (wordsList.get(i).length() == searchWordLength) {
				char s2Array[] = wordsList.get(i).toLowerCase().toCharArray();
				Arrays.sort(s2Array);
				if (Arrays.equals(s1Array, s2Array)) {
					similarWordsList.add(wordsList.get(i));
				}

			}
		}

		logger.info("similarWordsList:   " + similarWordsList);

		// Calling similarityWords method to arrange them based on similarity.
		ArrayList<String> finalList = similarityWords(searchWord, similarWordsList);

		logger.info("Similar words after rearranging according to similarity: " + finalList);

		return finalList;

	}

	/**
	 * @param inputWord:
	 *            Word that that is used to order the list of similar words.
	 * @param similarWords:
	 *            List of similar words which would be and ordered according to
	 *            the inputWord
	 * @return ArrayList of words after sorting them based on similarity
	 * 
	 */
	private static ArrayList<String> similarityWords(String inputWord, ArrayList<String> similarWords) {
		HashMap<String, Integer> similarWordsMap = new HashMap<String, Integer>();
		for (int i = 0; i < similarWords.size(); i++) {

			// Calling the minimumSwaps method to check for swapping of each
			// letter to order according
			// to the input word.
			int res = minimumSwaps(inputWord, similarWords.get(i));

			logger.info("Res value is :" + res + " word is: " + similarWords.get(i));

			similarWordsMap.put(similarWords.get(i), res);

		}

		Set<Entry<String, Integer>> set = similarWordsMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);

		// Sorting the words in ascending order which would order the words
		// according to minimum swaps
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		ArrayList<String> values = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : list) {
			logger.info(entry.getKey() + " ==== " + entry.getValue());

			values.add(entry.getKey());
		}
		// returning the list of values based on similarity.
		return values;
	}

	/**
	 * @param inputWord
	 *            : Word which would be used to order the output word
	 * @param outputWord
	 *            : This is the word that would be checked to find out the
	 *            minimum number of swaps to order according to input word
	 * @return int value would be returned which shows the minimum swaps
	 */
	public static int minimumSwaps(String inputWord, String outputWord) {
		int res = 0;
		int inputWordLen = inputWord.length();
		char[] inputWordArray = inputWord.toCharArray();
		char[] outputWordArray = outputWord.toCharArray();

		for (int i = 0; i < inputWordLen; i++) {
			if (inputWordArray[i] != outputWordArray[i]) {
				int index = outputWord.indexOf(inputWordArray[i]);
				char oldChar = outputWordArray[i];
				char newChar = outputWordArray[index];
				outputWordArray[i] = newChar;
				outputWordArray[index] = oldChar;
				res++;
			}
		}
		return res;
	}

}
