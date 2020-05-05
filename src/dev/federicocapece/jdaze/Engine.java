package dev.federicocapece.jdaze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class Engine {
    //TODO: javadoc
    private static Thread runningThread = null;

    public final static Renderer renderer;
    public final static Camera camera;

    protected final static ArrayList<GameObject> gameObjects;

    private static StopWatch stopWatch = new StopWatch();
    public static float deltaTime;

    private static float targetCycleMS;

    static {
        gameObjects = new ArrayList<>();

        //creating the renderer canvas
        renderer = new Renderer();
        //linking the Input listeners to the canvas
        renderer.addKeyListener(Input.keyListener);
        renderer.addMouseListener(Input.mouseInputListener);
        renderer.addMouseMotionListener(Input.mouseInputListener);
        renderer.addMouseWheelListener(Input.mouseWheelListener);

        //setting the Engine.camera to the renderer.camera
        camera = renderer.camera;
    }

    public static void start(){
        start(60);
    }

    public static void start(int maxFPS){
        //if a Game thread is already running i try to close it, i cannot start a new one otherwise
        if(runningThread != null) {
            runningThread.interrupt();
            while (runningThread.isAlive()) {
                try {
                    runningThread.interrupt();
                    runningThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //initialize runtime stuff
        targetCycleMS = 1000f / maxFPS;

        gameObjects.clear();
        renderer.init();


        stopWatch.start();
        //start the GameLoop
        runningThread = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()){
                    update();
                }
            }
        };

        runningThread.start();
    }

    public void stop(){
        if(runningThread != null) runningThread.interrupt();
    }

    private static void update(){
        //restarting stopwatch to measure MS in this game cycle
        stopWatch.start();
        //run each gameObject update
        for (GameObject gameObject : gameObjects){
            gameObject.update();
        }

        //clean the screen buffer
        renderer.clean();
        //draw the gameObjects on the buffer
        for (GameObject gameObject : gameObjects){
            renderer.update(gameObject);
        }
        //draw the buffer to the canvas
        renderer.update();

        //cleaning mouse wheel rotation for next game loop
        Input.mouseWheelReset();


        float elapsed = stopWatch.getElapsedMS();
        //System.out.println("PRESLEEP: "+elapsed);
        if(elapsed<targetCycleMS){
            //TODO: replace Thread.sleep with something more precise
            try {
                Thread.sleep((long) (targetCycleMS - elapsed));
            } catch (InterruptedException e) {
                return;
            }
        }
        elapsed = stopWatch.getElapsedMS();
        deltaTime = elapsed / 1000f;
        /*
        System.out.println("POSTSLEEP: "+elapsed);
        System.out.println("deltaTime = "+deltaTime);
        System.out.println("fps = "+ (1000f/elapsed));
        */
    }

}
