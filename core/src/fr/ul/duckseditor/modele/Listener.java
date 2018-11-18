package fr.ul.duckseditor.modele;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fr.ul.duckseditor.datafactory.TextureFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static fr.ul.duckseditor.datafactory.Constant.CARRE_WIDTH;
import static fr.ul.duckseditor.datafactory.Constant.RECTANGLE_HEIGHT;
import static fr.ul.duckseditor.datafactory.Constant.RECTANGLE_WIDTH;

public class Listener implements InputProcessor {
    Monde monde;
    List<Body> hasBeenTouch=new ArrayList<Body>();
    private Body bodyMoved;
    private Vector2 depart;
    private int initAngle;
    public Listener(Monde monde)
    {
        this.monde=monde;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE)
           ;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 v=monde.getCamera().unproject(new Vector3(screenX,screenY,0));
        hasBeenTouch=new ArrayList<Body>();
        monde.getWorld().QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                hasBeenTouch.add(fixture.getBody());
                System.out.println(hasBeenTouch.size());
                return true;
            }
        },v.x-3,v.y-6,v.x+3,v.y+6);
        for(Body body:hasBeenTouch) {
            Object o = null;
            if (body == monde.getCarre().getBody()) {
                o = new Object(monde, TextureFactory.getBlock(), v.x, v.y, CARRE_WIDTH, CARRE_WIDTH, BodyDef.BodyType.KinematicBody);
            } else if (body == monde.getRectangle().getBody()) {
                o = new Object(monde, TextureFactory.getBeam(), v.x, v.y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, BodyDef.BodyType.KinematicBody);
            } else if (body == monde.getTargetBeige().getBody()) {
                o = new Object(monde, TextureFactory.getTargetbeige(), v.x, v.y, CARRE_WIDTH, CARRE_WIDTH, BodyDef.BodyType.KinematicBody);

            }else if(body == monde.getTargetBleu().getBody()){
                o=new Object(monde,TextureFactory.getTargetblue(),v.x,v.y,CARRE_WIDTH,CARRE_WIDTH, BodyDef.BodyType.KinematicBody);
            }else if(body==monde.getLoad().getBody())
            {
                JFileChooser fileChooser=new JFileChooser();
                JFrame frame=new JFrame();
                frame.setVisible(true);
                int res=fileChooser.showOpenDialog(frame);
                frame.dispose();

            }else if(body==monde.getSave().getBody())
            {
                JFileChooser fileChooser=new JFileChooser();
                JFrame frame=new JFrame();
                frame.setVisible(true);
                int res=fileChooser.showSaveDialog(frame);
                frame.dispose();

            }else if(monde.isObjectInSurface(body)) {
                bodyMoved=body;
                depart=new Vector2(v.x,v.y);
            }else
            {
                System.out.println("pas de body");
            }
            if(o!=null)
            monde.getObjectOnSurface().add(o);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 vector3=monde.getCamera().unproject(new Vector3(screenX,screenY,0));
        if(bodyMoved!=null) {
            bodyMoved.setTransform(screenX, screenY, 20);
            System.out.println("bodyMoved non null");
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }

    public List<Body> getHasBeenTouch() {
        return hasBeenTouch;
    }
}
