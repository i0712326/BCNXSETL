package bol.bconnex.settlement.business.listener;

import java.io.IOException;

import org.apache.log4j.Logger;

public class RgtsWorkImp implements RgtsWork {
	private static final Logger logger = Logger.getLogger(RgtsWorkImp.class);
	private String command;
	public void setCommand(String command){
		this.command = command;
	}
	@Override
	public void doRgtsWork() {
		logger.debug(command);
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			logger.debug("Exception occured while try to execute batch.");
		}
	}

}
