package bol.bconnex.settlment.file.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import bol.bconnex.settlement.data.entity.Member;

public interface SwiftCodeGenerator {
	public void generateSwift(List<Member> members, File file)throws IOException;
}
