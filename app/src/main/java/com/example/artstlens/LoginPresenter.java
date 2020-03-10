package com.example.artstlens;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View mvpview;
    public LoginPresenter(LoginContract.View mvpview) {
        this.mvpview = mvpview;
    }

    public void signIn(FirebaseAuth mAuth, String email, String password){
        mvpview.setProgressBarVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mvpview.setProgressBarVisibility(View.GONE);
                if(task.isSuccessful()){
                    mvpview.startProfileActivity();
                }
                else{
                    mvpview.makeToast(task.getException().getMessage());
                }
            }
        });
    }
}
