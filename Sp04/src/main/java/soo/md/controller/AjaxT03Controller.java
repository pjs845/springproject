package soo.md.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.AllArgsConstructor;
import soo.md.domain.Address;
import soo.md.service.AddressAjaxService;

@AllArgsConstructor
@Controller
@RequestMapping("ajax03")
public class AjaxT03Controller {
	private AddressAjaxService service;
	
	@GetMapping("search01")
	public @ResponseBody Address search01(long seq) {
		Address address = service.selectBySeqS(seq);
		return address;
	}
	@PostMapping("search02")
	public @ResponseBody List<Address> search02(String name) {
		List<Address> list = service.selectByNameS(name);
		return list;
	}
	
	//Tip
	@GetMapping("txt")
	public String getText() {
		return "have_a_good_day";
	}
}
