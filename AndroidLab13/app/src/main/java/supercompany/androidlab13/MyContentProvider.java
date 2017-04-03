package supercompany.androidlab13;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Oona on 24-Apr-16.
 */
public class MyContentProvider extends ContentProvider {

    private SQLiteDatabase db;
    private PlayerDatabaseHelper dbHelper;
    public static final String PROVIDER_NAME = "supercompany.androidlab13.mycontentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/players");
    private static final String TABLE_NAME = "players";

    public static final String _ID = "_id";
    public static final String NAME = "name";
    private static final int PLAYERS = 1;
    private static final int PLAYER_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME, PLAYERS);
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME + "/#", PLAYER_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new PlayerDatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        if(db != null) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);
        if(uriMatcher.match(uri) == PLAYER_ID) {
            queryBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));
        }
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)) {
            case PLAYERS:
                return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME + "." + TABLE_NAME;
            case PLAYER_ID:
                return "vnd.android.cursor.item/vnd." + PROVIDER_NAME + "." + TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        db = dbHelper.getWritableDatabase();
        if(uriMatcher.match(uri) == PLAYERS) {
            db.insert(TABLE_NAME, null, values);
            Log.d("TAG", "inserting");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d("TAG", "trying to update here!");
        return 0;
    }
}
