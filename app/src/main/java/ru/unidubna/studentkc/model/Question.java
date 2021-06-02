package ru.unidubna.studentkc.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private Integer questionType;
    private String questionText;
    private List<Answer> questionAnswers;

    public Question(Integer questionType,
                    String questionText,
                    List<Answer> questionAnswers) {

        this.questionType = questionType;
        this.questionText = questionText;
        this.questionAnswers = questionAnswers;
    }


    public List<Answer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }


}

