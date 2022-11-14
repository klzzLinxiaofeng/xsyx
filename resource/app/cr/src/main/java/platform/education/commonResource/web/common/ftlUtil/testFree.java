package platform.education.commonResource.web.common.ftlUtil;

import java.util.HashMap;

import platform.education.commonResource.web.common.contants.FtlContants;

public class testFree {

	public static void main(String[] args) {
		GeneratorHtml cf = new GeneratorHtml();
		String templateFilePath = FtlContants.FTL_TEMPLATEPATH;
		String htmlFilePath = FtlContants.FTL_HTMLPATH;
		String htmlFileName = "123.html";
		HashMap<String, Object> data = new HashMap<String, Object>();
    	data.put("message", 12212);
		cf.geneHtmlFile(FtlContants.FTL_TEMPLATERESOURCENAME, templateFilePath, data, htmlFilePath, htmlFileName);

	}

}
