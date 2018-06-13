package org.daijie.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="第三方登录")
@FeignClient(value="${feign.social-cloud}")
public interface SocialLoginCloud {
	
	@ApiOperation(notes = "访问微信二维码", value = "访问微信二维码")
	@RequestMapping(value = "weixin/qrcode", method = RequestMethod.GET)
	public String loadQrcode(String state, HttpServletResponse response);
	
	@ApiOperation(notes = "微信扫码回调登录业务处理", value = "微信扫码回调登录业务处理")
	@RequestMapping(value = "weixin/callback", method = RequestMethod.GET)
	public String wxCallback(@RequestParam String code, String state);
}
