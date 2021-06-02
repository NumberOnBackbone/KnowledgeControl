package ru.unidubna.studentkc;

import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.unidubna.studentkc.studentSide.StudentMainFragment;
import ru.unidubna.studentkc.teacherSide.TeacherMainFragment;


public class ChooseUsertypeFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public ChooseUsertypeFragment() {
       super(R.layout.fragment_choose_usertype);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Toast.makeText(getContext(), "signed in", Toast.LENGTH_SHORT).show();
                        Intent data = result.getData();
                    } else if(result.getResultCode() == Activity.RESULT_CANCELED){
                        Toast.makeText(getContext(), "sign in cancelled", Toast.LENGTH_SHORT).show();
                        getActivity().finish();

                    }
                }
        );

        mFirebaseAuth = FirebaseAuth.getInstance();

        Button loginButton = view.findViewById(R.id.button_login);
        Button studentLoginButton = view.findViewById(R.id.button_student_login);
        FullscreenActivity activity = (FullscreenActivity) getActivity();
        assert activity != null;
        FragmentManager  fragmentManager = activity.getSupportFragmentManager();
        loginButton.setOnClickListener(v -> {
            TeacherMainFragment teacherMainFragment = new TeacherMainFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container_view,
                                                       teacherMainFragment)
                                              .addToBackStack("Stack")
                                              .commit();
        });

        studentLoginButton.setOnClickListener(v -> {
            StudentMainFragment studentMainFragment = new StudentMainFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container_view,
                    studentMainFragment)
                    .addToBackStack("Stack")
                    .commit();
        });

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Toast.makeText(getContext(),
                               R.string.login_toast,
                               Toast.LENGTH_SHORT).show();
            } else {
                doAuth(activityResultLauncher);
            }
        };

    }


    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    private void doAuth(@NonNull ActivityResultLauncher<Intent> launcher){
        launcher.launch(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build()))
                        .build());
    }
    private void onSignedInInitialize(String username){
        //todo implement
    }

    private void onSignedOutCleanup(){
        //todo implement
    }


}