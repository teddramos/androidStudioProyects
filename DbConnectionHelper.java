package DataPersistence;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Worker2 on 23/05/2016.
 */
public class DbConnectionHelper  extends SQLiteOpenHelper{

    //The Android's default system path of your application database.
    private final Context myContext;

    private static String DB_NAME = "mydbONE";

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase myDataBase;



    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DbConnectionHelper(Context context) {

        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
            //to modify database or table change to if(!dbExsit)...

            //metrics..: TEASPOON,TEASPOONS,TABLESPOON,TABLESPOONS,CUP,CUPS
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                String myPath =myContext.getDatabasePath(DB_NAME).getPath() ;
                myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

                //create Tables...
              myDataBase.execSQL("DROP TABLE RECIPE;");
              myDataBase.execSQL("DROP TABLE INGREDIENT;");
              myDataBase.execSQL("DROP TABLE AUTHOR;");
              myDataBase.execSQL("DROP TABLE METRIC;");
                myDataBase.execSQL("CREATE TABLE RECIPE(ID_RECIPE INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                        "DESCRIPTION TEXT NOT NULL,"+
                                                        "IMAGE BLOB,"+
                                                        "ID_AUTHOR INTEGER," +
                                                        "RECIPE_DATE DATE);");
                myDataBase.execSQL("CREATE TABLE INGREDIENT(ID_INGREDIENT  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                            "DESCRIPTION TEXT NOT NULL,"+
                                                            "METRIC TEXT,"+
                                                            "QUANTITY REAL,"+
                                                            "ID_RECIPE INTEGER," +
                                                            "PREPARATION TEXT);");
                myDataBase.execSQL("CREATE TABLE AUTHOR(ID_AUTHOR  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                        "NAME TEXT NOT NULL,"+
                                                        "LAST_NAME NOT NULL,"+
                                                        "EMAIL TEXT,"+
                                                        "IMAGE BLOB);");
                myDataBase.execSQL("CREATE TABLE METRIC(ID_METRIC  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                        "DESCRIPTION TEXT NOT NULL);");

                //inserts a new author to be updated with de owner real info...

                myDataBase.execSQL("INSERT INTO AUTHOR(NAME,LAST_NAME,EMAIL) VALUES (\"YOUR NAME\",\"YOUR LAST" +
                                                        " NAME\",\"YOUR_EMAIL@EMAIL.COM\");");


            } catch (SQLException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = myContext.getDatabasePath(DB_NAME).getPath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(myContext.getDatabasePath(DB_NAME).getPath());

        // Path to the just created empty db
        String outFileName = myContext.getFilesDir().getPath() +"/" + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath =myContext.getDatabasePath(DB_NAME).getPath() ;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}