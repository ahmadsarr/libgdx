package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.TextureFactory;

import static fr.ul.duckseditor.datafactory.Constant.*;

public class ZoneDeJeu extends Acteur {
    public ZoneDeJeu(Monde monde) {
        super(WORLD_WIDTH,WORLD_HEIGTH,monde);
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(0,0);
        bodyDef.type= BodyDef.BodyType.StaticBody;
        this.body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
       float[] points={0,WORLD_HEIGTH/6,0,WORLD_HEIGTH,WORLD_WIDTH,WORLD_HEIGTH,WORLD_WIDTH,WORLD_HEIGTH/6};
       // shape.set(points);
        shape.setAsBox(WORLD_WIDTH,WORLD_HEIGTH/(6*2)+3);
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=1200;
        fixtureDef.shape=shape;
       fixtureDef.restitution=0.1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    void draw(SpriteBatch sb) {

    }

}
