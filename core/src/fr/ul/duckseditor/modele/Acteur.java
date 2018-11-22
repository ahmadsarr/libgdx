package fr.ul.duckseditor.modele;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

abstract class Acteur implements Json.Serializable {
    protected float largeur;
    protected float hauteur;
    protected Monde monde;
    protected Body body=null;
    protected float x;
    protected  float y;
    protected   String type;
    protected Sprite sprite;
    public Acteur(float largeur,float hauteut,Monde monde)
    {
      this.largeur=largeur;
      this.hauteur=hauteut;
      this.monde=monde;

    }

    public Sprite getSprite() {
        return sprite;
    }

    abstract void draw(SpriteBatch sb);

    public float getLargeur() {
        return largeur;
    }

    public float getHauteur() {
        return hauteur;
    }

    public Monde getMonde() {
        return monde;
    }

    public Body getBody() {
        return body;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    public void setMonde(Monde monde) {
        this.monde = monde;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public float getAngle()
    {
        return body.getAngle();
    }
    @Override
    public void write(Json json) {
        json.writeObjectStart("coord");

        json.writeValue("x",getX());
        json.writeValue("y",getY());
        json.writeValue("angle",getAngle());
        json.writeObjectEnd();
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        Acteur acteur;
    }

}
