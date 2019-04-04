package com.example.liu.myappmao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DatabaseFactory implements DatabseCallback {

    private MyDatabaseHelper helper;
    private SQLiteDatabase db;
    private mHandler handler;
    private TreeSet<String> set;
    private List<String> list;
    private onDatabaseCallbacak callbacak;
    private static final String table_name = "Search_History";

    public DatabaseFactory(Context context) {

        helper = getHelper(context);

        db = helper.getWritableDatabase();
        list = new ArrayList<String>();
        handler = new mHandler();

    }

    private MyDatabaseHelper getHelper(Context context) {

        if (helper != null) {
            return helper;
        } else {
            return new MyDatabaseHelper(context);

        }

    }


    public void insertData(String data) {
        // Log.i(getClass().getSimpleName(), "insertData方法sname="+sname);

        if (data != null) {
            String sql = "insert into Search_History(centent) values(?)";

            db.execSQL(sql, new Object[]{data});
        }
    }

    public void deleteData(String data) {

        db.execSQL("delete from sname where name = ?", new String[]{data});
    }

    public void emptyData() {
        db.execSQL("delete from Search_History");
        db.execSQL("update sqlite_sequence SET seq = 0 where name ='Search_History'");
    }

    public void queryData() {
        list.clear();
        new Thread() {

            @Override
            public void run() {

                Cursor cursor = db.query(table_name, null, null, null, null, null, null);

                while (cursor.moveToNext()) {

                    String str = cursor.getString(1);
                    //   Log.i("DatabaseFactory", "queryData方法1 str=" + str);
                    list.add(str);
                }
                handler.sendEmptyMessage(1);
                cursor.close();

            }


        }.start();


    }

    @Override
    public void setData(onDatabaseCallbacak callbacak) {
        this.callbacak = callbacak;
    }


    private class mHandler extends Handler {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //   Log.i("DatabaseFactory", "mHandler方法list = " + list.size());

            callbacak.getData(list);

        }


    }


}
