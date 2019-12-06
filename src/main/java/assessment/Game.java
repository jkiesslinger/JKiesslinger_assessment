package assessment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Flaws that exist:
//treasures can start in the same place
public class Game {
	private int maxX, maxY, minX, minY;
	private List<Treasure> treasures = new ArrayList<>();
	private List<Player> players = new LinkedList<>();
	private boolean gameOver = false;
	private boolean noWinner = false;
	private int moves = 0;
	private int currentPlayerIndex = 0;
	private Player currentPlayer;
	public void setup(int noOfTreasures, int sizeOfBoard, int noOfPlayers) {
		boolean validPlayerPosition = true;
		if (maxX % 2 != 0)
			maxX++;
		this.maxX = sizeOfBoard / 2;
		this.maxY = maxX;
		this.minX = -maxX;
		this.minY = minX;
		for (int i = 0; i < noOfTreasures; i++) {
			int x = getRandomPosition(minX, maxX);
			int y = getRandomPosition(minY, maxY);
			treasures.add(new Treasure(x, y));
		}
		do {
			players.clear();
			for (int i = 0; i < noOfPlayers; i++) {
				int x = getRandomPosition(minX, maxX);
				int y = getRandomPosition(minY, maxY);
				players.add(new Player(x, y));
				List<Double> initialDistances = getDistance(players.get(i), treasures);
				for (Double d : initialDistances) {
					if (d == 0) {
						validPlayerPosition = false;
					}
				}

			}
		} while (!validPlayerPosition);

	}

	private int getRandomPosition(int min, int max) {
		Random r = new Random();
		return ((r.nextInt(max - min)) + min);

	}

	private List<Double> getDistance(Player p, List<Treasure> t) {
		int playerX = p.getxLocation();
		int playerY = p.getyLocation();
		List<Double> distances = new ArrayList<>();
		t.stream().map(i -> Math.sqrt(((i.getxLocation() - playerX) * (i.getxLocation() - playerX))
				+ ((i.getyLocation() - playerY) * (i.getyLocation() - playerY)))).forEach(distances::add);
		return distances;

	}

	private void userConditions(Scanner s) {
		
		int noOfPlayers = 0;
		while (noOfPlayers < 1) {
			System.out.println("How many players?");
			noOfPlayers = Integer.parseInt(s.nextLine());
		}
		
		
		int noOfTreasures = 0;
		while(noOfTreasures < 1) {
			System.out.println("How many treasures?");
			noOfTreasures = Integer.parseInt(s.nextLine());
		}
		

		int boardSize = 0;
		while (boardSize < 5) {
			System.out.println("What size board? (please use a single int, for example 8 = a 8x8 board)");
			boardSize = Integer.parseInt(s.nextLine());
		}
		setup(noOfTreasures, boardSize, noOfPlayers);
	}

	private void checkOutOfBounds() {
		if (currentPlayer.getxLocation() > maxX || currentPlayer.getxLocation() < minX
				|| currentPlayer.getyLocation() < minY || currentPlayer.getyLocation() > maxY) {
			if (currentPlayer.getTurnsOfBounds() > 2) {
				System.out.println("Player " + this.currentPlayerIndex
						+ "has been removed from the game for staying out of bounds. Bad player.");
				players.remove(currentPlayer);
				if (players.size() == 0) {
					this.gameOver = true;
					this.noWinner = true;
				} else {
					if (this.currentPlayerIndex < players.size() - 1) {
						this.currentPlayerIndex++;
					} else {
						this.currentPlayerIndex = 0;
					}
				}
			} else {
				currentPlayer.setTurnsOfBounds(currentPlayer.getTurnsOfBounds() + 1);
				System.out.println("It looks like you are lost. Please return to the board or be stuck forever.");
			}
		} else {
			currentPlayer.setTurnsOfBounds(0);
		}
	}

	private void checkTreasureFound(List<Double> dists) {
		if (!gameOver) {
			int count = 0;
			for (Double d : dists) {
				if (d == 0) {
					System.out.println("Congratulations! Player " + currentPlayerIndex + " found a treasure");
					currentPlayer.givePoint();
					treasures.remove(count);
					if (treasures.size() == 0) {
						gameOver = true;
					}
				} else {
					System.out.println("Player" + currentPlayerIndex + " is " + d + " units from treasure" + count);
					count++;
				}

			}
		}
	}

	public void play() {
		Scanner SCAN = new Scanner(System.in);
		while (true) {
			userConditions(SCAN);

			

			do {// Check for player finding treasure
				currentPlayer = players.get(currentPlayerIndex);
				List<Double> dists = getDistance(currentPlayer, treasures);
				checkOutOfBounds();
				checkTreasureFound(dists);
				takeTurn(SCAN);

			} while (!gameOver);
			if (!noWinner) {

				int winner = getWinner();
				int points = players.get(winner).getPoints();
				System.out
						.println("Player " + winner + " won in " + this.moves + " moves with " + points + " points!!");
			} else {
				System.out.println("I'm sorry. There is no winner because you all cheated.");
			}

			// show points
			System.out.println("Do you want to play again?");
			String again = SCAN.nextLine();

			// Clear ALL
			if (again.equals("N")) {
				break;
			} else {
				// CLEAR
			}

		}
		SCAN.close();
	}

	private void takeTurn(Scanner SCAN) {
		if (!gameOver) {
			System.out.println("Player " + currentPlayerIndex + ". Which way do you wish to move? (N,S,E,W)");
			String compass;

			compass = SCAN.nextLine();

			switch (compass) {
			case "N":
				move(1, 0, currentPlayer);
				break;
			case "E":
				move(0, 1, currentPlayer);
				break;
			case "S":
				move(-1, 0, currentPlayer);
				break;
			case "W":
				move(0, -1, currentPlayer);
				break;
			default:
				break;
			}
			this.moves++;
			if (currentPlayerIndex < players.size() - 1) {
				currentPlayerIndex++;
			} else {
				currentPlayerIndex = 0;
			}
		}

	}

	private int getWinner() {
		int winner = 0;
		int count = 0;
		for (Player p : players) {
			if (p.getPoints() > winner) {
				winner = count;
			}
			count++;
		}
		return winner;
	}

	public void move(int y, int x, Player p) {
		p.setxLocation(p.getxLocation() + x);
		p.setyLocation(p.getyLocation() + y);
	}

	public List<Treasure> getTreasures() {
		// TODO Auto-generated method stub
		return this.treasures;
	}

	public List<Player> getPlayers() {

		return this.players;
	}

}
