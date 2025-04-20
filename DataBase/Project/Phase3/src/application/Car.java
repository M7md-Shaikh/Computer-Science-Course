package application;

public class Car {
   
	private int regNumber;
    private String carType;
    private String carColor;
    private int driverId;

    public Car(int regNumber, String carType, String carColor, int driverId) {
        this.regNumber = regNumber;
        this.carType = carType;
        this.carColor = carColor;
        this.driverId=driverId;
    }

	
	public int getRegNumber() {
		return regNumber;
	}

	
	public void setRegNumber(int regNumber) {
		this.regNumber = regNumber;
	}

	
	public String getCarType() {
		return carType;
	}

	
	public void setCarType(String carType) {
		this.carType = carType;
	}

	
	public String getCarColor() {
		return carColor;
	}

	
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	
	public int getDriverId() {
		return driverId;
	}

	
	public void setDriverId(int driverID) {
		this.driverId = driverID;
	}

	@Override
	public String toString() {
		return "Car [regNumber=" + regNumber + ", carType=" + carType + ", carColor=" + carColor + ", driverId="
				+ driverId + "]";
	}
}
