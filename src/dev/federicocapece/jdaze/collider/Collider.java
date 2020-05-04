package dev.federicocapece.jdaze.collider;

import dev.federicocapece.jdaze.GameObject;

public abstract class Collider {
    //TODO: javadoc

    protected GameObject gameObject;

    public Collider(GameObject gameObject){
        this.gameObject = gameObject;
    }

    public boolean collide(Collider collider){
        return  collide(collider, true);
    }

    protected abstract boolean collide(Collider collider, boolean fistTry);

}
