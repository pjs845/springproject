package pjs.md.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import pjs.md.domain.Board2;
import pjs.md.domain.Board2ResultList;
import pjs.md.domain.Board2Vo;
import pjs.md.fileset.Path;
import pjs.md.mapper.Board2Mapper;
import soo.md.domain.Board;
import soo.md.domain.BoardListResult;
import soo.md.domain.BoardVo;

@Log4j
@AllArgsConstructor
@Service
public class Board2ServiceImpl implements Board2Service {
	private Board2Mapper board2Mapper;

	@Override
	public Board2ResultList listResult(int cp, int ps) {
		long totalPageCount = board2Mapper.allBoard();
		Board2Vo board2Vo = new Board2Vo(cp, ps);
		int endpaging = ((int)Math.ceil((double)cp / (double)5)) * 5;
		int startpaging = endpaging - 4;
		int cntpg = 0;
		cntpg = (int)(totalPageCount/ps);
		if(totalPageCount % ps != 0) {
			cntpg++;
		} 	
		if (cntpg < endpaging) {
			endpaging = cntpg;
		}	
		List<Board2> list =  board2Mapper.list(board2Vo);
		return new Board2ResultList(cp, ps, totalPageCount, list, startpaging, endpaging);
	}
	@Override
	Board2ResultList getBoardListResult(int cp, int ps, String catgo, String keyword) {
		Board2Vo board2Vo = new Board2Vo(cp, ps, catgo, keyword); 
		long totalCount = board2Mapper.selectCountByCatgo(board2Vo);
		List<Board> list = board2Mapper.selectPerPageByCatgo(board2Vo); 
		
		return new Board2ResultList(cp, totalCount, ps,  list);
	}

	@Override
	public Board2 selectlist(long seq) {
		Board2 board = board2Mapper.selectSeq(seq);
		return board;
	}

	@Override
	public void write(Board2 board2, MultipartFile file) {
		String ofname = file.getOriginalFilename();
		log.info("#ofname: " + ofname);
		if(ofname != null) ofname = ofname.trim();
	    if(ofname.length() != 0) {
	    	int idx = ofname.lastIndexOf(".");
			String ofheader = ofname.substring(0, idx);
			String ext = ofname.substring(idx);
			long ms = System.currentTimeMillis();
			
			StringBuilder sb = new StringBuilder();
			sb.append(ofheader);
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
			String saveFileName = sb.toString();//a.jpg
			
			long fsize = file.getSize();
			log.info("#board2----------1-1 : " + board2);
			//log.info("#ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize);
			board2.setFname(saveFileName);
			board2.setOfname(ofname);
			board2.setFsize(fsize);
			
			log.info("#board2----------1-2 : " + board2);
			boolean flag = writeFile(file, saveFileName);
			board2Mapper.insert(board2);
			if(flag) {
		       log.info("#업로드 성공");
		    }else {
		       log.info("#업로드 실패");
		    }
		    String url =  Path.FILE_STORE + saveFileName;
	    }else {
	    	board2.setFname("");
			board2.setOfname("파일이 없습니다.");
			board2.setFsize(0L);
	    	board2Mapper.insert(board2);
	    }
	}

	@Override
	public void remove(long seq) {
		Board2 board = board2Mapper.selectSeq(seq);
		board2Mapper.delete(seq);
		String fname = board.getFname();
		if(fname != null) {
			File file = new File(Path.FILE_STORE, fname);
			if(file.exists()) file.delete();
		}
	}

	@Override
	public void edit(Board2 board2, MultipartFile file) {
		long seq = board2.getSeq();
		Board2 board = board2Mapper.selectSeq(seq);
		String ofname = file.getOriginalFilename();
		log.info("#ofname: " + ofname);
		if(ofname != null) ofname = ofname.trim();
	    if(ofname.length() != 0) {
	    	int idx = ofname.lastIndexOf(".");
			String ofheader = ofname.substring(0, idx);
			String ext = ofname.substring(idx);
			long ms = System.currentTimeMillis();
			
			StringBuilder sb = new StringBuilder();
			sb.append(ofheader);
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
			String saveFileName = sb.toString();//a.jpg
			
			String fname = board.getFname();
			log.info("#fname: " + fname);
			if(fname != null) {
				File filedel = new File(Path.FILE_STORE, fname);
				if(filedel.exists()) filedel.delete();
			}
			
			long fsize = file.getSize();
			log.info("#board2----------1-1 : " + board2);
			//log.info("#ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize);
			board2.setFname(saveFileName);
			board2.setOfname(ofname);
			board2.setFsize(fsize);
			
			log.info("#board2----------1-2 : " + board2);
			board2Mapper.update(board2);
			writeFile(file, saveFileName);
	    }else {
	    	String fname = board.getFname();
	    	if(fname != null) {
	    		File filedel = new File(Path.FILE_STORE, fname);
				if(filedel.exists()) filedel.delete();
	    	}
	    	board2.setFname("");
			board2.setOfname("파일이 없습니다.");
			board2.setFsize(0L);
			board2Mapper.update(board2);
	    }
	}
	private boolean writeFile(MultipartFile file, String saveFileName) {
		File dir = new File(Path.FILE_STORE);
		if(!dir.exists()) dir.mkdirs();

		FileOutputStream fos = null;
		try {
			
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE + saveFileName);
			fos.write(data);
			fos.flush();
			
			return true;
		}
		catch(IOException ie) {
			return false;
		}
		finally {
			try {
				if( fos != null) fos.close();
			}
			catch(IOException ie) {}
		}
	}

}
