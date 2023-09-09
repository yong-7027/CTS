package System;

public class LoadingAnimation extends Thread {
    private volatile boolean stopRequested = false;

    @Override
    public void run() {
        while (!stopRequested) {
            System.out.print("\rLoading...|");
            sleep(500);
            System.out.print("\rLoading.../");
            sleep(500);
            System.out.print("\rLoading...-");
            sleep(500);
            System.out.print("\rLoading...\\");
            sleep(500);
        }
    }

    public void stopLoading() {
        stopRequested = true;
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
