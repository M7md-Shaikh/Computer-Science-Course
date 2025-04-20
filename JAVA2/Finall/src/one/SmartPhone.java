

//      Mohammad Sheikh 1221541

package one;

public class SmartPhone extends Device{

	private int data_mega_byte;
	private double minutes;
	private double dataCost;
	private double voiceCost;
	
	public SmartPhone(String modelName, String iMEI, double price, String manufactureDate) {
		super(modelName, iMEI, price, manufactureDate);
		
		
	}

	public SmartPhone(String modelName, String iMEI, double price, String manufactureDate, int data_mega_byte,
			double minutes, double datacost, double voiceecost) {
		super(modelName, iMEI, price, manufactureDate);
		this.data_mega_byte = data_mega_byte;
		this.minutes = minutes;
		this.dataCost = datacost;
		this.voiceCost = voiceecost;
	}

	public int getData_mega_byte() {
		return data_mega_byte;
	}

	public void setData_mega_byte(int data_mega_byte) {
		this.data_mega_byte = data_mega_byte;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

	public double getDatacost() {
		return dataCost;
	}

	public void setDatacost(double datacost) {
		this.dataCost = datacost;
	}

	public double getVoiceecost() {
		return voiceCost;
	}

	public void setVoiceecost(double voiceecost) {
		this.voiceCost = voiceecost;
	}

	@Override
	public String toString() {
		return "SmartPhone [data_mega_byte=" + data_mega_byte + ", minutes=" + minutes + ", datacost=" + dataCost
				+ ", voiceecost=" + voiceCost + "]";
	}
	
	@Override 
	public double getBill() {
		
		return data_mega_byte* (dataCost+minutes) *voiceCost;
		
	}

}
