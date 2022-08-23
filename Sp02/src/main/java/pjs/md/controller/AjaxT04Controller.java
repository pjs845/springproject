package pjs.md.controller;

import java.io.*;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import pjs.md.domain.Address;
import pjs.md.service.AddressAjaxService;

@Log4j
@AllArgsConstructor
@RestController //�Ϲ� jsp������ ã�� ���� �ƴ϶� json, xml, txt������ ã��
@RequestMapping("ajax04")
public class AjaxT04Controller {
	private AddressAjaxService service;
	
	@GetMapping(value="search01", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Address search01(long seq) {
		Address address = service.selectBySeqS(seq);
		return address;
	}
	@PostMapping(value="search02", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Address> search02(String name) {
		List<Address> list = service.selectByNameS(name);
		return list;
	}
	//Tip text�� �ν�
	@GetMapping("txt")
	public String getText() {
		return "Have a good day!!";
	}
}
