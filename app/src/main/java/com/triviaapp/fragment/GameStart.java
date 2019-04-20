package com.triviaapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.triviaapp.R;
import com.triviaapp.listener.GameResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameStart extends Fragment {

    @BindView(R.id.ed_name)
    EditText edName;


    public GameStart() {
        // Required empty public constructor
    }

    GameResponse gameResponse;
    public void setResponseListener(GameResponse gameResponse){
        this.gameResponse = gameResponse;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_start, container, false);

        ButterKnife.bind(this,view);
        return  view;
    }


    public boolean validation(){
        boolean flag = true;

        if(edName.getText().toString().isEmpty()){
            edName.setError("Name Required");
            flag = false;
        }else{
            edName.setError(null);
            gameResponse.gameResponse("0",edName.getText().toString());
        }

        return flag;
    }

}
