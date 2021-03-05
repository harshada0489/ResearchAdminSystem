package com.ras.model;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@SessionAttributes(names = "name")
public class RasSessionAttributes {

	
	String name ;
	
	public RasSessionAttributes() {
		// TODO Auto-generated constructor stub
	}

	@ModelAttribute
	public String getName() {
		return name;
	}

	@ModelAttribute
	public void setName(String name) {
		this.name = name;
	}

	
}
