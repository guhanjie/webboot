package com.guhanjie.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/context/application-context.xml"})
public class EmailServiceTest {
	@Autowired
	private EmailService emailService;
	
	@Test
	public void testDemo1() {
		emailService.demo1();
	}
	
	@Test
	public void testDemo2() {
		emailService.demo2();
	}
}
