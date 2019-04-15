//package birdyRun;

public class QuizQ {
	
	private String question; // quizzes have questions, answers and wrong choices
	private String answer;
	private String[] wrong;
	
	QuizQ(){
		question = "Do you want to continue?";
		answer = "y";
		wrong = new String[1];
		wrong[0] = "n";
	}
	public String toString() {
		String r = question;
		r+= "\n";
		r+="\t";
		r+=answer;
		for(int i=0; i< wrong.length; i++)
			r = r + " or "+wrong[i];
		
		return r;
	}
}
