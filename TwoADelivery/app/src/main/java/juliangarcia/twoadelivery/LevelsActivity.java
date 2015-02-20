package juliangarcia.twoadelivery;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;


public class LevelsActivity extends Activity implements View.OnClickListener {
    CountDownTimerActivity levelsCountdown;
    TextView text_levelsCountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        levelsCountdown = new CountDownTimerActivity(5000, 1000);
    }

    @Override
    public void onClick(View v) {

    }

    public class CountDownTimerActivity extends CountDownTimer {

        public CountDownTimerActivity(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            text_levelsCountdown.setText("Time's up!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text_levelsCountdown.setText("" + millisUntilFinished/1000);
        }

    }
}
