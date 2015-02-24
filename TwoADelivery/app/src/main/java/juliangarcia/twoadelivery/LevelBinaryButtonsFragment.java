package juliangarcia.twoadelivery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Julian on 2/21/2015.
 */
public class LevelBinaryButtonsFragment extends Fragment implements View.OnClickListener {

    private Button binary0;
    private Button binary1;

    BinaryButtonsListener activityCallback;

    public interface BinaryButtonsListener {
        public void onButtonClick(int i);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (BinaryButtonsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement BinaryButtonsListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.binary_buttons_fragment,
                container, false);

        binary0 = (Button) view.findViewById(R.id.button_binary0);
        binary0.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(0);
            }
        });

        binary1 = (Button) view.findViewById(R.id.button_binary1);
        binary1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(1);
            }
        });

        return view;
    }

    public void buttonClicked (int value) {
        activityCallback.onButtonClick(value);
    }

    @Override
    public void onClick(View v) {

    }
}
