package com.dictionary.model;

import java.util.ArrayList;

/**
 * DictionaryBean class would create a bean of the responseStatus and list of
 * words that would be returned to the user
 * 
 */
public class DictionaryBean {
	private ResponseBean response;
	private ArrayList<String> words;

	public ResponseBean getResponse() {
		return response;
	}

	public void setResponse(ResponseBean response) {
		this.response = response;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

}
