package be.abis.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Address {
	
	private String street;
	private String nr;
	private String zipCode;
	private String town;
	private String country;
	private String countryCode;

	@Override
	public String toString() {
		return street + " " + nr + ", " + zipCode + " " + town + ", " + country + " (" + countryCode +")";
	}

	public boolean isBelgianZipCodeNumeric() {
		return zipCode.matches("\\d+");
	}
}
