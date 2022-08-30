package pjs.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pjs.md.domain.AjaxBoard;
import pjs.md.domain.Board;
import pjs.md.mapper.BoardAjaxMapper;

@Service
public class BoardAjaxServiceImpl implements BoardAjaxService {
	@Autowired
	private BoardAjaxMapper boardAjaxMapper;

	@Override
	public List<AjaxBoard> listS() {
		return boardAjaxMapper.list();
	}
	@Override
	public void insertS(AjaxBoard board) {
		boardAjaxMapper.insert(board);
	}

	@Override
	public void deleteS(long seq) {
		boardAjaxMapper.delete(seq);
	}

	@Override
	public void updateS(AjaxBoard board) {
		boardAjaxMapper.update(board);

	}

	@Override
	public AjaxBoard selectBySeqS(long seq) {
		return boardAjaxMapper.selectBySeq(seq);
	}

	@Override
	public List<AjaxBoard> selectByNameS(String name) {
		return boardAjaxMapper.selectByName(name);
	}

}
