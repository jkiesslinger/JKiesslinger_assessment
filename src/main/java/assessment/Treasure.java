package assessment;

public class Treasure {
	private int xLocation, yLocation;

	public Treasure(int xLoc, int yLoc) {
		this.xLocation = xLoc;
		this.yLocation = yLoc;
	}
	public Treasure() {}
	public int getxLocation() {
		return xLocation;
	}
	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}
	public int getyLocation() {
		return yLocation;
	}
	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}
}
