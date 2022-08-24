package pjs.md.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;
import pjs.md.domain.Address;
import pjs.md.service.AddressAjaxService;

@Log4j
@RequestMapping("auto")
@Controller
public class AutoController2 {
	@Autowired
	private AddressAjaxService service;
	@GetMapping("auto2.do")
	public String showView() {
		return "auto2/autocomplete";
	}
	
	@PostMapping("autoData2")
	public @ResponseBody List<Address> getAddressList(String writer){
		List<Address> list = service.selectByNameS(writer);
		return list;
	}
}
