package com.example.roger.quiz;

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
    private int solution;
    private RadioGroup answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizactivity);
        TextView question_text = findViewById(R.id.question_text);
        question_text.setText(R.string.question_content);
        String[] answer_text = getResources().getStringArray(R.array.answer_text);
        for(int i =0;i<answer_text.length;i++){
            RadioButton rb = findViewById(ids_buttons[i]);
            rb.setText(answer_text[i]);
        }
        Button btn_chek =findViewById(R.id.btn_check);
        btn_chek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
        solution=getResources().getInteger(R.integer.solution);
        answers=findViewById(R.id.answers);
    }

    private void checkAnswer() {
        int id_check = answers.getCheckedRadioButtonId();
        int quin =-1;
        for (int i = 0; i <ids_buttons.length ; i++) {
            if (id_check==ids_buttons[i]){
                quin=i+1;
            }
            if(quin!=-1){
                if(quin==solution){
                    Toast.makeText(this, "Correcte!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "incorrecte ...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
