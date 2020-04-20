package com.example.truecitiizenquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.security.cert.CertPathBuilderSpi;

/*sample code

 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button falsebutton;
    Button truebutton;
    TextView questiontextview;
     ImageButton next_button;
    ImageButton back_button;
    Button timerbutton;
    TextView currentscore;
    int score=0;
    int attempts=0;
    int currentquestionindex=0;
     private  Question[] questions=new Question[]{
      new Question(R.string.question_declaration,true),
      new Question(R.string.question_amendmends,false),
      new Question(R.string.question_states,false),
      new Question(R.string.question_mostdenselypopulated,false),
      new Question(R.string.question_rajyasabha,true),
      new Question(R.string.question_union,true),
      new Question(R.string.question_primeminister,false),
      new Question(R.string.question_richest,true),
      new Question(R.string.question_population,false),
      new Question(R.string.question_cm,true),
      new Question(R.string.question_leastdenselypopulated,true),
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questiontextview=findViewById(R.id.textview);
        falsebutton=findViewById(R.id.falsebutton);
        truebutton=findViewById(R.id.truebutton);
        next_button=findViewById(R.id.nextbutton);
        back_button=findViewById(R.id.backbutton);
        timerbutton=findViewById(R.id.timerbutton);
        currentscore=findViewById(R.id.score);
        /*falsebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"False Button Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        truebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"True Button Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        if(truebutton.isPressed())
                {
                    if(truebutton.getText().toString().equalsIgnoreCase(questions[currentquestionindex].isAnswertrue()))
                        Toast.makeText(getApplicationContext(),"Rigth Answer",Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
                }

          if(falsebutton.isPressed())
                {
                if(falsebutton.getText().toString().equals(questions[currentquestionindex].isAnswertrue()))
                    Toast.makeText(getApplicationContext(),"Rigth Answer",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
                }
                */
        falsebutton.setOnClickListener(this);//register our event to the on click listener
        truebutton.setOnClickListener(this);
        next_button.setOnClickListener(this);
        back_button.setOnClickListener(this);
        new CountDownTimer(30000,1000)
        {
            @Override
            public void onTick(long l) {
             timerbutton.setText("seconds remaining :"+Integer.toString((int)l/1000));
            }

            @Override
            public void onFinish() {
             Toast.makeText(getApplicationContext(),"Time Over",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.falsebutton:
               // Toast.makeText(getApplicationContext(),"False Button Clicked",Toast.LENGTH_SHORT).show();
                checkanswer(false);
                break;
            case R.id.truebutton:
              //  Toast.makeText(getApplicationContext(),"True Button Clicked",Toast.LENGTH_SHORT).show();
                Log.i("True Button","Clicked");
                checkanswer(true);
                break;
            case R.id.nextbutton:
               // Toast.makeText(getApplicationContext(),"Next Button Clicked",Toast.LENGTH_SHORT).show();
                currentquestionindex++;
                if(currentquestionindex==questions.length) currentquestionindex=0;
                updatequestionnext();
                break;
            case R.id.backbutton:
                //Toast.makeText(getApplicationContext(),"back Button Clicked",Toast.LENGTH_SHORT).show();
                currentquestionindex--;
                if(currentquestionindex==-1) currentquestionindex=0;
                updatequesback();
                Log.i("Current index",Integer.toString(currentquestionindex));
            default:
                Log.i("Sorry ","Next time");
        }
    }
    public void updatequestionnext()
    {
        questiontextview.setText(questions[currentquestionindex].getAnsid());
        Log.i("Current index",Integer.toString(currentquestionindex));
    }
    public void updatequesback()
    {
        questiontextview.setText(questions[currentquestionindex].getAnsid());
        Log.i("Current index",Integer.toString(currentquestionindex));
    }

    private void checkanswer(boolean usercorrect)
    {
        boolean isanswertrue=questions[currentquestionindex].isAnswertrue();
        int toastmsgid=0;
        if(usercorrect==isanswertrue){ toastmsgid=R.string.correct_answer; score++; attempts++;}
        else {toastmsgid=R.string.wrong_answer; attempts++;};
        currentscore.setText("Score : "+Integer.toString(score)+" / "+ Integer.toString(attempts));
        Toast.makeText(getApplicationContext(),toastmsgid, Toast.LENGTH_SHORT).show();
    }
}
