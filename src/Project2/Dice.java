package Project2;

public class Dice {
	int result = 6;
	public int dice() {
		this.result = (int) (Math.random() * 6 + 1);
		return this.result;
	}
	
	public int getVlaue() {
		return this.result;
	}
}
