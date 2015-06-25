package bol.bconnex.settlement.business.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RgtsJob extends QuartzJobBean {
	private RgtsWork rgtsWork;
	public void setRgtsWork(RgtsWork rgtsJob){
		this.rgtsWork = rgtsJob;
	}
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		rgtsWork.doRgtsWork();
	}

}
