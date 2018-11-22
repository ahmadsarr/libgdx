package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import fr.ul.duckseditor.datafactory.Constant;
import fr.ul.duckseditor.datafactory.TextureFactory;
import static fr.ul.duckseditor.datafactory.Constant.CARRE_WIDTH;

public class Panel {

    private Monde monde;
    private Texture texture;
    public final int posX=0;
    public final int posY=0;
    public final float width=Constant.WORLD_WIDTH/9;
    public final float height=Constant.WORLD_HEIGTH;


    public Panel(Monde monde)
    {
        this.texture=TextureFactory.getPanel();
    }
    public void draw(SpriteBatch sb)
    {
       sb.draw(texture,0,0,width,height);

    }


}
