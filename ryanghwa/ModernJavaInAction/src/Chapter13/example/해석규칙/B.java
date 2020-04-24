package Chapter13.example.해석규칙;

public interface B {
    default void hello(){
        System.out.println("Hello void from B");
    }
}
