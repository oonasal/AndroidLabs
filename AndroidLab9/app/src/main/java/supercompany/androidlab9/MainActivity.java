package supercompany.androidlab9;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "logtag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        final EditText urlField = (EditText) findViewById(R.id.urlField);
        String givenUrl = "http://" + urlField.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(givenUrl));
        startActivity(intent);
    }
}
