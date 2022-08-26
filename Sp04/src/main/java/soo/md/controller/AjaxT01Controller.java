package soo.md.controller;

import java.io.*;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import soo.md.domain.Address;
import soo.md.service.AddressAjaxService;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("ajax01")
public class AjaxT01Controller {
	private AddressAjaxService service;
	
	@GetMapping("search01.do")
	public void search01(long seq, HttpServletResponse response) {
		Address address = service.selectBySeqS(seq);
		log.info("#address: " + address); 
		
		String addressJson = null;
		if(address != null) {
			addressJson = "{\"seq\":"+address.getSeq()
				+", \"name\":\""+address.getName()
				+"\", \"addr\":\""+address.getAddr()
				+"\", \"rdate\":\""+address.getRdate()
				+"\"}";
		}
		
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(addressJson);
		}catch(IOException ie) {}
	}
	@PostMapping("search02.do")
	public void search02(String name, HttpServletResponse response) {
		List<Address> list = service.selectByNameS(name);
		log.info("#list: " + list);
		
		String addressJson = null;
		if(list.size() != 0) {
			addressJson = "[";
			for(Address address: list) {
				addressJson += "{\"seq\":"+address.getSeq()
		           + ", \"name\":\""+address.getName()
		           +"\", \"addr\":\""+address.getAddr()
		           +"\", \"rdate\":\""+address.getRdate()
		           + "\"}";
				
				addressJson += ",";
			}
			addressJson = addressJson.substring(0, addressJson.length()-1);
			addressJson += "]";		
		}else {
			addressJson = "[]";
		}
		
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(addressJson);
		}catch(IOException ie) {}
	}
}
