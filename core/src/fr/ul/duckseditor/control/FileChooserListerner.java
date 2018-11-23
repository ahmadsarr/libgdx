package fr.ul.duckseditor.control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.datafactory.TextureFactory;
import fr.ul.duckseditor.modele.*;

import java.util.ArrayList;
import java.util.List;

public class FileChooserListerner implements InputProcessor {
    OrthographicCamera camera;
    private List<Body>hasBeenTouch;
    private FileChooser fileChooser;
    private int nbClick;
    public FileChooserListerner(OrthographicCamera camera, FileChooser fileChooser)
    {
        this.camera=camera;
        this.fileChooser=fileChooser;
        nbClick=-1;
    }
    @Override
    public boolean keyDown(int keycode) {
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
        Vector3 v=camera.unproject(new Vector3(screenX,screenY,0));
        hasBeenTouch=new ArrayList<Body>();
        fileChooser.getWorld().QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                hasBeenTouch.add(fixture.getBody());
                return true;
            }
        },v.x-0.00001f,v.y-0.00001f,v.x+0.00001f,v.y+0.00001f);
        if(hasBeenTouch.size()==0)
        {

                fileChooser.setSelected(false);

        }
        for(Body body:hasBeenTouch)
        {
            if(body==fileChooser.getLeft().getBody()) {
                fileChooser.postview();
            } else if(body==fileChooser.getRigth().getBody())
            {
               fileChooser.preview();
            }
            else if(body==fileChooser.getCancel().getBody())
            {
                fileChooser.setNiveauCourant(fileChooser.getEditorScreen().level);
                fileChooser.setSelected(false);

            }

        }
        return true;
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
}
