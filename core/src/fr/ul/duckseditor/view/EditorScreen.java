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
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.modele.Monde;

public class EditorScreen extends ScreenAdapter  {
    public static  final int WORLD_WIDTH=800;
    public static  final int WORLD_HEIGTH=480;
    SpriteBatch sb;
    OrthographicCamera camera;
    private World world;
    private Monde monde;

    public EditorScreen() {
        TextureFactory.load();
        monde=new Monde();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,WORLD_WIDTH,WORLD_HEIGTH);
        sb=new SpriteBatch();
        sb.setProjectionMatrix(camera.combined);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monde.getWorld().step(Gdx.graphics.getDeltaTime(),6,2);;
        sb.begin();
        sb.draw(TextureFactory.getBackground(),0,0,WORLD_WIDTH,WORLD_HEIGTH);
        monde.getDuck().draw(sb);
        monde.getCarre().draw(sb);
        monde.getPanel().draw(sb);
        monde.getRectangle().draw(sb);

        sb.end();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }


}
