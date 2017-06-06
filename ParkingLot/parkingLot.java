package ParkingLot;

public class parkingLot {
	private final level[] LEVEL;
	public parkingLot (int numOfLevels, int numOfSpots) {
		LEVEL = new level[numOfLevels];
		for (int i = 0; i < numOfLevels; i++) {
			LEVEL[i] = new level(numOfSpots);
		}
	}
	public boolean Park(Vehicle v) {
		for (level l : LEVEL) {
			if (l.hasSpot(v)) {
				l.Park(v);
				return true;
			}
		}
		return false;
	}
	public boolean hasSpot(Vehicle v) {
		for (level l : LEVEL) {
			if (l.hasSpot(v)) {
				return true;
			}
		}
		return false;		
	}
	public boolean Leave(Vehicle v) {
		for (level l : LEVEL) {
			if (l.Leave(v)) {
				return true;
			}
		}
		return false;
	}
}
