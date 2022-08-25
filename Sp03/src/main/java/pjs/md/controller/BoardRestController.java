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
		//AjaxBoard board = new AjaxBoard(-1L, "������", "aaa@aaa.aaa", "����321", "�ȳ�3", null);
		//log.info("#BoardRestController create() board: " + board);
		//service.insertS(board);
		log.info("#BoardRestController create() board: " + ajaxBoard);
		service.insertS(ajaxBoard);
	}
	//http://127.0.0.1:8080/rest_board/create1?writer=��1&email=��1&subject=��1&content=��1
	//http://127.0.0.1:8080/rest_board/create1.xml?writer=��2&email=��2&subject=��2&content=��2
	//http://127.0.0.1:8080/rest_board/create1.json?writer=��3&email=��3&subject=��3&content=��3
	@PostMapping("create2")
	public void create2(@RequestBody AjaxBoard ajaxBoard) { //�Ķ���͸� JSON���� ���� ��
		log.info("#BoardRestController create() address: " + ajaxBoard);
		service.insertS(ajaxBoard);
	}
	//http://127.0.0.1:8080/rest_board/create2.json �Ǵ� create2.xml �Ǵ� create2
	//{"writer":"�̱���", "email":"address@gmail.com", "subject":"�ȳ��ϼ���1", "content":"�ȳ�1"}
	
	//(2) Read(select)
	@GetMapping("read")
	public List<AjaxBoard> read(){
		List<AjaxBoard> list = service.listS();
		return list;
	}
	//http://127.0.0.1:8080/rest_board/read �Ǵ� read.xml �Ǵ� read.json
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
	//http://127.0.0.1:8080/rest_board/read.json?na=��
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
	//http://127.0.0.1:8080/rest_board/update?writer=��11&email=��11&subject=��11&content=��11&seq=17
}
