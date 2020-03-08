package com.asesoftware.pruebatecnica.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.asesoftware.pruebatecnica.ui.helper.DatabaseHelper;

public class DBArticulo {

    //<editor-fold desc="Atributos">
    private DatabaseHelper dbHelper;
    private String TAG="DBArticulo";
    private Context context;

    private SQLiteDatabase database;
    //</editor-fold>

    public DBArticulo(Context context) {
        this.context = context;
    }

    public DBArticulo open() throws SQLException {
        dbHelper= new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    //Metodo de cierre
    public  void close (){
        dbHelper.close();
    }
    //Insertar
    public void insert(String name, String desc, String precio) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NOMBRE, name);
        contentValue.put(DatabaseHelper.DESCRIPCION, desc);
        contentValue.put(DatabaseHelper.PREC, desc);
        //contentValue.put(DatabaseHelpers.ACTIVO, active);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }
    //Metodo de busqueda
    public Cursor fetch() {

        //String[] columns = new String[] { DatabaseHelpers._ID, DatabaseHelpers.NOMBRE, DatabaseHelpers.DESCRIPCION, DatabaseHelpers.ACTIVO };
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NOMBRE, DatabaseHelper.DESCRIPCION, DatabaseHelper.PREC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }

        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
        return cursor;
    }
    //Actualizar
    public int update(long _id, String nombre, String desc, String prec) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NOMBRE, nombre);
        contentValues.put(DatabaseHelper.DESCRIPCION, desc);
        contentValues.put(DatabaseHelper.PREC, prec);
        //String datoActivo = String.valueOf((boolean) activo);
        //contentValues.put(DatabaseHelpers.ACTIVO, datoActivo);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }
    //Eliminar
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
    //Listar
    public Cursor getAll() {
        //String[] columns={DatabaseHelpers._ID,DatabaseHelpers.NOMBRE, DatabaseHelpers.DESCRIPCION, DatabaseHelpers.ACTIVO};
        String[] columns={DatabaseHelper._ID,DatabaseHelper.NOMBRE, DatabaseHelper.DESCRIPCION, DatabaseHelper.PREC};
        return database.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);

    }


}
