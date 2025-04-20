package application;

public class Manager {
    private int managerId;
    private String name;
    private String phone;
    private String address;

    public Manager(int managerId, String name, String phone, String address) {
        this.managerId = managerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
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
		return "Manager [managerId=" + managerId + ", name=" + name + ", phone=" + phone + ", address=" + address + "]";
	}

    
}
