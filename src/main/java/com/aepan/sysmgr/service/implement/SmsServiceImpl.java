/**
 * 
 */
package com.aepan.sysmgr.service.implement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aepan.sysmgr.dao.SmsDao;
import com.aepan.sysmgr.model.config.SmsConfig;
import com.aepan.sysmgr.model.sms.SmsSendLog;
import com.aepan.sysmgr.model.sms.Xml;
import com.aepan.sysmgr.service.ConfigService;
import com.aepan.sysmgr.service.SmsService;
import com.aepan.sysmgr.util.DateUtil;
import com.aepan.sysmgr.util.SmsUtil;

/**
 * @author lanker
 * 2015年9月25日下午3:24:59
 */
@Service
public class SmsServiceImpl implements SmsService {
	@Autowired
	private SmsDao smsDao;
	@Override
	public String sendSms4Check(String phoneNo,String content,ConfigService cs){
		//step1:验证是否可发送 发送间隔60秒
		SmsSendLog log = smsDao.getLastByPhoneNo(phoneNo,SmsSendLog.TYPE_短信验证码);
		if(log!=null){
			Date now = new Date();
			long diffSeconds = DateUtil.diff(now, log.sendTime); 
			if(diffSeconds<SmsSendLog.TIME_发送频率){
				long needSeconds = SmsSendLog.TIME_发送频率 - diffSeconds;
				return "{\"code\":4,\"seconds\":"+needSeconds+"\"errormsg\":\"send multi time in 60 seconds\"}";
			}
		}
		String sendContent = "尊敬的顾客，您本次操作的验证码是"+content+",请在页面填写验证码完成验证，如非本人操作请忽略该信息或联系客服：400-664-9000";
		Xml rsxml = SmsUtil.send(sendContent, phoneNo, cs,SmsConfig.TYPE_验证码);
		if(rsxml.result == 1){
			log = new SmsSendLog();
			log.type = SmsSendLog.TYPE_短信验证码;
			log.phoneNo = phoneNo;
			log.content = content;
			smsDao.add(log);
			return "{\"code\":0}";
		}else{
			return "{\"code\":3,\"errormsg\":\""+rsxml.toString()+"\"}";
		}
	}
	@Override
	public String checkSms(String phoneNo,String content){
		SmsSendLog log = smsDao.getLastByPhoneNo(phoneNo,SmsSendLog.TYPE_短信验证码);
		Date now = new Date();
		long diffSeconds = DateUtil.diff(now, log.sendTime); 
		if(log!=null&&log.content.equals(content)&&diffSeconds<SmsSendLog.TIME_发送频率){
			smsDao.delete(log.id);
			return "{\"code\":0}";
		}else{
			return "{\"code\":3,\"errormsg\":\"checkcode error or time out of 60 seconds\"}";
		}
	}
}
