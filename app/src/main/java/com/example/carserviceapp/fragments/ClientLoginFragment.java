package com.example.carserviceapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.carserviceapp.R;
import com.example.carserviceapp.Screens.WelcomeScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ClientLoginFragment extends Fragment {
    private static final String TAG = "EmailPassword";

    Button clientLoginBu;

    private FirebaseAuth mAuth;
    EditText emailET;
    EditText passwordET;


    public ClientLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login_client, container, false);

        mAuth = FirebaseAuth.getInstance();
        emailET = view.findViewById(R.id.userName);
        passwordET = view.findViewById(R.id.password);

        clientLoginBu = view.findViewById(R.id.client_login_bu);

        clientLoginBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(emailET.getText().toString(),passwordET.getText().toString());

//                Intent intent = new Intent(getActivity(), WelcomeScreen.class);
//                startActivity(intent);
            }
        });


        return view;
    }



    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            //finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }


                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailET.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailET.setError("Required.");
            valid = false;
        } else {
            emailET.setError(null);
        }

        String password = passwordET.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordET.setError("Required.");
            valid = false;
        } else {
            passwordET.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser!=null){
            System.out.println("current user is "+currentUser.getEmail());
            Intent intent = new Intent(getActivity(), WelcomeScreen.class);
            // this line will clear the previous activity and never cannot bake to it by the button phone
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

}