package juliangarcia.twoadelivery;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static android.view.View.OnClickListener;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class LevelTemplateActivity extends FragmentActivity implements LevelBinaryButtonsFragment.BinaryButtonsListener, LevelHexButtonsFragment.HexButtonsListener {
    //variables
    private Globals g;
    private String correctAnswer, leveltype, levelnum, base;
    private int answeredCorrectly, total;
    private Intent intent;

    //UI variables
    private EditText editText_userInput;
    private Button button_levelsSubmit, button_clearInput, button_pause;
    private TextView textView_AddressDec, text_levelsCountdown, text_levelsNum;

    //hard coded variables
    private long timeRemaining = -1;
    private boolean onPause = false;
    // Change 30000 to 1200 (shorter time for testing purposes)
    private CounterClass timer = new CounterClass(30000, 100);

    //Persistent data variables
    public final static String EXTRA_MESSAGE = "juliangarcia.twoadelivery.MESSAGE";
    public SharedPreferences savedCurrency;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_template);

        g = (Globals) getApplication();
        answeredCorrectly = 0;
        total = 0;

        if (savedInstanceState == null) {
            intent = getIntent();
            leveltype = intent.getStringExtra("leveltype");
            base = intent.getStringExtra("base");

            setUIComponents();
            setUpFragments();
            setAnswer();
        }
    }

    public void onStop() {
        super.onStop();
        timer.cancel();
    }

    private void setUIComponents() {
        editText_userInput = (EditText) findViewById(R.id.editText_userInput);
        textView_AddressDec = (TextView) findViewById(R.id.textView_AddressDec);
        text_levelsCountdown = (TextView) findViewById(R.id.text_levelsCountdown);
        text_levelsNum = (TextView) findViewById(R.id.text_levelNum);
        //Buttons
        button_levelsSubmit = (Button) findViewById(R.id.button_levelsSubmit);
        button_levelsSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onPause) {
                    submitAnswer(editText_userInput.getText().toString());
                }
            }
        });
        button_clearInput = (Button) findViewById(R.id.button_clearInput);
        button_clearInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onPause) {
                    editText_userInput.setText("");
                }
            }
        });
        button_pause = (Button) findViewById(R.id.button_pause);
        button_pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onPause) {
                    onPause = true;
                    timeRemaining = timer.millis;
                    timer.cancel();
                    editText_userInput.setVisibility(View.INVISIBLE);
                    textView_AddressDec.setVisibility(View.INVISIBLE);
                    text_levelsCountdown.setText("PAUSED!");
                } else {
                    onPause = false;
                    timer = new CounterClass(timeRemaining, 100);
                    timer.start();
                    timeRemaining = -1;

                    editText_userInput.setVisibility(View.VISIBLE);
                    textView_AddressDec.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setUpFragments() {
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // add
        FragmentTransaction ft = fm.beginTransaction();
        //set level title text
        if (intent.getStringExtra("leveltype").equals("Zen") || intent.getStringExtra("leveltype").equals("Tutorial")) {
            text_levelsNum.setText(leveltype);
            switch (base) {
                case ("Binary"):
                    ft.add(R.id.user_input_buttons_container, new LevelBinaryButtonsFragment());
                    break;
                case ("Hex"):
                    ft.add(R.id.user_input_buttons_container, new LevelHexButtonsFragment());
                    break;
            }

            if (intent.getStringExtra("leveltype").equals("Tutorial")) {
                final RelativeLayout ins_tut = (RelativeLayout) findViewById(R.id.ins_tut_level);
                ins_tut.setVisibility(View.VISIBLE);

                ins_tut.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        ins_tut.setVisibility(View.INVISIBLE);
                    }
                });
            }
        } else {//if not zen or tutorial, it must be a regular level
            levelnum = intent.getStringExtra("levelnum");
            //in case I add more bases, I used a switch case here for easy implementation
            switch (base) {
                case ("Binary"):
                    text_levelsNum.setText(Integer.toBinaryString(Integer.parseInt(levelnum)));
                    ft.add(R.id.user_input_buttons_container, new LevelBinaryButtonsFragment());
                    break;
                case ("Hex"):
                    text_levelsNum.setText(Integer.toHexString(Integer.parseInt(levelnum)));
                    ft.add(R.id.user_input_buttons_container, new LevelHexButtonsFragment());
                    break;
            }
            timer.start();
        }
        ft.commit();
    }

    private void submitAnswer(String submission) {
        Toast toast;
        if(checkSubmission()) {
            g.addHydrocoins(5);
            answeredCorrectly++;
            toast = Toast.makeText(getApplicationContext(), "CORRECT!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            setAnswer();
        }
        else {
            toast = Toast.makeText(getApplicationContext(), "..incorrect..", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            editText_userInput.setText("");
        }
        toast.show();
        editText_userInput.setText("");
    }

    private boolean checkSubmission() {
        //allows the user to input 0s in front of the answer
        int num = Integer.parseInt(editText_userInput.getText().toString());
        return Integer.toString(num).equals(correctAnswer);
    }

    @Override
    public void onButtonClick(int value) {
        editText_userInput.append("" + value);
    }

    private void setAnswer() {
        total++;//increment the number of posed conversions

        //Using a custom random number generator
        RandomAddressChanger rand = new RandomAddressChanger();
        //set random address
        String correct = rand.loadAddress();
        textView_AddressDec.setText(correct);
        //convert address to proper base
        switch (base) {
            case ("Binary"):
                correctAnswer = Integer.toBinaryString(Integer.parseInt(correct));
                break;
            case ("Hex"):
                correctAnswer = Integer.toHexString(Integer.parseInt(correct));
                break;
        }

    }

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

        public long millis;

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
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            System.out.print(hms);
            text_levelsCountdown.setText(hms);
        }

        /**
         * Callback fired when the time is up.
         */
        @Override
        public void onFinish() {
            g.addTimesPlayed();
            if (leveltype.equals("Zen") || leveltype.equals("Tutorial")) {
                finish();
            } else {
                createCustomDialog(answeredCorrectly, total);
            }
        }
    }

    private void createCustomDialog(int correct, int total) {
        //TODO: Using the variables "correct" and "total", determine scoring system for players.

        // Create custom dialog object
        final Dialog dialog = new Dialog(this);
        // hide to default title for Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // inflate the layout dialog_layout.xml and set it as contentView
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_level_finish, null, false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        // Retrieve views from the inflated dialog layout and update their values
        TextView txtTitle = (TextView) dialog.findViewById(R.id.txt_dialog_title);
        txtTitle.setText("Level " + text_levelsNum.getText() + " Complete");

        TextView txtMessage = (TextView) dialog.findViewById(R.id.txt_dialog_message);
        txtMessage.setText(correct + " out of " + total + " correct.");

        //Create the ImageView to hold toe User's score
        ImageView imgStar1 = (ImageView) dialog.findViewById(R.id.img_dialog_star1);
        ImageView imgStar2 = (ImageView) dialog.findViewById(R.id.img_dialog_star2);
        ImageView imgStar3 = (ImageView) dialog.findViewById(R.id.img_dialog_star3);

        int sr = starRating(correct, total);
        switch (sr) {
            case 3:
                imgStar1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_full));
                imgStar2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_full));
                imgStar3.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_full));
                break;
            case 2:
                imgStar1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_full));
                imgStar2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_full));
                imgStar3.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_empty));
                break;
            case 1:
                imgStar1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_full));
                imgStar2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_empty));
                imgStar3.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_empty));
                break;
            case 0:
                imgStar1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_empty));
                imgStar2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_empty));
                imgStar3.setImageDrawable(getResources().getDrawable(R.mipmap.ic_star_empty));
                break;
        }

        if (g.getDrivers() == 5) {
            g.resetLastLostLifeTime();//the time should only reset
        }
        g.minusDriver();

        g.saveStars(base, levelnum, sr);

        Button btnStart = (Button) dialog.findViewById(R.id.button_to_menu);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save all data
                g.saveData();
                // Dismiss the dialog
                dialog.dismiss();

                finish();
            }
        });

        // Display the dialog
        dialog.show();
    }

    private int starRating(int correct, int total) {
        if (correct < 1) {
            return 0;
        }

        double percentage = (double) correct / total;
        //TODO: Implement difficulty here.
        // Easy:            percentage + 0.15
        // Intermediate:    percentage + 0.05
        // Hard:            percentage
        // Insane:          percentage - 0.1
        percentage = percentage + 0.15;
        if (percentage > 0.8) {
            return 3;
        } else if (percentage > 0.5) {
            return 2;
        } else if (percentage > 0.2) {
            return 1;
        } else {
            return 0;
        }
    }
}
