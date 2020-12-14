package by.gsu.pms.threads;

public class ThreadGenerator {
    private ClaculatorThread[] threads;
    private final OperationType operationType;

    public ThreadGenerator(int countOfThreads, OperationType operationType, int maxValue) {
        this.operationType = operationType;

        System.out.println("Количество потоков: " + countOfThreads +
                "\nЧисло пользователя: " + maxValue +
                "\nТип операции: " + operationType);

        if (maxValue > countOfThreads && maxValue % countOfThreads == 0) {
            ClaculatorThread[] arrayThreads = new ClaculatorThread[countOfThreads];

            int threadCapacity = maxValue / countOfThreads;
            for (int i = 0; i < countOfThreads; i++) {
                int start = i * threadCapacity + 1;
                int end = i * threadCapacity + threadCapacity;
                arrayThreads[i] = new ClaculatorThread(start, end);
            }
            this.threads = arrayThreads;
        } else {
            System.out.println("Ошибка! Число пользователя должно быть больше введенного числа потоков! ");
        }
    }

    public void execute() {
        for (ClaculatorThread thread : this.threads) {
            thread.start(); // позволяет запустить потоки
        }
    }

    public int getResult() throws InterruptedException { 
        int result = operationType.ordinal() < 2 ? 0 : 1;
        for (ClaculatorThread thread : this.threads) {
            thread.join(); // метод ожидания
            switch (this.operationType.ordinal()) {
                case (0):
                    result += thread.getResult();
                    break;
                case (1):
                    result -= thread.getResult();
                    break;
                case (2):
                    result *= thread.getResult();
                    break;
                default:
                    System.out.println("Ошибка выбора операции!");
                    break;
            }
        }
        return result;
    }
}
