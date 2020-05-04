package dev.federicocapece.jdaze;

class StopWatch {
  //TODO: javadoc

  private static final long NANO_TO_MS = 1000000;

  private long startTime = 0;
  private long stopTime = 0;
  private boolean running = false;

  public void start() {
    this.startTime = System.nanoTime()/NANO_TO_MS;
    this.running = true;
  }

  public void stop() {
    this.stopTime = System.nanoTime()/NANO_TO_MS;
    this.running = false;
  }

  public long getElapsedMS() {
    return running ? (System.nanoTime()/NANO_TO_MS - startTime) : (stopTime - startTime);
  }

}