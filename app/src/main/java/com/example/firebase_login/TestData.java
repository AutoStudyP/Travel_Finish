package com.example.firebase_login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TestData extends AppCompatActivity {
    Button getMain;
    String intentional,packagename;
    Button button3;
    ScrollView scrollt;
    private FirebaseAuth mFirebaseAuth;//파이어 베이스 인증

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //메뉴구성 -> Main Activity에서 설명
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "selected Memo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TestData.this, MenuForMemo.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseAuth.signOut();
                Intent intent2 = new Intent(TestData.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                Toast.makeText(this, "Logout_ID", Toast.LENGTH_SHORT).show();
                mFirebaseAuth.getCurrentUser().delete();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);
        getMain=findViewById(R.id.gomain);
        button3=findViewById(R.id.button3);

        Intent secondIntent = getIntent();
        intentional =secondIntent.getStringExtra("WWH"); //인텐트에 선택에 대한 fullname을 name(id)로 받아서 textdata에 저장

        packagename=this.getPackageName(); //현재 패키지 이름을 일일히 가져오는 것이 아닌 패키지 이름을 한번에 집어넣음/ this를 이용
        int resID=getResources().getIdentifier(intentional,"id",packagename);
        //id 값은 인트형으로 저장되므로 이름과 일치하는 아이디값을 가져오기위해
        //getResources, getIdentifier을 이용 identifier 신택스에는 아이디와 대조 가능한 string 데이터와 가져올 타입의 이름, 패키지 이름이 들어감
        //scroll view layout을 일일히 id와 대조하여 진행하는것보다 편리하다고 판단.
        scrollt=(ScrollView)findViewById(resID);
        scrollt.setVisibility(View.VISIBLE);
        //해당하는 스크롤 뷰 레이아웃을 visible 하게 만들어줌.



        button3.setOnClickListener(new View.OnClickListener() { //버튼을 눌렀을때 어떤곳을 선택 했는지 Toast를 이용하여 출력
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), intentional, Toast.LENGTH_SHORT).show();
            }
        });
        getMain.setOnClickListener(new View.OnClickListener() {  //main으로 갈 수 있도록 인텐트이용
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestData.this,MainActivity.class);
                intent.putExtra("WWH",intentional);
                startActivity(intent);
            }
        });


    }
}