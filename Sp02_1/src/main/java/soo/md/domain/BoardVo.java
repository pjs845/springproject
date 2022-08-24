package soo.md.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
	private int cp = 1; //������ ��ȣ 
	private int ps = 5; //������ ������ 
	//{cp =1; ps=5;}
	
	public int getStartRow() {
		return (cp-1)*ps; //ex) 0 * 5 = 0
	}
	public int getEndRow() {
		return cp*ps; //ex) 1 * 5 = 5
	}
}
