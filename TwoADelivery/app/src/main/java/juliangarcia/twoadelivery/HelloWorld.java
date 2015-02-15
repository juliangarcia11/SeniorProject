package juliangarcia.twoadelivery;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class HelloWorld extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
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
    public void onSettingsClick(View v) {
        Button settings = (Button) findViewById(R.id.button_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vi) {
                startActivity(new Intent(HelloWorld.this, SettingsActivity.class));
            }
        });
    }

    public void onLevelsClick(View v) {
        Button settings = (Button) findViewById(R.id.button_levels);

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vi) {
                startActivity(new Intent(HelloWorld.this, LevelsActivity.class));
            }
        });
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
