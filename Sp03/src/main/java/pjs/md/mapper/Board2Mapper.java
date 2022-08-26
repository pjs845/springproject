package pjs.md.mapper;

import java.util.List;
import java.util.Map;

import pjs.md.domain.AjaxBoard;
import pjs.md.domain.Board2;
import pjs.md.domain.Board2Vo;
import soo.md.domain.Board;
import soo.md.domain.BoardVo;

public interface Board2Mapper {
	List<AjaxBoard> selectPerPage(BoardVo boardVo);
	Board selectBySeq(long seq);
	long selectCount();
	
	void insert(Board board);
	void update(Board board);
	void delete(long seq);
	
	//for Search
	List<Board> selectPerPageByCatgo(BoardVo boardVo);
	long selectCountByCatgo(BoardVo boardVo);
}
