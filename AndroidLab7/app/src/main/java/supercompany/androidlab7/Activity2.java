package supercompany.androidlab7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Bundle inputData = getIntent().getExtras();
        if(inputData == null) {
            return;
        }
        String textInput = inputData.getString("textInput");
        final TextView animalText = (TextView) findViewById(R.id.animalText);
        animalText.setText(textInput);

    }
}
