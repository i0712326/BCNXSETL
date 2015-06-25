package bol.bconnex.settlment.file.generator;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import bol.bconnex.settlement.data.entity.Member;

public class SwiftCodeGeneratorImp implements SwiftCodeGenerator {
	private String filePath;
	private String source;
	private String destinate;
	@Override
	public void generateSwift(List<Member> members, File file) throws IOException {
		for (Member member : members){
			String fileName = filePath+member.getName();
			
		}
		
	}
	private void swiftTemplate(Member member, File file){
		String header 	= "{1:F01COEBLALAAATM}{2:I202LPDRLALAXRTGN}{3:{113:0010}{108:PARTICIPANT}}{4:";
		String body 	= ":20:";
		String body1 	= ":21:NONREF";
		String body2 	= ":32A:";
		String body3	= ":53A:/D/";
		String body4	= null;
		String body5	= ":58A:/";
		String body6	= null;
		String body7	= ":72:/CODTYPTR/001";
		String body8	= "/BNF/Bconnex Net Settlment";
		String body9	= "-}";
		String backDate = backDate();
		
	}
	
	private String backDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}
}
