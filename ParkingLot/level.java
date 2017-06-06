package ParkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class level {
	private final List<spot> spots;
	public level(int numOfVehicle) {
		List<spot> list = new ArrayList<spot>(numOfVehicle);
		for (int i = 0; i < numOfVehicle / 2; i++) {
			list.add(new spot(VehicleSize.CAR));
		}
		for (int i = numOfVehicle / 2; i < numOfVehicle; i++) {
			list.add(new spot(VehicleSize.TRUCK));
		}
		spots = Collections.unmodifiableList(list);
	}
	public boolean hasSpot(Vehicle vehicle) {
		for (spot s : spots) {
			if (s.canPark(vehicle)) {
				return true;
			}
		}
		return false;
	}
	public boolean Park(Vehicle vehicle) {
		for (spot s: spots) {
			if (s.canPark(vehicle)) {
				s.Park(vehicle);
				return true;
			}
		}
		return false;
	}
	public boolean Leave(Vehicle vehicle) {
		for (spot s : spots) {
			if (s.getVehicle() == vehicle) {
				s.Leave();
				return true;
			}
		}
		return false;
	}
}
