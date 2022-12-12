package com.example.firebase_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;//파이어 베이스 인증
    private DatabaseReference mDatabaseRef; //파이어베이스 데이터베이스
    private EditText editEmail,edtPad;
    private Button mbtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Travel");
        mbtnRegister=findViewById(R.id.MakeId);
        editEmail=findViewById(R.id.editEmail);
        edtPad=findViewById(R.id.edtPad);

        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strEmail =editEmail.getText().toString();   //db 테이블에는 text만 들어갈 수 있음.
                String strPwd=edtPad.getText().toString();

                mFirebaseAuth.createUserWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    //이메일과 패스워드에 대한 메소드 호출
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){             // db에 데이터가 기입되었을시
                            FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                            UserAccount account=new UserAccount();
                            account.setEmailid(firebaseUser.getEmail());
                            account.setPassword(strPwd);
                            account.setIdToken(firebaseUser.getUid());

                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(Register.this,"회원가입에 성공 하셨습니다.",Toast.LENGTH_SHORT).show();
                        }else{               //db에 데이터가 들어가지 않았을시
                            Toast.makeText(Register.this,"회원가입에 실패 하셨습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}