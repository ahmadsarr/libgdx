package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.duckseditor.datafactory.TextureFactory;

import static fr.ul.duckseditor.datafactory.Constant.*;
public class Carre extends Acteur {
    Sprite sprite;
    public Carre(Monde monde, BodyDef.BodyType type,float x,float y) {
        super(CARRE_WIDTH, CARRE_WIDTH, monde);
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type=type;
        this.body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(CARRE_WIDTH,CARRE_WIDTH);
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=0.2f;
        fixtureDef.shape=shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        this.type=getClass().toString();
        sprite=new Sprite(TextureFactory.getBlock());
        sprite.setSize(CARRE_WIDTH,CARRE_WIDTH);
        sprite.setPosition(x,y);
        sprite.setOrigin(0,0);
       // sprite.setCenter(0,0);
    }

    @Override
    void draw(SpriteBatch sb) {
       // sb.draw(TextureFactory.getBlock(),body.getPosition().x,body.getPosition().y,CARRE_WIDTH,CARRE_WIDTH);
        if(body.getType()== BodyDef.BodyType.DynamicBody) {
            float angle=body.getAngle()*MathUtils.radiansToDegrees;
            if(angle<0)
                angle+=360;

            sprite.setRotation(angle);
             System.out.println(sprite.getRotation());

        }
        sprite.setPosition(body.getPosition().x,body.getPosition().y);
         sprite.draw(sb);
    }
}
