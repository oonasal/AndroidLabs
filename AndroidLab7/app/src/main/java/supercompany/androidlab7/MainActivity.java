package supercompany.androidlab7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textField = (TextView) findViewById(R.id.textField);
        EditText textInputField = (EditText) findViewById(R.id.textInputField);
        Button submitButton = (Button) findViewById(R.id.submitButton);
    }

    public void onClick(View view) {
        Intent i = new Intent(this, Activity2.class);

        final EditText textInputField = (EditText) findViewById(R.id.textInputField);
        String textInput = textInputField.getText().toString();
        i.putExtra("textInput", textInput);

        startActivity(i);
    }



}
