package scrabblepkg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scrabble {
	private static String maxString = "";
	private static int maxScore = 0;
	private static long MIN_VALUE = -2147483648;
	private ArrayList<String> wordList;
	private int size;

	public Scrabble(int size, String file) {
		this.size = size;
		this.wordList = getWordsFromFile(file);
	}

	public int getSize() {
		return this.size;
	}

	public List<String> getListOfValidWords() {
		return this.wordList;
	}

	private static int getCharacterScore(char ch) {
		String s = String.valueOf(ch);

		int score = 0;
		ArrayList<String> scoreList = new ArrayList<>();
		scoreList.add(0, "");
		scoreList.add(1, "AEILNORSTU");
		scoreList.add("DG");
		scoreList.add("BCMP");
		scoreList.add("FHVWY");
		scoreList.add("K");
		scoreList.add("");
		scoreList.add("");
		scoreList.add("JX");
		scoreList.add("");
		scoreList.add("QZ");
		for (String str : scoreList) {
			if (str.contains(s)) {
				score = scoreList.indexOf(str);
			}
		}
		return score;

	}

	private ArrayList<String> getWordsFromFile(String path) {

		ArrayList<String> words = new ArrayList<String>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(path);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				words.add(line);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}

		return words;
	}

	private boolean isValidWord(String str) {
		return getListOfValidWords().contains(str);
	}

	public String getMaxScoringWord(String str) {
		getMaxScoreAndString(str, "", 0, 0);
		return this.maxString;
	}

	private long getMaxScoreAndString(String str1, String str2, int countScore, int index) {
		if (str1.length() - index == 0 && isValidWord(str2)) {
			if (countScore > maxScore) {
				maxScore = countScore;
				maxString = str2;
				return 0;
			}
		} else if (str1.length() - index == 0 && !isValidWord(str2)) {
			return MIN_VALUE;
		}
		char currentChar = str1.charAt(index);
		int currentLetterScore = getCharacterScore(currentChar);
		return Math.max(
				currentLetterScore
						+ getMaxScoreAndString(str1, str2 + currentChar, countScore + currentLetterScore, index + 1),
				getMaxScoreAndString(str1, str2, countScore, index + 1));
	}

}