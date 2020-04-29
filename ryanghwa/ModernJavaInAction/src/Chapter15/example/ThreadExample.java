package Chapter15.example;

import static Chapter15.example.Functions.f;
import static Chapter15.example.Functions.g;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(()-> {result.left = f(x);});
        Thread t2 = new Thread(()-> {result.right = g(x);});
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(result.left+result.right);
    }

    private static class Result{
        private int left;
        private int right;
    }
}
