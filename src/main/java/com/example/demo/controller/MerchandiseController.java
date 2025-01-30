package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Merchandise;
import com.example.demo.service.MerchandiseService;

@Controller
@RequestMapping("/merchandise")
public class MerchandiseController {
	@Autowired
	MerchandiseService merchandiseService; 
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String viewMerchandise(Model model) {
		List<Merchandise> merchandiseList = merchandiseService.getALLMerchandise();
		model.addAttribute("merchandiseList", merchandiseList);
		return "merchandise";
	}
		
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody  
	public Map<String, String> createData(@RequestBody Map<String, String> formData) {
	    System.out.println("接收到資料：" + formData);
	    Map<String, String> response = new HashMap<>();
	    try {
	    	Merchandise merchandise = toMerchandise(formData);
		    Merchandise newMerchandise = merchandiseService.createMerchandise(merchandise);
		    System.out.println("資料庫新增成功：" + newMerchandise);
		    response.put("status", "success");
	        response.put("message", "資料新增成功");
		    
	    }catch(Exception e) {
	    	 System.out.println(e);
	    	 response.put("status", "error");
	         response.put("message", "發生錯誤：" + e.getMessage());
	    }
	    
	    return response;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveData(@RequestBody Map<String, String> formData) {
	    System.out.println("接收到資料：" + formData);
	    Map<String, String> response = new HashMap<>();
	    // 把資料存到資料庫
	    try {
	    	// TODO: BUG:目前只能更新一個項目，前端目前只回傳一筆資料
	    	Merchandise merchandise = toMerchandise(formData);
		    Merchandise newMerchandise = merchandiseService.updateMerchandise(merchandise.getId(), merchandise);
		    System.out.println("資料庫更新成功：" + newMerchandise);
		    response.put("status", "success");
	        response.put("message", "資料更新成功");
		    
	    }catch(Exception e) {
	    	 System.out.println(e);
	    	 response.put("status", "error");
	         response.put("message", "發生錯誤：" + e.getMessage());
	    }
	    
	    return response;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteData(@RequestBody Map<String, String> formData) {
	    System.out.println("接收到資料：" + formData);
	    // 把資料從資料庫刪除
	    try {
	    	// TODO: BUG:目前只能刪除一個項目，前端目前只回傳一筆資料
	    	Merchandise merchandise = toMerchandise(formData);
	    	merchandiseService.deleteMerchandiseByid(merchandise.getId());
		    System.out.println("資料庫刪除成功：");
		    
	    }catch(Exception e) {
	    	 System.out.println(e);
	    }
	    
	    return ResponseEntity.ok("刪除成功");
	}
	
	private Merchandise toMerchandise(Map<String, String> formData) {
		 Merchandise  merchandise = new  Merchandise();
		 if (formData.get("id") != null && !formData.get("id").isEmpty()) {
			 merchandise.setId(formData.get("id"));
		 }
		 merchandise.setName(formData.get("name"));
		 merchandise.setPrice(Integer.parseInt(formData.get("price")));
		 merchandise.setQty(Integer.parseInt(formData.get("qty")));
		 merchandise.setImgurl(formData.get("url"));
		 
		 return  merchandise;
	}
}
