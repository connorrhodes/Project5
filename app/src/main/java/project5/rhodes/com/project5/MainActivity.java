package project5.rhodes.com.project5;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

//Project 5
//
public class MainActivity extends Activity {
    Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button;


        //Reference the chronometer
        mChronometer = (Chronometer) findViewById(R.id.chronometer);


        // References for the buttons and new onClick listeners
        button = (Button) findViewById(R.id.start);
        button.setOnClickListener(mStartListener);
        button = (Button) findViewById(R.id.stop);
        button.setOnClickListener(mStopListener);
        button = (Button) findViewById(R.id.reset);
        button.setOnClickListener(mResetListener);


    }
    //Method to notify the user of the elapsed milliseconds have been counted since boot
    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
        Toast.makeText(MainActivity.this, "Elapsed milliseconds: " + elapsedMillis,
                Toast.LENGTH_SHORT).show();
    }
    //Creates the listener method for the Start button
    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            int stoppedMilliseconds = 0;
    //Turns the time amount into the string "chronoText"
            String chronoText = mChronometer.getText().toString();
            String array[] = chronoText.split(":");//Makes sure string array separates the : from the variables
            if (array.length == 2) {
                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                        + Integer.parseInt(array[1]) * 1000;
            } else if (array.length == 3) {
                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                        + Integer.parseInt(array[1]) * 60 * 1000
                        + Integer.parseInt(array[2]) * 1000;
            }
            //Sets the Chronometer base time to what the time was during boot and/or when it was paused.
            //It subtracts the time added in the background to display the correct time you paused.
            mChronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
            mChronometer.start();
        }

    };

        //Created a new listener for the "stop" button
        View.OnClickListener mStopListener = new OnClickListener() {
            public void onClick(View v) {
                mChronometer.stop();//When the button is pressed, the chronometer display is stopped
                showElapsedTime();//everytime you stop the clock, the user is notified with the elapsed time in milliseconds.

            }
        };
        //Created a new listener for the "reset" button
        View.OnClickListener mResetListener = new OnClickListener() {
            public void onClick(View v) {
                mChronometer.setBase(SystemClock.elapsedRealtime());//Returns milliseconds since boot, including time spent in sleep.
                showElapsedTime();
            }
        };
}



    /*
    //Previous attempt to turn the functions into a switch

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case.R.id.start:

            mChronometer.start();

            break;

            case.R.id.stop:

            mChronometer.stop();

            break;

            case.R.id.reset:

            mChronometer.setBase(SystemClock.elapsedRealtime());//Returns milliseconds since boot, including time spent in sleep.
            break;
        }
    }
    */
