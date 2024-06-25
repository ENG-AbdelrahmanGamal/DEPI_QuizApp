package com.hanynemr.g4dquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView questionText,questionCount;
    Spinner answerText;
    private static final String TAG = "MainActivity";
    Button startButton,nextButton;
    String[] countries={"egypt","usa","france","uk"};
    String[] cities={"cairo","ws","paris","london"};


    ArrayList<String> items=new ArrayList<>();
    byte score;
    byte index;
    byte numberClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionText=findViewById(R.id.questionText);
        questionCount=findViewById(R.id.questionCount);
        answerText=findViewById(R.id.answerText);
        startButton=findViewById(R.id.startButton);
        nextButton=findViewById(R.id.nextButton);


        
    }

    public void start(View view) {
        numberClick++;
        index=0;
        score=0;
        questionText.setText("what is the capital of "+countries[index]);
        questionCount.setText("Question 1 of "+countries.length);
        nextButton.setEnabled(true);
        answerText.setEnabled(true);

        items.clear();
        Collections.addAll(items,"please select",
                "cairo",
                "damascus",
                "baghdad",
                "ws",
                "toronto",
                "london",
                "khartoum",
                "paris");

        //items->adapter->spinner
        ArrayAdapter adapter=new ArrayAdapter(this
                , android.R.layout.simple_list_item_1,items);
        answerText.setAdapter(adapter);

    }

    public void next(View view) {

        String answer=answerText.getSelectedItem().toString();
        if (answer.equalsIgnoreCase("please select")){
            Toast.makeText(this, "please answer", Toast.LENGTH_SHORT).show();
            return;
        }

        if (answer.equalsIgnoreCase(cities[index])) {
            score++;
            items.remove(answer);
        }
        if(numberClick>4 && numberClick>index){
            Toast.makeText(this, "You retrying many Time !!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "next:  number of trying is"+numberClick);
            finish();
        }


        index++;
        if(index<countries.length){
            questionText.setText("what is the capital of "+countries[index]);
            questionCount.setText("Question "+(index+1)+" of "+countries.length);
        }else{
            Toast.makeText(this, "score="+score, Toast.LENGTH_LONG).show();
            nextButton.setEnabled(false);
        }

        answerText.setSelection(0);


    }
}