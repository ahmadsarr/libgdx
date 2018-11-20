package fr.ul.duckseditor.modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.control.FileChooser;
import fr.ul.duckseditor.datafactory.Constant;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.view.EditorScreen;

import java.util.ArrayList;
import java.util.List;

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

    private OrthographicCamera camera;
    private Listener listener;
    private List<Object> objectOnSurface=new ArrayList<Object>();
    private List<Body> toDelete=new ArrayList<Body>();

    public Monde(OrthographicCamera camera)
    {
        FileChooser f=new FileChooser();
        world=new World(new Vector2(0,-10f),true);
        this.camera=camera;
        listener=new Listener(this);
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
        Gdx.input.setInputProcessor(listener);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                duck.getBody().applyForceToCenter(0,MathUtils.random(20,50),true);

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

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Object getTargetBeige() {
        return targetBeige;
    }

    public Object getTargetBleu() {
        return targetBleu;
    }

    public Object getPlay() {
        return play;
    }

    public Object getStop() {
        return stop;
    }

    public Object getCancel() {
        return cancel;
    }

    public Object getSupprimer() {
        return supprimer;
    }

    public Object getTrash() {
        return trash;
    }

    public Object getSave() {
        return save;
    }

    public Object getLoad() {
        return load;
    }

    public Object getRewrite() {
        return rewrite;
    }

    public Object getLeft() {
        return left;
    }

    public Object getRigth() {
        return rigth;
    }

    public Listener getListener() {
        return listener;
    }

    public List<Object> getObjectOnSurface() {
        return objectOnSurface;
    }

    public List<Body> getToDelete() {
        return toDelete;
    }

    public void setToDelete(List<Body> toDelete) {
        this.toDelete = toDelete;
    }

    public Panel getPanel() {
        return panel;
    }
    public void render(float delta,SpriteBatch sb)
    {
        for(Body body:toDelete)
        {
           //Object object;

            //world.destroyBody(body);

        }
        getWorld().step(Gdx.graphics.getDeltaTime(),6,2);;
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
       for(Object o:objectOnSurface)
       {
           o.draw(sb);
       }

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
    public boolean isObjectInSurface(Body body)
    {
        for(Object object:objectOnSurface)
        {
            if(body==object.getBody())
            {
                return true;
            }
        }
        return false;
    }
    public void screenShot()
    {

    }
}
