package chapter1;

/**
 * Created by YoungMan on 2020-03-16.
 */

public class Chap1_2_ThreadAndSynchronized {

    private static int number;

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                print(1);
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                print(2);
            }
        });

        threadOne.start();
        threadTwo.start();
    }

    private static synchronized void print(int threadNum) {
        for (int i = 0; i < 1000; i++) {
            System.out.println("[Thread" + threadNum + "] : " + ++number);
        }
    }
}
