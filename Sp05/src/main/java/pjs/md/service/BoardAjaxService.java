package pjs.md.service;

import java.util.List;
import java.util.Map;

import pjs.md.domain.AjaxBoard;

public interface BoardAjaxService {
	List<AjaxBoard> listS();
	void insertS(AjaxBoard board);
	void deleteS(long seq);
	void updateS(AjaxBoard board);
	//for Ajax
	AjaxBoard selectBySeqS(long seq);
	List<AjaxBoard> selectByNameS(String name);
}
