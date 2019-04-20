package com.triviaapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.triviaapp.R;
import com.triviaapp.model.Game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {

    private ArrayList<Game> mArrayList;
    private Context mContext;
    SimpleDateFormat sdf ;

    public GameAdapter(ArrayList <Game> mArrayList, Context mContext) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
        sdf = new SimpleDateFormat("dd MMM yy HH:mm", Locale.ENGLISH);
    }



    @NonNull
    @Override
    public GameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.game_listitem, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.txtName.setText("NAME :"+mArrayList.get(i).getName());
        myViewHolder.txtDate.setText("GAME 1 :"+sdf.format(mArrayList.get(i).getDate()));
        myViewHolder.txtAns1.setText("Answer :"+mArrayList.get(i).getAnswer1());
        myViewHolder.txtAns2.setText("Answers :"+mArrayList.get(i).getAnswer2());



    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_ans1)
        TextView txtAns1;
        @BindView(R.id.txt_ans2)
        TextView txtAns2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
