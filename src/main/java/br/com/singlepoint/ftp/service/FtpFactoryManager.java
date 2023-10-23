package br.com.singlepoint.ftp.service;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;

import br.com.singlepoint.ftp.model.FtpCredentials;

@Service
public class FtpFactoryManager {
	
	public void ftpConnect(FTPClient ftp, FtpCredentials ftpCredentials) {
		System.out.println("ftpConnect");
		
		
		try {
			ftp.connect(ftpCredentials.getHost(), 21);
			System.out.println("conectado no host - " + ftpCredentials.getHost());
			if(FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.login(ftpCredentials.getUsername(), ftpCredentials.getPassword());
				System.out.println("logado com usuario e senha -> " + ftpCredentials.getUsername() + " - " + ftpCredentials.getPassword());
				
				ftp.enterLocalPassiveMode();
				System.out.println("passiveMode");
				if(ftpCredentials.getPath() != null || ftpCredentials.getPath() != "") {
					ftp.changeWorkingDirectory(ftpCredentials.getPath());
					System.out.println("diretorio Selecionado - " + ftpCredentials.getPath());
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao Conectar");
			this.ftpDisconnect(ftp);
			e.printStackTrace();
		}
		System.out.println("Conexão pronta..");
	}
	
	public void ftpDisconnect(FTPClient ftp) {
		System.out.println("ftpDisconnect");
		try {
			ftp.disconnect();
			System.out.println("Desconectado");
		} catch(IOException ex) {
			System.out.println("Erro ao Desconectar");
			ex.printStackTrace();
		}
	}

}
