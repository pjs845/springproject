package pjs.md.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pjs.md.domain.Board2;
import pjs.md.domain.Board2ResultList;
import pjs.md.domain.Board2Vo;
import soo.md.domain.BoardListResult;

public interface Board2Service {
	Board2ResultList listResult(int cp, int ps);
	Board2ResultList getBoardListResult(int cp, int ps, String catgo, String keyword); //for Search
	Board2 selectlist(long seq);
	void write(Board2 board2, MultipartFile file);
	void remove(long seq);
	void edit(Board2 board2, MultipartFile file);
}
