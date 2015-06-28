package com.guhanjie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.guhanjie.mongo.entity.EmailEntity;
import com.guhanjie.mongo.repository.EmailRepository;


/**
 * 该服务使用MongoDB，演示CRUD<br/>
 * 工具类<b>MongoTemplate</b>的更多使用示例，请参见：<a href="http://www.mkyong.com/tutorials/java-mongodb-tutorials/">这里</a><br/>
 * @author Guhanjie
 * Created By 2015年6月28日17:02:27
 */
@Service
public class EmailService {

	@Autowired
	private MongoTemplate mongoUtil;
	
	@Autowired
	private EmailRepository emailRepository;
	
	/**
	 * 方式一：使用MongoTemplate工具类操作CRUD
	 */
	public void demo1() {
		EmailEntity email = new EmailEntity();
		email.setFrom("guhanjie");
		email.setCc(new String[]{"aaa", "bbb", "ccc"});
		email.setTo(new String[]{"ddd", "fff", "ggg"});
		email.setSubject("This is a test case.");
		
		//保存（插入或更新）
		mongoUtil.save(email);	 
		// now user object got the created id.
		System.out.println("1. email : " + email);
	 
		//查询
		Query query = new Query(Criteria.where("from").is("guhanjie"));
		EmailEntity savedEmail = mongoUtil.findOne(query, EmailEntity.class);
		System.out.println("2. find - savedEmail : " + savedEmail);
	 
		//更新
		mongoUtil.updateFirst(query, Update.update("content", "xoxoxo"),EmailEntity.class);
		EmailEntity updatedEmail = mongoUtil.findOne(query, EmailEntity.class);	 
		System.out.println("3. updatedEmail : " + updatedEmail);
	 
		//删除
		//mongoUtil.remove(query, EmailEntity.class);
	 
		//获取数据库中指定集合的大小
		List<EmailEntity> emails = mongoUtil.findAll(EmailEntity.class);
		System.out.println("4. Number of email = " + emails.size());
	}
	
	/**
	 * 方式二：使用Spring Data的Repository操作CRUD
	 */
	public void demo2() {
		List<EmailEntity> emails = emailRepository.findBySubject("This is a test case.");
		System.out.println("findBySubject: "+emails.size()+"\n"+emails);
		
		emails = emailRepository.findByFromAndTo("guhanjie", "ddd");
		System.out.println("findByFromAndTo: "+emails.size()+"\n"+emails);
	}
}
