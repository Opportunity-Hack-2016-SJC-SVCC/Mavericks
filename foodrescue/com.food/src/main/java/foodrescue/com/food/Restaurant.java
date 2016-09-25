package foodrescue.com.food;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restaurant {
	private String name;
	private String address;
	private String city;
	private String zip;
	
	private String password;
	private String phone;
	
	private String latitude;
	private String longitude;
		
	public String getCity() { 
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) { 
		this.longitude = longitude;
	}
	private String date;
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Restaurant [Name=" + name + ", address=" + address + ", city=" + city + ", zip="
				+ zip + ", password=" + password + ", Phone=" + phone + "latitude"+latitude+" "+longitude+"]";
	}
	
}
