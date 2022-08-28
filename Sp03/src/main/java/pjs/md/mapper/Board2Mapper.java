package pjs.md.mapper;

import java.util.List;
import java.util.Map;

import pjs.md.domain.AjaxBoard;
import pjs.md.domain.Board2;
import pjs.md.domain.Board2Vo;
import soo.md.domain.Board;
import soo.md.domain.BoardVo;

public interface Board2Mapper {
	List<Board2> list(Board2Vo board2Vo);
	Board2 selectBySeq(long seq);
	long allBoard();
	
	void insert(Board2 board);
	void update(Board2 board);
	void delete(long seq);
	
	//for Search
	List<Board2> selectPerPageByCatgo(Board2Vo boardVo);
	long selectCountByCatgo(Board2Vo boardVo);
}
