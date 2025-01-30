package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService usersService;
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, HttpServletResponse respons) {
		System.out.println("in controller \"showLoginView\" method\"GET");
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(@RequestBody Map<String, String> loginData, HttpSession session) {
		System.out.println("in controller \"showLoginView\" method\"POST");
		User user =  userLoginInfo(loginData);
		Map<String, String> response = new HashMap<>();
		try {
			if (!usersService.checkEmail(user.getEmail())){
				//將“此帳號尚未註冊”語句加入前端畫面
				
				System.out.println("此帳號尚未進行註冊：" + user.getEmail());
			    response.put("status", "unregistry");
		        response.put("message", "此帳號尚未進行註冊");
				return response;
			}
			
			//確認帳號密碼是否正確
			User completedUser = usersService.login(user);
			System.out.println("登入成功：" +user.getEmail());
		    response.put("status", "success");
	        response.put("message", "登入成功");
			
			//將user資訊裝到session當中
	        session.setAttribute("userId", completedUser.getId());  //存使用者 ID
	        session.setAttribute("userRole", completedUser.getRoles());  //存使用者 ID
			
		}catch (RuntimeException e) {
			 System.out.println(e);
	    	 response.put("status", "error");
	         response.put("message", "發生錯誤：" + e.getMessage());
        }
		return response;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		
		return "Hello User!!";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();  // 清除所有 Session 資料
	    return "redirect:/home"; // 導回登入頁面
		
	}
	
	private User userLoginInfo(Map<String, String> loginData) {
		String email = loginData.get("email");
	    String password = loginData.get("password");
	    
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRoles("user");
		
		return user;
				
	}

	
}

