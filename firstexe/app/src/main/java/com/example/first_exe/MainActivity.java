package com.example.first_exe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
enum State {
    O,
    X,
    EMPTY
}

public class MainActivity extends AppCompatActivity {

    private int counter;
    private State playerTurn;
    private State[] gameState = {State.EMPTY, State.EMPTY, State.EMPTY,
                                 State.EMPTY, State.EMPTY, State.EMPTY,
                                 State.EMPTY, State.EMPTY, State.EMPTY};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView gameStatus = findViewById(R.id.gameStatus);
        this.playerTurn = State.X;
        setTurnImage(gameStatus);
        this.counter = 0;


    }
    private void changePlayer()
    {
        if(this.playerTurn == State.O)
        {
            this.playerTurn = State.X;
        }
        else
        {
            this.playerTurn = State.O;
        }
    }
    private void setTurnImage(ImageView turnImage)
    {
        if(this.playerTurn == State.O)
        {
            turnImage.setImageResource(R.mipmap.ic_o_play_foreground);
        }
        else
        {
            turnImage.setImageResource(R.mipmap.ic_x_play_foreground);
        }
    }
    private void setImg(ImageView imgView)
    {
        if(this.playerTurn == State.O)
        {
            imgView.setImageResource(R.mipmap.ic_o_foreground);
        }
        else
        {
            imgView.setImageResource(R.mipmap.ic_x_foreground);
        }
    }
    public void playAgain(View v)
    {
        ImageView gameStatus = findViewById(R.id.gameStatus);
        Button playAgainButton = (Button)  findViewById(R.id.button);
        for (int i = 0; i < this.gameState.length; i++) {
            this.gameState[i] = State.EMPTY;
        }

        this.playerTurn = State.X;
        this.counter = 0;
        setTurnImage(gameStatus);
        ((ImageView) findViewById(R.id.cell0)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell1)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell2)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell3)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell4)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell5)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell6)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell7)).setImageResource(R.mipmap.ic_empty_background);
        ((ImageView) findViewById(R.id.cell8)).setImageResource(R.mipmap.ic_empty_background);
        playAgainButton.setVisibility(View.INVISIBLE);
    }
    public void playerPush(View v) {

        ImageView imgView = (ImageView) v;
        ImageView gameStatus = findViewById(R.id.gameStatus);
        Button playAgainButton = (Button)  findViewById(R.id.button);
        int chosenCell = Integer.parseInt(imgView.getTag().toString());
        if(this.gameState[chosenCell] == State.EMPTY)
        {
            this.counter++;
            this.gameState[chosenCell] = this.playerTurn;
            setImg(imgView);
            changePlayer();
            setTurnImage(gameStatus);

        }

        if(this.counter == this.gameState.length)
        {
            endGame();
        }
    }
    public void endGame()
    {
        ImageView gameStatus = findViewById(R.id.gameStatus);
        Button playAgainButton = (Button)  findViewById(R.id.button);
        playAgainButton.setVisibility(View.VISIBLE);
        if(this.counter == this.gameState.length)
        {
            gameStatus.setImageResource(R.mipmap.ic_no_win_foreground);
        }
        else
        {

        }
    }
}
