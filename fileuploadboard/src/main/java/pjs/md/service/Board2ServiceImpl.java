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

@Log4j
@AllArgsConstructor
@Service
public class Board2ServiceImpl implements Board2Service {
	private Board2Mapper board2Mapper;

	@Override
	public Board2ResultList listResult(int cp, int ps) {
		long totalPageCount = board2Mapper.allBoard();
		Board2Vo board2Vo = new Board2Vo(cp, ps);
		List<Board2> list =  board2Mapper.list(board2Vo);
		return new Board2ResultList(cp, ps, totalPageCount, list);
	}

	@Override
	public Board2 selectlist(long seq) {
		Board2 board = board2Mapper.selectSeq(seq);
		return board;
	}

	@Override
	public void write(Board2 board2, MultipartFile file) {
		String ofname = file.getOriginalFilename();
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
		//log.info("#ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize);
		board2.setFname(saveFileName);
		board2.setOfname(ofname);
		board2.setFsize(fsize);
		
		log.info("#board2----------1 : " + board2);
		boolean flag = writeFile(board2, file, saveFileName);
		
		if(flag) {
	       log.info("#업로드 성공");
	    }else {
	       log.info("#업로드 실패");
	    }
	    String url =  Path.FILE_STORE + saveFileName;
	}

	@Override
	public void remove(long seq) {
		board2Mapper.delete(seq);

	}

	@Override
	public void edit(Board2 board2) {
		board2Mapper.update(board2);

	}
	private boolean writeFile(Board2 board2, MultipartFile file, String saveFileName) {
		File dir = new File(Path.FILE_STORE);
		if(!dir.exists()) dir.mkdirs();

		FileOutputStream fos = null;
		try {
			log.info("#board2-----------2: " + board2);
			board2Mapper.insert(board2);
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
