package org.daijie.api;

import org.daijie.core.result.ModelResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="第三方验证码")
@FeignClient(value="${feign.social-cloud}")
public interface SocialCaptchaCloud {
	
	@ApiOperation(notes = "获取腾讯验证码", value = "获取腾讯验证码")
	@RequestMapping(value = "tx/captcha", method = RequestMethod.GET)
	public ModelResult<String> getTXCaptcha();
	
	@ApiOperation(notes = "验证腾讯验证码", value = "验证腾讯验证码")
	@RequestMapping(value = "tx/captcha/verify", method = RequestMethod.POST)
	public ModelResult<Boolean> verifyTXCaptcha(@RequestParam String verifyCode);
}
