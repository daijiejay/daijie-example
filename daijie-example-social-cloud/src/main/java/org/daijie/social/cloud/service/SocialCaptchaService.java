package org.daijie.social.cloud.service;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.social.captcha.SocialCaptchaTool;
import org.daijie.social.feign.SocialCaptchaCloud;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方验证码测试
 * @author daijie_jay
 * @since 2017年11月28日
 */
@RestController
public class SocialCaptchaService implements SocialCaptchaCloud {

	/**
	 * 获取腾讯验证码
	 * @return
	 */
	@Override
	public ModelResult<String> getTXCaptcha(){
		return Result.build(SocialCaptchaTool.getTXCaptcha());
	}
	
	/**
	 * 验证腾讯验证码
	 * @param verifyCode
	 * @return
	 */
	@Override
	public ModelResult<Boolean> verifyTXCaptcha(@RequestParam String verifyCode){
		return Result.build(SocialCaptchaTool.verifyTXCaptcha(verifyCode));
	}
}
