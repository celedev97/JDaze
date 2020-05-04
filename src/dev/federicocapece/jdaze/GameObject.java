package dev.federicocapece.jdaze;

import dev.federicocapece.jdaze.collider.Collider;

import java.awt.*;

public abstract class GameObject {
    //TODO: javadoc

    public Collider collider = null;

    private Vector position;

    public Vector getPosition(){
        return position;
    }

    public GameObject() {
        this(Vector.ZERO());
    }

    public GameObject(float x, float y){
        this(new Vector(x, y));
    }

    public GameObject(Vector position){
        this.position = position;
        Engine.gameObjects.add(this);
    }

    protected void update(){}

    protected void draw(Graphics graphics, int x, int y, float scale){}

    public void move(Vector offset){
        position.sumUpdate(offset);
    }

}
