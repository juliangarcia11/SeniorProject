package juliangarcia.twoadelivery;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class HelloWorld extends ActionBarActivity {

    private ArrayList<String> buttonText = new ArrayList<String>();
    private ArrayList<String> toggleText = new ArrayList<String>();
    private ArrayList<Integer> toggleColor = new ArrayList<Integer>();
    private int buttonInt = 0;
    private int toggleInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        makeButtonTexts();
        makeToggleTexts();
        makeToggleColor();
    }

    private void makeButtonTexts() {
        buttonText.add("Click me!");
        buttonText.add("Clicked!");
        buttonText.add("You clicked me again!");
        buttonText.add("You're way too excited about this.");
        buttonText.add("Okay. Take a break.");
        buttonText.add("Or don't :p");
        buttonText.add("Will you keep clicking if I start over?");
    }

    private void makeToggleTexts() {
        toggleText.add("Light Purple");
        toggleText.add("Dark Purple");

    }

    private void makeToggleColor() {
        toggleColor.add(0xFF471D70);
        toggleColor.add(0xFFA336FF);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_world, menu);
        return true;
    }

    /**
     * Part of the "Hello World" version.
     * When the button on the main menu is clicked,
     * the text on the button changes to "Clicked".
     * @param v The view passed in (not super sure)
     */
    public void onButtonClick(View v) {
        //do something
        Button button = (Button) v;

        ((Button) v).setText(buttonText.get(buttonInt));

        buttonInt = (buttonInt + 1) % buttonText.size();
    }

    public void onDarkToggle(View v) {
        Button tog = (Button) v;

        ((Button) v).setText(toggleText.get(toggleInt));

        ((Button) v).setBackgroundColor(toggleColor.get(toggleInt));

        toggleInt = (toggleInt + 1) % toggleText.size();
    }

    public void onQuitButtonClick(View v) {
        //do something
        finish();
        System.exit(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
