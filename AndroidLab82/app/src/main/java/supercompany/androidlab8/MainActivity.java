package supercompany.androidlab8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "logtag";
    static final String ACTION_TESTACTION = "supercompany.forandroidlab8.TESTACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        EditText textInputField = (EditText) findViewById(R.id.textInputField);
        String textInput = textInputField.getText().toString();

        Intent i = new Intent();
        i.setAction(ACTION_TESTACTION);
        i.putExtra("textInput", textInput);
        startActivity(i);
    }
}
