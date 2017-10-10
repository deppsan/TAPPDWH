package tarjetas.dwh.com.tarjetas.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import tarjetas.dwh.com.tarjetas.R;
import tarjetas.dwh.com.tarjetas.interfaces.FragmentPageLifeCycle;
import tarjetas.dwh.com.tarjetas.utilities.custom.CircleFillView;

/**
 * Created by ricar on 06/07/2017.
 */

public class Prueba extends Fragment implements FragmentPageLifeCycle {

/*    CircleFillView circleFillView;
    SeekBar seekBar;*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout,container,false);
/*
        seekBar = (SeekBar) v.findViewById(R.id.seekBar);
        circleFillView = (CircleFillView) v.findViewById(R.id.circleFillView);
        final CircleFillView circleFillView2 = new CircleFillView(getActivity());
        LinearLayout l = (LinearLayout)circleFillView.getParent();
        l.addView(circleFillView2);

        circleFillView.setVisibility(View.INVISIBLE);
        circleFillView2.setFillColor(Color.BLUE);
        circleFillView2.setStrokeColor(Color.GREEN);
        circleFillView2.setStrokeWidth(2);



        seekBar.setProgress(circleFillView2.getValue());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
                                           {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                                               {
                                                   if (fromUser)
                                                       circleFillView2.setValue(progress);
                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {}

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {}
                                           }
        );
*/

        return v;
    }
    public static Fragment newInstance() {
        return new Prueba();
    }

    @Override
    public void onResumeFragment() {

    }
}
