package juliangarcia.twoadelivery;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.view.View.OnClickListener;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class LevelTemplateActivity extends FragmentActivity implements LevelBinaryButtonsFragment.BinaryButtonsListener {

    private EditText editText_userInput;
    private Button button_levelsSubmit,button_clearInput;
    private TextView textView_AddressDec, text_levelsCountdown, text_levelsNum;
    private String correctAnswer, intentString;
    private LevelBinaryButtonsFragment binaryButtons;
    //private LevelHexButtonsFragment hexButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_template);

        Intent intent = getIntent();
        intentString = intent.getStringExtra(LevelsMenuActivity.EXTRA_MESSAGE);
        text_levelsNum = (TextView) findViewById(R.id.text_levelNum);
        text_levelsNum.setText(intentString);

        editText_userInput = (EditText) findViewById(R.id.editText_userInput);

        textView_AddressDec = (TextView) findViewById(R.id.textView_AddressDec);
        text_levelsCountdown = (TextView) findViewById(R.id.text_levelsCountdown);

        if(!intentString.equals("zen")) {
            //set the original text of the countdown clock
            text_levelsCountdown.setText("00:03:00");
            //create an instance of the class we created
            final CounterClass timer = new CounterClass(180000, 1000);
            timer.start();
        }

        button_levelsSubmit = (Button) findViewById(R.id.button_levelsSubmit);
        button_levelsSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(editText_userInput.getText().toString());
            }
        });

        button_clearInput = (Button) findViewById(R.id.button_clearInput);
        button_clearInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_userInput.setText("");
            }
        });

        /**
         * ADDING FRAGMENT
         * TODO Implement changing button fragment
         */
        binaryButtons = new LevelBinaryButtonsFragment();
        binaryButtons.setArguments(getIntent().getExtras());

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();

        transaction.add(R.id.LevelTemplate, binaryButtons);
        transaction.commit();

        //hexButtons = new LevelHexButtonsFragment();

        setAnswer();
    }

    private void submitAnswer(String submission) {
        if(checkSubmission()) {
            editText_userInput.setText("CORRECT!");
            setAnswer();
        }
        else {
            editText_userInput.setText("..incorrect..");
        }
    }

    private boolean checkSubmission() {
        return editText_userInput.getText().toString().equals(correctAnswer);
    }

    @Override
    public void onButtonClick(int value) {
        editText_userInput.append("" + value);
    }

    private void setAnswer() {
        RandomAddressChanger rand = new RandomAddressChanger();
        String correct = rand.loadAddress();
        textView_AddressDec.setText(correct);
        correctAnswer = Integer.toBinaryString(Integer.parseInt(correct));
    }

    //Part of the tutorial
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    /**
     * Makes a custom local instance of a CountDownTimer.
     * Implements the methods from CountDownTimer (onTick and onFinish)
     */
    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //Part of the tutorial
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")
        /**
         * Callback fired on regular interval.
         *
         * @param millisUntilFinished The amount of time until finished.
         */
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            System.out.print(hms);
            text_levelsCountdown.setText(hms);
        }

        /**
         * Callback fired when the time is up.
         */
        @Override
        public void onFinish() {
            text_levelsCountdown.setText("Completed.");
        }
    }
}
