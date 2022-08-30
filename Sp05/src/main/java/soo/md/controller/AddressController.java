package soo.md.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import soo.md.domain.Address;
import soo.md.service.AddressService;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("/address")
public class AddressController {
	//@Autowired
	private AddressService addressService;//Spring 4.3~: AutoInjection ( with @AllArgsConstructor )  
	
	//@RequestMapping(value="/list.do", method=RequestMethod.GET)
	@GetMapping("/list.do")
	public ModelAndView list(){
		List<Address> list = addressService.listS();
		
	    /*
		ModelAndView mv = new ModelAndView();
		mv.setViewName("address/list"); //V
		mv.addObject("list", list); //M
		*/
		ModelAndView mv = new ModelAndView("address/list", "list", list);
		return mv;
	}
	@GetMapping("/write.do")
    public String write(){
		return "address/write";
	}
	@PostMapping("/write.do")
	public String write(Address address){
		addressService.insertS(address);
		return "redirect:list.do";
	}
	@GetMapping("/del.do")
	public String delete(
			long seq, HttpSession session, HttpServletRequest request, Object page, 
			HttpServletResponse response){
		//log.info("#application: " + application); //Auto Injection ºÒ°¡ !!
		
		ServletContext application = session.getServletContext();
		log.info("#application: " + application);
		log.info("#session: " + session);
		log.info("#request: " + request);
		log.info("#page: " + page);
		
		log.info("#response: " + response);
		
		addressService.deleteS(seq);
		return "redirect:list.do";
	}
}
