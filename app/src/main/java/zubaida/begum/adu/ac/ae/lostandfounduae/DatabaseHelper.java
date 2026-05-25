package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostFoundDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ITEM = "items";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LOCATION = "location";
    private static final String DATE = "date";
    private static final String TYPE = "type";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_ITEM;
        sqlCreate += "(" + ID + " integer primary key autoincrement, ";
        sqlCreate += NAME + " text, ";
        sqlCreate += DESCRIPTION + " text, ";
        sqlCreate += LOCATION + " text, ";
        sqlCreate += DATE + " text, ";
        sqlCreate += TYPE + " text)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "drop table if exists " + TABLE_ITEM;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public void insertItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlInsert = "insert into " + TABLE_ITEM;
        sqlInsert += " values(null, '";
        sqlInsert += item.getItemName() + "', '";
        sqlInsert += item.getDescription() + "', '";
        sqlInsert += item.getLocation() + "', '";
        sqlInsert += item.getDate() + "', '";
        sqlInsert += item.getType() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Item> searchItems(String keyword) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "select * from " + TABLE_ITEM;
        sqlQuery += " where " + NAME + " like '%" + keyword + "%'";
        sqlQuery += " or " + DESCRIPTION + " like '%" + keyword + "%'";
        sqlQuery += " or " + LOCATION + " like '%" + keyword + "%'";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            items.add(item);
        }

        db.close();
        return items;
    }
}