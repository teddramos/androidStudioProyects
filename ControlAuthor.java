package Controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

import com.teddysoft.worker2.applicationcookmaster.AuthorActivity;

import java.util.ArrayList;
import java.util.List;

import DataPersistence.DBControlOperations;
import DataPersistence.QuerryArg;
import Models.Author;

/**
 * Created by Worker2 on 03/06/2016.
 */
public class ControlAuthor  {
    private DBControlOperations _control_db;
    private Author _my_author;

    public ControlAuthor(Context context) {
         _control_db = new DBControlOperations(context);
        _control_db.openDB();


    }


    public List<Author> getAllData() {
        List<Author> authors = new ArrayList<>();
        List<String[]> list = _control_db.getDatafromTable("Author",null);
        for(String[] data: list)
        {



            Author author = new Author(Integer.parseInt(data[0]),data[1],data[2],data[3],ControlRecipe.convertStringToBitmap(data[4]));
            authors.add(author);

        }

      return authors;

    }


    public void Insert() {
        Bitmap bmp = null;

        try

        {
            if(! get_my_author().getImage().equals(null))
            {
                bmp = get_my_author().getImage();
            }
        }
        catch (Exception ex)
        {
            bmp = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_4444);

        }

        QuerryArg args[]= {

                new QuerryArg("name",get_my_author().getName()),
                new QuerryArg("last_name",get_my_author().getLast_name()),
                new QuerryArg("email",get_my_author().getEmail()),
                new QuerryArg("image",ControlRecipe.convertBMPToString(bmp))
        };
        _control_db.insertDataOnTable("Author", args);
    }
    public void updateAuthor()
    {

        Bitmap bmp = null;

        try

        {
            if(! get_my_author().getImage().equals(null))
            {
                bmp = get_my_author().getImage();
            }
        }
        catch (Exception ex)
        {
            bmp = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_4444);

        }
        QuerryArg args[]= {

                new QuerryArg("name",get_my_author().getName()),
                new QuerryArg("last_name",get_my_author().getLast_name()),
                new QuerryArg("email",get_my_author().getEmail()),
                new QuerryArg("image",ControlRecipe.convertBMPToString(bmp))
        };

        _control_db.updateDataOnTable("Author", args, new QuerryArg("id_Author", get_my_author().getId_author() + ""));




    }

    public Author get_my_author() {
        return _my_author;
    }

    public void set_my_author(Author _my_author) {
        this._my_author = _my_author;
    }

}
