package bol.bconnex.settlement.business.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SwiftResponseJob extends QuartzJobBean {
	@Autowired
	private SwiftResponseWork swiftResponseWork;
	public void setSwiftResponseWork(SwiftResponseWork swiftResponseWork){
		this.swiftResponseWork = swiftResponseWork;
	}
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		swiftResponseWork.swiftResCheck();
	}
	

}
