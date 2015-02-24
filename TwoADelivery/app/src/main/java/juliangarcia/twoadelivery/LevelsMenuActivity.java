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
                Intent binaryIntent = new Intent((v.getContext()), LevelTemplateActivity.class);
                startActivityForResult(binaryIntent,2);
                break;
            case R.id.button_level2:
                Intent hexIntent = new Intent((v.getContext()), LevelTemplateActivity.class);
                startActivityForResult(hexIntent,16);
        }
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
