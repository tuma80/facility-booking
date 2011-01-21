package com.iclub.room.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iclub.common.CommonUtils;
import com.iclub.member.vo.Booking;
import com.iclub.member.vo.Room;
import com.iclub.member.vo.UserProfile;
import com.iclub.service.AuthenticateUserService;
import com.iclub.service.BookingService;
import com.iclub.service.RoomService;
import com.iclub.service.TimeRangeService;

@Controller
public class BookedRoomController {
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	TimeRangeService timeRangeService;
	
	@Autowired
	AuthenticateUserService authenticateUserService;
	
	@RequestMapping("list-bookedRoom.do")
	public ModelAndView list(@RequestParam(value="id")long id,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("list-bookedRoom");
		BookedRoomForm form = new BookedRoomForm();
		Room room = roomService.getRoom(id);
		form.setRoomName(room.getRoomName());
		form.setRoomId(room.getRoomId());
		mv.addObject("bookedRoomForm",form);
		String bookedDate = getBookedDateFromRequest(request);
		form.setSelDate(bookedDate);
		mv.addObject("bookedRoomList",roomService.getBookedRooms(id,bookedDate));
		return mv;
	}
	private String getBookedDateFromRequest(HttpServletRequest request){
		String date="";
		date = request.getParameter("selDate");
		if(date == null){
			date = (String)request.getAttribute("selDate"); 
		}
		if(date == null){
			date = CommonUtils.formatDate(new Date(),"yyyy-MM-dd");	
		}
		return date;
	}
	@RequestMapping(value="/list-booking.do",method=RequestMethod.GET)
	public ModelAndView listRoom() throws Exception{
		ModelAndView mv = new ModelAndView("list-booking");
		mv.addObject("roomList",roomService.getAllRooms());
		return mv;
	}		
	
	@RequestMapping("undo-bookedRoom.do")
	public ModelAndView undoBooking(
			@RequestParam(value="id")long bookingId,
			HttpServletRequest request
			){
		long roomId = Long.parseLong(request.getParameter("roomId"));
		String selDate = request.getParameter("selDate");
		roomService.undoBooking(bookingId,CommonUtils.getCurrentUserProfile().getUserId());
		request.setAttribute("selDate",selDate);
		return list(roomId, request);
	}
	
	@RequestMapping("book-room.do")
	public ModelAndView bookRoom(@RequestParam(value="trIds",required=false)long[] timeRanges,@ModelAttribute("bookedRoomForm")BookedRoomForm form,BindingResult bindingResult, HttpServletRequest request, ModelMap model){
		Room room = roomService.getRoom(form.getRoomId());
		UserProfile profile = (UserProfile)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(long timeRange:timeRanges){
			Booking booking = new Booking();
			booking.setDateBooked(CommonUtils.parseDate(form.getSelDate(),"yyyy-MM-dd"));
			booking.setRoom(room);
			booking.setTimeRange(timeRangeService.getTimeRange(timeRange));
			booking.setUserid(authenticateUserService.getAuthenticateUser(profile.getUserId()));
			bookingService.createBooking(booking);
		}
		
		return list(form.getRoomId(),request);
	}
}
