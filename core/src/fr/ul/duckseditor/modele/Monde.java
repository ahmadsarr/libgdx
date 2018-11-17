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
    private Bloc carre;
    private Bloc rectangle;
    private Panel panel;

    public Monde()
    {
        world=new World(new Vector2(0,-10f),true);
        duck=new Duck(this);
        border();
        carre=new Bloc(this,TextureFactory.getBlock(),WORLD_WIDTH/2,WORLD_HEIGTH,CARRE_WIDTH,CARRE_WIDTH);
        rectangle=new Bloc(this,TextureFactory.getBeam(),WORLD_WIDTH/2,WORLD_HEIGTH,RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
        panel=new Panel(this);
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

    public Bloc getCarre() {
        return carre;
    }

    public Bloc getRectangle() {
        return rectangle;
    }

    public Body getGroundBody() {
        return groundBody;
    }

    public Panel getPanel() {
        return panel;
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
