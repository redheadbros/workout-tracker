package com.example.workouttracker;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.workouttracker.datastructure.Workout;

public class CustomCounterHelper {

  static final int COUNTER_MINUS_INDEX = 0;
  static final int COUNTER_TEXT_INDEX = 1;
  static final int COUNTER_PLUS_INDEX = 2;

  public static void setupCycleCounter(LinearLayout counterLayout, Workout workout, WorkoutProgress progress,
                                       int cycleIndex) {
    //find max counter value
    final int maxValue = workout.getCycles().get(cycleIndex).getCycleRepetitions();

    //setup counter value
    TextView counterText = (TextView) counterLayout.getChildAt(COUNTER_TEXT_INDEX);
    counterText.setText(String.valueOf(progress.getCyclesCompleted(cycleIndex)));

    //get & setup buttons
    Button minus = (Button) counterLayout.getChildAt(COUNTER_MINUS_INDEX);
    Button plus = (Button) counterLayout.getChildAt(COUNTER_PLUS_INDEX);

    setupButton(-1, maxValue, minus);
    setupButton(1, maxValue, plus);
  }

  public static void setupSetCounter(LinearLayout counterLayout, Workout workout,
                                     WorkoutProgress progress, int cycleIndex, int exerciseIndex) {
    //find max counter value
    final int maxValue = workout.getCycles().get(cycleIndex)
        .getExercises().get(exerciseIndex).getSets();

    //setup counter value
    TextView counterText = (TextView) counterLayout.getChildAt(COUNTER_TEXT_INDEX);
    counterText.setText(String.valueOf(progress.getSetsCompleted(cycleIndex, exerciseIndex)));

    //get buttons
    Button minus = (Button) counterLayout.getChildAt(COUNTER_MINUS_INDEX);
    Button plus = (Button) counterLayout.getChildAt(COUNTER_PLUS_INDEX);

    //setup minus button
    setupButton(-1, maxValue, minus);
    setupButton(1, maxValue, plus);
  }

  private static void setupButton(final int increment, final int maxValue, Button button) {
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //get current value from textView
        LinearLayout cycleCounterLayout = (LinearLayout) v.getParent();
        TextView cycleCounterTextView = (TextView) cycleCounterLayout.getChildAt(1);
        int currentCyclesCompleted = Integer.parseInt((String) cycleCounterTextView.getText());

        //update text view
        //note: 'clamp' function formula:
        // https://stackoverflow.com/questions/16656651/does-java-have-a-clamp-function
        currentCyclesCompleted += increment;
        currentCyclesCompleted = Math.max(0, Math.min(maxValue, currentCyclesCompleted)); //clamp
        cycleCounterTextView.setText(String.valueOf(currentCyclesCompleted));
      }
    });
  }
}
