package com.flashcardbuilder.java;

public class FlashCards {
	//Instance variables
	private String question;
	private String answer;
	
	
	public FlashCards(String question, String answer){
		this.question = question;
		this.answer = answer;
	}
	
	//Getters and Setters for question and answer
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
