package bol.bconnex.settlement.action.authen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@Namespace("/authenticated/check")
@ResultPath(value="/")
public class SwiftResponseDownloadAction extends ActionSupport implements ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841333642719432876L;
	private String name;
	private String type;
	private String fileName;
	private InputStream fileInputStream;
	private ServletContext servletContext;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InputStream getFileInputStream(){
		return fileInputStream;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	public String getFileName(){
		return fileName;
	}
	@Action(value="download",results={
			@Result(name="error",location="error.jsp"),
			@Result(
				    name = "success", 
				    type = "stream", 
				    params = { 
				        "type", "application/octet-stream", 
				        "inputName", "fileInputStream", 
				        "bufferSize", "1024", 
				        "contentDisposition", "attachment;filename=\"${name}\"" 
				    }
				)
	})
	public String execute() throws Exception{
		String path = servletContext.getInitParameter("response-source");
		String file = path+"/"+name;
		setFileName(fileName);
		fileInputStream = new FileInputStream(new File(file));
		return SUCCESS;
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
