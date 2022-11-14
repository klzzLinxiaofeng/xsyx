package com.pantq.test;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:paper_app.xml" })
public class UploadImageTest {
	//上传
	@Resource
    private FileService fileService;
	
    @Test
    public void upload(){
    	
    	File file = new File("D:\\2017\\罗定邦\\试题\\xep\\images\\20170523143257516750.png");
    	FileResult fileResult = fileService.upload(file, "", "ftp");
    	
    	System.out.println(fileResult.getEntityFile().getRelativePath());
    	System.out.println(fileResult.getEntityFile().getExtension());
    	System.out.println(fileResult.getEntityFile().getDiskFileName());
    	System.out.println(fileResult.getEntityFile().getRelativePath());
    	//fileResult.getEntityFile().get
    	//FileServiceHolder.getInstance().getFileService().get
    	System.out.println(fileResult.getHttpUrl());
    }

}
