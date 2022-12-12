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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Button btn_where,btn_how,btn_who,btn_select;
    TextView tv_where,tv_how,tv_who;
    RadioGroup rg_where,rg_how,rg_who;
    RadioButton rb_naju,rb_hawsun,rb_jangsung,rb_alone,rb_car,rb_bus,rb_friend,rb_family,rb_x
            ,rb_damyang,rb_gwangju;
    String WherE,HoW,WhO,Fullname;
    int sum=0;
    private FirebaseAuth mFirebaseAuth;//파이어 베이스 인증

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "selected Memo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MenuForMemo.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseAuth.signOut();
                Intent intent2 = new Intent(MainActivity2.this, LoginActivity.class);
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
        setContentView(R.layout.activity_main2);
        btn_where=findViewById(R.id.button5);
        btn_how=findViewById(R.id.button2);
        btn_who=findViewById(R.id.button4);
        btn_select=findViewById(R.id.button6);
        //버튼 설정

        tv_where=findViewById(R.id.tv_where);
        tv_how=findViewById(R.id.tv_how);
        tv_who=findViewById(R.id.tv_who);

        rg_where=findViewById(R.id.rg_where);
        rg_how=findViewById(R.id.rg_how);
        rg_who=findViewById(R.id.rg_who);
        //라디오그룹

        rb_naju=findViewById(R.id.rb_naju);
        rb_hawsun=findViewById(R.id.rb_hawsun);
        rb_jangsung=findViewById(R.id.rb_jangsung);
        rb_gwangju=findViewById(R.id.rb_gwangju);
        rb_damyang=findViewById(R.id.rb_damyang);
        rb_car=findViewById(R.id.rb_car);
        rb_bus=findViewById(R.id.rb_bus);
        rb_alone=findViewById(R.id.rb_alone);
        rb_friend=findViewById(R.id.rb_friend);
        rb_family=findViewById(R.id.rb_family);
        rb_x=findViewById(R.id.rb_x);
        //라디오버튼

        btn_where.setOnClickListener(new View.OnClickListener() {
            @Override          //선택에 따라 값을 받도록 switch case 문 설정
            public void onClick(View view) {
                switch (rg_where.getCheckedRadioButtonId()){
                    case R.id.rb_naju:
                        tv_where.setText("장소: 나주"); //보여져야할 textView에 한글을 넣어줌.
                        WherE="naju";  //데이터를 String 에 저장.
                        break;
                    case R.id.rb_hawsun:
                        tv_where.setText("장소: 화순");
                        WherE="hwasun";//데이터를 String 에 저장.
                        break;
                    case R.id.rb_jangsung:
                        tv_where.setText("장소: 장성");
                        WherE="jangseong";//데이터를 String 에 저장.
                        break;
                    case R.id.rb_damyang:
                        tv_where.setText("장소: 담양");
                        WherE="damyang";//데이터를 String 에 저장.
                        break;
                    case R.id.rb_gwangju:
                        tv_where.setText("장소: 광주");
                        WherE="gwangju";//데이터를 String 에 저장.
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"장소를 먼저 선택하세요",Toast.LENGTH_SHORT).show();
                }
                rg_where.setVisibility(View.GONE);
                tv_where.setVisibility(View.VISIBLE);
                rg_how.setVisibility(View.VISIBLE);
            }
        });

        btn_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rg_how.getCheckedRadioButtonId()){
                    case R.id.rb_car:
                        tv_how.setText("장소: 자가용");
                        HoW="car";//데이터를 String 에 저장.
                        break;
                    case R.id.rb_bus:
                        tv_how.setText("장소: 대중교통");
                        HoW="public";//데이터를 String 에 저장.
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"이동수단을 먼저 선택하세요",Toast.LENGTH_SHORT).show();
                }
                rg_how.setVisibility(View.GONE);
                tv_how.setVisibility(View.VISIBLE);
                rg_who.setVisibility(View.VISIBLE);
            }
        });

        btn_who.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rg_who.getCheckedRadioButtonId()){
                    case R.id.rb_alone:
                        tv_who.setText("여행 구성원: 혼자");
                        WhO="alone";//데이터를 String 에 저장.
                        break;
                    case R.id.rb_friend:
                        tv_who.setText("여행 구성원: 친구");
                        WhO="friend";//데이터를 String 에 저장.
                        break;
                    case R.id.rb_family:
                        tv_who.setText("여행 구성원 : 가족");
                        WhO="family";//데이터를 String 에 저장.
                        break;
                    case R.id.rb_x:
                        tv_who.setText("여행 구성원 : 연인");
                        WhO="couple";//데이터를 String 에 저장.
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"여행인원을 먼저 선택하세요",Toast.LENGTH_SHORT).show();
                }
                rg_who.setVisibility(View.GONE);
                tv_who.setVisibility(View.VISIBLE);
                btn_select.setVisibility(view.VISIBLE);
            }
        });


        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fullname=WherE+"_"+WhO+"_"+HoW; //Fullname에 선택들을 모두 저장함.
                Intent intent=new Intent(MainActivity2.this, TestData.class);
                intent.putExtra("WWH",Fullname);    //Fullname을 인텐트로 넘겨줘서 다음 액티비티에서 받을 수 있도록 구현
                startActivity(intent);
            }
        });

    }
}