package com.example.resturant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.resturant.R;
import com.example.resturant.databinding.ActivityIntroBinding;
import com.example.resturant.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends BaseActivity {
ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setVariable();
    }

    private void setVariable() {
        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.userEdit.getText().toString();
                String password=binding.passEdt.getText().toString();
                
                if(password.length()<6)
                {
                    Toast.makeText(SignUpActivity.this, "you password must be 6 character", Toast.LENGTH_SHORT).show();
                    return;
                }
               mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isComplete())
                      {
                          Log.i(TAG, "onComplete: ");
                          startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                      }else {
                          Log.i(TAG, "failure: "+task.getException());
                          Toast.makeText(SignUpActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                      }
                   }
               });
            }
        });
    }
}