


//     Mohammad Sheikh 1221541


package one;
public abstract class Device implements Comparable<Device>{

	
	private String modelName;
	private String IMEI;
	private double price;
	private String manufactureDate;
	
	
	public Device(String modelName, String iMEI, double price, String manufactureDate) {
		super();
		this.modelName = modelName;
		IMEI = iMEI;
		this.price = price;
		this.manufactureDate = manufactureDate;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) throws IMEIException {
	try {
		if (IMEI.length()!= 9 || !Character.isLetter(IMEI.charAt(0))
				|| !Character.isLetter(IMEI.charAt(2))|| !Character.isLetter(IMEI.charAt(2))) {
					throw new IMEIException("“The entered IMEI is not valid”");
		}else {
			this.IMEI=IMEI;
		}
	}catch(IMEIException e) {
		System.out.println(e.getMessage());
	}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public String getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	
	@Override
	public String toString() {
		return "Device [modelName=" + modelName + ", IMEI=" + IMEI + ", price=" + price + ", manufactureDate="
				+ manufactureDate + "]";
	}

	@Override
	public int compareTo(Device o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public double getBill() {
		return price;
		
	}
	
	
}
