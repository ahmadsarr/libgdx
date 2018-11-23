package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import fr.ul.duckseditor.datafactory.TextureFactory;

import static fr.ul.duckseditor.datafactory.Constant.*;
public class Lanceur extends Acteur {
    public Lanceur(Monde monde, BodyDef.BodyType type) {
        super(RECTANGLE_WIDTH,RECTANGLE_HEIGHT, monde);
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(LANCEUR_X,LANCEUR_Y);
        bodyDef.type=type;
        this.body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=0.2f;
        fixtureDef.shape=shape;
        fixtureDef.restitution=0.1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getBeam(),body.getPosition().x,body.getPosition().y,RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
    }

}
