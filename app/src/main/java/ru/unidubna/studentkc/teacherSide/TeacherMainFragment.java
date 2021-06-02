package ru.unidubna.studentkc.teacherSide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.unidubna.studentkc.FullscreenActivity;
import ru.unidubna.studentkc.R;


public class TeacherMainFragment extends Fragment {

    public TeacherMainFragment() {
        super(R.layout.fragment_teacher_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button testEditButton = view.findViewById(R.id.button_edit_test);
        Button testCreateButton = view.findViewById(R.id.button_create_test);
        Button roomCreateButton = view.findViewById(R.id.button_create_room);
        Button roomEditButton = view.findViewById(R.id.button_edit_room);

        FullscreenActivity activity = (FullscreenActivity) getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        testEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomEditorFragment roomEditorFragment = new RoomEditorFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container_view, roomEditorFragment)
                                                  .addToBackStack("Stack")
                                                  .commit();
            }
        });

        testCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTestFragment createTestFragment = new CreateTestFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container_view, createTestFragment)
                                                  .addToBackStack("Stack")
                                                  .commit();

            }
        });





    }
}
