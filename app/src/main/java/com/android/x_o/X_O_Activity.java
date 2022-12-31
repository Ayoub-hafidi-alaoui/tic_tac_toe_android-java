package com.android.x_o;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class X_O_Activity extends AppCompatActivity {
    TextView player1_score;
    TextView player2_score;
    ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xo);
        for (int i = 0; i < 9; i++) {
            board.add("");
        }
        player1_score = findViewById(R.id.score_player1);
        player2_score = findViewById(R.id.score_player2);
        root = findViewById(R.id.rootElement);
    }

    int counter = 0;
    List<String> board = new ArrayList<String>();
    int score1 = 0;
    int score2 = 0;

    public void onButtonClick(View view) {
        Log.e("click event", "button clicked");
        Button btn = (Button) view;
        int buttonIndex = Integer.parseInt(btn.getTag().toString());
        String symbol = counter % 2 == 0 ? "O" : "X";
        Log.e("content of the button", String.valueOf(!btn.getText().toString().isEmpty()));
        if (!btn.getText().toString().isEmpty()) {
            return;
        }
        if (counter % 2 == 0) {
            btn.setText("O");
        } else {
            btn.setText("X");
        }
        board.set(buttonIndex, symbol);
        Log.e("board", board.toString());
        if (checkWinner(symbol)) {
            if (symbol.equals("O")) {
                score1++;
                player1_score.setText("" + score1);
            } else {
                score2++;
                player2_score.setText("" + score2);
            }
            resetBoard();
            counter = 0;
            Log.e("board after clearing", board.toString());
            return;
        }
        counter++;
    }

    public boolean checkWinner(String symbol) {
        if (board.get(0).equals(symbol) && board.get(1).equals(symbol) && board.get(2).equals(symbol)) {
            return true;
        } else if (board.get(3).equals(symbol) && board.get(4).equals(symbol) && board.get(5).equals(symbol)) {
            return true;
        } else if (board.get(6).equals(symbol) && board.get(7).equals(symbol) && board.get(8).equals(symbol)) {
            return true;
        } else if (board.get(0).equals(symbol) && board.get(4).equals(symbol) && board.get(8).equals(symbol)) {
            return true;
        } else if (board.get(2).equals(symbol) && board.get(4).equals(symbol) && board.get(6).equals(symbol)) {
            return true;
        } else {
            return false;
        }
    }

    public void resetBoard() {
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i) instanceof Button) {
                ((Button) root.getChildAt(i)).setText(null);
            }
        }
        for (int i = 0; i < 9; i++) {
            board.set(i, "");
        }
    }

}