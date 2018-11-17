package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.Constant;
import fr.ul.duckseditor.datafactory.TextureFactory;

public class Bloc {
    private Monde monde;
    private Texture texture;
    private Body body;
    public Bloc(Monde monde,Texture texture,float x,float y,float width,float height)
    {
        this.texture=texture;
        FixtureDef fixtureDef=new FixtureDef();
        BodyDef bodyDef=new BodyDef();
        bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x,y);
        body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(width,height);
        fixtureDef=new FixtureDef();
        fixtureDef.density=3;
        fixtureDef.shape=shape;
        body.createFixture(fixtureDef);

    }
    public void draw(SpriteBatch sb)
    {
        sb.draw(texture,body.getPosition().x,body.getPosition().y, Constant.DUCK_WIDTH,Constant.DUCK_HEIGHT);
    }
    public Body getBody()
    {
        return body;
    }
}
