package com.example.bashirbin.real_v20;

/**
 * Created by Bashirbin on 11/29/2017.
 */


// this is the class model or (mvc ) of the program where all the structure of the question
    // and the answer are stored
public class Question {
    private String question;

    private String [] choice = new String [4];

    private String answer ;



    // the first constructor of the class here
    public Question() {
    }

    // second consructor of the class here
    public Question(String question, String[] choice, String answer) {
        this.question = question;
        this.choice[0] = choice[0];
        this.choice[1] = choice[1];
        this.choice[2] = choice[2];
        this.choice[3] = choice[3];
        this.answer = answer;
    }



    // regular getter and setters for this one
    public String getQuestion() {
        return question;
    }

    // note carefully that this method retutn String of array and
    // the input parameter those not take an array rather it takes int
    public String getChoice(int i) {
        return choice[i];
    }

    // regular getter and setters for this one
    public String getAnswer() {
        return answer;
    }
    // regular getter and setters for this one
    public void setQuestion(String question) {
        this.question = question;
    }

    // note carefully that this method retutn String of array and
    // the input parameter those not take an array rather it takes int
    public void setChoice(int i,String choice) {
        this.choice[i] = choice;
    }

    // regular getter and setters for this one
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
