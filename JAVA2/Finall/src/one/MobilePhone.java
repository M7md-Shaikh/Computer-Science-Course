


// Mohammad Sheikh 1221541

package one;

public class MobilePhone extends Device{

	private String sms_no;
	private String package_no;
	private double price_package;
	private float smscost;
	
	public MobilePhone(String modelName, String iMEI, double price, String manufactureDate) {
		super(modelName, iMEI, price, manufactureDate);
		// TODO Auto-generated constructor stub
	}

	public MobilePhone(String modelName, String iMEI, double price, String manufactureDate, String sms_no,
			String package_no, double price_package, float smscost) {
		super(modelName, iMEI, price, manufactureDate);
		this.sms_no = sms_no;
		this.package_no = package_no;
		this.price_package = price_package;
		this.smscost = smscost;
	}

	public String getSms_no() {
		return sms_no;
	}

	public void setSms_no(String sms_no) {
		this.sms_no = sms_no;
	}

	public String getPackage_no() {
		return package_no;
	}

	public void setPackage_no(String package_no) {
		this.package_no = package_no;
	}

	public double getPrice_package() {
		return price_package;
	}

	public void setPrice_package(double price_package) {
		this.price_package = price_package;
	}

	public float getSmscost() {
		return smscost;
	}

	public void setSmscost(float smscost) {
		this.smscost = smscost;
	}

	@Override
	public String toString() {
		return "MobilePhone [sms_no=" + sms_no + ", package_no=" + package_no + ", price_package=" + price_package
				+ ", smscost=" + smscost + "]";
	}
	
	@Override
	public double getBill() {
		return smscost + price_package;
		
	}
}
