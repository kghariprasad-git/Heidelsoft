package com.test.sample;

/**
 * 
 *  program for reading the file and print the number of different words
 *   in it with its occurrence in ascending order of words
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
 
public class DuplicateWordCounter {
	private static final String CONFIG_FILE = "2.txt";
	URL fileUrl = getClass().getResource(CONFIG_FILE);

	public File readFileFromClasspath() {
		URL fileUrl = getClass().getResource(CONFIG_FILE);
		if (fileUrl != null) {
			return new File(fileUrl.getFile());
		} else {
			System.out.println("Expected File was not found .Please Create file in the same class path ");
			return null;
		}
	}

	public void processFile() {
		File file = readFileFromClasspath();
		FileInputStream fileStream;
		try {
			if (file != null) {
				fileStream = new FileInputStream(file);
				InputStreamReader input = new InputStreamReader(fileStream);
				BufferedReader reader = new BufferedReader(input);
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.replaceAll("^\"|\"$", "");
					List<String> list = Arrays.asList(line.split("\\s+"));

					SortedSet<String> uniqueWords = new TreeSet<String>(list);
					for (String word : uniqueWords) {
						System.out.println(word + ": " + Collections.frequency(list, word));
					}

				}
			}
		} catch (IOException e) {
			System.out.println("Exception occured.Please find the details " + e.getMessage());
		}
	}

	public static void main(String[] args) {

		DuplicateWordCounter test = new DuplicateWordCounter();
		test.processFile();

	}

}
