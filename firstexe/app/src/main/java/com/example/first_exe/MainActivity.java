package com.example.first_exe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

enum State {
    O,
    X,
    EMPTY
}

public class MainActivity extends AppCompatActivity {

    private int counter;
    private boolean isPlaying = true;
    private State playerTurn;
    private State[] gameState = {State.EMPTY, State.EMPTY, State.EMPTY,
                                 State.EMPTY, State.EMPTY, State.EMPTY,
                                 State.EMPTY, State.EMPTY, State.EMPTY};
    private int[][] winningStates = { {0, 1, 2, 6}, {3, 4, 5, 7}, {6, 7, 8, 8},         // Rows win states
                                      {0, 3, 6, 3}, {1, 4, 7, 4}, {2, 5, 8, 5},         // Columns win states
                                      {0, 4, 8, 1}, {2, 4, 6, 2} };                     // Diagonal win


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
        ImageView winningLine = findViewById(R.id.mark);

        for (int i = 0; i < this.gameState.length; i++) {
            this.gameState[i] = State.EMPTY;
        }

        this.isPlaying = true;
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
        winningLine.setImageResource(R.mipmap.ic_empty_background);
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    public void playerPush(View v) {

        ImageView imgView = (ImageView) v;
        ImageView gameStatus = findViewById(R.id.gameStatus);
        int chosenCell = Integer.parseInt(imgView.getTag().toString());

        if (isPlaying) {
            if (this.gameState[chosenCell] == State.EMPTY) {
                this.counter++;
                this.gameState[chosenCell] = this.playerTurn;
                setImg(imgView);
                changePlayer();
                setTurnImage(gameStatus);
            }

            if (checkWinner() || (this.counter == this.gameState.length)) {
                endGame();
            }
        }
    }

    private boolean checkWinner()
    {
        for (int[] winState : winningStates ) {
            if ( (this.gameState[winState[0]] == this.gameState[winState[1]]) &&
                 (this.gameState[winState[0]] == this.gameState[winState[2]]) &&
                 (this.gameState[winState[0]] != State.EMPTY) ) {
                ImageView winningLine = findViewById(R.id.mark);
                winningLine.setImageResource(getMarkImg(winState[3]));
                setWinningBoard(this.gameState[winState[0]]);
                return true;
            }
        }
        return false;
    }

    private void setWinningBoard(State winningState) {
        ImageView gameStatus = findViewById(R.id.gameStatus);

        if (winningState == State.X) {
            gameStatus.setImageResource(R.mipmap.ic_x_win_foreground);
        } else {
            gameStatus.setImageResource(R.mipmap.ic_o_win_foreground);
        }
    }

    private int getMarkImg(int winningCode) {
        switch (winningCode) {
            case 1:
                return R.mipmap.ic_mark_1_foreground;
            case 2:
                return R.mipmap.ic_mark_2_foreground;
            case 3:
                return R.mipmap.ic_mark_3_foreground;
            case 4:
                return R.mipmap.ic_mark_4_foreground;
            case 5:
                return R.mipmap.ic_mark_5_foreground;
            case 6:
                return R.mipmap.ic_mark_6_foreground;
            case 7:
                return R.mipmap.ic_mark_7_foreground;
            case 8:
                return R.mipmap.ic_mark_8_foreground;
            default:
                return R.mipmap.ic_empty_background;
        }
    }

    public void endGame()
    {
        ImageView gameStatus = findViewById(R.id.gameStatus);
        Button playAgainButton = (Button)  findViewById(R.id.button);
        playAgainButton.setVisibility(View.VISIBLE);

        this.isPlaying = false;
        if(this.counter == this.gameState.length)
        {
            gameStatus.setImageResource(R.mipmap.ic_no_win_foreground);
        }
    }
}
