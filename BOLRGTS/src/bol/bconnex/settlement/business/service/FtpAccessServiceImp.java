package bol.bconnex.settlement.business.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.common.Logger;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpAccessServiceImp implements FtpAccessService {
	private static final Logger logger = Logger.getLogger(FtpAccessServiceImp.class);
	private FTPClient ftpClient;
	private String host;
	private String user;
	private String passwd;
	private String in;
	private String out;
	public FtpAccessServiceImp(FTPClient ftpClient, String host, String user, String passwd, String in, String out){
		this.ftpClient = ftpClient;
		this.host = host;
		this.user = user;
		this.passwd = passwd;
		this.in = in;
		this.out = out;
	}
	@Override
	public void swiftUpload(File file) {
		FileInputStream fis = null;
		try {
		    ftpClient.connect(host);
		    ftpClient.login(user, passwd);
		    ftpClient.pasv();
		    String source = file.getPath();
		    fis = new FileInputStream(source);
		    String dest = in+"/"+file.getName();
		    // Store file to server
		    ftpClient.storeFile(dest, fis);
		    ftpClient.logout();
		} catch (IOException e) {
			logger.debug("IOException throws for ftp access", e);
		} finally {
		    try {
		        if (fis != null) {
		            fis.close();
		        }
		        ftpClient.disconnect();
		    } catch (IOException e) {
		    	logger.debug("IOException throws for ftp access", e);
		    }
		}
	}
	@Override
	public List<String> swiftDownload(List<String> names, String path) {
		try{
			ftpClient.connect(host);
		    ftpClient.login(user, passwd);
		    ftpClient.pasv();
		    FTPFile[] files = ftpClient.listFiles(out);
		    List<String> list = new ArrayList<String>();
		    if (files != null && files.length > 0) {
		    	for (FTPFile file : files) {
                    if (!file.isFile()) {
                        continue;
                    }
                    String fileName = file.getName();
                    boolean check = true;
                    for(String name : names){
                    	if(fileName.equals(name)){
                    		check = false;
                    		break;
                    	}
                    }
                    if(check){
                		// local file
                		String localPath = path+"/"+fileName;
                    	OutputStream outStream = new FileOutputStream(localPath);
                    	String remotePath = out+"/"+fileName;
                    	ftpClient.retrieveFile(remotePath, outStream);
                    	outStream.close();
                    	list.add(localPath);
                    }
		    	}
		    }
		    ftpClient.logout();
		    ftpClient.disconnect();
		    return list;
		}
		catch(IOException ex){
			logger.debug("IOException throws for ftp access", ex);
			return null;
		}
	}
}
