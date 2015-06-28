package com.guhanjie.memcache;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/context/application-context.xml"})
public class MemcacheTest {
    
    @Autowired
    MemCachedClient memCachedClient;  
    
    @Test
    public void test() {
        memCachedClient.set("name", "han");
        System.out.println(memCachedClient.get("name"));  
    }
    
    @Test
    public void testCRUD() {
        //开始设值
        memCachedClient.set("name", " string");
        memCachedClient.set("int", 5);
        memCachedClient.set("double", 5.5);               
        TestBean bean = new TestBean();
        bean.setAge(21);
        bean.setName("名字");       
        memCachedClient.set("bean", bean); 
        List<TestBean> data = new ArrayList<TestBean>();
        for(int i=0;i<3;i++) {
            TestBean xbean = new TestBean();
            xbean.setAge(i);
            xbean.setName("test_"+i);
            data.add(xbean) ;
        }
        memCachedClient.set("data", data);       
        try {
            Thread.sleep(50);       
            //开始取值
            String name =(String) memCachedClient.get("name");
            int i = (Integer) memCachedClient.get("int");
            double d = (Double) memCachedClient.get("double") ;
            TestBean b = (TestBean) memCachedClient.get("bean") ;
            data =  (List<TestBean>) memCachedClient.get("data") ;
           
            System.out.println("字符串："+name);
            System.out.println("数字型："+i);
            System.out.println("双精度："+d);
            System.out.println("bean  toString ："+b.toString());           
            System.out.println("data  toString ："+data.toString());
           
            //开始删除值
            System.out.println("开始删除 ：》》》》》》》》》");
            memCachedClient.delete("name");
            memCachedClient.delete("int");
            memCachedClient.delete("double");
            memCachedClient.delete("bean");
            
            String name_d =(String) memCachedClient.get("name");
            int i_d = (Integer) memCachedClient.get("int");
            double d_d = (Double) memCachedClient.get("double") ;
            TestBean b_d = (TestBean) memCachedClient.get("bean") ;
           
            System.out.println("字符串："+name_d);
            System.out.println("数字型："+i_d);
            System.out.println("双精度："+d_d);
            System.out.println("bean  toString ："+b_d.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
