package Chapter9.example;
interface Task{
    public void execute();
}
public class 익명클래스_람다표현식으로_1 {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        Runnable r2 = ()-> System.out.println("Hello");

        r1.run();
        r2.run();

        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!!");
            }
        });
        doSomething((Runnable) () -> System.out.println("Danger danger!!"));
    }

    public static void doSomething(Runnable r){
        r.run();
    }
    public static void doSomething(Task a){
        a.execute();
    }
}
