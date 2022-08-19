package pjs.md.mapper;

import java.util.List;
import java.util.Map;

import pjs.md.domain.Board2;
import pjs.md.domain.Board2Vo;

public interface Board2Mapper {
	long listall();
	List<Board2> list(Board2Vo board2Vo);
	Board2 select(long seq);
	
	void insert(Board2 board);
	void delete(long seq);
	void update(Board2 board);
}
