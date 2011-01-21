package com.iclub.room.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iclub.common.UserException;
import com.iclub.member.vo.Room;
import com.iclub.service.RoomService;

@Controller
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setRequiredFields(new String[] {"roomName"});
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}	
	
	@RequestMapping(value="/add-room.do",method=RequestMethod.GET)
	public ModelAndView showAddRoom() throws Exception{
		ModelAndView mv = new ModelAndView("add-room");
		mv.addObject("room",new Room());
		return mv;
	}

	@RequestMapping(value="/add-room.do",method=RequestMethod.POST)
	public ModelAndView addRoom(@ModelAttribute("room") Room form,BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception{
		if(bindingResult.hasErrors()){
			return showAddRoom();
		}
		roomService.createRoom(form);
		return listRoom();
	}
	
	@RequestMapping(value="/list-room.do",method=RequestMethod.GET)
	public ModelAndView listRoom() throws Exception{
		ModelAndView mv = new ModelAndView("list-room");
		mv.addObject("roomList",roomService.getAllRooms());
		return mv;
	}	
	
	@RequestMapping(value="/edit-room.do",method=RequestMethod.GET)
	public ModelAndView showeditRoom(@RequestParam("id") long id,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("edit-room");
		Room form = roomService.getRoom(id);
		mv.addObject("room",form);
		return mv;
	}	
	
	@RequestMapping(value="/edit-room.do",method=RequestMethod.POST)
	public ModelAndView editRoom(@ModelAttribute("room")Room form,BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception{
		if(bindingResult.hasErrors()){
			return showeditRoom(form.getRoomId(),request);
		}
		roomService.update(form);
		return listRoom();	
	}		
	
	@RequestMapping(value="/delete-room.do",method=RequestMethod.POST)
	public ModelAndView deleteRoom(@RequestParam("roomId") long id,HttpServletRequest request) throws Exception{
		try{
			roomService.delete(id);
		}catch (UserException e) {
			return showeditRoom(id, request).addObject("error",e.getMessage());
		}
		return listRoom();
	}
	
	@RequestMapping(value="/room.do",method=RequestMethod.GET)
	public ModelAndView showRoomArea() throws Exception{
		return new ModelAndView("room");
	}
}
