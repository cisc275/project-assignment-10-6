
public class Answers {

	boolean correct;
	String ans;
	
	public Answers(boolean correct, String ans) {
		this.correct = correct;
		this.ans = ans;
	}
	
	public boolean isCorrect() {
		return this.correct;
	}
	
	public String getAns() {
		return this.ans;
	}
}
