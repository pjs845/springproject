package pjs.md.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Board2ResultList {
	private List<Board2> list;
	private int cp;
	private int ps;
	private int startpaging;
	private int endpaging;
	private long totalPageCount;
	private long totalCount;
	
	public Board2ResultList(int cp, int ps, long totalBoard, List<Board2> list, int startpaging, int endpaging) {
		this.cp = cp;
		this.ps = ps;
		this.startpaging = startpaging;
		this.endpaging = endpaging;
		this.totalCount = totalBoard;
		this.list = list;
		this.totalPageCount = getTotalPage();
		
	}
	private long getTotalPage() {
		long tcp = totalCount/ps;
		if(totalCount%ps!=0) {
			tcp++;
		}
		return tcp; 
	}
}
