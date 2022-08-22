package pjs.md.service;

import java.util.List;
import java.util.Map;

import pjs.md.domain.Board;

public interface BoardService {
	long listallB();
	List<Board> listB(Map<String, Object> map);
	Board selectB(long seq);
	void insertB(Board board);
	void deleteB(long seq);
	void updateB(Board board);
}
