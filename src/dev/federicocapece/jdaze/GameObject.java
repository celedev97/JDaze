package dev.federicocapece.jdaze;

import dev.federicocapece.jdaze.collider.Collider;

import java.awt.*;

/**
 * A GameObject; it is a game element that will get updated by the engine.
 * it can be drawn onto the screen, moved and it includes collision checking.
 */
public abstract class GameObject {
    /**
     * The collider of this GameObject see Collider.
     */
    public Collider collider = null;

    /**
     * The position of the gameObject.
     * Updating this manually will actually move the gameObject,
     * but it is not reccomended since it will not check collisions.
     * You should always use the move method unless you have a valid reason to use this one.
     */
    public final Vector position = Vector.ZERO();

    /**
     * Create a gameObject at the position {0,0}
     */
    public GameObject() {
        this(Vector.ZERO());
    }

    /**
     * Create a gameObject setting its position
     * @param x The x coordinate of the position Vector
     * @param y The y coordinate of the position Vector
     */
    public GameObject(float x, float y){
        this(new Vector(x, y));
    }

    /**
     * Create a gameObject setting its position
     * @param position The position as a vector
     */
    public GameObject(Vector position){
        this.position.set(position);
        Engine.gameObjects.add(this);
        start();
    }

    //#region Methods that can be overridden


    /**
     * This method get executed once after the initialization of the gameObject
     */
    public void start(){}

    /**
     * This method get executed every frame as long as the GameObject exists
     */
    protected void update(){}

    /**
     * The method using for drawing this GameObject.
     * You can use in any way you like.
     * Keep in mind that x, y is the center of your GameObject position.
     * You should scale your object according to the camera zoom.
     *
     * @param graphics The java.awt.Graphics class for drawing
     * @param x the center x position on the screen of this gameObject
     * @param y the center y position on the screen of this gameObject
     * @param scale the scale of the object according to the camera
     */
    protected void draw(Graphics graphics, int x, int y, float scale){}


    /**
     * This method get called when a collision is recognized.
     * You need to override this to intercept collisions.
     * @param collider The other collider that touched this GameObject
     */
    protected void onCollisionEnter(Collider collider) {}

    //#endregion

    /**
     * Move the GameObject and checks collisions
     * @param offset the movement that will be done in this frame.
     */
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

    /**
     * Extrapolate this GameObject from inside another collider.
     * This ignores the possibility of collisions during the extrapolation for making this lighter.
     * @param collider the collider that's touching this object
     */
    private void extrapolate(Collider collider) {
        Vector extrapolateDirection = position.sub(collider.gameObject.position).normalize();
        position.sumUpdate(extrapolateDirection.multiplyUpdate(collider.size() + this.collider.size()));
    }


    /**
     * Check if this gameObject collides with another GameObject collider
     * @param collider the other GameObject collider
     * @return true/false
     */
    private boolean collide(Collider collider) {
        return this.collider != null && this.collider.collide(collider);
    }

}
