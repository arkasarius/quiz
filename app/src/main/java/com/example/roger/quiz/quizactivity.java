package com.example.roger.quiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class quizactivity extends AppCompatActivity {

    private final int[] ids_buttons={
            R.id.answer1,
            R.id.answer2,
            R.id.answer3,
            R.id.answer4
    };

    private RadioGroup rgroup;//shift + f6 --

    private String[] questions;
    private String[][] answers;
    private int[] solutions;
    private int[] responses;// the answers from the user;
    private int curr;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizactivity);
        curr=0;
        loadQuestions();
        showQuestion();

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkAnswer();
                nextQuestions();
            }
        });

        rgroup =findViewById(R.id.answers);
    }

    private void nextQuestions() {
        responses[curr]=getResponse();
        //si estic a la ultima pregunta donar els resultats
        if(curr==questions.length -1){
            giveResults();
        }else{
            curr++;
            if(curr==questions.length -1){
                btn_next.setText(R.string.check);
            }
            //si veiem que es la ultima hem de canviar el boto
            showQuestion();
        }
    }

    private void giveResults() {
        int good =0;
        int bad = 0;
        for (int i = 0; i < responses.length; i++) {
            if(responses[i]==solutions[i]){
                good++;
            }else{
                bad++;
            }
        }
        String msg = String.format("Correctes: %d, Incorrectes %d",good,bad);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void loadQuestions() {
        Resources res = getResources();
        questions = res.getStringArray(R.array.questions);
        solutions = res.getIntArray(R.array.solutions);
        String[] answ = res.getStringArray(R.array.answers);
        answers = new String[answ.length][];
        for (int i = 0; i < answ.length; i++) {
            answers[i]= answ[i].split(";");
        }
        responses = new int[answ.length]; // el array esta ple de 0
    }

    private void showQuestion() {
        TextView question_text = findViewById(R.id.question_text);
        question_text.setText(questions[curr]);
        for(int i =0;i<answers[curr].length;i++){
            RadioButton rb = findViewById(ids_buttons[i]);
            rb.setText(answers[curr][i]);
        }
    }

    private void checkAnswer() {
        int quin = getResponse();
        if(quin!=-1){
            if(quin==solutions[curr]){
                Toast.makeText(this, "Correcte!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "incorrecte ...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int getResponse() {
        int id_check = rgroup.getCheckedRadioButtonId();
        int quin =-1;
        for (int i = 0; i <ids_buttons.length ; i++) {
            if (id_check==ids_buttons[i]){
                quin=i+1;
            }
        }
        return quin;
    }
}
