package soo.md.service;

import java.util.List;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import soo.md.domain.Board;
import soo.md.domain.BoardListResult;
import soo.md.domain.BoardVo;
import soo.md.mapper.BoardMapper;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper; //Spring 4.3~: AutoInjection ( with @AllArgsConstructor )  

	@Override
	public BoardListResult getBoardListResult(int cp, int ps) {
		BoardVo boardVo = new BoardVo(cp, ps);
		long totalCount = boardMapper.selectCount();
		List<Board> list = boardMapper.selectPerPage(boardVo);
		
		return new BoardListResult(cp, totalCount, ps,  list);
	}
	@Override
	public BoardListResult getBoardListResult(int cp, int ps, String catgo, String keyword) { //for Search
		BoardVo boardVo = new BoardVo(cp, ps, catgo, keyword); 
		long totalCount = boardMapper.selectCountByCatgo(boardVo);
		List<Board> list = boardMapper.selectPerPageByCatgo(boardVo); 
		
		return new BoardListResult(cp, totalCount, ps,  list);
	}
	@Override
	public Board getBoard(long seq) {
		Board board = boardMapper.selectBySeq(seq);
		return board;
	}
	@Override
	public void write(Board board) {
		boardMapper.insert(board);
	}
	@Override
	public void edit(Board board) {
		boardMapper.update(board);
	}
	@Override
	public void remove(long seq) {
		boardMapper.delete(seq);
	}
}
