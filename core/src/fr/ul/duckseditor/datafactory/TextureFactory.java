package fr.ul.duckseditor.datafactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {
    private static Texture background;
    private static Texture duck;
    private static Texture targetbeige;
    private static Texture targetblue;
    private static Texture block;
    private static Texture beam;
    private static Texture cancel;
    private static Texture panel;
    private static Texture load;
    private static Texture play;
    private static Texture save;
    private static Texture trash;
    private static Texture stop;
    private static Texture left;
    private static Texture rigth;
    private static Texture rewrite;

    public static void load()
    {
        background=new Texture(Gdx.files.internal("data/images/background.png"));
        duck=new Texture(Gdx.files.internal("data/images/duck.png"));
        targetbeige=new Texture(Gdx.files.internal("data/images/targetbeige.png"));
        targetblue=new Texture(Gdx.files.internal("data/images/targetblue.png"));
        block=new Texture(Gdx.files.internal("data/images/block.png"));
        beam=new Texture(Gdx.files.internal("data/images/beam.png"));
        cancel=new Texture(Gdx.files.internal("data/images/cancel.png"));
        panel=new Texture(Gdx.files.internal("data/images/editPanel.png"));
        load=new Texture(Gdx.files.internal("data/images/Load.png"));
        save=new Texture(Gdx.files.internal("data/images/Save.png"));
        save=new Texture(Gdx.files.internal("data/images/Trash.png"));
        stop=new Texture(Gdx.files.internal("data/images/Stop.png"));
        rigth=new Texture(Gdx.files.internal("data/images/rightarrow.png"));
        rewrite=new Texture(Gdx.files.internal("data/images/Rewrite.png"));
        left=new Texture(Gdx.files.internal("data/images/leftarrow.png"));
        play=new Texture(Gdx.files.internal("data/images/Play.png"));



    }

    public static Texture getBackground() {
        return background;
    }

    public static Texture getDuck() {
        return duck;
    }

    public static Texture getTargetbeige() {
        return targetbeige;
    }

    public static Texture getTargetblue() {
        return targetblue;
    }

    public static Texture getBlock() {
        return block;
    }

    public static Texture getBeam() {
        return beam;
    }

    public static Texture getCancel() {
        return cancel;
    }

    public static Texture getPanel() {
        return panel;
    }

    public static Texture getLoad() {
        return load;
    }

    public static Texture getPlay() {
        return play;
    }

    public static Texture getSave() {
        return save;
    }

    public static Texture getTrash() {
        return trash;
    }

    public static Texture getStop() {
        return stop;
    }

    public static Texture getLeft() {
        return left;
    }

    public static Texture getRigth() {
        return rigth;
    }

    public static Texture getRewrite() {
        return rewrite;
    }
}
