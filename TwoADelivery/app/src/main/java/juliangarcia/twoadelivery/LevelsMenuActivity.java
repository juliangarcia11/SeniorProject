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


public class LevelsMenuActivity extends Activity implements View.OnClickListener {

    private Button zen, level1, level2;
    public final static String EXTRA_MESSAGE = "juliangarcia.twoadelivery.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_menu);

        createButtonListeners();
    }

    public void createButtonListeners() {
        zen = (Button) findViewById(R.id.button_levelzen);
        zen.setOnClickListener(this);

        level1 = (Button) findViewById(R.id.button_level1);
        level1.setOnClickListener(this);

        level2 = (Button) findViewById(R.id.button_level2);
        level2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LevelTemplateActivity.class);
        String message = "";

        switch (v.getId()) {
            case R.id.button_levelzen:
                message = zen.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                break;
            case R.id.button_level1:
                message = level1.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                break;
            case R.id.button_level2:
                message = level2.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                break;
        }

        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
//        if (requestCode == PICK_CONTACT_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                // A contact was picked.  Here we will just display it
//                // to the user.
//                //startActivity(new Intent(Intent.ACTION_VIEW, data));
//            }
//        }
    }

}
