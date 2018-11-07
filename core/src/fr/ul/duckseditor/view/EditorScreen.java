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

public class EditorScreen extends ScreenAdapter {
    public static  final int WORLD_WIDTH=800;
    public static  final int WORLD_HEIGTH=480;
    SpriteBatch sb;
    OrthographicCamera camera;
    private World world;
    private Body duckBody;
    private Sprite duck;

    public EditorScreen() {
        TextureFactory.load();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,WORLD_WIDTH,WORLD_HEIGTH);
        sb=new SpriteBatch();
        world=new World(new Vector2(0f,-5f),false);
        sb.setProjectionMatrix(camera.combined);
        duck=new Sprite(TextureFactory.getDuck());
        duck.setPosition(WORLD_WIDTH/2f,WORLD_HEIGTH/2f);
        createBodyDuck();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(Gdx.graphics.getDeltaTime(),6,2);
        duck.setPosition(duckBody.getPosition().x,duckBody.getPosition().y);
        sb.begin();
        sb.draw(TextureFactory.getBackground(),0,0,WORLD_WIDTH,WORLD_HEIGTH);
        duck.draw(sb);
        sb.end();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

   public void createBodyDuck()
   {
       BodyDef bodyDef=new BodyDef();
       bodyDef.type=BodyDef.BodyType.DynamicBody;
       bodyDef.position.set(duck.getX()+duck.getWidth()/2,duck.getY()+duck.getHeight()/2);
       duckBody=world.createBody(bodyDef);
       PolygonShape shape=new PolygonShape();
       shape.setAsBox(duck.getWidth()/2,duck.getHeight()/2);
       FixtureDef fixtureDef=new FixtureDef();
       fixtureDef.density=20;
       fixtureDef.shape=shape;
       duckBody.createFixture(fixtureDef);


   }
}
