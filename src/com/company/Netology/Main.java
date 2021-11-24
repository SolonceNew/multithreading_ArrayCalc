package com.company.Netology;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private final static int size = 999999;
    private final static Random random = new Random();

    private static int[] creationArray() {
        final int[] array = new int[Main.size];
        for(int i = 0; i < array.length; i++) {
            array[i] = Main.random.nextInt();
        }
        return array;
    }

    public static int sumArrayElements(int[] array) {
        int sum = 0;
        for(int element : array) {
            sum += element;
        }
        return sum;
    }

    public static double average(int[] array) {
        int sum = 0;
        for(int element : array) {
            sum += element;
        }
        return sum / array.length;
    }
//в результате выполенения программы затраченное время при многпоточности больше, чем при одном потоке.
    public static void main(String[] args) {
        int[] array = creationArray();
        //однопоточное решение
        long startTime = System.currentTimeMillis();
        System.out.println("Cумма элементов массива равна: " + sumArrayElements(array));
        long resultTime = System.currentTimeMillis() - startTime;
        System.out.println("Время выполнения однопоточного сложения: " + resultTime);

        long startTimeAverage = System.currentTimeMillis();
        System.out.println("Среднее арифметическое элементов массива равно: " + average(array));
        long resultAverageTime = System.currentTimeMillis() - startTimeAverage;
        System.out.println("Время выполнения однопоточного вычисления среднего " +
                "арифметического элементов массива: " + resultAverageTime);

        //решение с forkJoinPool
        ForkJoinPool fjp = new ForkJoinPool();
        ArraySum task = new ArraySum(0, array.length, array);
        long startForkTime = System.currentTimeMillis();
        int summator = fjp.invoke(task);
        long resultForkTime = System.currentTimeMillis() - startForkTime;
        System.out.println("Cумма элементов массива равна (многопоточное): " + summator);
        System.out.println("Время выполнения многопоточного сложения: " + resultForkTime);

        long startForkTimeAverage = System.currentTimeMillis();
        double averageFork = summator / array.length;
        long resultForkAverageTime = System.currentTimeMillis() - startForkTimeAverage;
        System.out.println("Среднее арифметическое элементов массива равно (fork): " + averageFork);
        System.out.println("Время выполнения однопоточного вычисления среднего " +
                "арифметического элементов массива: " + resultForkAverageTime);
    }
}
