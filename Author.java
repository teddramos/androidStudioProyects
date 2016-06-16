package Models;

import android.graphics.Bitmap;

/**
 * Created by Worker2 on 21/05/2016.
 */
public class Author {
    private  int id_author;
    private String name;
    private String last_name;
    private String email;
    private Bitmap image;

    public Author(){};
    public Author(int id, String name, String last_name, String email,Bitmap image)
    {
        this.setId_author(id);
        this.setName(name);
        this.setLast_name(last_name);
        this.setEmail(email);
        this.setImage(image);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }
}
