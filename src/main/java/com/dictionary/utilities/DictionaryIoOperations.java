package com.dictionary.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DictionaryIoOperations class contains the logic required for writing and
 * reading into dict.txt file which acts as a dictionary
 *
 */
public class DictionaryIoOperations {
	private static final Logger logger = LoggerFactory.getLogger(DictionaryIoOperations.class);
	public static String path = null;

	/**
	 * This static block contains the logic for creating a file "dict.txt" if it
	 * does not exist and if exists then the canonical path would be retrieved.
	 * Canonical path is used to retrieve the file which would be used to add
	 * words OR delete words OR retrieve words from dictionary
	 * 
	 */
	static {
		File file = null;
		File dir = new File("dictionary" + File.separator + "Words");
		file = new File(dir, "dict.txt");
		try {
			System.out.println("Dictionary Folder exists:" + dir.exists());
			if (!dir.exists()) {
				dir.mkdirs();
				file.createNewFile();
			}
			path = file.getCanonicalPath();
			System.out.println(("Canonical Path:" + path));
			logger.info("Canonical Path:" + path);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * addWordsToDict method is used to add words to the dictionary.
	 * 
	 * @param word:
	 *            Word that needs to be added to the dictionary
	 * 
	 *            TODO: Here the logic needs to be rectified to avoid adding
	 *            duplicate words.
	 */
	public static void addWordsToDict(String word) {
		try {
			FileWriter fileWriter = new FileWriter(path, true);
			fileWriter.write("\n");
			fileWriter.write(word + "\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Given word: " + word + " added successfully to the dictionary");
	}

	/**
	 * deleteWordFromDict method is used to delete the word form dictionary if
	 * exists, else it would return index -1 which would help in determining
	 * that the word doesn't really exists.
	 * 
	 * @param wordToBeDeleted
	 *            : word that has to be removed from the dictionary.
	 * @return index would be returned. -1 would indicate word doesn't exist
	 */
	public static int deleteWordFromDict(String wordToBeDeleted) {
		int index = -1;
		if (wordToBeDeleted != null && !wordToBeDeleted.isEmpty()) {
			File fileToBeModified = new File(path);
			String emptyString = "";
			String oldContent = "";
			String newContent = "";
			BufferedReader reader = null;
			FileWriter writer = null;

			try {
				reader = new BufferedReader(new FileReader(fileToBeModified));
				String line = reader.readLine();
				while (line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					line = reader.readLine();
				}
				index = oldContent.indexOf(wordToBeDeleted);
				if (oldContent.indexOf(wordToBeDeleted) >= 0) {
					newContent = oldContent.replaceAll(wordToBeDeleted, emptyString).trim();
					writer = new FileWriter(fileToBeModified);
					writer.write(newContent);
					writer.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return index;
	}

	/**
	 * listOfWordsInDict method returns list of words after reading from
	 * dictionary
	 * 
	 * @return List of all the words present in the dictionary.
	 */
	public static ArrayList<String> listOfWordsInDict() {
		FileInputStream fis = null;
		BufferedReader reader = null;
		ArrayList<String> listOfWords = new ArrayList<String>();

		try {
			fis = new FileInputStream(path);
			reader = new BufferedReader(new InputStreamReader(fis));
			System.out.println("Reading File line by line using BufferedReader");
			String line = reader.readLine();
			if (line != null && !line.isEmpty()) {
				listOfWords.add(line);
			}

			while (line != null) {
				line = reader.readLine();
				if (line != null && !line.isEmpty()) {
					listOfWords.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				reader.close();
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return listOfWords;
	}

}
