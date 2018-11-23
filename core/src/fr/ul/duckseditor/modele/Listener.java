package fr.ul.duckseditor.modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
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
import fr.ul.duckseditor.control.FileChooser;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.view.EditorScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static fr.ul.duckseditor.datafactory.Constant.*;

public class Listener implements InputProcessor {
    private boolean isPlaying=false;
    Monde monde;
    List<Body> hasBeenTouch=new ArrayList<Body>();
    private Body bodyMoved;
    private Vector2 depart=null;
    private  int btn=-1;
    private FileChooser fileChooser;
    private EditorScreen screen;
    public Listener(Monde monde,EditorScreen screen)
    {
        this.monde=monde;
        this.screen=screen;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE)
           Gdx.app.exit();
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
        btn=button;
        hasBeenTouch=new ArrayList<Body>();
        monde.getWorld().QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                hasBeenTouch.add(fixture.getBody());
                return true;
            }
        },v.x-0.00001f,v.y-0.00001f,v.x+0.00001f,v.y+0.00001f);
        for(Body body:hasBeenTouch) {
            Acteur o = null;
            //gerer les clicks sur les boutton;
            if (body == monde.getCarre().getBody()) {
                o =new Carre(monde, BodyDef.BodyType.DynamicBody,v.x,v.y);
            } else if (body == monde.getRectangle().getBody()) {
                o =new Rectangulaire(monde, BodyDef.BodyType.DynamicBody,v.x,v.y);
            }else if(body == monde.getBandit().getBody()){
                o=new Bandit(monde,v.x,v.y);
            }else if(body == monde.getPrisonnier().getBody()){
                o=new Prisonnier(monde,v.x,v.y);
            }else if(body==monde.getRewrite().getBody())
            {
                screen.rewrite();

            }else if(body==monde.getSave().getBody())
            {
                screen.save();
            }else if(body==monde.getLoad().getBody())
            {
                screen.reload();
            }else if(body==monde.getPlay().getBody())
            {
                isPlaying=!isPlaying;
                if(isPlaying) {
                    monde.getWorld().setGravity(new Vector2(0f, -10f));
                    monde.getPlay().getSprite().setTexture(TextureFactory.getStop());
                }else
                {
                    monde.getWorld().setGravity(new Vector2(0f, 0f));
                    monde.getPlay().getSprite().setTexture(TextureFactory.getPlay());
                }
            }
            else if(monde.isActeurInSurface(body)) {
                body.setUserData(new Vector2(v.x,v.y));
            }else
            {
                ;
            }
            if(o!=null)
                monde.getActeurOnSurface().add(o);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 v=monde.getCamera().unproject(new Vector3(screenX,screenY,0));
        final List<Body> selectedBody=new ArrayList<Body>();
        monde.getWorld().QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                selectedBody.add(fixture.getBody());
                return true;
            }
        },v.x-0.0001f,v.y-0.0001f,v.x+0.0001f,v.y+0.0001f);

        if(selectedBody.contains(monde.getTrash().getBody()))
        {
            monde.setToDelete(hasBeenTouch);
        }

        return true;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (Body bodyMoved:hasBeenTouch) {
            if (bodyMoved .getUserData()!= null) {
                Vector3 v = monde.getCamera().unproject(new Vector3(screenX, screenY, 0));
                Vector2 last = (Vector2) bodyMoved.getUserData();
                if(btn==0) {
                    bodyMoved.setTransform(v.x, v.y, 0);
                }else {
                    Vector2 vector2 = new Vector2(v.x, v.y);
                    bodyMoved.setUserData(vector2);
                    float dv=vector2.angleRad()-last.angleRad();
                    float angle= bodyMoved.getAngle()+dv;
                    bodyMoved.setTransform(bodyMoved.getPosition(),angle);
                }

            }
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

    public ScreenAdapter getScreen() {
        return screen;
    }

    public void setScreen(EditorScreen screen) {
        this.screen = screen;
    }
}
