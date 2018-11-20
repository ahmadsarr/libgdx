package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.duckseditor.datafactory.Constant;


public class Object {
    private Monde monde;
    private Texture texture;
    private Body body;
    float width;
    float height;
    float x;
    float y;
    public Object(Monde monde, Texture texture, float x, float y, float width, float height, BodyDef.BodyType type)
    {
        this.texture=texture;
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        FixtureDef fixtureDef=new FixtureDef();
        BodyDef bodyDef=new BodyDef();
        bodyDef=new BodyDef();
        bodyDef.type=type;
        bodyDef.position.set(x,y);
        body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(width,height);
        fixtureDef=new FixtureDef();
        fixtureDef.density=0.1f;
        fixtureDef.shape=shape;
        body.createFixture(fixtureDef);

    }
    public void draw(SpriteBatch sb)
    {
        sb.draw(texture, x,y,width,height);
    }
    public Body getBody()
    {
        return body;
    }
}
