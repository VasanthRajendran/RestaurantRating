package dataObjects;

public class Address {
	
	private String address;
	private String state;
	private String city;
	private String zipCode;
	
	public Address(String address, String state, String city, String zipCode)
	{
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
}
