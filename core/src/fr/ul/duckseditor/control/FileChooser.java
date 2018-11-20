package fr.ul.duckseditor.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.ul.duckseditor.view.EditorScreen;

import java.util.List;

public class FileChooser {
    protected EditorScreen editorScreen;
    protected final String path="MadDuckFiles";
    protected List<String> files;
    protected int niveauCourant;
    public FileChooser()
    {

        setListFile();

    }
     void setListFile()
    {
        System.out.println(Gdx.files.getExternalStoragePath()+path);
        FileHandle dir= Gdx.files.absolute(Gdx.files.getExternalStoragePath()+path);
        if(!dir.exists())
        {
            System.out.println("Le dossier a ete cree");
            dir.mkdirs();

            niveauCourant=-1;
        }else {
            System.out.println("Le dossier exite deja");
            for (FileHandle f : dir.list())
            {
                niveauCourant=Math.max(Integer.parseInt(f.name().substring(f.name().indexOf('_'),f.name().length())),niveauCourant);
                files.add(f.name());
            }
        }

    }
    public void update()
    {

    }

    public EditorScreen getEditorScreen() {
        return editorScreen;
    }
}
