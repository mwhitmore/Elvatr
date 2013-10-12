package com.tejens.elvatr;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity {

  private static final String DATA = "data";
  private static final String TOTAL_RIDES = "totalRides";
  private static final String ELVTR_ONE_RIDES = "elvtrOneRides";
  private static final String ELVTR_TWO_RIDES = "elvtrTwoRides";
  private static final String ELVTR_THREE_RIDES = "elvtrThreeRides";
  private static final String ELVTR_FOUR_RIDES = "elvtrFourRides";
  private static final String ELVTR_ONE_PERCENT = "elvtrOnePercent";
  private static final String ELVTR_TWO_PERCENT = "elvtrTwoPercent";
  private static final String ELVTR_THREE_PERCENT = "elvtrThreePercent";
  private static final String ELVTR_FOUR_PERCENT = "elvtrFourPercent";
  private static final String RIDES = "Rides: ";
  private static final String PERCENT = "Percent: ";
  private static TextView elvtrOneRides;
  private static TextView elvtrOnePercent;
  private static TextView elvtrTwoRides;
  private static TextView elvtrTwoPercent;
  private static TextView elvtrThreeRides;
  private static TextView elvtrThreePercent;
  private static TextView elvtrFourRides;
  private static TextView elvtrFourPercent;
  private static TextView totalRidesView;
  private static int totalRides;
  private SharedPreferences data;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    elvtrOneRides = (TextView) findViewById(R.id.elvtrOneRides);
    elvtrOnePercent = (TextView) findViewById(R.id.elvtrOnePercent);

    elvtrTwoRides = (TextView) findViewById(R.id.elvtrTwoRides);
    elvtrTwoPercent = (TextView) findViewById(R.id.elvtrTwoPercent);

    elvtrThreeRides = (TextView) findViewById(R.id.elvtrThreeRides);
    elvtrThreePercent = (TextView) findViewById(R.id.elvtrThreePercent);

    elvtrFourRides = (TextView) findViewById(R.id.elvtrFourRides);
    elvtrFourPercent = (TextView) findViewById(R.id.elvtrFourPercent);

    totalRidesView = (TextView) findViewById(R.id.totalRides);

    data = getSharedPreferences(DATA, 0);
    if (data != null) {
      totalRides = data.getInt(TOTAL_RIDES, 0);
      totalRidesView.setText("Total rides: " + totalRides);

      elvtrOneRides.setText(RIDES + data.getInt(ELVTR_ONE_RIDES, 0));
      elvtrOnePercent.setText(PERCENT + data.getString(ELVTR_ONE_PERCENT, ""));

      elvtrTwoRides.setText(RIDES + data.getInt(ELVTR_TWO_RIDES, 0));
      elvtrTwoPercent.setText(PERCENT + data.getString(ELVTR_TWO_PERCENT, ""));

      elvtrThreeRides.setText(RIDES + data.getInt(ELVTR_THREE_RIDES, 0));
      elvtrThreePercent.setText(PERCENT + data.getString(ELVTR_THREE_PERCENT, ""));

      elvtrFourRides.setText(RIDES + data.getInt(ELVTR_FOUR_RIDES, 0));
      elvtrFourPercent.setText(PERCENT + data.getString(ELVTR_FOUR_PERCENT, ""));
    }

    final Button elvtrOneBtn = (Button) findViewById(R.id.elvtrOneBtn);
    final Button elvtrTwoBtn = (Button) findViewById(R.id.elvtrTwoBtn);
    final Button elvtrThreeBtn = (Button) findViewById(R.id.elvtrThreeBtn);
    final Button elvtrFourBtn = (Button) findViewById(R.id.elvtrFourBtn);

    elvtrOneBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        totalRides++;
        totalRidesView.setText("Total rides: " + totalRides);

        final int rides = getValue(elvtrOneRides.getText()) + 1;
        elvtrOneRides.setText(RIDES + rides);
        updatePercent();
      }
    });

    elvtrTwoBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        totalRides++;
        totalRidesView.setText("Total rides: " + totalRides);

        final int rides = getValue(elvtrTwoRides.getText()) + 1;
        elvtrTwoRides.setText(RIDES + rides);
        updatePercent();
      }
    });

    elvtrThreeBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        totalRides++;
        totalRidesView.setText("Total rides: " + totalRides);

        final int rides = getValue(elvtrThreeRides.getText()) + 1;
        elvtrThreeRides.setText(RIDES + rides);
        updatePercent();
      }
    });

    elvtrFourBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        totalRides++;
        totalRidesView.setText("Total rides: " + totalRides);

        final int rides = getValue(elvtrFourRides.getText()) + 1;
        elvtrFourRides.setText(RIDES + rides);
        updatePercent();
      }
    });
  }

  @Override
  public void onPause() {
    super.onPause();
    SharedPreferences.Editor editor = data.edit();

    editor.putInt(TOTAL_RIDES, totalRides);
    editor.putInt(ELVTR_ONE_RIDES, getValue(elvtrOneRides.getText()));
    editor.putString(ELVTR_ONE_PERCENT, elvtrOnePercent.getText().toString().split(": ")[1]);

    editor.putInt(ELVTR_TWO_RIDES, getValue(elvtrTwoRides.getText()));
    editor.putString(ELVTR_TWO_PERCENT, elvtrTwoPercent.getText().toString().split(": ")[1]);

    editor.putInt(ELVTR_THREE_RIDES, getValue(elvtrThreeRides.getText()));
    editor.putString(ELVTR_THREE_PERCENT, elvtrThreePercent.getText().toString().split(": ")[1]);

    editor.putInt(ELVTR_FOUR_RIDES, getValue(elvtrFourRides.getText()));
    editor.putString(ELVTR_FOUR_PERCENT, elvtrFourPercent.getText().toString().split(": ")[1]);

    editor.apply();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  private int getValue(CharSequence text) {
    return Integer.parseInt(text.toString().split(": ")[1]);
  }

  private void updatePercent() {
    final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    percentFormat.setMinimumFractionDigits(3);

    double rides1 = getValue(elvtrOneRides.getText());
    double rides2 = getValue(elvtrTwoRides.getText());
    double rides3 = getValue(elvtrThreeRides.getText());
    double rides4 = getValue(elvtrFourRides.getText());

    elvtrOnePercent.setText(PERCENT + percentFormat.format(rides1 / totalRides));
    elvtrTwoPercent.setText(PERCENT + percentFormat.format(rides2 / totalRides));
    elvtrThreePercent.setText(PERCENT + percentFormat.format(rides3 / totalRides));
    elvtrFourPercent.setText(PERCENT + percentFormat.format(rides4 / totalRides));
  }
}
