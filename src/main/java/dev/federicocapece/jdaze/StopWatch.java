package dev.federicocapece.jdaze;


/**
 * A StopWatch, inspired by the C# StopWatch.
 * It uses nanoseconds to be more precise,
 * then convert them to ms when it returns them.
 */
class StopWatch {
  /**
   * A constant useful for converting nanoseconds to milliseconds.
   *
   * milliseconds = nanoseconds / NANO_TO_MS;
   */
  private static final long NANO_TO_MS = 1000000;

  /**
   * The time at witch the stopwatch has been started
   */
  private long startTime = 0;

  /**
   * The time at witch the stopwatch has been stopped
   */
  private long stopTime = 0;

  /**
   * Internal variable to check if the stopwatch is currently running
   */
  private boolean running = false;

  /**
   * Start the stopwatch.
   */
  public void start() {
    this.startTime = System.nanoTime()/NANO_TO_MS;
    this.running = true;
  }

  /**
   * Stop the stopwatch.
   */
  public void stop() {
    this.stopTime = System.nanoTime()/NANO_TO_MS;
    this.running = false;
  }

  /**
   * The number of elapsed milliseconds from when the Timer has been started.
   * @return the milliseconds
   */
  public long getElapsedMS() {
    return running ? (System.nanoTime()/NANO_TO_MS - startTime) : (stopTime - startTime);
  }

}