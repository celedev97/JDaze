package dev.federicocapece.jdaze;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;


/**
 * The Engine Renderer, this is basically a Canvas managed directly from the Engine.
 * Add Engine.renderer to your GUI, then call Engine.start() to make the renderer start drawing.
 */
public class Renderer extends Canvas {
    /**
     * The graphics used to draw on canvas,
     * this should never be used except from inside the Engine.
     */
    private Graphics canvasGraphics;

    /**
     * Half of the canvas height, this is useful for offset calculations
     */
    protected int halfCanvasHeight;

    /**
     * Half of the canvas width, this is useful for offset calculations
     */
    protected int halfCanvasWidth;

    /**
     * The buffered image that this renderer uses for drawing
     */
    private BufferedImage image;

    /**
     * The graphic used to draw on the BufferedImage used by this rendered.
     */
    private Graphics2D bufferGraphics;

    /**
     * The pixels of the BufferedImage used by this rendered.
     * You could use those to do cool stuff (like filters and effects).
     */
    public int[] pixels;

    /**
     * The camera used by the renderer
     * for now it cannot be changed.
     * I'm not sure if i will change this and allow multiple cameras
     * in future versions.
     */
    protected final static Camera camera = new Camera();


    /**
     * The rendered shouldn't be created by anything other than the Engine.
     */
    protected Renderer(){

    }

    /**
     * Initialize the renderer.
     * This should be called every time the canvas size is changed.
     * It is automatically called when the Engine starts.
     */
    public void init(){
        //canvas init
        canvasGraphics = getGraphics();
        halfCanvasHeight = getHeight()/2;
        halfCanvasWidth = getWidth()/2;

        //buffer init
        image = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_RGB);
        bufferGraphics = image.createGraphics();;
        this.image = image;

        //direct pixel edit init:
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        clean();
    }

    /**
     * Draw a GameObject into the buffer if it is inside the screen.
     * @param gameObject the GameObject to be drawn
     */
    protected void update(GameObject gameObject) {
        int cameraOffsetX = (int)((gameObject.position.x - camera.position.x)* camera.getScale()) + halfCanvasWidth;
        int cameraOffsetY = (int)((gameObject.position.y - camera.position.y)* camera.getScale()) + halfCanvasHeight;
        gameObject.draw(bufferGraphics,cameraOffsetX , cameraOffsetY, camera.getScale());
    }

    /**
     * Draw the buffer onto the canvas
     */
    protected void update() {
        canvasGraphics.drawImage(image,0,0,null);
    }

    /**
     * Turn the screen buffer full white
     */
    protected void clean() {
        Arrays.fill(pixels, 0xffffff);
    }

}
