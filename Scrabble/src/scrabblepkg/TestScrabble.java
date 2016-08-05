package scrabblepkg;

public class TestScrabble {
	public static void main(String[] args) {
		Scrabble sc = new Scrabble(7, "C:\\Users\\pushpisingh\\Desktop\\sowpods.txt");
		System.out.println("Maximum scoring word : " + sc.getMaxScoringWord("acantha"));
	}
}
