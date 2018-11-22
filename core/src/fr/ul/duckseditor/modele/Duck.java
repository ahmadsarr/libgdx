package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.Constant;
import fr.ul.duckseditor.datafactory.TextureFactory;

public class Duck {
    private Monde monde;
    private Texture duck;
    private Body duckBody;
   public Duck(Monde monde)
   {
       duck= TextureFactory.getDuck();
       FixtureDef fixtureDef=new FixtureDef();
       PolygonShape shape=new PolygonShape();
       BodyDef bodyDef=new BodyDef();
       duck=TextureFactory.getDuck();
       bodyDef=new BodyDef();
       bodyDef.type=BodyDef.BodyType.DynamicBody;
       bodyDef.position.set(Constant.WORLD_WIDTH/2,Constant.WORLD_HEIGTH/2);
       duckBody=monde.getWorld().createBody(bodyDef);
       CircleShape sha=new CircleShape();
       sha.setRadius(Constant.DUCK_WIDTH/2);
       fixtureDef=new FixtureDef();
       fixtureDef.density=0.1f;
       fixtureDef.restitution=0.5f;
       fixtureDef.shape=sha;
       duckBody.createFixture(fixtureDef);
       sha.dispose();
   }
   public void draw(SpriteBatch sb)
   {
       sb.draw(duck,duckBody.getPosition().x,duckBody.getPosition().y,Constant.DUCK_WIDTH,Constant.DUCK_HEIGHT);
   }
   public Body getBody(){return duckBody;}
}
