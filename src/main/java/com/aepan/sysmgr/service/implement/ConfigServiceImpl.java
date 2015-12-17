/**
 * 
 */
package com.aepan.sysmgr.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;














import com.aepan.sysmgr.dao.ConfigDao;
import com.aepan.sysmgr.model.config.AlipayConfig;
import com.aepan.sysmgr.model.config.AzureConfig;
import com.aepan.sysmgr.model.config.Config;
import com.aepan.sysmgr.model.config.ConfigInfo;
import com.aepan.sysmgr.model.config.EmailConfig;
import com.aepan.sysmgr.model.config.FileConfig;
import com.aepan.sysmgr.model.config.LuceneConfig;
import com.aepan.sysmgr.model.config.PartnerConfig;
import com.aepan.sysmgr.model.config.SmsConfig;
import com.aepan.sysmgr.model.config.WechatpayConfig;
import com.aepan.sysmgr.service.ConfigService;
import com.aepan.sysmgr.util.JSONUtil;
import com.aepan.sysmgr.util.UuidUtil;

/**
 * @author lanker
 * 2015年8月10日上午11:22:52
 */
@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;
	@Override
	public void add(Config c) {
		configDao.add(c);
	}
	@Override
	public void update(Config c){
		configDao.update(c);
	}
	@Override
	public Config query(int id){
		Config config = configDao.query(id);
		if(config==null&&id==Config.ID_配置维护key){
			config = new Config();
			config.id  = id;
			config.name = "配置维护key";
			config.config = new UuidUtil().getUUID();
			add(config);
		}
		return config;
	}
	@Override
	public ConfigInfo getAllConfig(){
		List<Config> list= configDao.queryAll();
		ConfigInfo c = new ConfigInfo();
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				switch (list.get(i).id) {
				case Config.ID_文件上传配置:c.fileConfig = JSONUtil.fromJson(list.get(i).config, FileConfig.class);break;
				case Config.ID_微软配置:c.azureConfig = JSONUtil.fromJson(list.get(i).config,AzureConfig.class);break;
				case Config.ID_微信配置:c.wechatpayConfig = JSONUtil.fromJson(list.get(i).config, WechatpayConfig.class);break;
				case Config.ID_支付宝配置:c.alipayConfig = JSONUtil.fromJson(list.get(i).config, AlipayConfig.class);break;
				case Config.ID_搜索引擎:c.luceneConfig = JSONUtil.fromJson(list.get(i).config, LuceneConfig.class);break;
				case Config.ID_短信配置:c.smsConfig = JSONUtil.fromJson(list.get(i).config, SmsConfig.class);break;
				case Config.ID_邮件配置:c.emailConfig = JSONUtil.fromJson(list.get(i).config, EmailConfig.class);break;
				case Config.ID_合作方地址配置:c.partnerConfig = JSONUtil.fromJson(list.get(i).config, PartnerConfig.class);break;
				case Config.ID_配置维护key:c.configMgrKey = list.get(i).config;break;
				default:
					break;
				}
			}
		}
		return c;
	}
}
