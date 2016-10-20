package com.casumo.videorental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SwaggerController {

	@RequestMapping(value = "/documentation", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:swagger-ui.html";
	}
}
