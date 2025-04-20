package application;

import java.sql.Date;

public class Order {
    private int orderId;
    private int customerId;
    private String productName;
    private int quantity;
    private Date orderDate;
    private String status;
    private int managerId;
    private int driverId;
    private int carId;

    
	public Order(int orderId, int customerId, String productName, int quantity, Date orderDate, String status,
			int managerId, int driverId, int carId) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.productName = productName;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.status = status;
		this.managerId = managerId;
		this.driverId = driverId;
		this.carId = carId;
	}

	public int getManagerId() {
		return managerId;
	}

	
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	
	public int getDriverId() {
		return driverId;
	}

	
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	
	public int getCarId() {
		return carId;
	}

	
	public void setCarId(int carId) {
		this.carId = carId;
	}


	// Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", customerId=" + customerId + ", productName=" + productName
                + ", quantity=" + quantity + ", orderDate=" + orderDate + ", status=" + status + "]";
    }
}
