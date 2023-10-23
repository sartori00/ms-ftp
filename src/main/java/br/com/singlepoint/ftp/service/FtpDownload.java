package br.com.singlepoint.ftp.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.singlepoint.ftp.model.FtpCredentials;

@Service
public class FtpDownload {
	
	@Autowired
	FtpFactoryManager factory;

	
	public FTPFile[] listFiles(FtpCredentials ftpCredentials) {
		System.out.println("----------> listFiles - Inicio");
		FTPClient ftp = new FTPClient();
		
		factory.ftpConnect(ftp, ftpCredentials);
		
		FTPFile[] fileList = this.getFileList(ftp);
		
		factory.ftpDisconnect(ftp);
		
		System.out.println("----------> listFiles - FIM");
		
		return fileList;
	}

	private FTPFile[] getFileList(FTPClient ftp) {
		FTPFile[] fileList = null;
		try {
			fileList = ftp.listFiles();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return fileList;
	}

	public byte[] downloadFile(FtpCredentials ftpCredentials, String filename) {
		System.out.println("----------> downloadFile - Inicio");
		FTPClient ftp = new FTPClient();
		
		factory.ftpConnect(ftp, ftpCredentials);
		
		InputStream fileRetrieve = this.getFile(ftp, filename);
		
		factory.ftpDisconnect(ftp);
	
		byte[] bytesFile = this.getBytes(fileRetrieve);
		 
		System.out.println("----------> downloadFile - FIM");
		 
		return bytesFile;
	}

	private InputStream getFile(FTPClient ftp, String fileName) {
		System.out.println("getFile");
		InputStream fileStream = null;
		
		try {
			fileStream = ftp.retrieveFileStream(fileName);
			System.out.println("Arquivo recuperado");
		} catch (IOException e) {
			System.out.println("Erro ao realizar download");
			e.printStackTrace();
		}
		
		return fileStream;
	}
	
	private byte[] getBytes(InputStream fileRetrieve) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
		int nRead;
		byte[] data = new byte[16384];
	
		try {
			while ((nRead = fileRetrieve.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] byteArray = buffer.toByteArray();
		
		return byteArray;
		
	}

	public void moveFile(FtpCredentials ftpCredentials, String filename, String newPathName) {
		System.out.println("----------> moveFile - Inicio");
		FTPClient ftp = new FTPClient();
		
		factory.ftpConnect(ftp, ftpCredentials);
		
		this.setNewPath(ftp, filename, newPathName);
		
		factory.ftpDisconnect(ftp);
		
		System.out.println("----------> moveFile - Fim");
	}

	private void setNewPath(FTPClient ftp, String filename, String newPathName) {
		System.out.println("setNewPath");
		try {
			ftp.rename(filename, newPathName);
			System.out.println("Arquivo movido para " + newPathName);
		} catch (IOException e) {
			System.out.println("Erro ao mover arquivo - ");
			e.printStackTrace();
		}
		
	}

	

}
