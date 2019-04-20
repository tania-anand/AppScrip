package com.triviaapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.triviaapp.R;
import com.triviaapp.listener.GameResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameQuestion1 extends Fragment implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radio_group_ques1)
    RadioGroup mRadioGroup;
    String text;


    public GameQuestion1() {
        // Required empty public constructor
    }


    View view;

    GameResponse gameResponse;
    public void setResponseListener(GameResponse gameResponse){
        this.gameResponse = gameResponse;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_game_questions, container, false);
        ButterKnife.bind(this,view);

        mRadioGroup.setOnCheckedChangeListener(this);

        return view;
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {

        RadioButton radioButton = radioGroup.findViewById(id);

        if(radioButton.isChecked()){
            text = radioButton.getText().toString();
        }

    }

    public boolean validation(){
        boolean flag = true;


        if(text==null) {
            flag = false;
            Toast.makeText(getContext(),"Select Any One Option ",Toast.LENGTH_LONG).show();
        }else{
            gameResponse.gameResponse("1",text);
        }

        return  flag;
    }
}
