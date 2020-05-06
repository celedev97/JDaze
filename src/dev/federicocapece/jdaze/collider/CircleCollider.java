package dev.federicocapece.jdaze.collider;

import dev.federicocapece.jdaze.GameObject;
import dev.federicocapece.jdaze.Vector;

/**
 * The most basic collider implementation: a circle.
 */
public class CircleCollider extends Collider {

    /**
     * The ray of the circle
     */
    private float ray;

    /**
     * Create a circle collider for a GameObject
     * @param gameObject the GameObject that will be linked to this collider
     * @param ray the ray of the circle
     */
    public CircleCollider(GameObject gameObject, float ray) {
        super(gameObject);
        this.ray = ray;
    }


    /**
     * Return the size of this collider
     * @return the ray of the circle
     */
    @Override
    public float size() {
        return ray;
    }

    /**
     * Check if this collider collide with another collider
     * @param collider the other collider to check
     * @param firstTry a variable indicating if this collider is checking the collision because the Engine decided so
     *                 or if it is checking the collision because it has been required by the other collider.
     * @return true if they collide, false otherwise.
     */
    @Override
    public boolean collide(Collider collider, boolean firstTry){
        /*
         * this collider automatically delegate the collision check to the other one since it's a basic collider
         * and doesn't consider the existence of other colliders
         */
        if(firstTry)
            return collider.collide(this, false);

        //the other collider doesn't have a way to check collision with a CircleCollider, so i'm just gonna use it like a circle
        return (collider.size() + this.ray)< Vector.distance(this.gameObject.position, collider.gameObject.position);
    }

}
