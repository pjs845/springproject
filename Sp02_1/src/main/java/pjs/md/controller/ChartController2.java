package pjs.md.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;
import pjs.md.domain.ChartDTO2;

@Log4j
@Controller
@RequestMapping("chart")
public class ChartController2 {
	@GetMapping("chart2.do")
	public String chart() {
		return "chart2/chart";
	}
	
	private Random r = new Random();
	@ResponseBody
	@PostMapping("chartData2")
	public List<ChartDTO2> getChartData(){
		List<ChartDTO2> list = new ArrayList<ChartDTO2>();
		String items[] = {"봄", "초여름", "여름", "가을", "늦가을", "겨울"};
		
		for (int i=0; i<items.length; i++) {
			int number = r.nextInt(100);
			ChartDTO2 dto = new ChartDTO2(items[i], number);
			list.add(dto);
		}
		return list;
	}
}
