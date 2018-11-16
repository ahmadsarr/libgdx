package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.TextureFactory;

public class Panel {
    private Monde monde;
    private Texture beam;
    private Body body;
    public Panel(Monde monde)
    {
        this.monde=monde;
        World world=monde.getWorld();
        //debugRenderer =new Box2DDebugRenderer();
        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        BodyDef bodyDef=new BodyDef();
        beam= TextureFactory.getPanel();
        bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(beam.getWidth()/2,60+beam.getHeight());
        body=world.createBody(bodyDef);
        CircleShape sha=new CircleShape();
        sha.setRadius(20f);

        fixtureDef=new FixtureDef();
        fixtureDef.density=10;
        fixtureDef.shape=sha;
        body.createFixture(fixtureDef);

    }
    public void draw(SpriteBatch sb)
    {
        sb.draw(beam,0,0);
    }

    public Body getDuckBody() {
        return body;
    }
}
