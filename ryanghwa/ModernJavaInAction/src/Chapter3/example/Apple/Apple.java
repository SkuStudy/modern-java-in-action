package Chapter3.example.Apple;

public class Apple {
	Color color;
	int weight;

	public Apple(int weight, Color color) {
		this.weight = weight;
		this.color = color;
	}

	public Apple() {

	}

	public Apple(int weight) {
		this.weight = weight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
