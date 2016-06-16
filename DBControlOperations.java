package DataPersistence;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Worker2 on 23/05/2016.
 */
public class DBControlOperations {

    private DbConnectionHelper dbSchema;
    private final Context myContext;
    private SQLiteDatabase DB;

    public DBControlOperations(Context context)
    { myContext =context;
        dbSchema = new DbConnectionHelper(myContext);
    }
    public DBControlOperations openDB()throws SQLException
    {
        try{
            dbSchema.createDataBase();
            dbSchema.openDataBase();
            dbSchema.close();
            DB = dbSchema.getWritableDatabase();
        }catch (SQLException mSQLException)
        {
            Log.e(null, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        catch (IOException ioe)
        {
            Log.e(null, "open >>"+ ioe.toString());

        }
        catch (Exception ex)
        {
            Log.e(null, "open >>"+ ex.toString());
        }
        return this;

    }
    public void closeDB()
    {
        dbSchema.close();
    }


    public List<String[]> getDatafromTable(String table_name, @Nullable QuerryArg arg)
    {
        String querry;
        Cursor myCursor = null;
        List<String[]> lista = new ArrayList<>();
        if (arg == null) {
             querry = "select * from " + table_name + ";";
             myCursor = DB.rawQuery(querry, null);
        }
        else {
            querry = "select * from " + table_name + " where "+arg._name+" = \""+arg._value+ "\";";
            myCursor = DB.rawQuery(querry, null);
        }


        for(int i = 0; i < myCursor.getCount(); i++)
        {
            myCursor.moveToPosition(i);
            String[] row = new String[myCursor.getColumnCount()];
            for(int x = 0;x < myCursor.getColumnCount(); x++)
            {
                row[x] = myCursor.getString(x);
            }
            lista.add(row);
        }
        return lista;
    }
    public void insertDataOnTable(String table_name,QuerryArg[] args)
    {
        String sql = "insert into "+table_name+" (";
        for(int i = 0; i < args.length; i++)
        {
            sql +=args[i]._name;
            if(i ==  args.length-1)
            {
                break;
            }
            else{ sql +=",";}
        }
        sql +=") values(";
        for(int i = 0; i < args.length; i++)
        {
            sql +="\""+args[i]._value+"\"";
            if(i ==  args.length-1)
            {
                break;
            }
            else{ sql +=",";}
        }
        sql +=");";
        DB.execSQL(sql);
    }
    public void updateDataOnTable(String table_name,QuerryArg[] newdata,QuerryArg key)
    {
        String sql = "update "+table_name+" set ";
        for(int i = 0; i < newdata.length; i++)
        {
            sql +=newdata[i]._name+" = \""+ newdata[i]._value+"\"";
            if(i ==  newdata.length-1)
            {
                break;
            }
            else{ sql +=", ";}
        }

        sql +=" where "+key._name+" = \""+key._value+"\"";
        sql +=";";
        DB.execSQL(sql);
    }
    public void DeleteRowOnTable(String table_name, QuerryArg key)
    {
      String sql = "delete from "+table_name+" where "+key._name+" = "+key._value+";";
       DB.execSQL(sql);

    }





}

