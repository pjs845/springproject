package pjs.md.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pjs.md.domain.Board;
import pjs.md.mapper.BoardMapper;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;
	
	@Override
	public long listallB() {
		
		return boardMapper.listall();
	}
	
	@Override
	public List<Board> listB(Map<String, Object> map) {
		
		return boardMapper.list(map);
	}
	@Override
	public Board selectB(long seq) {
		return boardMapper.select(seq);
	}

	@Override
	public void insertB(Board board) {
		boardMapper.insert(board);
	}

	@Override
	public void deleteB(long seq) {
		boardMapper.delete(seq);
	}

	@Override
	public void updateB(Board board) {
		boardMapper.update(board);
	}

}
