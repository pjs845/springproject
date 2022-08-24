package pjs.md.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChartDTO2 {
	private String item;
	private int number;
	
	@Override
	public String toString() {
		return "#ChartDTO2 item: " + item + ", number: " + number;
	}
}
