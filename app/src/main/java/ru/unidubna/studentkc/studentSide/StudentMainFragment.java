package ru.unidubna.studentkc.studentSide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.unidubna.studentkc.R;
import ru.unidubna.studentkc.teacherSide.RoomEditorFragment;


public class StudentMainFragment extends Fragment {

    public StudentMainFragment() {
        super(R.layout.fragment_student_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                        Intent data = result.getData();
                    } else if(result.getResultCode() == Activity.RESULT_CANCELED){
                        Toast.makeText(getContext(), "ty pidar i ne smog", Toast.LENGTH_SHORT).show();
                        //getActivity().finish();

                    }
                }
        );

        Button doTestButton = view.findViewById(R.id.button_do_test);


        doTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LaunchTestCheckActivity.class);
                activityResultLauncher.launch(intent);

            }
        });


    }
}
