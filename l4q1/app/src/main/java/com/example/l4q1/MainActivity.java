package com.example.l4q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4;
    TextView qtview;
    int index = 0;
    int correct = 0;
    private Question[] bank = new Question[]{
            new Question(R.string.q1, 2),
            new Question(R.string.q2,0),
            new Question(R.string.q3,1)

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.b1);
        b2= findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        qtview = findViewById(R.id.textView);

        b1.setOnClickListener((View.OnClickListener) this);
        b2.setOnClickListener((View.OnClickListener) this);

        b3.setOnClickListener((View.OnClickListener) this);

        b4.setOnClickListener((View.OnClickListener) this);
         if(index == 0) {
             qtview.setText(bank[0].getQuestion());
             b1.setText("1928");
             b2.setText("1946");
             b3.setText("1975");
             b4.setText("2000");
         }



    }
   public void onClick(View v){
       System.out.println("Clicked: "+v.getId());

           if (bank[index].checkAnswerisCorrect(v.getId()))
           //correct answer
           {
               correct += 1;

           }
           System.out.println("Correct:" + correct);
           index++;
           if(index<=2)
           updateQuestions();
           else
           {
               Intent intent = new Intent(MainActivity.this,result.class);
                intent.putExtra("correct",correct);
                startActivity(intent);

           }

       }







   public void updateQuestions(){
       switch(index)
       {
           case 0: {
               qtview.setText(bank[0].getQuestion());
               b1.setText("1928");
               b2.setText("1946");
               b3.setText("1975");
               b4.setText("2000");
               break;
           }

           case 1: {
               qtview.setText(bank[1].getQuestion());
               b1.setText("Mark Zuckerberg");
               b2.setText("Tim Berners Lee");
               b3.setText("Sundar Pichai");
               b4.setText("Richard Henricks");
               break;
           }
           case 2: {
               qtview.setText(bank[2].getQuestion());
               b1.setText("Google");
               b2.setText("Facebook");
               b3.setText("Apple");
               b4.setText("Microsoft");
               break;
           }


       }

   }
}