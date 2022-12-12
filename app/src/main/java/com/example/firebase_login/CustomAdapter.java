package com.example.firebase_login;
//어댑터 생성
//database와 연동 할 수 있도록 생성
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewholder>{

    private ArrayList<Went> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Went> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomViewholder holder = new CustomViewholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewholder holder, int position) {
        Glide.with(holder.itemView)           //그래픽을 database에서 가져오기 위해 Glide를 이용해서 picture을 get
                .load(arrayList.get(position).getPicture())
                .into(holder.iv_picture);
        holder.place.setText(arrayList.get(position).getPlace()); //place와 id 커넥트
        holder.rating.setText(String.valueOf(arrayList.get(position).getRating()));//place와 rating int 커넥트
        holder.selection.setText(arrayList.get(position).getSelection());//place와 selection 커넥트
    }

    @Override
    public int getItemCount() {
        return (arrayList !=null ? arrayList.size():0);
    }

    public static class CustomViewholder extends RecyclerView.ViewHolder {
        ImageView iv_picture;
        TextView place;
        TextView rating;
        TextView selection;

        public CustomViewholder(@NonNull View itemView) {
            super(itemView); //Went 클래스와 id 연동
            this.iv_picture=itemView.findViewById(R.id.iv_picture);
            this.place=itemView.findViewById(R.id.place);
            this.rating=itemView.findViewById(R.id.rating);
            this.selection=itemView.findViewById(R.id.selection);
        }
    }
}
