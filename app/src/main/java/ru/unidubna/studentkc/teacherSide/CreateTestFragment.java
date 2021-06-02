package ru.unidubna.studentkc.teacherSide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import ru.unidubna.studentkc.FullscreenActivity;
import ru.unidubna.studentkc.R;
import ru.unidubna.studentkc.model.Question;
import ru.unidubna.studentkc.model.Test;


public class CreateTestFragment extends Fragment {

    public CreateTestFragment() {
        super(R.layout.fragment_create_test);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference testsDatabaseReference = firebaseDatabase.getReference().child("tests");


        FullscreenActivity activity = (FullscreenActivity) getActivity();
        Button newQuestionButton = view.findViewById(R.id.button_new_answer);
        Button saveTestButton = view.findViewById(R.id.button_save_test);
        EditText testNameEditText = view.findViewById(R.id.editTextTestName);
        CreateQuestionViewModel viewModel = new ViewModelProvider(requireActivity()).get(CreateQuestionViewModel.class);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        viewModel.getQuestions().observe(getViewLifecycleOwner(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                // idk how it remove
            }
        });


        newQuestionButton.setOnClickListener(v -> {
            CreateQuestionFragment createQuestionFragment = new CreateQuestionFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.fragment_container_view, createQuestionFragment)
                    .addToBackStack("TestStack")
                    .commit();
        });

        saveTestButton.setOnClickListener(v -> {
            List<Question> questions = viewModel.getQuestions().getValue();
            Test currentTest = new Test(testNameEditText.getText().toString(), questions);
            testsDatabaseReference.push().setValue(currentTest);
            Log.v("testSaved", currentTest.toString());

        });
    }


}