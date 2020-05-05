package dev.federicocapece.jdaze.collider;

import dev.federicocapece.jdaze.GameObject;
import dev.federicocapece.jdaze.Vector;

public class CircleCollider extends Collider {
    //TODO: javadoc

    private float ray;

    public CircleCollider(GameObject gameObject, float ray) {
        super(gameObject);
        this.ray = ray;
    }

    @Override
    public float size() {
        return ray;
    }

    @Override
    public boolean collide(Collider collider, boolean firstTry){
        if(collider.getClass() == this.getClass()){
            CircleCollider circle2 = (CircleCollider) collider;
            return (circle2.ray + this.ray)< Vector.distance(this.gameObject.position, collider.gameObject.position);
        }

        if(firstTry)
            return collider.collide(this, false);

        //both the colliders don't have a way to check the collision
        System.out.println(this.getClass()+" has no way to check collision with: "+collider.getClass());
        return false;
    }


}
