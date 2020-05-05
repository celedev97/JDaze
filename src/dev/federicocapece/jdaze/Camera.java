package dev.federicocapece.jdaze;

import java.awt.*;

/**
 * The main camera used by the renderer.
 * For now there can be only one camera.
 */
public class Camera extends GameObject {
    //TODO: javadoc

    /**
     * This is protected to avoid creating new cameras.
     */
    protected Camera() {
        position.set(0,0);
    }


    /**
     * The scale of the camera
     */
    private float scale = 1;


    /**
     * Get the scale of the camera as a float
     * <pre>
     * Examples:
     * With scale = 1 the camera will render pixel 1:1
     * With scale = 2 the camera will be zoomed in and each pixel will render to 4 pixels
     * With scale = .5 the camera will be zoomed in and each 4 pixel will be rendered as 1 pixel
     * </pre>
     * @return the scale
     */
    public float getScale() {
        return scale;
    }


    /**
     * Zoom in the camera by X2
     */
    public void zoomIn(){
        scale *= 2;
    }

    /**
     * Zoom out the camera by X2
     */
    public void zoomOut(){
        scale *= .5f;
    }

}
