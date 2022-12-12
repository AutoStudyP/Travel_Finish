package com.example.firebase_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuForMemo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Went> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    Button reMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_list);

        recyclerView = findViewById(R.id.recyclerview);     //리사이클 뷰를 이용하여 데이터베이스의 인덱스에 따른 데이터 기입.(인덱스에 맞는 데이터를 list에 저장)
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();
        reMenu=findViewById(R.id.button8);
        database= FirebaseDatabase.getInstance();

        databaseReference=database.getReference("Went");   //데이터 베이스 path에 맞는 레퍼런스를 가져옴
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {       //path에 있는 데이터를 가져옴.
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();    //어레이 리스트의 충돌을 방지하기 위한 코드
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Went went=snapshot1.getValue(Went.class);   //data를 가져와서 went에 집어넣음
                    arrayList.add(went);                        //went 클래스를 arraylist에 삽입
                }
                adapter.notifyDataSetChanged();                 //adapter 업데이트
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Menu Activity", String.valueOf(error.toException()));
            }
        });
        adapter=new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);

        reMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}