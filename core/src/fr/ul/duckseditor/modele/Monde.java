package fr.ul.duckseditor.modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.Constant;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.view.EditorScreen;

import static fr.ul.duckseditor.datafactory.Constant.*;

public class Monde {
    private World world;
    private Duck duck;
    private Body groundBody;

    private Object carre;
    private Object rectangle;

    private Object targetBeige;
    private Object targetBleu;

    private Object play;
    private Object stop;

    private  Object cancel;
    private  Object supprimer;
    private  Object trash;

    private  Object save;
    private  Object load;
    private  Object rewrite;

    private Object left;
    private Object rigth;
    private Panel panel;


    public Monde()
    {
        world=new World(new Vector2(0,-10f),true);
        duck=new Duck(this);
        border();
        panel=new Panel(this);
        carre=new Object(this,TextureFactory.getBlock(),0,0,CARRE_WIDTH,CARRE_WIDTH, BodyDef.BodyType.StaticBody);
        rectangle=new Object(this,TextureFactory.getBeam(),carre.width+10,carre.y,RECTANGLE_WIDTH,RECTANGLE_HEIGHT, BodyDef.BodyType.StaticBody);

        targetBeige=new Object(this,TextureFactory.getTargetbeige(),carre.x,rectangle.height+rectangle.y+10,CARRE_WIDTH,CARRE_WIDTH, BodyDef.BodyType.StaticBody);
        targetBleu=new Object(this,TextureFactory.getTargetblue(),targetBeige.x+targetBeige.width+10,rectangle.height+rectangle.y+10,CARRE_WIDTH,CARRE_WIDTH, BodyDef.BodyType.StaticBody);

        play=new Object(this,TextureFactory.getPlay(),0,targetBeige.height+targetBeige.y+40,CARRE_WIDTH/2,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);
        stop=new Object(this,TextureFactory.getStop(),play.x+targetBeige.width+10,play.y,CARRE_WIDTH/2,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);
        trash=new Object(this,TextureFactory.getTrash(),panel.width/4,stop.height+stop.y+10,CARRE_WIDTH/2,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);

        load=new Object(this,TextureFactory.getLoad(),0,trash.height+trash.y+40,CARRE_WIDTH/2,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);
        rewrite=new Object(this,TextureFactory.getRewrite(),play.x+targetBeige.width+10,load.y,CARRE_WIDTH/2,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);
        save=new Object(this,TextureFactory.getSave(),panel.width/4,rewrite.height+rewrite.y+10,CARRE_WIDTH/2,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);

        left=new Object(this,TextureFactory.getLeft(),0,panel.height-50,CARRE_WIDTH,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);
        rigth=new Object(this,TextureFactory.getRigth(),left.width+10,panel.height-50,CARRE_WIDTH,CARRE_WIDTH/2, BodyDef.BodyType.StaticBody);



        float posX=panel.width/2;
        float posY=panel.height-TextureFactory.getCancel().getWidth();
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.RIGHT)
                    duck.getBody().setLinearVelocity(10f, 0f);
                if(keycode == Input.Keys.LEFT)
                    duck.getBody().setLinearVelocity(-10f,0f);
                if(keycode == Input.Keys.UP)
                    duck.getBody().applyForceToCenter(0f,10f,true);
                if(keycode == Input.Keys.DOWN)
                    duck.getBody().applyForceToCenter(0f, -10f, true);
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                duck.getBody().applyForceToCenter(0,MathUtils.random(20,50),true);
                System.out.println("ici");
                //duck.getBody().
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    public World getWorld() {
        return world;
    }

    public Duck getDuck() {
        return duck;
    }

    public Object getCarre() {
        return carre;
    }

    public Object getRectangle() {
        return rectangle;
    }

    public Body getGroundBody() {
        return groundBody;
    }

    public Panel getPanel() {
        return panel;
    }
    public void render(float delta,SpriteBatch sb)
    {
       panel.draw(sb);
       duck.draw(sb);
//       cancel.draw(sb);
       carre.draw(sb);
       rectangle.draw(sb);
       targetBeige.draw(sb);
       targetBleu.draw(sb);
       play.draw(sb);
       stop.draw(sb);
       trash.draw(sb);
       save.draw(sb);
       load.draw(sb);
       rewrite.draw(sb);
       left.draw(sb);
       rigth.draw(sb);
    }

    public void border()
    {

        BodyDef bodyDef=new BodyDef();
        bodyDef.type=BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, Constant.WORLD_HEIGTH/6);
        PolygonShape shape=new PolygonShape();
        float[] points={0,WORLD_HEIGTH/6,0,WORLD_HEIGTH,WORLD_WIDTH,WORLD_HEIGTH,WORLD_WIDTH,WORLD_HEIGTH/6};
        shape.set(points);
        shape.setAsBox(WORLD_WIDTH,6);
        groundBody=world.createBody(bodyDef);
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=0;
        fixtureDef.shape=shape;
        groundBody.createFixture(fixtureDef);
        shape.dispose();
    }
}
