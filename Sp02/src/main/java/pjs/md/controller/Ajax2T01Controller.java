package pjs.md.controller;

import java.io.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import pjs.md.domain.Address;
import pjs.md.service.AddressAjaxService2;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("ajax02")
public class Ajax2T01Controller {
	private AddressAjaxService2 service;
	@GetMapping("search2_01.do")
	public void search01(long seq, HttpServletResponse response) {
		
		Address address = service.selectBySeqS(seq);
		String addressJson = null;
		if(address != null) {
			addressJson = "{\"seq\":"+address.getSeq()
			+", \"name\":\""+address.getName()
			+"\", \"addr\":\""+address.getAddr()
			+"\", \"rdate\":\""+address.getRdate()
			+"\"}";
		}
		try {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(addressJson);
		}catch(IOException ie) {
			
		}
		
	}
}
