package br.com.singlepoint.ftp.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.singlepoint.ftp.model.FtpCredentials;

@Service
public class FtpUpload {
	
	@Autowired
	FtpFactoryManager factory;
	
	public void uploadFile(FtpCredentials ftpCredentials, byte[] fileBytes, String filename) {
		System.out.println("----------> uploadFile - Inicio");
		FTPClient ftp = new FTPClient();
		
		factory.ftpConnect(ftp, ftpCredentials);
		
		this.transferFile(ftp, fileBytes, filename);
		
		factory.ftpDisconnect(ftp);
		
		System.out.println("----------> uploadFile - Fim");
	}
	
	
	private void transferFile(FTPClient ftp, byte[] fileBytes, String filename) {
		System.out.println("Preparando arquivo para transferencia");
		try {
			InputStream inputStream = new ByteArrayInputStream(fileBytes);

			System.out.println("Transferindo arquivo..");
			Boolean isFtpStored = ftp.storeFile(filename, inputStream);
			
			if(isFtpStored) {
				System.out.println("Arquivo " + filename + " transferido com sucesso - ");
			} else {
				System.out.println("Erro ao transferir Arquivo");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
