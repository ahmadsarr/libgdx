package fr.ul.duckseditor.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Json;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.modele.Acteur;
import fr.ul.duckseditor.modele.Bouton;
import fr.ul.duckseditor.view.EditorScreen;
import static fr.ul.duckseditor.datafactory.Constant.*;
import java.util.ArrayList;
import java.util.List;

public class FileChooser {
    protected EditorScreen editorScreen;
    protected final String path=Gdx.files.getExternalStoragePath()+"MadDuckFiles\\";
    protected List<String> files;
    protected int niveauCourant;
    protected Bouton left;
    protected Bouton rigth;
    protected Bouton view;
    protected Bouton cancel;
    protected World world;
    protected boolean isSelected;
    protected Texture levelViewer;
    protected InputProcessor listerner;
    public FileChooser(EditorScreen screen)
    {
        world=new World(new Vector2(0,0),false);
        editorScreen=screen;
        setListFile();
        left=new Bouton(world,LEFT_X,LEFT_Y,TRASH_WIDTH,TRASH_HEIGHT);
        left.setTexture(TextureFactory.getLeft());
        rigth=new Bouton(world,RIGHT_X,RIGHT_Y,TRASH_WIDTH,TRASH_HEIGHT);
        rigth.setTexture(TextureFactory.getRigth());
        view=new Bouton(world,0,0,TRASH_WIDTH,TRASH_HEIGHT);
        levelViewer=null;
        if(files.size()>0) {
          levelViewer=new Texture(Gdx.files.absolute(path+"niveau_"+niveauCourant+".png"));
        }
        cancel=new Bouton(world,CANCEL_X,CANCEL_Y,TRASH_WIDTH,TRASH_HEIGHT);
        cancel.setTexture(TextureFactory.getCancel());
        isSelected=false;


    }

     void setListFile()
    {
        FileHandle dir= Gdx.files.absolute(path);
        files=new ArrayList<String>();
        niveauCourant=-1;
        if(!dir.exists())
        {
            dir.mkdirs();
        }else {
            for (FileHandle f : dir.list())
            {

               if(!files.contains(f.nameWithoutExtension())) {
                   files.add(f.nameWithoutExtension());
                   niveauCourant++;
               }
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
    public void view(int file)
    {

    }
    public void preview()
    {
        if(files.size()==0)
            return;
        try {
            niveauCourant++;
            levelViewer=new Texture(Gdx.files.absolute(path+files.get(niveauCourant)+".png"));
        }catch (IndexOutOfBoundsException i)
        {
            niveauCourant=0;
        }
    }
    public void postview()
    {
        if(files.size()==0)
            return;
        try {
            niveauCourant--;
            levelViewer=new Texture(Gdx.files.absolute(path+files.get(niveauCourant)+".png"));
        }catch (IndexOutOfBoundsException i)
        {
            niveauCourant=files.size()-1;
        }
    }
    public void draw(SpriteBatch sb)
    {
        if(levelViewer!=null)
        {
            sb.draw(levelViewer,3,3,WORLD_WIDTH-3,WORLD_HEIGTH);

        }
        left.draw(sb);
        rigth.draw(sb);
        cancel.draw(sb);
       /* ShapeRenderer shapeRenderer=new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(220,220,225,1);
        float[] points=new float[]{
                Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/6,
                Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/6,
                Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/6,
                Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/6
        };
        shapeRenderer.polygon(points);

        shapeRenderer.end();*/
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

    public void setEditorScreen(EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public Bouton getLeft() {
        return left;
    }

    public void setLeft(Bouton left) {
        this.left = left;
    }

    public Bouton getRigth() {
        return rigth;
    }

    public void setRigth(Bouton rigth) {
        this.rigth = rigth;
    }

    public Bouton getView() {
        return view;
    }

    public void setView(Bouton view) {
        this.view = view;
    }

    public Bouton getCancel() {
        return cancel;
    }

    public void setCancel(Bouton cancel) {
        this.cancel = cancel;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
       editorScreen.select();
    }

    public Texture getLevelViewer() {
        return levelViewer;
    }

    public void setLevelViewer(Texture levelViewer) {
        this.levelViewer = levelViewer;
    }

}
