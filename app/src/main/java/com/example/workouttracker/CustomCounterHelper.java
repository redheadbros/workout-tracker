package com.example.workouttracker;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomCounterHelper {

  public static void setupCycleCounter(LinearLayout counterLayout, final WorkoutProgress progress,
                                final int cycleIndex) {
    //setup counter value
    TextView counterText = (TextView) counterLayout.getChildAt(1);
    counterText.setText(String.valueOf(progress.getCyclesCompleted(cycleIndex)));

    //get buttons
    Button minus = (Button) counterLayout.getChildAt(0);
    Button plus = (Button) counterLayout.getChildAt(2);

    //setup minus button
    minus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //change progress
        progress.setCyclesCompleted(cycleIndex, progress.getCyclesCompleted(cycleIndex) - 1);

        //update text view
        LinearLayout cycleCounterLayout = (LinearLayout) v.getParent();
        TextView cycleCounterTextView = (TextView) cycleCounterLayout.getChildAt(1);
        cycleCounterTextView.setText(String.valueOf(progress.getCyclesCompleted(cycleIndex)));
      }
    });

    //setup plus button
    plus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //change progress
        progress.setCyclesCompleted(cycleIndex, progress.getCyclesCompleted(cycleIndex) + 1);

        //update text view
        LinearLayout cycleCounterLayout = (LinearLayout) v.getParent();
        TextView cycleCounterTextView = (TextView) cycleCounterLayout.getChildAt(1);
        cycleCounterTextView.setText(String.valueOf(progress.getCyclesCompleted(cycleIndex)));
      }
    });

  }

  public static void setupSetCounter(LinearLayout counterLayout, final WorkoutProgress progress,
                              final int cycleIndex, final int exerciseIndex) {
    //setup counter value
    TextView counterText = (TextView) counterLayout.getChildAt(1);
    counterText.setText(String.valueOf(progress.getSetsCompleted(cycleIndex, exerciseIndex)));

    //get buttons
    Button minus = (Button) counterLayout.getChildAt(0);
    Button plus = (Button) counterLayout.getChildAt(2);

    //setup minus button
    minus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //change progress
        progress.setSetsCompleted(cycleIndex, exerciseIndex, progress.getCyclesCompleted(cycleIndex) - 1);

        //update text view
        LinearLayout setCounterLayout = (LinearLayout) v.getParent();
        TextView setCounterTextView = (TextView) setCounterLayout.getChildAt(1);
        setCounterTextView.setText(String.valueOf(progress.getSetsCompleted(cycleIndex, exerciseIndex)));
      }
    });
  }
}
