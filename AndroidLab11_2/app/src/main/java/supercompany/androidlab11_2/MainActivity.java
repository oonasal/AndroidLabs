package supercompany.androidlab11_2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText numberOfSteps;
    EditText delayBetweenSteps;
    //Counter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfSteps = (EditText) findViewById(R.id.numberOfSteps);
        delayBetweenSteps = (EditText) findViewById(R.id.delayBetweenSteps);
        //counter = new Counter();

    }

    public void startClicked(View view) {

        String steps = numberOfSteps.getText().toString();
        String delay = delayBetweenSteps.getText().toString();



    }

    private class CounterTask extends AsyncTask<Integer, Double, String> {

        @Override
        protected String doInBackground(Integer... params) {
            int number = params[0];
            int i = number;

            while(i > 0) {
                double progress = i / number;
                this.publishProgress();
            }

            return "Done";
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);
        }
    }

    /*this activity will implement custom observer(s), e.g. TickDownObserver
    for lab 12: use startHTTPRequest.setOnCLickListener or similar for the HTTP GET
    URL url = new URL(urlStringFromUI)
    AsyncHTTPRequestDataAnalysis httpReq = new ...();
    httpReq.registerObserver(MainActivity.this);
    httpReq.execute(url);

    <-- these in onCreate

    in onClick: convert steps-string into int, same with delay
    new TickDown()
    tickdown.registerObserver(this)
    tickdown.execute(steps, delay);

    set methods for changing the UI

    an interface: AsyncHTTPRequestDataAnalysisObserver with update and set methods

    AsyncHTTPRequestDataAnalysis class: extends AsuncTask<URL, Integer, Integer>
    AsyncHTTPRequestDataAnalysisObserver observer;

    registerObserver(AsyncHTTPRequestDataAnalysisObserver o)
    this.observer = o;

    postExecute(Integer i) --> sets longest line length

    onprogressupdate(Integer... values) --> observer.updatenumlines and observer.settotalsize

    doinbakground(URL... params)
    try {
    HttpURLConnection connection = (HttpURLConnection) params[0].openConnection();
    connection.setdoinput(true)
    connection.connect()
    scanner sc = new scanner(connection.getinputstream());
    ....
    publishprogress()
    ...
    connection.disconnect()
    return longestlinelength


    TickDown extends asynctask<integer, double, string>
    tickdownobserver observer

    registerobserver

    doinbackground()
    Thread.sleep((long) delayinsec * 1000);
    ...
    publishprogress(progresspercentage)
    return "Done"


   onprogressupdate(double... values) --> observer.setprogresspercentage(values[0])

   onpostexecute(string s) --> observer.setdonemessage(s)


    tickdownobserver interface:
    methods setprogresspercentage and setdonemessage
     */

}
