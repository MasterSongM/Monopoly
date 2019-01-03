package project2;

public class GoSquare extends Square {
	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public void aotuEvent(Player p) {
			p.addGold(this.value);
	}
	
}
