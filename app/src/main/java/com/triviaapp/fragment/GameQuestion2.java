package com.triviaapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.triviaapp.R;
import com.triviaapp.listener.GameResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GameQuestion2 extends Fragment implements CheckBox.OnCheckedChangeListener {

    @BindView(R.id.ques2_opt1)
    CheckBox mOpt1;
    @BindView(R.id.ques2_opt2)
    CheckBox mOpt2;
    @BindView(R.id.ques2_opt3)
    CheckBox mOpt3;
    @BindView(R.id.ques2_opt4)
    CheckBox mOpt4;

    ArrayList<String> arrayList;


    GameResponse gameResponse;
    public void setResponseListener(GameResponse gameResponse){
        this.gameResponse = gameResponse;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game_question2, container, false);

        ButterKnife.bind(this,view);
        mOpt1.setOnCheckedChangeListener(this);
        mOpt2.setOnCheckedChangeListener(this);
        mOpt3.setOnCheckedChangeListener(this);
        mOpt4.setOnCheckedChangeListener(this);
        arrayList = new ArrayList <>();

        return  view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(compoundButton.isChecked()){
            arrayList.add(compoundButton.getText().toString());
        }else{
            arrayList.remove(compoundButton.getText().toString());
        }

    }


    public boolean validation(){
        boolean flag = true;

        if(arrayList.isEmpty()) {
            flag = false;
            Toast.makeText(getContext(),"Select atleast one option",Toast.LENGTH_LONG).show();
        }else{

            StringBuilder sb = new StringBuilder();

            if(arrayList.size()==1){
                sb.append(arrayList.get(0));
            }else {
                sb.append(arrayList.get(0));
                for (int i = 1; i < arrayList.size(); i++) {
                    sb.append(',');
                    sb.append(arrayList.get(i));
                }
            }
            String result = sb.toString();
            gameResponse.gameResponse("2",result);
        }


        return flag;
    }
}
