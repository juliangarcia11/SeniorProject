package juliangarcia.twoadelivery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Julian on 2/22/2015.
 */
public class LevelHexButtonsFragment extends Fragment implements View.OnClickListener {
    private Button hex0;
    private Button hex1;

    HexButtonsListener activityCallback;

    public interface HexButtonsListener {
        public void onButtonClick(int i);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (HexButtonsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement HexButtonsListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.hex_buttons_fragment,
                container, false);

        hex0 = (Button) view.findViewById(R.id.button_binary0);
        hex0.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(0);
            }
        });

        hex1 = (Button) view.findViewById(R.id.button_binary1);
        hex1.setOnClickListener(new View.OnClickListener() {
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

