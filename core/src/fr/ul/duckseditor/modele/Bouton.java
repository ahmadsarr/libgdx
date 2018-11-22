package fr.ul.duckseditor.modele;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.modele.Monde;

import static fr.ul.duckseditor.datafactory.Constant.*;
public class Bouton extends Acteur {
    private Texture texture;
    public Bouton (Monde monde, int x, int y) {
        super(TRASH_WIDTH, TRASH_HEIGHT, monde);
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type= BodyDef.BodyType.StaticBody;
        this.body=monde.getWorld().createBody(bodyDef);
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(getLargeur(),getHauteur());
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=0f;
        fixtureDef.shape=shape;
        fixtureDef.restitution=0.1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    void draw(SpriteBatch sb) {
        sb.draw(texture,body.getPosition().x,body.getPosition().y,getLargeur(),getHauteur());
    }
}
