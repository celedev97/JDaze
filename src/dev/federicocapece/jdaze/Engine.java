package dev.federicocapece.jdaze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The Engine of JDaze.
 * The Engine is the very core of JDaze, it manages the game loop.
 * To use the Engine you need to add the Engine.renderer to your GUI first,
 * then call Engine.start() and enjoy the magic of your GameObjects being automatically updated and drawn.
 * NOTE: Instantiate the GameObjects only after the start() of the Engine.
 */
public final class Engine {
    /**
     * The thread of the gameLoop, use start() and stop() to manage it.
     * Do not touch this directly, for any reason, this will cause unexpected behaviour in the engine.
     */
    private static Thread runningThread = null;

    /**
     * The renderer of the Engine.
     * You can add this to the GUI like you would normally add any Swing component.
     */
    public final static Renderer renderer;

    /**
     * The camera that is being used by the renderer to render the screen.
     */
    public final static Camera camera;

    /**
     * The list of the gameObjects that are currently managed from the Game Loop.
     * You shouldn't touch this directly.
     */
    protected final static ArrayList<GameObject> gameObjects;
    /**
     * The list of the gameObjects that will be destroyed in this Game Loop.
     * You shouldn't touch this directly.
     */
    protected final static ArrayList<GameObject> toDestroy;

    /**
     * The StopWatch used to measure the Game Loop duration.
     */
    private static StopWatch stopWatch = new StopWatch();

    /**
     * The time in seconds that the last Game Loop run did take.
     * This is essential for making physics frame-rate independent
     *
     * <pre>
     * Example:
     * Vector movement = Vector.UP().multiply(speed);
     * this will move the item up of 'speed' every frame
     *
     * Vector movement = Vector.UP().multiply(speed * Engine.deltaTime);
     * this will move the item up of 'speed' every second.
     * </pre>
     */
    public static float deltaTime;

    /**
     * The milliseconds that a Game Loop execution should take to match the desired framerate.
     */
    private static float targetCycleMS;

    static {
        gameObjects = new ArrayList<>();
        toDestroy   = new ArrayList<>();

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


    /**
     * Start the GameLoop.
     * This should be called after putting Engine.renderer inside the GUI and after repainting and re-validating the GUI.
     * Since the GameLoop starts in a new Thread this will be non-blocking for your code,
     * so you should start the Engine before every other Game related stuff
     * (for example before GameObjects' instantiation)
     * The framecap will be 60FPS if you don't specify it.
     */
    public static void start(){
        start(60);
    }

    /**
     * Start the GameLoop.
     * This should be called after putting Engine.renderer inside the GUI and after repainting and re-validating the GUI.
     * Since the GameLoop starts in a new Thread this will be non-blocking for your code,
     * so you should start the Engine before every other Game related stuff
     * (for example before GameObjects' instantiation)
     * @param maxFPS the framecap to the Engine
     */
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

    /**
     * Stop the execution of the Game Loop
     */
    public void stop(){
        if(runningThread != null) runningThread.interrupt();
    }


    /**
     * The Game Loop, does literally everything.
     */
    private static void update(){
        //restarting stopwatch to measure MS in this game cycle
        stopWatch.start();

        //run each gameObject update
        //TODO: FIX java.util.ConcurrentModificationException
        //	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1012)
        //	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:966)
        //	at dev.federicocapece.jdaze.Engine.update(Engine.java:156)
        //	at dev.federicocapece.jdaze.Engine$1.run(Engine.java:132)
        // PROBLEM CAUSED BY GAMEOBJECT ADDS IN UPDATE, ADD NEW GAMEOBJECT IN ANOTHER CYCLE
        for (GameObject gameObject : gameObjects){
            //skip gameObject if it's been destroyed
            if(toDestroy.contains(gameObject)) continue;
            gameObject.update();
        }

        //delete deleted gameObjects
        for (GameObject gameObject : toDestroy){
            gameObjects.remove(toDestroy);
        }
        toDestroy.clear();

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

        //sleeping for the remaining time of this cycle
        float targetSleep = targetCycleMS - stopWatch.getElapsedMS();
        try {
            while(targetSleep > 1){
                Thread.sleep(1);
                targetSleep = targetCycleMS - stopWatch.getElapsedMS();
            }
        }catch (InterruptedException ex){
            return;
        }
        //calculating deltaTime for next Cycle
        deltaTime = stopWatch.getElapsedMS() / 1000f;
    }

}
