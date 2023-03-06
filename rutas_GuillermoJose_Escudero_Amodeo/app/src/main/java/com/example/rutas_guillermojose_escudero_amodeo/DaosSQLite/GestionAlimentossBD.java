package com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestionAlimentossBD extends SQLiteOpenHelper  {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BDAlimentos33";

    private static final String TABLE_ALIMENTOS = "Alimentos";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_KCAL = "kcal";

    private static final String SQL_CREATE_ALIMENTOS =
            "CREATE TABLE " + TABLE_ALIMENTOS + " (" +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_KCAL + " NUMBER" +
                    ");";

    public GestionAlimentossBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ALIMENTOS);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No es necesario implementar este método si no se va a realizar ninguna actualización
    }

    public void borrarTablas() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALIMENTOS);
        db.execSQL(SQL_CREATE_ALIMENTOS);
        db.execSQL("PRAGMA foreign_keys = ON;");
        db.close();
    }
}
