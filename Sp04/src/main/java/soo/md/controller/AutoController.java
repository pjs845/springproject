package soo.md.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.log4j.Log4j;
import soo.md.domain.Address;
import soo.md.service.AddressAjaxService;

@Log4j
@Controller
@RequestMapping("auto")
public class AutoController {
	@Autowired
	private AddressAjaxService service;
	
	@GetMapping("auto.do")
	public String showView() {
		return "auto/autocomplete";
	}
	@PostMapping("autoData")
	public @ResponseBody List<Address> getAddressList(String writer){
		List<Address> list = service.selectByNameS(writer);
		return list;
	}
}
