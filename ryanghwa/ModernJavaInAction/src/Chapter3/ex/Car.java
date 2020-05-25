package Chapter3.ex;

public class Car {
    private String name;

    public Car(){
        System.out.println("기본생성자");
    }
    public Car(String name){
        this.name = name;
        System.out.println("Car(String name) 생성");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
