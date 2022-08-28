package pjs.md.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Board2Vo {
	private int cp = 1;
	private int ps = 5;
	
	public int getStartRow() {
		return (cp-1)*ps;
	}
	public int getEndRow() {
		return cp*ps;
	}
	
	public Board2Vo(int cp, int ps){ //추가해줘야 함 
		this.cp = cp;
		this.ps = ps;
	}
	
	String catgo; //for Search 
	String keyword; //for Search 
	public String getCatgo() { //for Search
		return catgo;
	}
	public String getKeyword() { //for Search
		return keyword;
	}
}
