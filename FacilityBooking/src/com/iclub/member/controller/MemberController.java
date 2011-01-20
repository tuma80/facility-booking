package com.iclub.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setRequiredFields(new String[] {"person.name", "person.address", "person.phone", "person.email"});
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));

	}
	
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) throws Exception{
		request.getSession().invalidate();
		return new ModelAndView("login");
	}	
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public ModelAndView showLogin(@RequestParam(value="error", required=false) boolean error) throws Exception{
		ModelAndView mv = new ModelAndView("login");
		if(error){
			mv.addObject("error","User Name or Password is incorrect");
		}
		return mv;
	}
	
}