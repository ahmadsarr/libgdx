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
    private Duck duck;
    private Beam beam;
    private Panel panel;
    private Box2DDebugRenderer debugRenderer;
    public Monde()
    {
        world=new World(new Vector2(0,-10f),true);
        duck=new Duck(this);
        beam=new Beam(this);
        panel=new Panel(this);



    }

    public World getWorld() {
        return world;
    }

    public Duck getDuck() {
        return duck;
    }

    public Body getDuckBody() {
        return duckBody;
    }

    public Beam getBeam() {
        return beam;
    }

    public Panel getPanel() {
        return panel;
    }

    public void createGround()
    {

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type=BodyDef.BodyType.StaticBody;
       groundBodyDef.position.set(new Vector2(WORLD_WIDTH/2,45));
        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        //float[] pts={0,6,0,36,64,36,64,6};
        //groundBox.set(pts);
        groundBox.setAsBox(WORLD_WIDTH/2, 6);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }
}
