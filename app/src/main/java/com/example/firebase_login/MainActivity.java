package com.example.firebase_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;//파이어 베이스 인증


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:// 메뉴 1을 선택했을 때
                Toast.makeText(this, "selected Memo", Toast.LENGTH_SHORT).show();
                //메모를 선택했음. 이라는 문구를 Toast 해줌
                Intent intent=new Intent(MainActivity.this, MenuForMemo.class);
                //인텐트를 열어서 MenuForMemo로 이동시켜줌
                startActivity(intent);
                break;
            case R.id.menu2:
                mFirebaseAuth = FirebaseAuth.getInstance();
                //파이어베이스와 연동했던 데이터
                mFirebaseAuth.signOut();
                // signout 함.
                Intent intent2 =new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent2);
                // 로그인 액티비티로 이동시키고
                finish();
                //인텐트 종료
                Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
                //로그아웃 내용 토스트
                mFirebaseAuth.getCurrentUser().delete();
                //현재의 유저를 delete 하므로서 할당된 메모리를 free 시켜줌.
        }


        return super.onOptionsItemSelected(item);
    }
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}