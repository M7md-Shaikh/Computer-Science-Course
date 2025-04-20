package application;

public class Driver {
    private int driverId;
    private int managerId;
    private String name;
    private String phone;
    private String address;

    public Driver(int driverId, int managerId, String name, String phone, String address) {
        this.driverId = driverId;
        this.managerId = managerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Getters and setters
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", managerId=" + managerId + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + "]";
	}
    
}
