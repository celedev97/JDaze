package dev.federicocapece.jdaze;


/**
 * The Vector class, it is used to represent both positions and physical vectors.
 * It includes some basic vectors math.
 */
public class Vector {
    /**
     * The x coordinate of the Vector
     */
    private float x;
    /**
     * The y coordinate of the Vector
     */
    private float y;

    //#region Static methods used as constants

    /**
     * This method gives you a new instance of a vector with
     * {0, 0} as coordinates
     * @return a new instance of the ZERO vector
     */
    public static Vector ZERO () { return new Vector( 0,  0);}

    /**
     * This method gives you a new instance of a vector with
     * {0, -1} as coordinates
     * @return a new instance of the UP vector
     */
    public static Vector UP   () { return new Vector( 0, -1);}

    /**
     * This method gives you a new instance of a vector with
     * {0, 1} as coordinates
     * @return a new instance of the DOWN vector
     */
    public static Vector DOWN () { return new Vector( 0,  1);}

    /**
     * This method gives you a new instance of a vector with
     * {-1, 0} as coordinates
     * @return a new instance of the LEFT vector
     */
    public static Vector LEFT () { return new Vector(-1,  0);}

    /**
     * This method gives you a new instance of a vector with
     * {1, 0} as coordinates
     * @return a new instance of the RIGHT vector
     */
    public static Vector RIGHT() { return new Vector(+1,  0);}

    //#endregion

    //#region Constructors

    /**
     * Create a Vector from its coordinates
     * @param x the x coordinate of the vector
     * @param y the y coordinate of the vector
     */
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a Vector by copying the coordinates of another Vector
     * @param vectorToCopy the vector that will be copied
     */
    public Vector(Vector vectorToCopy) {
        this.x = vectorToCopy.x;
        this.y = vectorToCopy.y;
    }

    //#endregion

    //#region Utilities

    /**
     * Calculate the distance between two Vectors
     * @param point1 The first Vector
     * @param point2 The second Vector
     * @return the distance
     */
    public static float distance(Vector point1, Vector point2) {
        return point1.sub(point2).magnitude();
    }
    //#endregion

    //#region Setters

    /**
     * Set this Vector coordinates.
     * @param x the new value of the x coordinate
     * @param y the new value of the y coordinate
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set this Vector coordinates using another Vector.
     * <pre>
     * Doing:
     * vector1.set(vector2);
     * It's almost the same as doing:
     * vector1 = new Vector(vector2);
     * </pre>
     * @param vectorToCopy the vector that should be copied
     */
    public void set(Vector vectorToCopy) {
        this.x = vectorToCopy.x;
        this.y = vectorToCopy.y;
    }

    /** Set the x coordinate of this Vector
     * @param x the new x coordinate value
     */
    public void setX(float x) {
        this.x = x;
    }

    /** Set the y coordinate of this Vector
     * @param y the new y coordinate value
     */
    public void setY(float y) {
        this.y = y;
    }

    //#endregion

    //#region Getters

    /**
     * Return the value of the x coordinate of this Vector
     * @return the x coordinate value
     */
    public float getX() {
        return x;
    }

    /**
     * Return the value of the y coordinate of this Vector
     * @return the y coordinate value
     */
    public float getY() {
        return y;
    }

    //#endregion

    //#region Vector Math

    //#region Sum

    /**
     * Calculate the sum between this vector and another one.
     * This doesn't edit the vectors.
     * @param vector the second vector
     * @return the sum
     */
    public Vector sum(Vector vector){
        return new Vector(x + vector.x, y + vector.y);
    }

    /**
     * Calculate the sum between this vector and another one.
     * This doesn't edit the vectors.
     * @param x the x coordinate of the second vector to sum
     * @param y the y coordinate of the second vector to sum
     * @return the sum
     */
    public Vector sum(float x, float y) {
        return new Vector(this.x + x, this.y + y);
    }

    /**
     * Calculate the sum between this vector and another one,
     * and store the result on this vector.
     * @param vector the second vector
     * @return this vector after the sum
     */
    public Vector sumUpdate(Vector vector){
        x += vector.x;
        y += vector.y;
        return this;
    }


    /**
     * Calculate the sum between this vector and another one,
     * and store the result on this vector.
     * @param x the x coordinate of the second vector to sum
     * @param y the y coordinate of the second vector to sum
     * @return this vector after the sum
     */
    public Vector sumUpdate(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    //#endregion

    //#region Subtract

    /**
     * Calculate the subtraction of the second vector from this vector.
     * This doesn't edit the vectors.
     * @param vector the second vector
     * @return the subtraction
     */
    public Vector sub(Vector vector){
        return new Vector(x - vector.x, y - vector.y);
    }

    /**
     * Calculate the subtraction of the second vector from this vector.
     * This doesn't edit the vectors.
     * @param x the x coordinate of the second vector to subtract
     * @param y the y coordinate of the second vector to subtract
     * @return the subtraction
     */
    public Vector sub(float x, float y) {
        return new Vector(this.x - x, this.y - y);
    }

    /**
     * Calculate the subtraction of the second vector from this vector,
     * and store the result on this vector.
     * @param vector the second vector
     * @return this vector after the subtraction
     */
    public Vector subUpdate(Vector vector){
        x -= vector.x;
        y -= vector.y;
        return this;
    }

    /**
     * Calculate the subtraction of the second vector from this vector,
     * and store the result on this vector.
     * @param x the x coordinate of the second vector to subtract
     * @param y the y coordinate of the second vector to subtract
     * @return this vector after the subtraction
     */
    public Vector subUpdate(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }


    //#endregion

    //#region Multiply

    /**
     * Multiply this vector coordinates by a multiplier.
     * This does not edit this vector
     * @param multiplier the multiplier to use for the multiplication
     * @return the multiplication result
     */
    public Vector multiply(float multiplier){
        return new Vector(this.x * multiplier, this.y * multiplier);
    }

    /**
     * Multiply this vector coordinates by a multiplier,
     * then store the results into this vector.
     * @param multiplier the multiplier to use for the multiplication
     * @return this vector after the multiplication
     */
    public Vector multiplyUpdate(float multiplier){
        this.x *= multiplier;
        this.y *= multiplier;
        return this;
    }

    //#endregion

    /**
     * Returns the length of the Vector
     * @return the length
     */
    public float magnitude() {
        return (float)Math.sqrt(x*x+y*y);
    }

    //#endregion

    //#region Object override

    /** Check if this Vector is equal to another Vector
     * @param obj the object to be checked (it must be a Vector)
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;
        return this.x == ((Vector)obj).x && this.y == ((Vector)obj).y;
    }

    /**
     * Get the hashCode of the Vector, this is not actually 100%
     * guaranted to be unique, but usually it should be.
     * @return the hashCode
     */
    @Override
    public int hashCode() {
        return Float.valueOf(x).hashCode() + Float.valueOf(y).hashCode();
    }


    /**
     * Show a textual representation of the Vector in the form of
     * {x, y}
     * @return the textual representation of the Vector
     */
    @Override
    public String toString() {
        return "{"+x+", "+y+"}";
    }

    //#endregion
}
