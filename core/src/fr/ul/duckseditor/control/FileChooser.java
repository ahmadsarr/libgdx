package fr.ul.duckseditor.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.ul.duckseditor.view.EditorScreen;

import java.util.List;

public class FileChooser {
    protected EditorScreen editorScreen;
    protected String path;
    protected List<String> files;
    protected int niveauCourant;
    public FileChooser()
    {
        if(Gdx.files.isExternalStorageAvailable())
        {
         FileHandle file= Gdx.files.external("test");
         if(!file.exists())
         {
             file.mkdirs();
         }
        }
    }
}
