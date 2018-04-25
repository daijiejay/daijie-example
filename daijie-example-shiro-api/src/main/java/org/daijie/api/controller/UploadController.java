package org.daijie.api.controller;

import java.io.IOException;

import org.daijie.fastdfs.FastdfsUtil;
import org.daijie.hadoop.dfs.HdfsUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

@Api(description="文件上传")
@RestController
public class UploadController {

	@RequestMapping(value = "fastdfs/upload", method = RequestMethod.POST)
	public String fastdfsUploadFile(MultipartFile file) throws IOException {
		return FastdfsUtil.upload(file);
    }
	
	@RequestMapping(value = "hdfs/upload", method = RequestMethod.POST)
	public String hdfsUploadFile(MultipartFile file) throws IOException {
		return HdfsUtil.upload(file);
	}
	
	@RequestMapping(value = "hdfs/download/{fileName}", method = RequestMethod.GET)
	public String hdfsDownloadFile(@PathVariable String fileName) throws IOException {
		HdfsUtil.download(fileName);
		return "";
	}
}
