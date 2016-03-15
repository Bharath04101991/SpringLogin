package com.vlabs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vlabs.form.ChangePasswordForm;
import com.vlabs.form.ForgotPasswordForm;
import com.vlabs.form.RegistrationForm;
import com.vlabs.form.UsersForm;
import com.vlabs.model.Users;
import com.vlabs.model.Users_Passwords;
import com.vlabs.service.LoginService;
import com.vlabs.service.RegistrationService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	public LoginController() {

	}

	public LoginController(LoginService loginService,RegistrationService registrationService) {
		this.loginService = loginService;
		this.registration = registrationService;

	}
	

	@RequestMapping(value = { "/", "welcome" },method=RequestMethod.GET)
	public ModelAndView showLoginForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("usersForm", new UsersForm());
		mav.setViewName("loginform");
		return mav;

	}
	
	@RequestMapping(value = { "/", "welcome" },method=RequestMethod.POST)
	public ModelAndView showLoginFormPost(ModelAndView mav,HttpServletRequest request, HttpServletResponse response) {
		if(mav==null){
		mav = new ModelAndView();
		}
		mav.addObject("usersForm", new UsersForm());
		mav.setViewName("loginform");
		HttpSession session=request.getSession(false);
		if(session==null){
			session=request.getSession(true);
		}
		String sessionid = request.getSession().getId();
	    response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; secure ; HttpOnly");
		return mav;

	}
	public static final String TRUE = "true";
	@RequestMapping("/login")
	public ModelAndView login(
			@ModelAttribute("usersForm") @Valid UsersForm usersForm,
			BindingResult res,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (res.hasErrors()) {
			mav.addObject(usersForm);
			mav.setViewName("loginform");
			return mav;
		}
		/*char uservalue;
        uservalue=usersForm.getUserEmail().charAt(0);
        char[] numArray={'1','2','3','4','5','6','7','8','9','0'};
        int count=0;
        for (char c : numArray) {
			if(uservalue==c)
			{
				count++;
			}
		}*/
		boolean count=NumberUtils.isNumber(usersForm.getUserEmail());
		HttpSession session=request.getSession(false);
		if(null==session)
		{
			session=request.getSession(true);
		}
		session.setAttribute("UserName", usersForm.getUserEmail());
		boolean result;
		if(!count){
		result = loginService.loginService(usersForm.getUserEmail(),
				usersForm.getPassword());
		}
		else{
		result=loginService.loginServiceusingMobileNum(usersForm.getUserEmail(),
					usersForm.getPassword());
		}
		if (result == true) {
			session.setAttribute("sessionKey", TRUE);
			mav.setViewName("sucesslogin");
			return mav;
		} else {
			mav.addObject(usersForm);
			mav.addObject("message", "invalid credentials");
			mav.setViewName("loginform");
			return mav;
		}

	}

	@RequestMapping(value = "forgotpassword")
	public ModelAndView forgotPassword(
			@ModelAttribute ForgotPasswordForm forgotPasswordForm) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(forgotPasswordForm);
		mav.setViewName("forgotpassword");
		return mav;

	}

	@RequestMapping("getpassword")
	public ModelAndView getPassword(
			@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult res) {
		ModelAndView mav = new ModelAndView();
		if (res.hasErrors()) {
			mav.addObject(forgotPasswordForm);
			mav.setViewName("forgotpassword");
			return mav;
		}
		boolean result = loginService.forgotPasswordService(forgotPasswordForm
				.getEmailId());
		if (result) {
			mav.addObject("message", "PasswordSent to your mail sucessfully");
			mav.addObject("usersForm", new UsersForm());
			mav.setViewName("loginform");
		} else {
			mav.setViewName("forgotpassword");
			mav.addObject("errorMessage", "Invalid EmailId");
			mav.addObject("forgotPasswordForm", new ForgotPasswordForm());
		}
		return mav;

	}

	@RequestMapping("registration")
	public ModelAndView userRegistrationpage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("registrationForm", new RegistrationForm());
		mav.setViewName("registrationpage");
		return mav;

	}

	@Autowired
	RegistrationService registration;

	@RequestMapping("register")
	public ModelAndView userRegistration(
			@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm,
			BindingResult res) {
		ModelAndView mav = new ModelAndView();

		if (!res.hasErrors()) {
			mav.addObject("message", "UserRegisteredSucessfully");
			mav.addObject("registrationForm", new RegistrationForm());
			// mav.addObject("usersForm",new UsersForm());
			mav.setViewName("registrationpage");
		} else {
			mav.setViewName("registrationpage");
			return mav;
		}
		boolean result = registration.addEmployee(registrationForm);
		return mav;

	}

	@RequestMapping("list")
	public ModelAndView getList() {
		List<Users> list = registration.getList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("list");
		return mav;

	}

	@RequestMapping("edit")
	public ModelAndView editUser(@RequestParam int id,
			@ModelAttribute RegistrationForm registrationForm) {
		Users user = new Users();
		user = registration.getUserById(id);
		registrationForm = registration.editService(user);
		ModelAndView mav = new ModelAndView();
		mav.addObject(registrationForm);
		mav.setViewName("edit");
		return mav;
	}

	@RequestMapping("update")
	public ModelAndView update(@ModelAttribute RegistrationForm registrationForm) {
		System.out.println("ID :" + registrationForm.getId());
		registration.update(registrationForm);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		return mav;
	}

	@RequestMapping("delete")
	public ModelAndView deleteUser(
			@ModelAttribute RegistrationForm registrationForm) {
		System.out.println("------------------" + registrationForm.getEmailId()
				+ "" + registrationForm.getId());
		registration.delete(registrationForm);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		return mav;
	}

	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if(session != null){
			session.removeAttribute("sessionKey");
			session.invalidate();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:welcome");
		return mav;
	}

	@RequestMapping("changePasswordpage")
	public ModelAndView changePasswordpage(HttpServletRequest request){
	    HttpSession session = request.getSession(false);
		Users user=registration.getUserByEmail((String) session.getAttribute("UserName"));
		ChangePasswordForm form=new ChangePasswordForm();
		form.setId(user.getId());
		form.setPassword(user.getPassword());
		ModelAndView mav=new ModelAndView();
		mav.addObject(form);
		mav.setViewName("changepassword");
		return mav;
	}
	
	/*@RequestMapping("resetPassword")
	public ModelAndView changePasswordpage(@ModelAttribute("form") ChangePasswordForm form){
		Users_Passwords passwords;
		passwords=registration.getPasswordsById(form.getId());
		
	}*/
	
}
