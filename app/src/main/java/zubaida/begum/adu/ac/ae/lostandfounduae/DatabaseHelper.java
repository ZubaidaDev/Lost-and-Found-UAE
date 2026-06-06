package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostFoundDB";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_ITEM = "items";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LOCATION = "location";
    private static final String DATE = "date";
    private static final String IMAGE_LINK = "image_link";
    private static final String TYPE = "type";
    private static final String STATUS = "status";

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
        sqlCreate += IMAGE_LINK + " text, ";
        sqlCreate += TYPE + " text, ";
        sqlCreate += STATUS + " text)";
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
        sqlInsert += item.getImageLink() + "', '";
        sqlInsert += item.getType() + "', '";
        sqlInsert += item.getStatus() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Item> searchItems(String keyword) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "select * from " + TABLE_ITEM;
        sqlQuery += " where " + STATUS + " = 'active'";
        sqlQuery += " and (";
        sqlQuery += NAME + " like '%" + keyword + "%'";
        sqlQuery += " or " + DESCRIPTION + " like '%" + keyword + "%'";
        sqlQuery += " or " + LOCATION + " like '%" + keyword + "%'";
        sqlQuery += ")";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {

            Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );

            items.add(item);
        }

        cursor.close();
        db.close();

        return items;
    }

    public ArrayList<Item> searchItemsByType(String keyword, String type) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "select * from " + TABLE_ITEM;
        sqlQuery += " where " + STATUS + " = 'active'";
        sqlQuery += " and " + TYPE + " = '" + type + "'";
        sqlQuery += " and (";
        sqlQuery += NAME + " like '%" + keyword + "%'";
        sqlQuery += " or " + DESCRIPTION + " like '%" + keyword + "%'";
        sqlQuery += " or " + LOCATION + " like '%" + keyword + "%'";
        sqlQuery += ")";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {

            Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );

            items.add(item);
        }

        cursor.close();
        db.close();

        return items;
    }

    public ArrayList<Item> selectPendingItems() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "select * from " + TABLE_ITEM;
        sqlQuery += " where " + STATUS + " = 'pending'";

        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Item> items = new ArrayList<>();

        while (cursor.moveToNext()) {

            Item item = new Item(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );

            items.add(item);
        }

        cursor.close();
        db.close();

        return items;
    }

    public void updateStatusById(int id, String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_ITEM;
        sqlUpdate += " set " + STATUS + " = '" + status + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteById(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlDelete = "delete from " + TABLE_ITEM;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }
}
