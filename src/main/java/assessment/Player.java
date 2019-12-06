package assessment;

public class Player {
	private int xLocation, yLocation, points = 0;
	private int id;
	private int  turnsOfBounds = 0;

	public Player(int xLoc, int yLoc) {
		this.setxLocation(xLoc);
		this.setyLocation(yLoc);
	}
	public Player() {}
	public void givePoint() {
		this.setPoints(this.getPoints() + 1);
	}
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
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getTurnsOfBounds() {
		return turnsOfBounds;
	}
	public void setTurnsOfBounds(int turnsOfBounds) {
		this.turnsOfBounds = turnsOfBounds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
