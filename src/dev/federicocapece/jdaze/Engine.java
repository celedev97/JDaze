package dev.federicocapece.jdaze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class Engine {
    //TODO: javadoc
    private static Thread runningThread = null;

    public final static Renderer renderer;
    public final static Camera camera;
    public final static Input input = new Input();

    protected final static ArrayList<GameObject> gameObjects;

    private static StopWatch stopWatch = new StopWatch();
    public static float deltaTime;

    private static float targetCycleMS;

    static {
        gameObjects = new ArrayList<>();

        renderer = new Renderer();
        renderer.addKeyListener(input.keyListener);

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

    public static void update(){
        stopWatch.start();
        renderer.clean();
        for (GameObject gameObject : gameObjects){
            gameObject.update();
            renderer.update(gameObject);
        }
        renderer.update();

        float elapsed = stopWatch.getElapsedMS();
        System.out.println("PRESLEEP: "+elapsed);
        if(elapsed<targetCycleMS){
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
