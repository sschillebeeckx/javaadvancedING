package be.abis.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Company {
	
	private String name;
	private Address address;

	public double calculateTaxToPay(){
		double taxToPay = 0.0;
		switch(address.getCountryCode()){
			case "BE"  : taxToPay = 51.0; break;
			case "NL" : taxToPay = 47.0; break;
			default : taxToPay = 35.0; break;
		}
		return taxToPay;
	}

}
