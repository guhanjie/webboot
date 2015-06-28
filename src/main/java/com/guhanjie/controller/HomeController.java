package com.guhanjie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guhanjie.model.User;
import com.guhanjie.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	public String hello(@PathVariable("userId") Integer id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "user";
	}
	
	/**
	 * 这里就是定义RESTful接口的地方，本系统可以将自己的服务接口暴露给外部系统
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rest/user/{userId}/", method=RequestMethod.GET)
	@ResponseBody
	public User restful(@PathVariable("userId") Integer id, Model model) {
		User user = userService.getUser(id);
		return user;
	}
}
