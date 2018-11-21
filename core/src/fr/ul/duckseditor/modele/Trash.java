package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.duckseditor.datafactory.TextureFactory;

import static fr.ul.duckseditor.datafactory.Constant.*;
public class Trash extends Acteur {
    public Trash (Monde monde,int x,int y) {
        super(CARRE_WIDTH, CARRE_WIDTH, monde);
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type= BodyDef.BodyType.StaticBody;
        this.body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=0f;
        fixtureDef.shape=shape;
        fixtureDef.restitution=0.1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getBlock(),body.getPosition().x,body.getPosition().y,RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
    }
}
