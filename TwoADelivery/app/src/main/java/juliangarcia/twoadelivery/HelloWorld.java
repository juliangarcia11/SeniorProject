package juliangarcia.twoadelivery;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class HelloWorld extends Activity implements View.OnClickListener {

    private Button settings;
    private Button levels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        createButtonListeners();
    }

    public void createButtonListeners() {
        settings = (Button) findViewById(R.id.button_settings);
        settings.setOnClickListener(this);

        levels = (Button) findViewById(R.id.button_levels);
        levels.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_settings:
                startActivity(new Intent(HelloWorld.this, SettingsActivity.class));
                break;
            case R.id.button_levels:
                startActivity(new Intent(HelloWorld.this, LevelsMenuActivity.class));
                break;
        }
    }

    public void onQuitButtonClick(View v) {
        //do something
        finish();
        System.exit(0);
    }
}
