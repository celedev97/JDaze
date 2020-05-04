package dev.federicocapece.jdaze;

import java.awt.*;

public class Camera extends GameObject {
    public Camera() {
        super();
    }

    private float scale = 1;

    public float getScale() {
        return scale;
    }

    public void zoomIn(){
        scale *= 2;
    }

    public void zoomOut(){
        scale *= .5f;
    }

}
