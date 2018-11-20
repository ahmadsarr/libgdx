package fr.ul.duckseditor.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.modele.Monde;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditorScreen extends ScreenAdapter  {
    public static  final int WORLD_WIDTH=800;
    public static  final int WORLD_HEIGTH=480;
    SpriteBatch sb;
    OrthographicCamera camera;
    private World world;
    private Monde monde;
    private FitViewport vp;
    public EditorScreen() {
        TextureFactory.load();
        camera=new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGTH);
        monde=new Monde(camera);
        //camera.setToOrtho(false,);
        sb=new SpriteBatch();


    }

    @Override
    public void render(float delta) {
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0);

        monde.getToDelete().clear();;
       
        sb.setProjectionMatrix(camera.combined);
        camera.update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monde.getWorld().step(Gdx.graphics.getDeltaTime(),6,2);;
        sb.begin();
        sb.draw(TextureFactory.getBackground(),0,0,WORLD_WIDTH,WORLD_HEIGTH);
        monde.render(delta,sb);
        sb.end();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }


}
