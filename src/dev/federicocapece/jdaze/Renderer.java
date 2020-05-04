package dev.federicocapece.jdaze;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

class Renderer extends Canvas {
    //TODO: javadoc

    //canvas
    private Graphics canvasGraphics;

    private int halfCanvasHeight;
    private int halfCanvasWidth;

    //buffered image
    private BufferedImage image;
    private Graphics2D bufferGraphics;

    //manual pixel writings to buffered image
    private int[] pixels;

    protected static Camera camera = new Camera();;

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

    public void update(GameObject gameObject) {
        if (gameObject == camera) return;
        //TODO: calcoli basati sulla telecamera
        int cameraOffsetX = (int)(gameObject.position.x - camera.position.x + halfCanvasWidth);
        int cameraOffsetY = (int)(gameObject.position.y - camera.position.y + halfCanvasHeight);
        gameObject.draw(bufferGraphics,cameraOffsetX, cameraOffsetY, camera.getScale());
    }

    public void update() {
        canvasGraphics.drawImage(image,0,0,null);
    }

    public void clean() {
        //turn the image full white
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xffffff;
        }
    }
}
