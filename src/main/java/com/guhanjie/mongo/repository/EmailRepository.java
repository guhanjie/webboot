package com.guhanjie.mongo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.guhanjie.mongo.entity.EmailEntity;

/**
 * 基于MongoDB的Repository
 * Created by Guhanjie on 2015-06-28.<br/>
 * 采用Spring Data Repository风格的接口实现MongoDB的数据存取，
 * Repository的代理实例有两种方式根据方法导出数据库特定的语句： 一种是根据方法名直接导出。 另一种是开发人员手工定义。
 * 这里使用的就是根据方法签名直接导出，无需写具体的实现代码，Spring会对方法签名进行分析，由代理自动帮你生成实现。
 */
public interface EmailRepository extends CrudRepository<EmailEntity, String> {
	
	/**
	 * 按照邮件主题查询
	 * findBy...形式的方法签名（按字段查询）
	 * @param subject
	 * @return
	 */
	List<EmailEntity> findBySubject(String subject);
	
	/**
	 * 按照邮件发送者和接受者查询
	 * findBy...And...形式的方法签名（按逻辑关系查询）
	 * @param from
	 * @param to
	 * @return
	 */
	List<EmailEntity> findByFromAndTo(String from, String to);
	
	/**
	 * 限制查询结果，查出前10条结果
	 * @param from
	 * @return
	 */
	List<EmailEntity> findTop10ByFrom(String from);
}
