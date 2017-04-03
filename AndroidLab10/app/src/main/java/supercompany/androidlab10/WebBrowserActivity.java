package supercompany.androidlab10;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.net.MalformedURLException;
import java.net.URL;

public class WebBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        Uri uriData = i.getData();
        URL url = null;

        try {
            url = new URL(uriData.getScheme(), uriData.getHost(), uriData.getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url.toString());
    }
}
