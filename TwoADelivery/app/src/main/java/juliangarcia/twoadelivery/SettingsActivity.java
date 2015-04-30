package juliangarcia.twoadelivery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class SettingsActivity extends Activity implements View.OnClickListener {
    //UI variables
    private Button login, binaryTut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createButtonListeners();
    }

    public void createButtonListeners() {
        login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(this);

        binaryTut = (Button) findViewById(R.id.button_binary_tut);
        binaryTut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                break;
            case R.id.button_binary_tut:
                createBinaryTutDialog();
                break;
        }
    }

    private void createBinaryTutDialog() {
        final Dialog dialog = new Dialog(this);
        // hide to default title for Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // inflate the layout dialog_layout.xml and set it as contentView
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_binary_tutorial, null, false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        TextView whatis = (TextView) dialog.findViewById(R.id.textView_binary_tut_what);
        whatis.setText("What is Binary?\n\n" +
                "Binary is a number system that only uses 0s and 1s, which makes " +
                "it base 2, to represent data. Most of us grew up learning how to " +
                "count using the decimal system, which uses 0 through 9, which makes " +
                "it base 10 to represent data. For example, the decimal number '3' is " +
                "the same as the binary number '10'.");

        TextView why = (TextView) dialog.findViewById(R.id.textView_binary_tut_why);
        why.setText("Why should someone learn binary?\n\n" +
                "Computers are famous in their use of binary but they aren't the only things " +
                "who can benefit from it. By learning to convert between base 10 and base 2, " +
                "you will strengthen your quick math skills and learn about exponents.");

        TextView how = (TextView) dialog.findViewById(R.id.textView_binary_tut_how);
        how.setText("How to:\n\n" +
                "Let's examine what makes up a multi-digit number. In decimal, we count " +
                "'0, 1, 2, ..., 8, 9' in every digit. Each digit then corresponds to a power of 10:\n" +
                "     The 1st digit is a digit*10^0 = 1\n" +
                "     The 2nd digit is a digit*10^1 = 10\n" +
                "     The 3rd digit is a digit*10^2 = 100, and so on.\n" +
                "We then use those powers to create numbers. For example:\n" +
                "     256 = 2*10^2 + 5*10^1 + 6*10^0\n" +
                "             = 2*100  + 5*10   + 6*1\n" +
                "The process is not so different when counting in binary:\n" +
                "     The 1st digit is a digit*2^0 = 1\n" +
                "     The 2nd digit is a digit*2^1 = 2\n" +
                "     The 3rd digit is a digit*2^2 = 4\n" +
                "     The 4th digit is a digit*2^3 = 8\n" +
                "     The 5th digit is a digit*2^4 = 16, and so on.\n" +
                "So, let's do a simple conversion from the decimal number '5' to its binary counterpart. " +
                "From our decimal example, we know that a number is the sum of its digits multiplied by " +
                "its base to a power depending on its position, so\n" +
                "     5 = ?*2^3 + ?*2^2 + ?*2^1 + ?*2^0\n\n" +
                "Is 1*2^3 greater than, less than, or equal to 5? 8 is greater than 5 so a 0 should go in the 4th digit.\n" +
                "     5 = 0*2^3 + ?*2^2 + ?*2^1 + ?*2^0\n\n" +
                "Is 1*2^2 greater than, less than, or equal to 5? 4 is less than 5 so a 1 should go in the 3rd digit.\n" +
                "     5 = 0*2^3 + 1*2^2 + ?*2^1 + ?*2^0\n\n" +
                "Is 1*2^1 greater than, less than, or equal to 1(the remainder after subtracting the " +
                "4 from 5)? 2 is greater than 1 so a 0 should go in the 2th digit.\n" +
                "     5 = 0*2^3 + 1*2^2 + 0*2^1 + ?*2^0\n\n" +
                "Is 1*2^0 greater than, less than, or equal to 1? 1 is equal to 1 so a 1 should go " +
                "in the 1st digit. Now that we have all the digits,\n" +
                "     5 = 0*2^3 + 1*2^2 + 0*2^1 + 1*2^0\n" +
                "         = 0*8 + 1*4 + 0*2 + 1*1\n" +
                "         = 0101 in binary.\n" +
                "Did you see what happened there? The numbers you multiplied the bases by represent " +
                "each digit in the completed binary number.");

        TextView congrats = (TextView) dialog.findViewById(R.id.textView_binary_tut_congrats);
        congrats.setText("Congratulations! You've converted your first binary number!");

        Button btnDismiss = (Button) dialog.findViewById(R.id.btnDismiss);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the dialog
                dialog.dismiss();
            }
        });

        // Display the dialog
        dialog.show();
    }
}
