package com.vlabs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.vlabs.controller.LoginController;
import com.vlabs.form.ForgotPasswordForm;
import com.vlabs.form.RegistrationForm;
import com.vlabs.form.UsersForm;
import com.vlabs.model.Users;
import com.vlabs.service.LoginService;
import com.vlabs.service.RegistrationService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
	private static final int id=1;
	private static final String userName = "bharathsai";
	private static final String password = "bharath";
	private static final String emailId = "bharathsai007@gmail.com";
	private static final String mobileNo = "1236547890";
	@Mock
	LoginService loginService;

	@Mock
	BindingResult res;

	@Mock
	RegistrationService registrationService;

	
   @Mock
   HttpServletRequest request;
	Users users;
	
	Users user;
	
	List<Users> list;

	UsersForm usersForm;

	ForgotPasswordForm forgotPasswordForm;

	RegistrationForm registrationForm;

	private LoginController loginController;

	@Before
	public void setup() {
		loginController = new LoginController(loginService, registrationService);
	}

	/*@Test
	public void loginTest() {
		usersForm = new UsersForm();
		usersForm.setUserEmail(userName);
		usersForm.setPassword(password);
		when(res.hasErrors()).thenReturn(false);
		when(loginService.loginService(usersForm.getUserEmail(),usersForm.getPassword())).thenReturn(true);
		ModelAndView mav = loginController.login(usersForm, res,request);
		assertNotNull(mav);
		assertEquals("sucesslogin", mav.getViewName());
		
	}
*/
	@Test
	public void login_ValidationTest() {
		usersForm = new UsersForm();
		usersForm.setUserEmail(userName);
		usersForm.setPassword(password);
		when(res.hasErrors()).thenReturn(true);
		ModelAndView mav = loginController.login(usersForm, res,request);
		assertNotNull(mav);
		assertEquals("loginform", mav.getViewName());
		ModelMap map = mav.getModelMap();
		assertTrue(map.containsValue(usersForm));
	}

	@Test
	public void login_FailureTest() {
		usersForm = new UsersForm();
		usersForm.setUserEmail(userName);
		usersForm.setPassword(password);
		when(res.hasErrors()).thenReturn(false);
		when(
				loginService.loginService(usersForm.getUserEmail(),
						usersForm.getPassword())).thenReturn(false);
		ModelAndView mav = loginController.login(usersForm, res,request);
		assertNotNull(mav);
		assertEquals("loginform", mav.getViewName());
		ModelMap model = mav.getModelMap();
		assertTrue(model.containsValue(usersForm));
		assertTrue(model.containsKey("message"));

	}

	@Test
	public void forgotPasswordTest() {
		forgotPasswordForm = new ForgotPasswordForm();
		forgotPasswordForm.setEmailId(emailId);
		when(res.hasErrors()).thenReturn(false);
		when(
				loginService.forgotPasswordService(forgotPasswordForm
						.getEmailId())).thenReturn(true);
		ModelAndView mav = loginController.getPassword(forgotPasswordForm, res);
		assertNotNull(mav);
		assertEquals("loginform", mav.getViewName());
		ModelMap map=mav.getModelMap();
		assertTrue(map.containsKey("message"));
		assertTrue(map.containsKey("usersForm"));
        
	}

	@Test
	public void forgotPassword_FailureTest() {
		forgotPasswordForm = new ForgotPasswordForm();
		forgotPasswordForm.setEmailId(emailId);
		when(res.hasErrors()).thenReturn(false);
		when(
				loginService.forgotPasswordService(forgotPasswordForm
						.getEmailId())).thenReturn(false);
		ModelAndView mav = loginController.getPassword(forgotPasswordForm, res);
		assertNotNull(mav);
		assertEquals("forgotpassword", mav.getViewName());
		ModelMap model = mav.getModelMap();
		assertTrue(model.containsKey("forgotPasswordForm"));
		assertTrue(model.containsKey("errorMessage"));
	}

	@Test
	public void registrationTest() {
		registrationForm = createRegistrationForm();
		when(res.hasErrors()).thenReturn(false);
		when(registrationService.addEmployee(registrationForm))
				.thenReturn(true);
		ModelAndView mav = loginController.userRegistration(registrationForm,
				res);
		assertNotNull(mav);
		assertEquals("registrationpage", mav.getViewName());
		ModelMap model = mav.getModelMap();
		assertTrue(model.containsKey("message"));
		assertTrue(model.containsKey("registrationForm"));
	}

	@Test
	public void registration_FailedTest() {
		registrationForm = createRegistrationForm();
		when(res.hasErrors()).thenReturn(true);
		when(registrationService.addEmployee(registrationForm)).thenReturn(
				false);
		ModelAndView mav = loginController.userRegistration(registrationForm,
				res);
		assertNotNull(mav);
		assertEquals("registrationpage", mav.getViewName());
	}

	 @Test 
	 public void getListTest(){
	  users=createUsersBean();
      list=new ArrayList<Users>();
      list.add(users);
	  when(registrationService.getList()).thenReturn(list);
	  ModelAndView mav=loginController.getList();
	  assertNotNull(mav);
	  assertEquals("list", mav.getViewName());
	  ModelMap map=mav.getModelMap();
	  assertTrue(map.containsKey("list"));
	  }
	 
	 @Test
	 public void editUserTest(){
		  users=createUsersBean();
	      when(registrationService.getUserById(id)).thenReturn(users);
	      registrationForm = createRegistrationForm();
		  when(registrationService.editService(users)).thenReturn(registrationForm);
		  ModelAndView mav = loginController.editUser(id, registrationForm);
		  assertNotNull(mav);
		  assertEquals("edit", mav.getViewName());
		  ModelMap map=mav.getModelMap();
		  assertTrue(map.containsValue(registrationForm));
	 }
	 
	 @Test
	 public void updateTest(){
		 registrationForm=createRegistrationForm();
		 when(registrationService.update(registrationForm)).thenReturn(true);
		 ModelAndView mav=loginController.update(registrationForm);
		 assertNotNull(mav);
		 assertEquals("redirect:list", mav.getViewName());
	 }
	 
	 @Test
	 public void deleteTest(){
		 registrationForm=createRegistrationForm();
		 when(registrationService.delete(id)).thenReturn(true);
		 ModelAndView mav=loginController.deleteUser(registrationForm);
		 assertNotNull(mav);
		 assertEquals("redirect:list", mav.getViewName());
	 }
	 
	 public Users createUsersBean(){
		  Users user=new Users();
		  user.setUserName(userName);
		  user.setPassword(password);
		  user.setId(id);
		  user.setMobileNum(mobileNo);
	      user.setEmailId(emailId);
	      return user;
	 }
	 
	 public RegistrationForm createRegistrationForm(){
		  RegistrationForm form=new RegistrationForm();
		  form.setUserName(userName);
		  form.setPassword(password);
		  form.setMobileNum(mobileNo);
		  form.setEmailId(emailId);
	      return form;
	 }
	 
	 
	 

}
