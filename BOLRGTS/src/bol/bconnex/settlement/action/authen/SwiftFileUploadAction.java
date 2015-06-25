package bol.bconnex.settlement.action.authen;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bol.bconnex.settlement.business.service.FtpAccessService;
import bol.bconnex.settlement.business.util.UtilityService;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@Namespace("/authenticated/upload")
@ResultPath(value="/")
public class SwiftFileUploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private FtpAccessService ftpAccessService;
	public void setFtpAccessService(FtpAccessService ftpAccessService){
		this.ftpAccessService = ftpAccessService;
	}
	@Action(value="confirm",results={
			@Result(name="success",location="success.jsp"),
			@Result(name="error",location="error.jsp")
	})
	public String execute() throws Exception{
		String path = ServletActionContext.getServletContext().getRealPath("/swift");
		String dir = path+"/"+UtilityService.backStrDate();
		File directory = new File(dir);
		File[] files = directory.listFiles();
		for(int i=0;i<files.length;i++){
			File file = files[i];
			ftpAccessService.swiftUpload(file);
		}
		return SUCCESS;
	}
}
