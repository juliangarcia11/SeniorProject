package juliangarcia.twoadelivery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class LevelsMenuActivity extends Activity implements View.OnClickListener {
    //Variables
    private Globals g;
    private String intentString;

    //UI Variables
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    private Button tutorial, zen, stats;
    private TextView levelTitle, hydrocoins, drivers;

    //Persistent data variables
    public final static String EXTRA_MESSAGE = "juliangarcia.twoadelivery.MESSAGE";
    public SharedPreferences savedCurrency;
    public SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_menu);


        g = (Globals) getApplication();
        g.setData();

        Intent intent = getIntent();
        intentString = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        levelTitle = (TextView) findViewById(R.id.levels_title);
        levelTitle.setText(intentString + " Levels");

        hydrocoins = (TextView) findViewById(R.id.menu_text_hydrocoin);
        hydrocoins.setText(Integer.toString(g.getHydrocoins()));

        drivers = (TextView) findViewById(R.id.menu_text_drivers);
        drivers.setText(Integer.toString(g.getDrivers()));

        createButtons();

        if (!g.getSeenBinaryTut()) {
            createBinaryTutDialog();
        }
    }

    public void createButtons() {
        zen = (Button) findViewById(R.id.button_level_zen);
        zen.setOnClickListener(this);
        zen.setText(R.string.level_zen);

        stats = (Button) findViewById(R.id.button_level_stats);
        stats.setOnClickListener(this);
        stats.setText(R.string.button_level_stats);

        tutorial = (Button) findViewById(R.id.button_level_00000);
        tutorial.setOnClickListener(this);
        tutorial.setText(R.string.level_tutorial);

        buttonList.add((Button) findViewById(R.id.button_level_00001));
        buttonList.add((Button) findViewById(R.id.button_level_00010));
        buttonList.add((Button) findViewById(R.id.button_level_00011));
        buttonList.add((Button) findViewById(R.id.button_level_00100));
        buttonList.add((Button) findViewById(R.id.button_level_00101));
        buttonList.add((Button) findViewById(R.id.button_level_00110));
        buttonList.add((Button) findViewById(R.id.button_level_00111));
        buttonList.add((Button) findViewById(R.id.button_level_01000));
        buttonList.add((Button) findViewById(R.id.button_level_01001));
        buttonList.add((Button) findViewById(R.id.button_level_01010));
        buttonList.add((Button) findViewById(R.id.button_level_01011));
        buttonList.add((Button) findViewById(R.id.button_level_01100));

        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setOnClickListener(this);
            if (intentString.equals("Binary")) {
                buttonList.get(i).setText(Integer.toBinaryString(i + 1));
            } else {
                buttonList.get(i).setText(Integer.toHexString(i + 1));
            }
        }
    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        String levelName = "ERROR";
        if (v.getId() != R.id.button_level_stats) {
            intent = new Intent(this, LevelTemplateActivity.class);
            intent.putExtra("base", intentString);

            switch (v.getId()) {
                case R.id.button_level_zen:
                    String messageZen = "Zen";
                    levelName = "Zen";
                    intent.putExtra("leveltype", messageZen);
                    break;

                case R.id.button_level_00000:
                    String messageTut = "Tutorial";
                    levelName = "Tutorial";
                    intent.putExtra("leveltype", messageTut);
                    break;

                default:
                    for (int i = 0; i < buttonList.size(); i++) {
                        if (buttonList.get(i).getId() == v.getId()) {
                            String levelNum = i + 1 + "";
                            levelName = levelNum + "";
                            String message = "Num";
                            intent.putExtra("leveltype", message);
                            intent.putExtra("levelnum", levelNum);
                        }
                    }
                    break;
            }

            //TODO: Implement driver < 1 check here
            // If drivers < 1, show correct dialog with "Back to Menu" button

            createLevelStartDialog(levelName, intent);
        } else {
            intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
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
        congrats.setText("Congratulations! You've converted your first binary number! If you want to see this tutorial again, " +
                "it can be found in 'Settings'");

        Button btnDismiss = (Button) dialog.findViewById(R.id.btnDismiss);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.setSeenBinaryTut(true);
                // Close the dialog
                dialog.dismiss();
            }
        });

        // Display the dialog
        dialog.show();
    }

    /**
     * TODO: Move this method to the LevelTemplateActivity and call it on start
     *
     * @param levelName Used to title the dialog
     * @param i         The intent to start (NEEDS TO BE CHANGED)
     */
    private void createLevelStartDialog(String levelName, Intent i) {
        final Intent intent = i;
        // Create custom dialog object
        final Dialog dialog = new Dialog(this);
        // hide to default title for Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // inflate the layout dialog_layout.xml and set it as contentView
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_level_start, null, false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        // Retrieve views from the inflated dialog layout and update their values
        TextView txtTitle = (TextView) dialog.findViewById(R.id.txt_dialog_title);
        txtTitle.setText("Level: " + levelName);

        int stars = g.getStars(intentString, levelName);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.txt_dialog_message);
        txtMessage.setText(stars + " out of " + 3);

        //Create the ImageView to hold the User's score
        ImageView imgStar1 = (ImageView) dialog.findViewById(R.id.img_dialog_star1);
        ImageView imgStar2 = (ImageView) dialog.findViewById(R.id.img_dialog_star2);
        ImageView imgStar3 = (ImageView) dialog.findViewById(R.id.img_dialog_star3);

        switch (g.getStars(intentString, levelName)) {
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
            default:
                txtMessage.setText("Press Start");
                imgStar1.setImageResource(android.R.color.transparent);
                imgStar2.setImageResource(android.R.color.transparent);
                imgStar3.setImageResource(android.R.color.transparent);
                break;
        }

        Button btnStart = (Button) dialog.findViewById(R.id.button_level_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
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
