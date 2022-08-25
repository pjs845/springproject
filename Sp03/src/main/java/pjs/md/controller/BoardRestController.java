package pjs.md.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;
import pjs.md.domain.Address;
import pjs.md.domain.AjaxBoard;
import pjs.md.domain.Board;
import pjs.md.service.BoardAjaxService;
import pjs.md.service.BoardService;

@Log4j
@RestController
@RequestMapping("rest_board")
public class BoardRestController {
	@Autowired
	private BoardAjaxService service;
	
	//(1) Create(insert)
	@PostMapping("create1")
	public void create(AjaxBoard ajaxBoard) {
		//AjaxBoard board = new AjaxBoard(-1L, "마동석", "aaa@aaa.aaa", "제목321", "안녕3", null);
		//log.info("#BoardRestController create() board: " + board);
		//service.insertS(board);
		log.info("#BoardRestController create() board: " + ajaxBoard);
		service.insertS(ajaxBoard);
	}
	//http://127.0.0.1:8080/rest_board/create1?writer=가1&email=나1&subject=다1&content=라1
	//http://127.0.0.1:8080/rest_board/create1.xml?writer=가2&email=나2&subject=다2&content=라2
	//http://127.0.0.1:8080/rest_board/create1.json?writer=가3&email=나3&subject=다3&content=라3
	@PostMapping("create2")
	public void create2(@RequestBody AjaxBoard ajaxBoard) { //파라미터를 JSON으로 받을 때
		log.info("#BoardRestController create() address: " + ajaxBoard);
		service.insertS(ajaxBoard);
	}
	//http://127.0.0.1:8080/rest_board/create2.json 또는 create2.xml 또는 create2
	//{"writer":"이광수", "email":"address@gmail.com", "subject":"안녕하세요1", "content":"안녕1"}
	
	//(2) Read(select)
	@GetMapping("read")
	public List<AjaxBoard> read(){
		List<AjaxBoard> list = service.listS();
		return list;
	}
	//http://127.0.0.1:8080/rest_board/read 또는 read.xml 또는 read.json
	@GetMapping("read/{seq}")
	public AjaxBoard read(@PathVariable long seq){
		AjaxBoard ajaxBoard = service.selectBySeqS(seq);
		return ajaxBoard;
	}
	//http://127.0.0.1:8080/rest_board/read/1
	//http://127.0.0.1:8080/rest_board/read/1.xml
	//http://127.0.0.1:8080/rest_board/read/1.json
	
	@GetMapping(value="read", params= {"na"})
	public List<AjaxBoard> read(String na){
		List<AjaxBoard> list = service.selectByNameS(na);
		return list;
	}
	//http://127.0.0.1:8080/rest_board/read.json?na=이
	//(3) Delete (delete)
	@GetMapping("delete/{seq}")
	public void delete(@PathVariable long seq, HttpServletResponse response) {
		log.info("#BoardRestController delete() address: " + seq);
		service.deleteS(seq);
		try {
			response.sendRedirect("/rest_board/board");
		}
		catch(IOException ie) {}
	}
	//http://127.0.0.1:8080/rest_board/delete/3
	//http://127.0.0.1:8080/rest_board/delete/2.json
	//(4) Update (update)
	@PutMapping("update")
	public void update(AjaxBoard ajaxBoard) {
		log.info("#BoardRestController update() board: " + ajaxBoard);
		service.updateS(ajaxBoard);
	}
	//http://127.0.0.1:8080/rest_board/update?writer=가11&email=나11&subject=다11&content=라11&seq=17
}
