package assessment;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class testMain {
	@Test
	public void setup() {
		Game g = new Game();
		int noOfTreasures = 1;
		int sizeOfBoard = 10;
		int noOfPlayers = 1;
		g.setup(noOfTreasures, sizeOfBoard, noOfPlayers);
		assert (g.getTreasures().get(0).getxLocation() <= sizeOfBoard / 2);

		assert (g.getTreasures().get(0).getxLocation() >= -sizeOfBoard / 2);

		assert (g.getTreasures().get(0).getyLocation() <= sizeOfBoard / 2);
		assert (g.getTreasures().get(0).getyLocation() >= -sizeOfBoard / 2);
		System.out.println("Treasure X: " + g.getTreasures().get(0).getxLocation());
		System.out.println("Treasure Y: " + g.getTreasures().get(0).getyLocation());
		assertEquals(g.getTreasures().size(), noOfTreasures);

	}

	@Test
	public void userMoves() {
		Game g = new Game();
		int noOfTreasures = 1;
		int sizeOfBoard = 10;
		int noOfPlayers = 1;
		g.setup(noOfTreasures, sizeOfBoard, noOfPlayers);
		List<Player> players = g.getPlayers();
		assert (players.get(0).getxLocation() <= sizeOfBoard / 2);

		assert (players.get(0).getxLocation() >= -sizeOfBoard / 2);

		assert (players.get(0).getyLocation() <= sizeOfBoard / 2);
		assert (players.get(0).getyLocation() >= -sizeOfBoard / 2);
		assertEquals(players.size(), noOfPlayers);
		int xBefore = players.get(0).getxLocation();
		int yBefore = players.get(0).getyLocation();
		g.move(1, 0, players.get(0)); // Move North
		assertEquals(xBefore, players.get(0).getxLocation());
		assertEquals(yBefore+1, players.get(0).getyLocation());
		
		xBefore = players.get(0).getxLocation();
		yBefore = players.get(0).getyLocation();
		g.move(-1, 0, players.get(0)); // Move South
		assertEquals(xBefore, players.get(0).getxLocation());
		assertEquals(yBefore-1, players.get(0).getyLocation());
		
		xBefore = players.get(0).getxLocation();
		yBefore = players.get(0).getyLocation();
		g.move(0, -1, players.get(0)); // Move West
		assertEquals(xBefore-1, players.get(0).getxLocation());
		assertEquals(yBefore, players.get(0).getyLocation());
		
		xBefore = players.get(0).getxLocation();
		yBefore = players.get(0).getyLocation();
		g.move(0, 1, players.get(0)); // Move East
		assertEquals(xBefore+1, players.get(0).getxLocation());
		assertEquals(yBefore, players.get(0).getyLocation());
	}
}
