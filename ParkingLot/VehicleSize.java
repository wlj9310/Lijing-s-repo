package ParkingLot;

public enum VehicleSize {
	CAR(1),
	TRUCK(2);
	
	private final int size;
	VehicleSize (int size) {
		this.size = size;
	}
	public int getSize() {
		return size;
	}
}