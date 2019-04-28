import java.util.*;


//package birdyRun;

public class QuizQ extends Answers{
	
	private String question; // quizzes have questions, answers and wrong choices
	private String answer;
	
	
	// Array of answers for each question
	Answers[] a0 = new Answers[] {new Answers(false, "ONE"), new Answers(true, "TWO T"), new Answers(false, "THREE"), new Answers(false, "FOUR")};
	Answers[] a1 = new Answers[] {new Answers(true, "ONE T"), new Answers(false, "TWO"), new Answers(false, "THREE"), new Answers(false, "FOUR")};
	Answers[] a2 = new Answers[] {new Answers(false, "ONE"), new Answers(false, "TWO"), new Answers(true, "THREE T"), new Answers(false, "FOUR")};
	Answers[] a3 = new Answers[] {new Answers(true, "ONE T"), new Answers(false, "TWO"), new Answers(false, "THREE"), new Answers(false, "FOUR")};
	Answers[] a4 = new Answers[] {new Answers(false, "ONE"), new Answers(false, "TWO"), new Answers(false, "THREE"), new Answers(true, "FOUR T")};
	Answers[] a5 = new Answers[] {new Answers(false, "ONE"), new Answers(false, "TWO"), new Answers(true, "THREE T"), new Answers(false, "FOUR")};
	
	// Make a 2d array of answers 
	Answers[][] allAns = new Answers[][] {a0,a1,a2,a3,a4,a5};
	
	// Need an array of questions 
	String[] questions = new String[] {"Q0","Q1","Q2","Q3","Q4","Q5"};
	
	public void chooseQandA() {
		// choosing the question to display
		int num = (int)(Math.random() % 5);
		question = questions[num];
		System.out.println(question);
		
		// getting the correct answer for that question
		for (int i = 0; i < 4; i++) {
			if (allAns[num][i].isCorrect()) {
				answer = allAns[num][i].getAns();
				break;
			}
		}
	}
	
	
	public QuizQ(boolean correct, String ans) {
		super(correct, ans);
		chooseQandA();
		
		//question = "Do you want to continue?";
		//answer = "y";
		//wrong = new String[1];
		//wrong[0] = "n";
	}
	
	/**
	public String toString() {
		String r = question;
		r+= "\n";
		r+="\t";
		r+=answer;
		for(int i=0; i< wrong.length; i++)
			r = r + " or "+wrong[i];
		
		return r;
	}
	*/
}
