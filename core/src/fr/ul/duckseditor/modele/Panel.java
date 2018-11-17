package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.Constant;
import fr.ul.duckseditor.datafactory.TextureFactory;

public class Panel {

    private Monde monde;
    private Texture texture;
    private Body body;
    public Panel(Monde monde)
    {
        this.texture=TextureFactory.getPanel();
        FixtureDef fixtureDef=new FixtureDef();
        BodyDef bodyDef=new BodyDef();
        bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);
        body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(Constant.WORLD_WIDTH/6,Constant.WORLD_HEIGTH);
        fixtureDef=new FixtureDef();
        fixtureDef.density=3;
        fixtureDef.shape=shape;
        body.createFixture(fixtureDef);

    }
    public void draw(SpriteBatch sb)
    {
        sb.draw(texture,0,0, Constant.WORLD_WIDTH/6,Constant.WORLD_HEIGTH);
    }
    public Body getBody()
    {
        return body;
    }
}
