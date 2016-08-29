package model;

public class Player implements Comparable<Player> {
	private String name;
	private int nbrOfWins;
	
	/**
	 * Creates a player with the specified name
	 * @param name of the player
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the number of times the player has won
	 */
	public int getNbrOfWins() {
		return nbrOfWins;
		
	}
	
	/**
	 * Sets the amount of wins a player has
	 * @param wins, the amount of wins
	 */
	public void setNbrOfWins(int wins) {
		nbrOfWins = wins;
	}
	
	/**
	 * Increments the number of times a player has won by 1
	 */
	public void addWin() {
		nbrOfWins++;
		
	}
	
	public int compareTo(Player player) {
		if ( nbrOfWins < player.getNbrOfWins()) {
			return 1;
			
		} else if ( nbrOfWins > player.getNbrOfWins()) {
			return -1;
		} else {
			return 0;	
		}
	}
	
	public boolean equals(Object object) {
		if (object instanceof Player) {
			Player player = (Player) object;
			return player.getName().equals(name);
		}
		return false;
		
	}
	
	public String toString() {
		return name + " " + nbrOfWins;
	}

}
