package bol.bconnex.settlement.business.service;

import java.io.File;
import java.util.List;

public interface FtpAccessService {
	public void swiftUpload(File file);
	public List<String> swiftDownload(List<String> names, String path);
}
