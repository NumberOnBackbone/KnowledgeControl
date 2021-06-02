package ru.unidubna.studentkc.teacherSide;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ru.unidubna.studentkc.model.Answer;
import ru.unidubna.studentkc.model.Question;

public class CreateQuestionViewModel extends ViewModel {
    private MutableLiveData<List<Answer>> questiontAnswers;
    private MutableLiveData<List<Question>> testQuestions;

    public void setAnswers(List<Answer> answers){
        if(answers == null){
            questiontAnswers = new MutableLiveData<>();
        }
        questiontAnswers.setValue(answers);
    }

    public void settQuestions(List<Question> questions){
        if(questions == null){
            testQuestions = new MutableLiveData<>();
        }
        testQuestions.setValue(questions);
    }

    public LiveData<List<Answer>> getAnswers(){
        if(questiontAnswers == null){
            questiontAnswers = new MutableLiveData<>();
        }
        return questiontAnswers;
    }

    public LiveData<List<Question>> getQuestions(){
        if(testQuestions == null){
            testQuestions = new MutableLiveData<>();
        }
        return testQuestions;
    }

    public void addQuestion(Question question){
        List<Question> currentQuestions = testQuestions.getValue();
        if(currentQuestions == null){
            currentQuestions = new ArrayList<>();
        }
        currentQuestions.add(question);
        testQuestions.setValue(currentQuestions);


    }

}
