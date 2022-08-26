package soo.md.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;
import soo.ct.domain.Human;
import soo.ct.domain.HumanList;
import soo.ct.domain.Todo;

@Log4j
@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("")
	public void m01() {
		log.info("m01() with log4j");
	}
	@RequestMapping("/base1")	
	public void m02() {
		log.info("m02() - Get방식 || Post방식 || ...");
	}	
	@RequestMapping(value="/base2", method= {RequestMethod.GET, RequestMethod.POST})	
	public void m03() {
		log.info("m03() - Get방식 || Post방식");
	}	
	@RequestMapping(value="/form", method=RequestMethod.GET)	
	public String form() {
		return "test/form";
	}
	
	@RequestMapping(value="/param1", method= RequestMethod.GET)
	public void m04(String name) {
		log.info("m04() - name: " + name);
	}
	@RequestMapping(value="/param2", method= RequestMethod.GET)
	public void m05(@RequestParam("na") String name, int age) {
		log.info("m05() - name: " + name + ", age: " + age);
	}
	@RequestMapping("/param3")
	public void m06(Human dto) {
		//log.info("m06() - name: " + dto.getName() + ", age: " + dto.getAge());
		log.info("#m06() - "  + dto);
	}
	@RequestMapping("/param4")
	public void m07(@RequestParam ArrayList<String> names) {
		log.info("m07() - names: "+ names);
	}
	@RequestMapping("/param5")
	public void m08(@RequestParam String[] names) {
		//log.info("m08() - names: "+ names);
		for(String name: names) log.info("name: " + name);
	}
	@RequestMapping("/param6")
	public void m09(@RequestParam("ns") ArrayList<String> names) {
		log.info("m09() - names: "+ names);
	}
	
	@RequestMapping("/param7")
	public void m10(HumanList hlist) {
		//log.info("m06() - name: " + dto.getName() + ", age: " + dto.getAge());
		log.info("#m10() - "  + hlist);
	}
	@RequestMapping("/param8")
	public void m11(@RequestParam("dump") String cla, Human dto, int grade) {
		log.info("#m11() - "  + dto  + ", cla: " + cla + ", grade: " + grade) ;
	}
	@RequestMapping("/param9")
	public void m12(Todo dto) {
		log.info("#m12() - dto: " + dto);
		Date cdate = dto.getCdate();
		log.info("#m12() - cdate: " + cdate);
		
		showDate(cdate);
	}
	private void showDate(Date cdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(cdate);
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH); 
		int d1 = cal.get(Calendar.DATE);
		int d2 = cal.get(Calendar.DAY_OF_WEEK);
		String day = "";
		switch(d2) {
			case 1: day = "일"; break;
			case 2: day = "월"; break;
			case 3: day = "화"; break;
			case 4: day = "수"; break;
			case 5: day = "목"; break;
			case 6: day = "금"; break;
			case 7: day = "토"; break;
		}
		log.info("#m12(): " + y + "년 " + (m+1) + "월 " + d1 + "일("+day+")");
	}
}
