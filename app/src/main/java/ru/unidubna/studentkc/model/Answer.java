package ru.unidubna.studentkc.model;

public class Answer {

    private String answerText;
    private Boolean isRight;



    public Answer(String answerText, Boolean isRight) {
        this.answerText = answerText;
        this.isRight = isRight;
    }

    public Boolean isRight(){
        return this.isRight;
    }
    public String getAnswerText() {
        return answerText;
    }
}