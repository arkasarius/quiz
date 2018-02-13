package com.example.roger.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class quizactivity extends AppCompatActivity {

    private final int[] ids_buttons={
            R.id.answer1,
            R.id.answer2,
            R.id.answer3,
            R.id.answer4
    };

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

    }
}
