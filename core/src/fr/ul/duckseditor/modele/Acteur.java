package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

abstract class Acteur {
    protected float largeur;
    protected float hauteur;
    protected Monde monde;
    protected Body body=null;

    public Acteur(float largeur,float hauteut,Monde monde)
    {
      this.largeur=largeur;
      this.hauteur=hauteut;
      this.monde=monde;

    }
    abstract void draw(SpriteBatch sb);
}
