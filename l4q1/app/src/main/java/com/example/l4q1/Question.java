package com.example.l4q1;

public class Question {
    private int answerresID;
    private int question;

    public Question(int question, int answerresID)
    {
        //constructor for the Question
        //answwer is the option to be selected
        this.question = question;
        this.answerresID = answerresID;
    }

    public int getAnswerresID() {
        return answerresID;
    }

    public void setAnswerresID(int answerresID) {
        this.answerresID = answerresID;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
    public boolean checkAnswerisCorrect(int answer){
        System.out.println(answer);
        System.out.println("answer index: "+ (answer-2131230800));
        System.out.println("answerresID:" + getAnswerresID());

        return (this.answerresID == (answer-2131230800));
    }
}
