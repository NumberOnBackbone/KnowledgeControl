package ru.unidubna.studentkc.model;

import java.util.List;

public class Test {

    private final String testName;
    private final List<Question> listOfQuestions;

    public Test(String testName, List<Question> listOfQuestions){
        this.testName = testName;
        this.listOfQuestions = listOfQuestions;

    }

    public String getTestName() {
        return testName;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }
}
