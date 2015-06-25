package bol.bconnex.settlement.action.authen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.stereotype.Controller;

import bol.bconnex.settlement.business.util.UtilityService;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@Namespace("/authenticated/settlement")
@ResultPath(value="/")
public class SwiftFileDownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1702189986780442848L;
	private String name;
	private String type;
	private String fileName;
	private InputStream fileInputStream;
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
				        "contentDisposition", "attachment;filename=\"${fileName}\"" 
				    }
				)
	})
	public String execute() throws Exception{
		String path = ServletActionContext.getServletContext().getRealPath("/swift");
		String backDate = UtilityService.backStrDate();
		String fileName = name+backDate+".swift";
		String file = path+"/"+backDate+"/"+name+backDate+".swift";
		setFileName(fileName);
		fileInputStream = new FileInputStream(new File(file));
		return SUCCESS;
	}

}
