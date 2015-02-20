package juliangarcia.twoadelivery;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class LevelsMenuActivity extends Activity implements View.OnClickListener {

    private Button level1;
    private Button level2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_menu);

        createButtonListeners();
    }

    public void createButtonListeners() {
        level1 = (Button) findViewById(R.id.button_level1);
        level1.setOnClickListener(this);

        level2 = (Button) findViewById(R.id.button_level2);
        level2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_level1:
                startActivity(new Intent(LevelsMenuActivity.this, LevelsActivity.class));
                break;
            case R.id.button_level2:
                startActivity(new Intent(LevelsMenuActivity.this, LevelsActivity.class));
                break;
        }
    }
}
