package ru.unidubna.studentkc.studentSide;

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


public class AnswerTestFragment extends Fragment {

    public AnswerTestFragment() {
        super(R.layout.fragment_answer_question);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}