package pjs.md.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;
import pjs.md.service.DragDropService2;

@Log4j
@Controller
@RequestMapping("file")
public class DragdropController2 {
	@Autowired
	private DragDropService2 service;
	@GetMapping("form_dd2.do")
	public String form() {
		return "drag_drop2/form";
	}
	@PostMapping("fileUpload2.do")
	public String fileUpload(MultipartHttpServletRequest mhsr) {
		service.setMultipartRequest(mhsr);
		Map<String, List<Object>> map = service.getUpdateFileName();
		String appendData = mhsr.getParameter("temp");
		return "redirect:list.do";
	}
}
