package org.daijie.api.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.daijie.core.util.http.QrcodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QrcodeController {

	@RequestMapping(value = "prcode")
	public Object prcode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Color color = new Color(131, 80, 233);
		BufferedImage createQRCode = QrcodeUtil.create(request, "https://github.com/daijiejay", null, QrcodeUtil.SIZE_360, color);
		ImageIO.write(createQRCode, "png", response.getOutputStream());
		return null;
	}
}
