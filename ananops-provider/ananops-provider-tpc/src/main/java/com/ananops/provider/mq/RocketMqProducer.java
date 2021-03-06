/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：RocketMqProducer.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.ananops.provider.mq;


import com.ananops.base.constant.GlobalConstant;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.mq.MqMessage;
import com.ananops.provider.exceptions.TpcBizException;
import com.ananops.provider.service.MqProducerBeanFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * The class Rocket mq producer.
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RocketMqProducer {

	private static final int PRODUCER_RETRY_TIMES = 3;

	public static SendResult sendSimpleMessage(String body, String topic, String tag, String key, String pid, Integer delayLevel) {
		if (delayLevel == null) {
			delayLevel = 0;
		}
		Message message = MqMessage.checkMessage(body, topic, tag, key);
		if (delayLevel < 0 || delayLevel > GlobalConstant.Number.EIGHTEEN_INT) {
			throw new TpcBizException(ErrorCodeEnum.TPC100500013, topic, key);
		}
		message.setDelayTimeLevel(delayLevel);
		return retrySendMessage(pid, message);
	}
	//核心方法：重试发送消息（重试次数3次）
	//pid:producerGroup -->发送邮件服务是PID_UAC
	//cid:consumerGroup -->监听邮件消息服务是CID_OPC
	private static SendResult retrySendMessage(String pid, Message msg) {
		int iniCount = 1;
		SendResult result;
		while (true) {
			try {
				result = MqProducerBeanFactory.getBean(pid).send(msg);
				break;
			} catch (Exception e) {
				log.error("发送消息失败:", e);
				if (iniCount++ >= PRODUCER_RETRY_TIMES) {
					throw new TpcBizException(ErrorCodeEnum.TPC100500014, msg.getTopic(), msg.getKeys());
				}
			}
		}
		log.info("<== 发送MQ SendResult={}", result);
		return result;
	}

}
