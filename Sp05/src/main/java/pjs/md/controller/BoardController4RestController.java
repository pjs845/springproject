package pjs.md.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rest_board")
public class BoardController4RestController {
	@GetMapping("write.do")
	public String write() {
		return "rest_board/write";
	}
	@GetMapping("board")
	public String board() {
		return "rest_board/list";
	}
	@GetMapping("content.do")
	public String content() {
		return "rest_board/content";
	}
	@GetMapping("update.do")
	public String update() {
		return "rest_board/update";
	}
}
