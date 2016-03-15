package com.vlabs.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vlabs.dao.LoginDAO;
import com.vlabs.form.HateoasForm;
import com.vlabs.form.RegistrationForm;
import com.vlabs.model.Greeting;
import com.vlabs.model.Users;
import com.vlabs.repository.UserRepository;
import com.vlabs.service.RegistrationService;
import com.vlabs.utility.PageResource;

@RestController
@RequestMapping(value="/allUsers")
public class HateaosController {

    private static final String MESSAGE = "Hello, %s!";
    
   /* @RequestMapping(value="/greeting")
    public HttpEntity<Greeting> greeting(@RequestParam(value="name",required=false,defaultValue="world")String name){
        
    	Greeting greeting=new Greeting(String.format(MESSAGE, name));
    	greeting.add(linkTo(methodOn(HateaosController.class).greeting(name)).withSelfRel());
    	Link link=greeting.getLink(Link.REL_SELF);
    	System.out.println(link.getHref());
    	return new ResponseEntity<Greeting>(greeting,HttpStatus.OK);
    }*/
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(method=RequestMethod.GET)
    public List<HateoasForm> getAllUsers(){
    	List<Users> list=new ArrayList();
    	list=userRepository.findAll();
    	List<HateoasForm> userslist=new ArrayList<HateoasForm>(list.size());
    	for(Users user:list){
    		HateoasForm resource=new HateoasForm();
    		resource.setUserName(user.getUserName());
    		resource.setPassword(user.getPassword());
    		resource.setPhoneNo(user.getMobileNum());
    		resource.setEmailId(user.getEmailId());
    		Link details=linkTo(methodOn(HateaosController.class).getAllUsers()).slash(user.getId()).withSelfRel();
    		resource.add(details);
    		userslist.add(resource);
    	}
    
        
    	return userslist;
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Users> contact(@PathVariable int id) {
    	Integer ids=new Integer(id);
		Users user = userRepository.findOne(ids);
		ResponseEntity<Users> response = new ResponseEntity<Users>(
				user,
				user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
		);
		return response;
	}
    
    @RequestMapping(value="pages",method=RequestMethod.GET)
    public PageResource<Users> usersPages(@RequestParam(required=false,defaultValue="0") int page,
    		@RequestParam(required=false,defaultValue="1") int size){
    	Pageable pageable=new PageRequest(page, size, new Sort("id"));
    	Page<Users> pageResult=userRepository.findAll(pageable);
    	return new PageResource<Users>(pageResult, "page", "size");
    }
}
