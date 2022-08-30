package pjs.md.mapper;

import java.util.List;
import java.util.Map;

import pjs.md.domain.Board;
public interface BoardMapper {
	long listall();
	List<Board> list(Map<String, Object> map);
	Board select(long seq);
	void insert(Board board);
	void delete(long seq);
	void update(Board board);
}
