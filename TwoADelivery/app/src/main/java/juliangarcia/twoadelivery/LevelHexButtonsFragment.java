package juliangarcia.twoadelivery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Julian on 2/22/2015.
 */
public class LevelHexButtonsFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    public HexButtonsListener activityCallback;

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

        View view = inflater.inflate(R.layout.hex_buttons_fragment,
                container, false);

        createButtons(view);

        return view;
    }

    public void createButtons(View view) {

        buttonList.add((Button) view.findViewById(R.id.button_hex0));
        buttonList.add((Button) view.findViewById(R.id.button_hex1));
        buttonList.add((Button) view.findViewById(R.id.button_hex2));
        buttonList.add((Button) view.findViewById(R.id.button_hex3));
        buttonList.add((Button) view.findViewById(R.id.button_hex4));
        buttonList.add((Button) view.findViewById(R.id.button_hex5));
        buttonList.add((Button) view.findViewById(R.id.button_hex6));
        buttonList.add((Button) view.findViewById(R.id.button_hex7));
        buttonList.add((Button) view.findViewById(R.id.button_hex8));
        buttonList.add((Button) view.findViewById(R.id.button_hex9));
        buttonList.add((Button) view.findViewById(R.id.button_hexA));
        buttonList.add((Button) view.findViewById(R.id.button_hexB));
        buttonList.add((Button) view.findViewById(R.id.button_hexC));
        buttonList.add((Button) view.findViewById(R.id.button_hexD));
        buttonList.add((Button) view.findViewById(R.id.button_hexE));
        buttonList.add((Button) view.findViewById(R.id.button_hexF));

        for (int i = 0; i < buttonList.size(); i++) {
            final int temp = i;
            buttonList.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    buttonClicked(temp);
                }
            });
        }
    }

    public void buttonClicked(int value) {
        activityCallback.onButtonClick(value);
    }

    @Override
    public void onClick(View v) {

    }
}

