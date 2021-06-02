package ru.unidubna.studentkc.teacherSide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ru.unidubna.studentkc.R;
import ru.unidubna.studentkc.model.Answer;


public class SetAnswersFragment extends Fragment {

    List<Answer> answersList = new ArrayList<>();
    AnswerAdapter answerAdapter;

    public SetAnswersFragment() {
        super(R.layout.fragment_set_answers);
    }


    @Override
    public  View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_set_answers, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        answerAdapter = new AnswerAdapter(getActivity(), answersList);
        recyclerView.setAdapter(answerAdapter);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button deleteLastAnswerButton = view.findViewById(R.id.button_delete_last_answer);
        Button saveAnswerButton = view.findViewById(R.id.button_save_answer);
        EditText answerTextEdit = view.findViewById(R.id.answer_edit_text);
        CheckBox correctnessCheckBox = view.findViewById(R.id.answer_checkBox);
        CreateQuestionViewModel viewModel = new ViewModelProvider(requireActivity()).get(CreateQuestionViewModel.class);

        deleteLastAnswerButton.setOnClickListener(v -> {
            int index = answersList.size()-1;
            if (index >= 0){
                answersList.remove(index);
                notifyAdapter();
            }
        });

        saveAnswerButton.setOnClickListener(v -> {

            Answer answer = new Answer(answerTextEdit.getText().toString(),
                                       correctnessCheckBox.isChecked());

            answersList.add(answer);
            viewModel.setAnswers(answersList);

            answerTextEdit.setText("");
            correctnessCheckBox.setActivated((Boolean) false);
            notifyAdapter();

           //getActivity().getSupportFragmentManager().popBackStackImmediate();
        });

    }


    private void notifyAdapter(){
        answerAdapter.notifyDataSetChanged();
    }
}