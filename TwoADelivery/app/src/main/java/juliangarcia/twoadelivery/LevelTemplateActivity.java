package juliangarcia.twoadelivery;

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

import static android.view.View.OnClickListener;


public class LevelTemplateActivity extends FragmentActivity implements LevelBinaryButtonsFragment.BinaryButtonsListener {

    private EditText editText_userInput;
    private Button button_levelsSubmit;
    private TextView textView_AddressDec;
    private String correctAnswer;
    private LevelBinaryButtonsFragment binaryButtons;
    //private LevelHexButtonsFragment hexButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_template);

        editText_userInput = (EditText) findViewById(R.id.editText_userInput);

        button_levelsSubmit = (Button) findViewById(R.id.button_levelsSubmit);
        button_levelsSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(editText_userInput.getText().toString());
            }
        });

        textView_AddressDec = (TextView) findViewById(R.id.textView_AddressDec);

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
}
