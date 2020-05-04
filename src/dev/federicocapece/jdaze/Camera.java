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

    @Override
    protected void draw(Graphics graphics, int x, int y, float scale) {
        //a camera shouldn't be drawn, NEVER
        //unless you are an idiot...
        //or you want a gizmo...
        //fuck it TODO: write some proper Camera javadoc
    }
}
