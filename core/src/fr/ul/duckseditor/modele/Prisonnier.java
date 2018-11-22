package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.duckseditor.datafactory.TextureFactory;

import static fr.ul.duckseditor.datafactory.Constant.*;
public class Prisonnier extends Acteur {
    public Prisonnier(Monde monde,int x,int y) {
        super(PERSONNAGE_RAYON,PERSONNAGE_RAYON, monde);
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type= BodyDef.BodyType.StaticBody;
        this.body=monde.getWorld().createBody(bodyDef);
        CircleShape shape=new CircleShape();
        shape.setRadius(PERSONNAGE_RAYON);
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=0.2f;
        fixtureDef.shape=shape;
        fixtureDef.restitution=0.1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getDuck(),body.getPosition().x,body.getPosition().y,PERSONNAGE_RAYON,PERSONNAGE_RAYON);
    }
}
