package by.gsu.pms.threads;

public class ClaculatorThread extends Thread {

    private long result = 1;
    private final int minValue;
    private final int maxValue;

    public long getResult() {
        return this.result;
    }

    public ClaculatorThread(int minValue, int maxValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadName + " starting ");
        for (int i = this.minValue; i <= this.maxValue; i++) {
            if (i % 4 == 0)
                this.result *= i;

        }
    }

}