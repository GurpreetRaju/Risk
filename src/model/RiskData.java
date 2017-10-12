package model;

public enum RiskData {
	
	InitialArmiesCount{
		public int getArmiesCount(int n){
			int armies;
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
			return 0;
		}
	}
	
}
