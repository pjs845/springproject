package pjs.md.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import pjs.md.domain.Board2;
import pjs.md.domain.Board2ResultList;
import pjs.md.fileset.Path;
import pjs.md.service.Board2Service;
import soo.md.domain.Board;
import soo.md.domain.BoardListResult;


@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("/board2")
public class Board2Controller {
	private Board2Service boardService; //Spring 4.3~: AutoInjection(with @AllArgsConstructor)
	
	// ?̼?!!
	
	//@RequestMapping(value="/list.do", method=RequestMethod.GET)
	@GetMapping("/list.do")
	public ModelAndView list(HttpServletRequest request, HttpSession session) {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		session.setAttribute("catgo", "subject");
		session.setAttribute("keyword", null);
		
		//(1) cp 
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2) ps 
		int ps = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		
		//(3) ModelAndView 
		Board2ResultList listResult = boardService.listResult(cp, ps);
		ModelAndView mv = new ModelAndView("board2/list", "listResult", listResult);
		
		if(listResult.getList().size() == 0) {
			if(cp > 1)
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else 
				return new ModelAndView("board2/list", "listResult", null);
		}else {
			return mv;
		}
	}
	
	@GetMapping("search/list.do") //for Search
	public ModelAndView list(HttpServletRequest request, HttpSession session, 
			String catgo, String keyword, String searchModeStr) {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		
		//(1) cp 
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2) ps 
		int ps = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		
		log.info("#cp: " + cp + ", #ps: " + ps);
		log.info("#searchModeStr1: " + searchModeStr);
		log.info("#catgo1: " + catgo);
		log.info("#keyword1: " + keyword);
		
		//(3) catgo 
		if(catgo == null) {
			Object catgoObj = session.getAttribute("catgo");
			if(catgoObj != null) {
				catgo = (String)catgoObj;
			}
		}else {
			catgo = catgo.trim();
		}
		session.setAttribute("catgo", catgo);
		log.info("#catgo2: " + catgo);
		
		//(4) keyword 
		if(keyword == null) {
			Object keywordObj = session.getAttribute("keyword");
			if(keywordObj != null) {
				keyword = (String)keywordObj;
			}
		}else {
			keyword = keyword.trim();
		}
		session.setAttribute("keyword", keyword);
		log.info("#keyword2: " + keyword);
		
		//(5) searchModeStr
		if(searchModeStr == null) {
			Object searchModeStrObj = session.getAttribute("searchModeStr");
			if(searchModeStrObj != null) {
				searchModeStr = (String)searchModeStrObj;
			}else {
				searchModeStr = "F";
			}
		}else {
			searchModeStr = searchModeStr.trim();
		}
		session.setAttribute("searchModeStr", searchModeStr);
		log.info("#searchModeStr2: " + searchModeStr);
		
		boolean searchMode = false;
		if(searchModeStr.equalsIgnoreCase("T")) {
			searchMode = true;
		}
		
		//(6) ModelAndView 
		Board2ResultList listResult = null;
		listResult = boardService.getBoardListResult(cp, ps, catgo, keyword);//for Search
		ModelAndView mv = new ModelAndView("board2/list", "listResult", listResult);
		
		if(listResult.getList().size() == 0) {
			if(cp > 1)
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else 
				return new ModelAndView("board2/list", "listResult", null);
		}else {
			return mv;
		}
	}
	@GetMapping("write.do")
	public String write() {
		return "board2/write";
	}
	@PostMapping("write.do")
	public String write(Board2 board, @RequestParam MultipartFile file) {
		boardService.write(board, file);
		return "redirect:list.do?cp=1";
	}
	@GetMapping("content.do")
	public ModelAndView content(long seq) {
		Board2 board = boardService.selectlist(seq);
		ModelAndView mv = new ModelAndView("board2/content", "board", board);
		
		return mv;
	}
	@GetMapping("update.do")
	public ModelAndView update(long seq) {
		Board2 board = boardService.selectlist(seq);
		ModelAndView mv = new ModelAndView("board2/update", "board", board);
		
		return mv;
	}
	@PostMapping("update.do")
	public String update(Board2 board, MultipartFile file) {
		boardService.edit(board, file);
		return "redirect:list.do";
	}
	@GetMapping("del.do")
	public String delete(long seq) {
		boardService.remove(seq);
		return "redirect:list.do";
	}
	@GetMapping("download.do")
	public ModelAndView download(String fname) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) {
			return new ModelAndView("fileDownloadView", "downloadFile", file);
		}else {
			return new ModelAndView("redirect:list.do");
		}
	}
}
