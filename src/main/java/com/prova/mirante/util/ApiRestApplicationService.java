package com.prova.mirante.util;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(value = "/api")
public class ApiRestApplicationService extends Application {
	
	public Set<Class<?>> getClasses(){
		return Collections.emptySet();
	}
	
	public Set<Object> getSingletons(){
		Set<Object> classes = new HashSet<Object>();
		return classes;
	}
}