package fr.ul.duckseditor.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.ul.duckseditor.control.FileChooser;
import fr.ul.duckseditor.control.FileChooserListerner;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.modele.Acteur;
import fr.ul.duckseditor.modele.Listener;
import fr.ul.duckseditor.modele.Monde;
import javafx.stage.Stage;

import java.util.ArrayList;
import static fr.ul.duckseditor.datafactory.Constant.*;
public class EditorScreen extends ScreenAdapter  {

    SpriteBatch sb;
    OrthographicCamera camera;
    private World world;
    private Monde monde;
    private FitViewport vp;
    private FileChooser fileChooser;
    public int level=-1;
    private InputProcessor[] listener=new InputProcessor[2];
    public EditorScreen() {
        TextureFactory.load();
        camera=new OrthographicCamera();
        vp=new FitViewport(WORLD_WIDTH,WORLD_HEIGTH,camera);
        vp.apply();
        monde=new Monde(camera);
        //camera.setToOrtho(false,);
        sb=new SpriteBatch();
        camera.update();
        fileChooser=new FileChooser(this);
        level=fileChooser.getNiveauCourant();

          listener[0]=new FileChooserListerner(camera,fileChooser);
          listener[1]=new Listener(monde,this);
    }

    @Override
    public void render(float delta) {
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0);
        sb.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        if(fileChooser.isSelected())
        {
            fileChooser.draw(sb);
            Gdx.input.setInputProcessor(listener[0]);

        }else {
            sb.draw(TextureFactory.getBackground(), 0, 0, WORLD_WIDTH, WORLD_HEIGTH);

                monde.render(delta, sb);
                monde.load(fileChooser.getPath() + fileChooser.getFiles().get(fileChooser.getNiveauCourant()) + ".mdl");
                level = fileChooser.getNiveauCourant();
                 Gdx.input.setInputProcessor(listener[1]);
        }
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width,height);
        camera.position.set(camera.viewportWidth /2,camera.viewportHeight/2,0);
        camera.update();
    }
    public void save()
    {
        int niveau=fileChooser.update().size();
        fileChooser.setNiveauCourant(niveau);
        monde.save(fileChooser.getPath()+"niveau_"+niveau);
    }
    public void rewrite()
    {
        int niveau=fileChooser.getNiveauCourant();
        monde.save((fileChooser.getPath()+"niveau_"+niveau));
    }
    public void select()
    {
        int niveau=fileChooser.getNiveauCourant();
        if(niveau!=level)
        {
            monde.load(fileChooser.getPath()+fileChooser.getFiles().get(niveau)+".mdl");
        }
    }
    public void reload()
    {
        fileChooser.setSelected(true);
    }
}
