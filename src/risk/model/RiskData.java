package risk.model;

/**
 * Enumeration for the record of armies to be assigned to the players 
 * according to the number of players.
 */
public enum RiskData {
	
	InitialArmiesCount{
		public int getArmiesCount(int n){
			int armies = 0;
			switch(n){
				case 2:
					armies = 40;
					break;
				case 3:
					armies = 35;
					break;
				case 4:
					armies = 30;
					break;
				case 5:
					armies = 25;
					break;
				case 6:
					armies = 20;
					break;
			}
			return armies;
		}
	};
	
	/**
	 * Abstract method for Risk Data enum to get number of armies given based on number of players
	 * @param n number of players
	 * @return number of armies each player gets
	 */
	public abstract int getArmiesCount(int n);
}
