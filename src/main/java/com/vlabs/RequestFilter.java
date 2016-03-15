package com.vlabs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestFilter extends HandlerInterceptorAdapter {

/*  private final String LOGOUT_URL = "logout";
  private final String SIGN_IN_URL = "signIn";
  private final String LOGIN_URL = "login";
  public final String REST_SEND_MESSAGE_URI = "/sendMessageQueue";
  public final String REST_RECEIVE_MESSAGE_URI = "/receiveMessageQueue";*/


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    boolean returnVal = true;
    String uri = request.getRequestURI();
    //Allow only login, singIn and log out urls, not to check for existing session and its keys.
    /*if (null != uri && !uri.endsWith("/") && !uri.endsWith("/login") && !uri.endsWith("logout") && !uri.endsWith("registration") && !uri.endsWith("register")
    		&& !uri.endsWith("forgotpassword") &&!uri.endsWith("getpassword") &&!uri.endsWith("sendmessage") &&!uri.endsWith("receivemessage") &&!uri.endsWith("rest_delete")
    		&& !uri.endsWith("greeting") && !uri.endsWith("getUserById") && !uri.endsWith("allUsers") && !uri.endsWith("allUsers/{id}") && !uri.endsWith("rest_login"))
    {
      returnVal = isValidSession(request, response);
    }*/
    return returnVal;
  }
  public static final String TRUE="true";
  private boolean isValidSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
    boolean returnVal = true;
    //Get existing session object from request scope.
    HttpSession session = request.getSession(false);
    if (null != session) {
      String isValidSession = (String) session.getAttribute("sessionKey");
      //If VALID_SESSION_KEY is not in session, redirect page to login.
      if (null == isValidSession || !TRUE.equals(isValidSession)) {
        response.sendRedirect("/");
        returnVal = false;
      }
    } else {   //If session is null.
      response.sendRedirect("/");
      returnVal = false;
    }
    return returnVal;
  }


  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    String uri = request.getRequestURI();
    //If it is logout, invalidate the session.
    if (null != uri && (uri.endsWith("logout"))) {
      HttpSession session = request.getSession(false);
      if (session != null) {
        session.invalidate();
      }
    }
  }


}
