package pjs.md.mapper;
import java.util.List;
import java.util.Map;

import pjs.md.domain.AjaxBoard;

public interface BoardAjaxMapper {
	List<AjaxBoard> list();
	void insert(AjaxBoard board);
	void delete(long seq);
	void update(AjaxBoard board);
	
	//for Ajax
	AjaxBoard selectBySeq(long seq);
	List<AjaxBoard> selectByName(String name);
}
