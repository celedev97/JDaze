package dev.federicocapece.jdaze.collider;

import dev.federicocapece.jdaze.GameObject;

/**
 * A GameObject Collider.
 * A Collider is an object that retains a physical shape for the gameObject and while
 * the Object moves it check for contacts with other colliders, sending the result to the
 * method onCollisionEnter of both the GameObjects that are involved with the collision.
 */
public abstract class Collider {

    /**
     * The collider to witch this collider is attached
     */
    public final GameObject gameObject;

    /**
     * Create a collider attached to a gameObject
     * @param gameObject the gameObject to witch this collider should be attached
     */
    public Collider(GameObject gameObject){
        this.gameObject = gameObject;
    }

    /**
     * This method checks if this collider collide with the other one.
     * Id this collider can't solve the collision it will send the check to the other collider.
     * @param collider
     * @return a float representing the size of the collider
     */
    public boolean collide(Collider collider){
        return  collide(collider, true);
    }

    /**
     * This method checks if this collider collide with the other one.
     * <pre>
     * If you need to implement this:
     * - Check the other collider type and implement the collision between the collider you can
     * - If you don't recognize the collider and it's the firstTry then use the other collider's collide method.
     * - If it's not the firstTry (alas the other collider asked to your collider to solve the collision)
     *  simply check the size and compare it with the distance.
     * </pre>
     * @param collider
     * @param fistTry
     * @return a float representing the size of the collider
     */
    protected abstract boolean collide(Collider collider, boolean fistTry);


    /**
     * The size of the collider, used for preemptive non-precise collision checks
     * This should ideally be the size of a circle that can inscribe the whole collider
     * @return a float representing the size of the collider
     */
    public abstract float size();
}
