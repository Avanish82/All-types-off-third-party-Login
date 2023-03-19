package com.oauth.implementation.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping;

import com.oauth.implementation.dto.UserRegisteredDTO;
import com.oauth.implementation.model.SendMailUser;
import com.oauth.implementation.service.DefaultUserService;
import com.oauth.implementation.service.SendMailService;

@Controller
@RequestMapping("/mail")
public class SendMailController {
	
	@Autowired
	private SendMailService sendMailService;
	
	 private DefaultUserService userService;
	
//	@PostMapping //("/SendMail")
//	public String sendMailByUser(@ModelAttribute("SendMail")  SendMailUser sendMailUser) throws MessagingException {
//		return sendMailService.sendMailUser(sendMailUser);
//	}
//	@PostMapping
//    public String registerUserAccount(@ModelAttribute("user") 
//              UserRegisteredDTO registrationDto) {
//        userService.save(registrationDto);
//        return "redirect:/login";
//    }

}
