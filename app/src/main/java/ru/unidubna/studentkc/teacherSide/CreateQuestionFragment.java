package ru.unidubna.studentkc.teacherSide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ru.unidubna.studentkc.R;
import ru.unidubna.studentkc.model.Answer;
import ru.unidubna.studentkc.model.Question;


public class CreateQuestionFragment extends Fragment {
    public static final int QUESTION_TYPE = 0;

    public CreateQuestionFragment() {
        super(R.layout.fragment_create_question);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button newAnswerButton = view.findViewById(R.id.button_new_answer);
        Button saveQuestionButton = view.findViewById(R.id.button_save_question);
        EditText questionTextEditText = view.findViewById(R.id.editTextQuestionText);

        List<Question> questionList = new ArrayList<>();

        CreateQuestionViewModel viewModel = new ViewModelProvider(requireActivity()).get(CreateQuestionViewModel.class);

        viewModel.getAnswers().observe(getViewLifecycleOwner(), new Observer<List<Answer>>() {
            @Override
            public void onChanged(List<Answer> questions) {
               // idk how it remove
            }
        });


        newAnswerButton.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                                                                   .beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.fragment_container_view_set_answers, new SetAnswersFragment())
                               .addToBackStack("TestStack")
                               .commit();
        });

        saveQuestionButton.setOnClickListener(v -> {
            LiveData<List<Answer>> data = viewModel.getAnswers();
            Question savedQuestion = new Question(QUESTION_TYPE,
                                                  questionTextEditText.getText().toString(),
                                                  data.getValue());


            getActivity().getSupportFragmentManager().popBackStack("Stack", 0);
            viewModel.addQuestion(savedQuestion);
            Log.v("savedQuestion", savedQuestion.toString());

        });
    }


}