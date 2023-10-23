package br.com.singlepoint.ftp.controller;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.singlepoint.ftp.model.FtpCredentials;
import br.com.singlepoint.ftp.service.FtpDownload;
import br.com.singlepoint.ftp.service.FtpUpload;

@RestController
@RequestMapping("/ftp")
public class FtpController {
	

	
	@Autowired
	FtpUpload ftpUpload;
	
	@Autowired
	FtpDownload ftpDownload;
	
	@PostMapping("/upload-file")
	public ResponseEntity<Object> uploadFile(@RequestBody byte[] fileBytes,
			@RequestHeader("urlftp") String host, @RequestHeader("username") String username,
			@RequestHeader("password") String password, @RequestHeader("path") String path, @RequestHeader("token") String token, @RequestHeader("filename") String filename) {
		
		ftpUpload.uploadFile(new FtpCredentials(host, username, password, path), fileBytes, filename);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("/list-files")
	public ResponseEntity<FTPFile[]> listFiles(@RequestBody FtpCredentials ftpCredentials, @RequestHeader("token") String token) {
	
		return ResponseEntity.ok(ftpDownload.listFiles(ftpCredentials));
	}
	
	@PostMapping("/download-file")
	public ResponseEntity<byte[]> downloadFile(@RequestBody FtpCredentials ftpCredentials, @RequestHeader("filename") String filename, @RequestHeader("token") String token) {
		
		return ResponseEntity.ok(ftpDownload.downloadFile(ftpCredentials, filename));
	}
	
	@PostMapping("/move-file")
	public ResponseEntity<byte[]> moveFile(@RequestBody FtpCredentials ftpCredentials, @RequestHeader("filename") String filename, @RequestHeader("newpathname") String newPathName, @RequestHeader("token") String token) {
		
		return ResponseEntity.ok().build();
	}

}
