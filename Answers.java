
public class Answers {

	boolean correct;
	String ans;
	char letter;
	
	public Answers(boolean correct, String ans, char letter) {
		this.correct = correct;
		this.ans = ans;
		this.letter = letter;
	}
	
	public boolean isCorrect() {
		return this.correct;
	}
	
	public String getAns() {
		return this.ans;
	}
	
	public String toString() {
		return (this.ans);
	}
	
	public char getLetter() {
		return (this.letter);
	}
	
}
