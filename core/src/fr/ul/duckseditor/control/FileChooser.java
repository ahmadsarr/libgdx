package fr.ul.duckseditor.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.ul.duckseditor.view.EditorScreen;

import java.util.ArrayList;
import java.util.List;

public class FileChooser {
    protected EditorScreen editorScreen;
    protected final String path=Gdx.files.getExternalStoragePath()+"MadDuckFiles\\";
    protected List<String> files;
    protected int niveauCourant;
    public FileChooser()
    {

        setListFile();

    }
     void setListFile()
    {
        FileHandle dir= Gdx.files.absolute(path);
        files=new ArrayList<String>();
        if(!dir.exists())
        {
            System.out.println("Le dossier a ete cree");
            dir.mkdirs();

            niveauCourant=-1;
        }else {
            System.out.println("Le dossier exite deja");
            for (FileHandle f : dir.list())
            {
               niveauCourant=Math.max(Integer.parseInt(""+f.name().substring(f.name().indexOf('_')+1,f.name().lastIndexOf('.'))),niveauCourant);
                files.add(f.name());
            }
        }

    }
    public List<String> update()
    {
        niveauCourant++;
        FileHandle mdl=new FileHandle( getPath()+"niveau_"+niveauCourant+".mdl");
        FileHandle png=new FileHandle( getPath()+"niveau_"+niveauCourant+".png");
        return files;
    }

    public EditorScreen getEditorScreen() {
        return editorScreen;
    }

    public String getPath() {
        return path;
    }

    public int getNiveauCourant() {
        return niveauCourant;
    }

    public void setNiveauCourant(int niveauCourant) {
        this.niveauCourant = niveauCourant;
    }
}
