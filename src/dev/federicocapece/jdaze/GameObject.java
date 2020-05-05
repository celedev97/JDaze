package dev.federicocapece.jdaze;

import dev.federicocapece.jdaze.collider.Collider;

import java.awt.*;

public abstract class GameObject {
    //TODO: javadoc

    public Collider collider = null;
    public final Vector position = Vector.ZERO();

    public GameObject() {
        this(Vector.ZERO());
    }

    public GameObject(float x, float y){
        this(new Vector(x, y));
    }

    public GameObject(Vector position){
        this.position.set(position);
        Engine.gameObjects.add(this);
        start();
    }

    //#region Methods that can be overridden

    public void start(){}

    protected void update(){}

    protected void draw(Graphics graphics, int x, int y, float scale){}

    protected void onCollisionEnter(Collider collider) {}

    //#endregion

    public final void move(Vector offset){
        //move the item
        position.sumUpdate(offset);

        if(this.collider == null) return;

        //check collisions
        for(GameObject gameObject : Engine.gameObjects){
            if(gameObject.collider == null) continue;

            if(gameObject.collide(this.collider)){
                //fire collision for both the objects
                onCollisionEnter(gameObject.collider);
                gameObject.onCollisionEnter(this.collider);
                //TODO: make it so that the other gameObject move won't register the collision with this if it's already registered
                //probably the best way is to keep a list of the collision of the last frame, that gets reset at every frame?
                extrapolate(gameObject.collider);
            }
        }


    }

    private void extrapolate(Collider collider) {
        Vector extrapolateDirection = position.sub(collider.gameObject.position).normalize();
        position.sumUpdate(extrapolateDirection.multiplyUpdate(collider.size() + this.collider.size()));
    }

    private boolean collide(Collider collider) {
        return this.collider != null && this.collider.collide(collider);
    }

}
