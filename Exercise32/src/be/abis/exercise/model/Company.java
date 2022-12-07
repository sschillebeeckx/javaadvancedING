package be.abis.exercise.model;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Company {
	
	private String name;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Address address;

}
