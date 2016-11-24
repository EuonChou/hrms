package com.hrms.web;

import java.sql.Connection;

public class ConnectionContext {
	
	private static ConnectionContext instance = new ConnectionContext();
	
	private ThreadLocal<Connection> connectionThreadLocal = 
			new ThreadLocal<>();

	private ConnectionContext(){				
	}
	
	public static ConnectionContext getInstance(){
		return instance;
	}
			
	public Connection get(){
		return connectionThreadLocal.get();
	}
}
