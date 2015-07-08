package com.guhanjie.util.redis;

import java.io.Serializable;


/**
 * 缓存对象
 * @author a
 *
 */
public class KeyValueEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String key;
	private String value;		// value
	private String param1;		// 扩展参数1
	private String param2;		// 扩展参数2
	
	private long createTime;	// 新建对象时间
	private long updateTime;	// 最后一次更新时间
	private int updateCount;	// 更新次数
	private int checkCount;		// 检查次数
	
	public KeyValueEntity(){
		createTime = System.currentTimeMillis();
	}
	public KeyValueEntity(String key){
		this.key = key;
		createTime = System.currentTimeMillis();
	}
	public void updateValue(String value){
		this.value = value;
		updateTime = System.currentTimeMillis();
		updateCount++;
	}
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public int getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}
	public int getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(int checkCount) {
		this.checkCount = checkCount;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
}
