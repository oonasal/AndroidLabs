package supercompany.androidlab13;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String URL = ("http://users.metropolia.fi/~peterh/players.xml");
    private Context context;
    private PlayerDatabaseHelper dbHelper;
    //private SQLiteDatabase db;
    private static final String[] PROJECTION = new String[] {"_id", "name"};
    private static final String SELECTION = "";
    private SimpleCursorAdapter scAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        //new XmlLoaderTask().execute(URL);
        dbHelper = new PlayerDatabaseHelper(this);

        handleContent();

        /*handler = new Handler();
        handler.post(updatePlayerData);*/
    }

    //creating a new player and adding it to the database
    public void createNewPlayer(View view) {
        EditText playerIdField = (EditText) findViewById(R.id.playerIdField);
        EditText playerNameField = (EditText) findViewById(R.id.playerNameField);

        Player player = new Player(playerIdField.getText().toString(), playerNameField.getText().toString());
        //dbHelper.addPlayer(player);
        playerIdField.setText("");
        playerNameField.setText("");

        ContentValues values = new ContentValues();
        values.put("name", player.getName());
        values.put("_id", player.getId());
        getContentResolver().insert(Uri.parse("content://supercompany.androidlab13.mycontentprovider/players"), values);
    }

    //setting the created list view
    public void loadListView(Players<Player> players) {
        ListView playersListView = (ListView) findViewById(R.id.playersListView);
        playersListView.setAdapter(new MyAdapter(this.context, players));
    }

    //adding the players read from the xml to the database
    public void savePlayers(Players<Player> players) {
        for(Player p : players) {
            dbHelper.addPlayer(p);
        }
    }

    //loading the xml from a url and parsing it
    private class XmlLoaderTask extends AsyncTask<String, Void, Players<Player>> {
        private PlayersXmlParser playersParser = new PlayersXmlParser();

        @Override
        protected Players<Player> doInBackground(String... urls) {

            try {
                return playersParser.handleXml(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Players<Player> players) {
            setContentView(R.layout.activity_main);
            loadListView(players);
            savePlayers(players);
        }
    }

    //uses the content provider to set the list view
    public void handleContent() {
        setContentView(R.layout.activity_main);
        String[] fromColumns = {"_id", "name"};
        int[] toViews = {android.R.id.text1, android.R.id.text2};
        scAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, fromColumns, toViews, 0);
        ListView playersListView = (ListView) findViewById(R.id.playersListView);
        playersListView.setAdapter(scAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("TAG", "oncreate");

        return new CursorLoader(this, Uri.parse("content://supercompany.androidlab13.mycontentprovider/players"), PROJECTION, SELECTION, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }

    /*private Runnable updatePlayerData = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(updatePlayerData, 2000);
        }
    };*/
}
