package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.duckseditor.datafactory.TextureFactory;

import static fr.ul.duckseditor.datafactory.Constant.*;
public class Rectangulaire extends Acteur {
    public Rectangulaire(Monde monde, BodyDef.BodyType type,float x,float y) {
        super(RECTANGLE_WIDTH,RECTANGLE_HEIGHT, monde);
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type=type;
        this.body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
        //shape.setRadius(RECTANGLE_HEIGHT);
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=0.1f;
        fixtureDef.shape=shape;
       // fixtureDef.restitution=0.1f;
        body.createFixture(fixtureDef);
        shape.dispose();
        this.type=getClass().toString();
        sprite=new Sprite(TextureFactory.getBeam());
        sprite.setSize(RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
        sprite.setPosition(x,y);
        sprite.setOrigin(0,0);
    }

    void draw(SpriteBatch sb) {
        // sb.draw(TextureFactory.getBlock(),body.getPosition().x,body.getPosition().y,CARRE_WIDTH,CARRE_WIDTH);
        if(body.getType()== BodyDef.BodyType.DynamicBody) {
            float angle=body.getAngle()* MathUtils.radiansToDegrees;
            if(angle<0)
                angle+=360;

            sprite.setRotation(angle);
            System.out.println(sprite.getRotation());

        }
        sprite.setPosition(body.getPosition().x,body.getPosition().y);
        sprite.draw(sb);
    }
}
