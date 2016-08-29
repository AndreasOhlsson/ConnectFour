package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ConnectFour {
	/**
	 *  List of players in the current game 
	 */
	private ArrayList<Player> players;
	
	/**
	 *  matrix that represents the Connect Four grid 
	 */
	private int[][] matrix;
	
	/** 
	 * Time object showing the current time when called upon
	 */
	private Calendar calendar;
	
	private StringBuilder auditlog;
	
	/** 
	 * Integer representing whose turn it is, ranging from 0-1
	 */
	private int playerTurn;
	
	/**
	 *  Boolean representing as a check if the game is finished or not
	 */
	private boolean finished;
	
	/**
	 *  List of all players that have played the game on this client
	 */
	private ArrayList<Player> allPlayers = new ArrayList<Player>();
	
	
	private int width = 7;
	private int height = 6;
	
	
	/**
	 * Constructor of the game, with 7x6 grids representing it
	 */
	public ConnectFour() {
		players = new ArrayList<Player>();
		finished = false;
		playerTurn = 0;

		calendar = Calendar.getInstance();
		auditlog = new StringBuilder();
		matrix = new int[width][height];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				matrix[j][i] = -1;
			}
		}
		loadAll();

	}
	
	/**
	 * Returns the auditlog
	 * @return, auditlog
	 */
	public String getAuditlog() {
		return auditlog.toString();
	}

	
	/**
	 * Returns the width of the game
	 * @return, width
	 */
	public int getWidth() {
		return width;
	}

	
	/**
	 * Returns the height of the game
	 * @return, height
	 */
	public int getHeight() {
		return height;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	
	/**
	 * Returns the list of the players in the current game
	 * @return, list of current players
	 */
	public ArrayList<Player> getCurrent() {
		return players;
	}

	
	/**
	 * Returns the list of all the players that have played on this client
	 * @return, list of all the players
	 */
	public ArrayList<Player> getAllPlayers() {
		return allPlayers;
	}
	
	/**
	 * Add a player to the current game
	 * @param name of the player
	 */
	public void addPlayerToGame(String name) {
		Player player = new Player(name);
		players.add(player);
		addPlayerToDataBase(name);
	}
	
	/**
	 * Add a player to the player database if the player does not already exists
	 * @param name of the player
	 */
	public void addPlayerToDataBase(String name) {
		Player player = new Player(name);

		if (!allPlayers.contains(player)) {
			allPlayers.add(player);
		}
	}
	
	/**
	 * Empty the current player list
	 */
	public void clearCurrentPlayers() {
		players.clear();
	}

	/**
	 * Returns true if the game is finished
	 * @return, finished true if the game is finished
	 */
	public boolean isFinished() {
		return finished;
	}
	
	
	/**
	 * Returns the name of the player who won the latest game
	 * @return, name of the player who won the latest game
	 */
	public String getPlayerWinnerName() {
		return players.get(playerTurn).getName();
	}

	
	/**
	 * If the game is not finished a circle will be put in the next available row at the chosen column.
	 * If a move is the finishing move, the game will stop.
	 * @param column, position y
	 * @param row, position x
	 * @return, 1 if the last move was a finishing, 0 if the last move was successful but not a win,
	 *  -1 if the chosen column is full
	 * 
	 */
	public int putCircle(int column, int row) {
		if (!finished) {

			int coord = nextRowInColumn(column);

			if (coord != -1) {
				Player p = players.get(playerTurn);
				matrix[column][coord] = playerTurn;
				auditlog.insert(0,calendar.getTime().toString() + " " + p.getName() + " put a circle in column " + column
						+ " and at row " + coord + "\n");

				if (isWin()) {
					p.addWin();
					auditlog.insert(0,calendar.getTime().toString() + " " + p.getName() + " wins the game \n");
					for ( int i = 0; i < allPlayers.size(); i++) {
						Player search = allPlayers.get(i);
						if ( p.equals(search)) {
							search.addWin();
						}
					}
					finished = true;
					System.out.println(showHighscore());
					return 1;

				}

				playerTurn = (playerTurn + 1) % 2;
			}

			if (coord == -1) {
				return -1;
			}

		}

		return 0;

	}

	/**
	 * Returns true if last move was a winning move
	 * @return true if last move was a winning move, false otherwise
	 */
	public boolean isWin() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix[j][i] != -1) {
					if (checkIfWinHorizontal(j, i, 1)) {
						return true;
					}
					if (checkIfWinVertical(j, i, 1)) {
						return true;

					}
					if (checkIfWinNWtoSE(j, i, 1)) {
						return true;
					}
					if (checkIfWinSWtoNE(j, i, 1)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is horizontal win in the game
	 * @param x, start position horizontal
	 * @param y, start position vertical
	 * @param consecutive, variable to check how many in a row found so far
	 * @return true if it is a win, false otherwise
	 */
	private boolean checkIfWinHorizontal(int x, int y, int consecutive) {
		try {

			if (consecutive == 4) {
				return true;
			}
			if (matrix[x][y] == matrix[x + 1][y]) {
				return checkIfWinHorizontal(x + 1, y, consecutive + 1);
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return false;

	}

	/**
	 * Checks if there is vertical win in the game
	 * @param x, start position horizontal
	 * @param y, start position vertical
	 * @param consecutive, variable to check how many in a row found so far
	 * @return true if it is a win, false otherwise
	 */
	private boolean checkIfWinVertical(int x, int y, int consecutive) {
		try {

			if (consecutive == 4) {
				return true;
			}
			if (matrix[x][y] == matrix[x][y - 1]) {
				return checkIfWinVertical(x, y - 1, consecutive + 1);
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return false;
	}

	/**
	 * Checks if there is northwest to southeast (diagonal) win in the game
	 * @param x, start position horizontal
	 * @param y, start position vertical
	 * @param consecutive, variable to check how many in a row found so far
	 * @return true if it is a win, false otherwise
	 */
	private boolean checkIfWinNWtoSE(int x, int y, int consecutive) {
		try {

			if (consecutive == 4) {
				return true;
			}
			if (matrix[x][y] == matrix[x + 1][y + 1]) {
				return checkIfWinNWtoSE(x + 1, y + 1, consecutive + 1);
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return false;
	}

	/**
	 * Checks if there is southwest to northeast (diagonal) win in the game
	 * @param x, start position horizontal
	 * @param y, start position vertical
	 * @param consecutive, variable to check how many in a row found so far
	 * @return true if it is a win, false otherwise
	 */
	private boolean checkIfWinSWtoNE(int x, int y, int consecutive) {
		try {

			if (consecutive == 4) {
				return true;
			}
			if (matrix[x][y] == matrix[x + 1][y - 1]) {
				return checkIfWinSWtoNE(x + 1, y - 1, consecutive + 1);
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return false;
	}
	
	
	/**
	 * Checks which index that is the next available one at the chosen column
	 * @param column, vertical position
	 * @return the index of the next available slot, -1 if the chosen column is full
	 */
	public int nextRowInColumn(int column) {
		for (int i = height - 1; i >= 0; i--) {
			if (matrix[column][i] == -1) {
				return i;
			}

		}
		return -1;

	}

	/**
	 * Reset the game, turn all values in the matrix to -1, and sets finished to false
	 */
	public void clear() {
		auditlog.insert(0,calendar.getTime().toString() + " " + "Board cleared \n");
		finished = false;
		playerTurn = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				matrix[j][i] = -1;
			}
		}
	}

	/**
	 * Show the highscore board of the top 10 players with most wins
	 * @return string of top 10 players
	 */
	public String showHighscore() {
		String[] highscoreArray = new String[10];
		Collections.sort(allPlayers);
		int size = allPlayers.size();
		int k = 0;
		while (size > k && k < 10) {
			Player player = allPlayers.get(k);
			highscoreArray[k] = player.getName() + " " + player.getNbrOfWins() + "\n";
			k++;
		}
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < highscoreArray.length; i++) {
			String s = highscoreArray[i];
			if ( s != null) {
				result.append(s);
				
			}
		}
		return result.toString();

	}
	
	
	/**
	 * Saves the auditlog and database of all players to files
	 */
	public void saveAll() {
		BufferedWriter auditlogWriter = null;
		BufferedWriter playerWriter = null;

		try {
			auditlogWriter = new BufferedWriter(new FileWriter("auditlog.txt"));
			auditlogWriter.write(auditlog.toString());
			playerWriter = new BufferedWriter(new FileWriter("players.txt"));
			for (Player p : allPlayers) {
				playerWriter.write(p.getName() + " " + p.getNbrOfWins() + "\n");
			}

		} catch (IOException e) {
		} finally {
			try {
				if (auditlogWriter != null)
					auditlogWriter.close();
				if (playerWriter != null) {
					playerWriter.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Loads the data for the auditlog and the database consisting of all players
	 */
	public void loadAll() {
		try {
			Scanner auditlogScan = new Scanner(new File("auditlog.txt"));
			Scanner playerScan = new Scanner(new File("players.txt"));

			String auditInput;
			while (auditlogScan.hasNext()) {
				auditInput = auditlogScan.nextLine();
				auditlog.append(auditInput + "\n");
			}

			String playerInput;
			int nbrOfWins;
			String playerName;
			while (playerScan.hasNext()) {
				playerInput = playerScan.nextLine();
				String[] split = playerInput.split(" ");
				playerName = split[0];
				nbrOfWins = Integer.valueOf(split[1]);
				Player player = new Player(playerName);
				player.setNbrOfWins(nbrOfWins);
				allPlayers.add(player);
			}

		} catch (Exception e) {

		}
	}
}
