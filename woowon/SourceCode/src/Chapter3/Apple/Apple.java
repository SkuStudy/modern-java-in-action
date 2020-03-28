package Chapter3.Apple;

public class Apple {
    int weight;
    Color color;

    public Apple() {
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, Color color){
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Chapter3.Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}
