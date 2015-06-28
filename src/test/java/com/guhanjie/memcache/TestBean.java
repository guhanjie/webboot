package com.guhanjie.memcache;

import java.io.Serializable;

/**
 * 测试用的Java对象类，注意：向Memcached存对象需要序列化
 * @author Guhanjie
 *
 */
public class TestBean implements Serializable{
   private static final long serialVersionUID = 1L;

   private String name;
   
   private int age;

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public int getAge() {
       return age;
   }

   public void setAge(int age) {
       this.age = age;
   }
    
   public String toString() {
       return "{name:"+this.getName()+",age:"+this.getAge()+"}";
   }
}