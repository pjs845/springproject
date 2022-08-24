package pjs.md.controller;

import java.io.*;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import pjs.md.domain.Address;
import pjs.md.service.AddressAjaxService;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("ajax02")
public class AjaxT02Controller {
	private AddressAjaxService service;
	
	@GetMapping("search01.do")
	public void search01(long seq, HttpServletResponse response) {
		Address address = service.selectBySeqS(seq);
		log.info("#address: " + address);
		
		ObjectMapper om = new ObjectMapper();
		try {
			String addressJson = om.writeValueAsString(address);
			response.setContentType("application/json; charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(addressJson);
			
		}catch(JsonProcessingException je) {
			
		}
		catch(IOException ie) {}
	}
	@PostMapping("search02.do")
	public void search02(String name, HttpServletResponse response) {
		List<Address> list = service.selectByNameS(name);
		log.info("#list: " + list);
		//list를 json형태로 변경 후, response객체를 통해 웹브라우저에게 보내줌
		
		ObjectMapper om = new ObjectMapper();
	      
        try {
           String addressJson = om.writeValueAsString(list);
           response.setContentType("application/json;charset=utf-8");
           PrintWriter pw = response.getWriter();
           pw.println(addressJson);
        }
        catch(JsonProcessingException je) {}
        catch(IOException ie) {}
	}
}
