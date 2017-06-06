package ParkingLot;

public class spot {
	private Vehicle current;
	private final VehicleSize size;
	public spot (VehicleSize s) {
		this.size = s;
	}
	public boolean canPark(Vehicle vehicle) {
		if (vehicle == null && size.getSize() >= vehicle.getSize().getSize()) {
			return true;
		}
		return false;
	}
	public void Park(Vehicle v) {
		current = v;
	}
	public Vehicle Leave() {
		Vehicle cur = current;
		current = null;
		return cur;
	}
	public Vehicle getVehicle() {
		return current;
	}
}
