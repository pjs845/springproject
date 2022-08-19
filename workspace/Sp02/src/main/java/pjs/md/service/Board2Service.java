package pjs.md.service;

import java.util.List;
import pjs.md.domain.Board2;
import pjs.md.domain.Board2Vo;

public interface Board2Service {
	long boardNum();
	List<Board2> list(Board2Vo board2Vo);
	Board2 selectlist(long seq);
	void write(Board2 board2);
	void remove(long seq);
	void edit(Board2 board2);
}
