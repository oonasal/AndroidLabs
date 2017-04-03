package supercompany.androidlab13;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Oona on 20-Apr-16.
 */
public class PlayerDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "playersDB";
    private static final int DATABASE_VERSION = 1;
    private static final String PLAYERS_TABLE_NAME = "players";
    private static final String KEY_PLAYER_NAME = "name";
    private static final String KEY_PLAYER_ID = "_id";

    private static final String PLAYERS_CREATE_TABLE = "CREATE TABLE " + PLAYERS_TABLE_NAME + " (" + KEY_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_PLAYER_NAME + " TEXT, UNIQUE(" + KEY_PLAYER_NAME + ", " + KEY_PLAYER_ID + ") ON CONFLICT IGNORE);";

    public PlayerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
        Log.d("TAG", "PlayerDatabaseHelper constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PLAYERS_CREATE_TABLE);
        Log.d("TAG", "onCreate() for databasehelper called");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
        onCreate(db);
    }

    //adds a player into the database
    public void addPlayer(Player player) {
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_NAME, player.getName());
        values.put(KEY_PLAYER_ID, player.getId());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(PLAYERS_TABLE_NAME, null, values);
        db.close();
        Log.d("TAG", "player added to database");
    }
}
