package com.dictionary.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dictionary.businesslogic.DictionarySimilarWords;
import com.dictionary.constants.DictionaryUriConstants;
import com.dictionary.model.DictionaryBean;
import com.dictionary.model.ResponseBean;
import com.dictionary.model.WordBean;
import com.dictionary.utilities.DictionaryIoOperations;

/**
 * Handles requests for the Dictionary service.
 */
@Controller
public class DictionaryController {

	private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

	/**
	 * getwords requestHandler method is used retrieve the words if present in
	 * the dictionary and would return them, if not present then it would return
	 * empty list
	 * 
	 * @param searchCriteria:
	 *            word that needs to be searched from the dictionary
	 * 
	 * @return ResponseBody containing the status and the list of words if
	 *         present OR empty list
	 * 
	 */
	@RequestMapping(value = "/dictionary/{searchCriteria}", method = RequestMethod.GET)
	public @ResponseBody DictionaryBean getwords(@PathVariable("searchCriteria") String searchCriteria) {

		logger.info("Word to be searched is: " + searchCriteria);
		ResponseBean responseBean = null;
		ArrayList<String> listOfWords = DictionarySimilarWords.similarWords(searchCriteria);
		DictionaryBean dictionaryBean = new DictionaryBean();
		if (listOfWords.size() > 0) {
			responseBean = new ResponseBean(HttpStatus.OK, "Found similar words arranged according to similarity");
		} else {
			responseBean = new ResponseBean(HttpStatus.NOT_FOUND, "There are no similar words found!");
		}
		dictionaryBean.setWords(listOfWords);
		dictionaryBean.setResponse(responseBean);
		return dictionaryBean;
	}

	/**
	 * createWord requestHandler method would add the word specified in the url
	 * path to the dictionary
	 * 
	 * @param wordBean:
	 *            Word that needs to be added to the dictionary.
	 * 
	 * @return ResponseBody containing the status and the word added to
	 *         dictionary
	 * 
	 */
	@RequestMapping(value = DictionaryUriConstants.CREATE_WORD, method = RequestMethod.POST)
	public @ResponseBody DictionaryBean createWord(@RequestBody WordBean wordBean) {
		logger.info("Creating the word: " + wordBean.getWord());
		DictionaryIoOperations.addWordsToDict(wordBean.getWord());
		ArrayList<String> wordList = new ArrayList<String>();
		wordList.add(wordBean.getWord());
		ResponseBean responseBean = new ResponseBean(HttpStatus.CREATED, "Word added to the dictionary");
		DictionaryBean dictionaryBean = new DictionaryBean();
		dictionaryBean.setWords(wordList);
		dictionaryBean.setResponse(responseBean);

		return dictionaryBean;
	}

	/**
	 * deleteWord requestHandler method deletes the word from dictionary if
	 * present
	 * 
	 * @param wordBean
	 *            : Word that needs to be deleted from the dictionary.
	 * 
	 * @return ResponseBody containing the status and the word that needs to be
	 *         deleted from dictionary
	 * 
	 */
	@RequestMapping(value = DictionaryUriConstants.DELETE_WORD, method = RequestMethod.DELETE)
	public @ResponseBody DictionaryBean deleteWord(@RequestBody WordBean wordBean) {
		logger.info("Deleting the word: " + wordBean.getWord());
		ResponseBean responseBean = null;
		int index = DictionaryIoOperations.deleteWordFromDict(wordBean.getWord());

		ArrayList<String> wordList = new ArrayList<String>();
		wordList.add(wordBean.getWord());
		if (index >= 0) {
			responseBean = new ResponseBean(HttpStatus.OK, "Given word removed from dictionary successfully");
		} else {
			responseBean = new ResponseBean(HttpStatus.NOT_FOUND, "Word to be deleted not found in dictionary");
		}
		DictionaryBean dictionaryBean = new DictionaryBean();
		dictionaryBean.setWords(wordList);
		dictionaryBean.setResponse(responseBean);

		return dictionaryBean;

	}

}
