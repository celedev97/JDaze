package dev.federicocapece.jdaze;

import dev.federicocapece.jdaze.collider.Collider;

import java.awt.*;

public abstract class GameObject {
    //TODO: javadoc

    public Collider collider = null;
    public final Vector position;

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

    //#region Methods that can be overridden

    protected void update(){}

    protected void draw(Graphics graphics, int x, int y, float scale){}

    //#endregion

    public final void move(Vector offset){
        position.sumUpdate(offset);
    }

}
