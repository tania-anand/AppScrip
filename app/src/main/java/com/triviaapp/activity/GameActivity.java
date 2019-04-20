package com.triviaapp.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.triviaapp.R;
import com.triviaapp.fragment.GameQuestion1;
import com.triviaapp.fragment.GameQuestion2;
import com.triviaapp.fragment.GameStart;
import com.triviaapp.listener.GameResponse;
import com.triviaapp.model.Game;
import com.triviaapp.util.GameTable;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener , GameResponse {

    private static final String TAG = "GameActivity";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    /*mPageNo is used  to store gameQuestions */
    private int mPageNo;

    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.btn_show_history)
    Button mBtnShowHistory;
    @BindView(R.id.game_container)
    FrameLayout mFrameLayout;

    GameStart gameStart;
    GameQuestion2 gameQues2Fragment;
    GameQuestion1 gameQues1Fragment;

    Game game;

    @Override
    public void onBackPressed() {
       handleBackPress();
    }

    /*
    * handle back pressed for fragment and  activity*/

    private void handleBackPress(){
        if (fragmentManager.getBackStackEntryCount() > 0) {

            if (mPageNo == 2) {
                mBtnNext.setText("Next");
                mBtnShowHistory.setVisibility(View.GONE);
                mPageNo = 1;
            } else if (mPageNo == 1) {
                mBtnNext.setText("Start Game");
                mBtnShowHistory.setVisibility(View.VISIBLE);
                mPageNo = 0;
            }
            fragmentManager.popBackStackImmediate();
        } else {
            super.onBackPressed();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
    }


    private  void initView(){
        mBtnNext.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();

        game = new Game();

        gameStart = new GameStart();
        gameStart.setResponseListener(this);
        gameQues1Fragment = new GameQuestion1();
        gameQues1Fragment.setResponseListener(this);
        gameQues2Fragment = new GameQuestion2();
        gameQues2Fragment.setResponseListener(this);

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_container, gameStart, "GameStart");
        fragmentTransaction.commit();
        mBtnNext.setText("Start Game");


        mBtnShowHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameActivity.this,HistoryActivity.class);
                startActivity(i);

            }
        });

    }

    /*
    * on click for button handle for different fragments going from one page to another
    * */
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btn_next){

            if(mPageNo == 0){

                if(gameStart.validation()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.hide(fragmentManager.findFragmentByTag("GameStart"));
                    fragmentTransaction.add(R.id.game_container, gameQues1Fragment, "GameQues1");
                    fragmentTransaction.addToBackStack("GameQues1");
                    fragmentTransaction.commit();

                    mPageNo = 1;
                    mBtnNext.setText("Next");
                    mBtnShowHistory.setVisibility(View.GONE);
                }
            }

            else if(mPageNo == 1){


                if(gameQues1Fragment.validation()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.hide(fragmentManager.findFragmentByTag("GameQues1"));
                    fragmentTransaction.add(R.id.game_container, gameQues2Fragment, "GameQues2");
                    fragmentTransaction.addToBackStack("GameQues2");
                    fragmentTransaction.commit();


                    mPageNo = 2;
                    mBtnNext.setText("Finish");
                    mBtnShowHistory.setVisibility(View.GONE);
                }


            }else if(mPageNo == 2){

                if(gameQues2Fragment.validation()) {

                    game.setDate(new Date());
                    GameTable.getInstance(getApplicationContext()).addData(game);

                    gameStart = new GameStart();
                    gameStart.setResponseListener(this);
                    gameQues1Fragment = new GameQuestion1();
                    gameQues1Fragment.setResponseListener(this);
                    gameQues2Fragment = new GameQuestion2();
                    gameQues2Fragment.setResponseListener(this);


                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentManager.popBackStack("GameStart", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentTransaction.replace(R.id.game_container, gameStart, "GameStart");
                    fragmentTransaction.commit();


                    mPageNo = 0;
                    mBtnNext.setText("Start");
                    mBtnShowHistory.setVisibility(View.VISIBLE);
                }

            }
        }
    }


    /* listener to handle response of game question from each framgent*/
    @Override
    public void gameResponse(String tag,String response) {


        if(tag.equals("0")){
            game.setName(response);
        }else  if(tag.equals("1")){
            game.setAnswer1(response);
        }
        else  if(tag.equals("2")) {
            game.setAnswer2(response);
        }

        Log.d(TAG, "gameResponse: "+game.toString());

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            handleBackPress();
        }

        return super.onOptionsItemSelected(item);
    }



}
