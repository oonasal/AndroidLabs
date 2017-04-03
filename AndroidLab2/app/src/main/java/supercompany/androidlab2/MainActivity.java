package supercompany.androidlab2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Counter counter = new Counter();
    public final static String TAG = "Checking!";
    private String numberValue;
    private TextView numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button incrementButton = (Button) findViewById(R.id.incrementButton);
        final Button decrementButton = (Button) findViewById(R.id.decrementButton);
        final Button resetButton = (Button) findViewById(R.id.resetButton);
        numberField = (TextView) findViewById(R.id.numberField);

        //set the event listeners

        //listens to short clicks and calls a method that increments the value in the number field by 1
        incrementButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int i = Integer.parseInt(numberField.getText().toString());
                int changed = counter.incrementByOne(i);
                numberField.setText(Integer.toString(changed));
                Log.d(TAG, "increment onClick");
                numberValue = Integer.toString(changed);
            }
        });

        //listens to long clicks and calls a method that increments the value in the number field by 10
        incrementButton.setOnLongClickListener(new Button.OnLongClickListener() {
            public boolean onLongClick(View v) {
                int i = Integer.parseInt(numberField.getText().toString());
                int changed = counter.incrementByTen(i);
                numberField.setText(Integer.toString(changed));
                Log.d(TAG, "increment onLongClick");
                numberValue = Integer.toString(changed);
                return true;
            }
        });

        //listens to short clicks and calls a method that decrements the value in the number field by 1
        decrementButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int i = Integer.parseInt(numberField.getText().toString());
                int changed = counter.decrementByOne(i);
                numberField.setText(Integer.toString(changed));
                Log.d(TAG, "decrement onClick");
                numberValue = Integer.toString(changed);
            }
        });

        //listens to long clicks and calls a method that decrements the value in the number field by 10
        decrementButton.setOnLongClickListener(new Button.OnLongClickListener() {
            public boolean onLongClick(View v) {
                int i = Integer.parseInt(numberField.getText().toString());
                int changed = counter.decrementByTen(i);
                numberField.setText(Integer.toString(changed));
                Log.d(TAG, "decrement onLongClick");
                numberValue = Integer.toString(changed);
                return true;
            }
        });

        //listens to clicks and calls a method that resets the value in the number field
        resetButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                numberField.setText("5");
                Log.d(TAG, "reset onClick");
                numberValue = "5";
            }
        });
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        save(numberValue);
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        numberField.setText(read());
        super.onStart();
    }

    public void save(String value) {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number", value);
        editor.commit();
    }

    public String read() {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String readValue = sharedPreferences.getString("number", "");
        return readValue;
    }
}
