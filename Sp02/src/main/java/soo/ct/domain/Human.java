package soo.ct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Human {
	private String name;
	private int age;
	
	@Override
	public String toString() {
		return "name: " + name + ", age: " + age;
	}
}
