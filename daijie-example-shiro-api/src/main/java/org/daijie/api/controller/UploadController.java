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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="文件上传")
@RestController
public class UploadController {

	@ApiOperation(notes = "fastdfs文件上传", value = "hadoop dfs文件上传")
	@RequestMapping(value = "fastdfs/upload", method = RequestMethod.POST)
	public String fastdfsUploadFile(@ApiParam(value = "选择文件") MultipartFile file) throws IOException {
		return FastdfsUtil.upload(file);
    }

	@ApiOperation(notes = "hadoop dfs文件上传", value = "hadoop dfs文件上传")
	@RequestMapping(value = "hdfs/upload", method = RequestMethod.POST)
	public String hdfsUploadFile(@ApiParam(value = "选择文件") MultipartFile file) throws IOException {
		return HdfsUtil.upload(file);
	}

	@ApiOperation(notes = "hadoop dfs文件下载，直接浏览器访问地址http://localhost/hdfs/download/{fileName}", 
			value = "hadoop dfs文件下载，直接浏览器访问地址http://localhost/hdfs/download/{fileName}")
	@RequestMapping(value = "hdfs/download/{fileName}", method = RequestMethod.GET)
	public String hdfsDownloadFile(@ApiParam(value = "文件名") @PathVariable String fileName) throws IOException {
		HdfsUtil.download(fileName);
		return "";
	}
}
