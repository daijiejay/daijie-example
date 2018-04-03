package org.daijie.api.controller;

import java.io.IOException;

import org.daijie.fastdfs.FastdfsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(MultipartFile file) throws IOException {
		return FastdfsUtil.upload(file);
    }
}
