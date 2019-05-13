import java.util.*;
//package birdyRun;

import javax.swing.JTextArea;

public class QuizQ {
	
	private String question; // quizzes have questions, answers and wrong choices
	public String answer;
	public char correctLetter;
	private int correctNum;

	
	// Array of answers for each question
	Answers[] a0 = new Answers[] {new Answers(false, "ONE", '1'), new Answers(true, "TWO T",'2'), new Answers(false, "THREE",'3'), new Answers(false, "FOUR",'4')};
	Answers[] a1 = new Answers[] {new Answers(true, "ONE T", '1'), new Answers(false, "TWO",'2'), new Answers(false, "THREE",'3'), new Answers(false, "FOUR",'4')};
	Answers[] a2 = new Answers[] {new Answers(false, "ONE", '1'), new Answers(false, "TWO",'2'), new Answers(true, "THREE T",'3'), new Answers(false, "FOUR",'4')};
	Answers[] a3 = new Answers[] {new Answers(true, "ONE T", '1'), new Answers(false, "TWO",'2'), new Answers(false, "THREE",'3'), new Answers(false, "FOUR",'4')};
	Answers[] a4 = new Answers[] {new Answers(false, "ONE", '1'), new Answers(false, "TWO",'2'), new Answers(false, "THREE",'3'), new Answers(true, "FOUR T",'4')};
	Answers[] a5 = new Answers[] {new Answers(false, "ONE", '1'), new Answers(false, "TWO",'2'), new Answers(true, "THREE T",'3'), new Answers(false, "FOUR",'4')};
	
	// Make a 2d array of all answers; each row is for the corresponding question, and each column is answer choices 1-4
	Answers[][] allAns = new Answers[][] {a0,a1,a2,a3,a4,a5};
	
	// Need an array of questions that correspond to row of 2d allAns array
	String[] questions = new String[] {"Q0","Q1","Q2","Q3","Q4","Q5"};
	
	public char getCorrectLetter() {
		return this.correctLetter;
	}
	
	public int getCorrectNum() {
		int return_val = 0;
		for (int i = 0; i < 4; i++) {
			if (allAns[correctNum][i].isCorrect()) {
				return_val = i;
			}
		}
		return return_val;
	}
	
	// this method will choose a random question and print it to the screen
	public int chooseQ() {
		
		// choosing a random question to display
		Random rand = new Random();
		int nextNum = rand.nextInt(6); // choose a random number from 0-5 to be the question number to be asked
		System.out.println("Question Number " + nextNum);
		System.out.println("IN QUIZ");
		// avoid asking the same question twice in a row; check to see if previous question # is the same as this next one
		//while (correctNum == nextNum) {
		//	nextNum = (int)(Math.random() % 5);
		//}
		correctNum = nextNum;
		question = questions[nextNum];
		//System.out.println(question);
		return nextNum;
	}
	
	// getting the correct answer index of subarray for the particular question
	public int getCorrectAns(int correctNum) {
		int ansNum = 0;
		for (int i = 0; i < 4; i++) {
			ansNum = i;
			if (allAns[correctNum][i].isCorrect()) { // if this is the correct answer, set this as the answer and break from loop
				answer = allAns[correctNum][i].getAns();
				break;
			}
		}
		return ansNum;
	}
	public String toString() {
		return this.question;
	}
	
	public void printChoices() {
		for (int i = 0; i < 4; i++) {
			System.out.println(allAns[correctNum][i].getAns());
		}
	}
	
	public Answers[] getArrayAns() {
		return allAns[getCorrectAns(correctNum)];
	}
	
	public String getQuestion() {
		return (this.question);
	}
	
	public QuizQ() {
		System.out.println("Entering quiz constructor");
		int questI = chooseQ();
		int ansI = getCorrectAns(questI); // this provides second array index where the correct answer is. So coordinates of correct answer are
		// given by chooseQ method (questI) and ansI
		
		// print out question
		System.out.println(question);
		
		// to print out answers:
		for (int i = 0; i < 4; i++) {
			System.out.println(allAns[questI][i]);
		}
		
		// get letter input that is correct answer
		correctLetter = allAns[questI][ansI].getLetter();
		
		
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
/*Questions
 * 1) Ospery like to eat: a. seeds b. crabs ansc.(fish) d.meat
 * Clapper Rail like to eat: a. candy b. insects ansc. crabs d. fruit
 * 2)Ospery live in what kind of habitat?
 * ansa. near shallow bodies of water b. near cities c. on mountains d. in swamps
 * Clapper Rail. . .?
 * ansa. in saltmarshes b. on mountains c. near shallow bodies of water d. near cities
 * 3)Ospery usually travel by: a. walking ansb. flying c. swimming d. jumping
 * Clapper Rail . . .: a. flying ansb. on ground c. jumping d. swimming
 * 4)Ospery like to build their nest out of: a. steel b. foam c. soil ansd. sticks
 * Clapper Rail . . .: a. steel b. foam c. soil ansd. sticks  
 * 5)Ospery are what kind of bird? ansa. migratory b. nonmigratory c. both d. neither
 * Clapper Rail . . .? ansa. nonmigratory b. migratory c. both d. neither
 */
}
