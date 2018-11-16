package fr.ul.duckseditor.modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.view.EditorScreen;
import static  fr.ul.duckseditor.view.EditorScreen.*;

public class Monde {
    private World world;
    private Body duckBody;
    private Texture duck;
    private Box2DDebugRenderer debugRenderer;
    public Monde()
    {
        world=new World(new Vector2(0,-10f),true);



        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        BodyDef bodyDef=new BodyDef();
        duck=TextureFactory.getDuck();
        bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(duck.getWidth()/2,60+duck.getHeight());
        duckBody=world.createBody(bodyDef);
         CircleShape sha=new CircleShape();
         sha.setRadius(3f);

        fixtureDef=new FixtureDef();
        fixtureDef.density=10;
        fixtureDef.shape=sha;
        duckBody.createFixture(fixtureDef);



    }

    public World getWorld() {
        return world;
    }

    public Texture getDuck() {
        return duck;
    }

    public Body getDuckBody() {
        return duckBody;
    }
    public void createGround()
    {

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type=BodyDef.BodyType.KinematicBody;
       groundBodyDef.position.set(new Vector2(WORLD_WIDTH/2,6));
        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        //float[] pts={0,6,0,36,64,36,64,6};
        //groundBox.set(pts);
        groundBox.setAsBox(WORLD_WIDTH/2, 6);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
    }
}
