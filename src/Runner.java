import by.gsu.pms.threads.OperationType;
import by.gsu.pms.threads.ThreadGenerator;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws InterruptedException {


        Scanner scan = new Scanner(System.in); //Чтение с клавиатуры
        System.out.println("Введите число потоков: ");
        int countOfThreads = scan.nextInt(); //ожидаем число
        System.out.println("Введите число А: ");
        int maxValue = scan.nextInt();
        System.out.println("Enter operation sign ('1 - сложение', '2 - вычитание', '3 - умножение");
        String operationType = scan.next(); //ожидаем строку

        OperationType op = OperationType.ADDITION;

        switch (operationType) {
            case "1":
                op = OperationType.ADDITION;
                break;
            case "2":
                op = OperationType.SUBTRACTION;
                break;
            case "3":
                op = OperationType.MULTIPLICATION;
                break;
            default:
                System.out.println("Ошибка! Выберите один из предложенных вариантов");
        }

        ThreadGenerator calculator;
        calculator = new ThreadGenerator(countOfThreads, op, maxValue);
        calculator.execute();
        System.out.println("Вывод: " + calculator.getResult());

    }
}