package br.com.singlepoint.ftp.model;

import java.io.Serializable;


public class FtpCredentials implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7930335917845894777L;
	
	private String host;
	private String username;
	private String password;
	private String path;
	
	public FtpCredentials(String host, String username, String password, String path) {

		this.host = host;
		this.username = username;
		this.password = password;
		this.path = path;
	}
	
	public FtpCredentials(String host, String username, String password) {

		this.host = host;
		this.username = username;
		this.password = password;
		this.path = "";
	}
	
	public FtpCredentials() {}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	

}
