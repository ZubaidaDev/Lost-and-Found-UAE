package zubaida.begum.adu.ac.ae.lostandfounduae;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lostandfound.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_LOST  = "lost_items";
    private static final String TABLE_FOUND = "found_items";

    // Common columns
    private static final String COL_ID          = "id";
    private static final String COL_ITEM_NAME   = "item_name";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_LOCATION    = "location";
    private static final String COL_DATE        = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createLost =
                "CREATE TABLE " + TABLE_LOST + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_ITEM_NAME + " TEXT, " +
                        COL_DESCRIPTION + " TEXT, " +
                        COL_LOCATION + " TEXT, " +
                        COL_DATE + " TEXT)";

        String createFound =
                "CREATE TABLE " + TABLE_FOUND + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_ITEM_NAME + " TEXT, " +
                        COL_DESCRIPTION + " TEXT, " +
                        COL_LOCATION + " TEXT, " +
                        COL_DATE + " TEXT)";

        db.execSQL(createLost);
        db.execSQL(createFound);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOUND);
        onCreate(db);
    }

    // Insert a lost item
    public void insertLostItem(String itemName, String description, String location, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ITEM_NAME,   itemName);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_LOCATION,    location);
        values.put(COL_DATE,        date);
        db.insert(TABLE_LOST, null, values);
        db.close();
    }

    // Insert a found item
    public void insertFoundItem(String itemName, String description, String location, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ITEM_NAME,   itemName);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_LOCATION,    location);
        values.put(COL_DATE,        date);
        db.insert(TABLE_FOUND, null, values);
        db.close();
    }

    // Search both tables by keyword
    public List<Item> searchItems(String keyword) {
        List<Item> results = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] tables = {TABLE_LOST, TABLE_FOUND};
        String[] types  = {"lost", "found"};

        for (int i = 0; i < tables.length; i++) {
            String query =
                    "SELECT * FROM " + tables[i] +
                            " WHERE " + COL_ITEM_NAME   + " LIKE '%" + keyword + "%'" +
                            " OR "    + COL_DESCRIPTION + " LIKE '%" + keyword + "%'" +
                            " OR "    + COL_LOCATION    + " LIKE '%" + keyword + "%'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COL_ITEM_NAME)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COL_LOCATION)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE)),
                            types[i]
                    );
                    results.add(item);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return results;
    }
}
