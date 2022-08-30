package pjs.md.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import pjs.md.domain.Board;
import pjs.md.service.BoardService;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService; //Spring 4.3~: AutoInjection(with @AllArgsConstructor)
	
	// �̼�!!
	
	//@RequestMapping(value="/list.do", method=RequestMethod.GET)
	@GetMapping("/list.do")
	public ModelAndView list(HttpServletRequest request, Integer ps, Integer cp){
		Map<String, Object> listResult = new HashMap<>();
		if(ps == null) {
			ps = 3;
		}
		if(cp == null) {
			cp = 1;
		}
		HttpSession session = request.getSession();
		session.setAttribute("ps", ps);
		ps = (int)session.getAttribute("ps");
		log.info("#session.ps: " + ps);
		int pageStart = ps*(cp-1)+1;
		int perPageNum = pageStart + ps;
		int endpaging = ((int)Math.ceil((double)cp / (double)5)) * 5;
		int startpaging = endpaging - 4;
		long max = boardService.listallB();
		int cntpg = 0;
		cntpg = (int)(max/ps);
		if(max % ps != 0) {
			cntpg++;
		} 	
		if (cntpg < endpaging) {
			endpaging = cntpg;
		}	
		listResult.put("pageStart", pageStart); //����������
		listResult.put("perPageNum", perPageNum); //���������� + �Խñ� ����
		listResult.put("cp", cp); //����������
		listResult.put("ps", ps); //����Խñ� ����
		listResult.put("startpaging", startpaging);
		listResult.put("endpaging", endpaging);
		List<Board> list = boardService.listB(listResult); 
		listResult.put("list", list);
		listResult.put("totalCount", max);
		listResult.put("totalPageCount", cntpg);
		ModelAndView mv = new ModelAndView("board/list", "listResult", listResult);
		return mv;
	}
	@GetMapping("/write.do")
	public String write(HttpServletRequest request){
		return "board/write";
	}
	@PostMapping("/write.do")
	public String write(Board board, HttpSession session){
		int ps = (int)session.getAttribute("ps");
		log.info("#write ps: " + ps);
		boardService.insertB(board);
		return "redirect:list.do?ps="+ps;
	}
	@GetMapping("/content.do")
	public ModelAndView content(long seq, HttpServletRequest request){
		Board board = boardService.selectB(seq);
		ModelAndView mv = new ModelAndView("board/content", "board", board);
		return mv;
	}
	
	@GetMapping("/update.do")
	public ModelAndView update(long seq, HttpServletRequest request){
		Board board = boardService.selectB(seq);
		ModelAndView mv = new ModelAndView("board/update", "board", board);
		return mv;
	}
	@PostMapping("/update.do")
	public String update(Board board, HttpSession session){
		int ps = (int)session.getAttribute("ps");
		boardService.updateB(board);
		return "redirect:list.do?ps="+ps;
	}
	
	@GetMapping("/del.do")
	public String delete(long seq, HttpSession session, HttpServletRequest request){
		int ps = (int)session.getAttribute("ps");
		boardService.deleteB(seq);
		return "redirect:list.do?ps="+ps;
	}
}