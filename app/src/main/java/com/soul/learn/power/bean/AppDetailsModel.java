package com.soul.learn.power.bean;

import java.util.List;


public class AppDetailsModel extends AppBaseInfo {
	public double score;
	public int downloadCount;
	public String downloadCountString;
	public String downloadUrl;
	public int size;
	public int versionCode;
	public String versionName;
	public String updateTime;
	private String intr;
	private String desc;
	public List<String> screenshots;
	public String[] operType;
	public String developer;
	
	public String costType;
	public String sku;
	public boolean isBuy;
	public String displayPrice;
	public String apkUrl;
	public boolean isInstalled;
	public int downloadStaus; //下载状态
//	public int percent; //下载进度
	public long totalSize;
	public long currentSize;
	public String categories;
	public boolean bRangeUpdate;
	
	public String getIntr() {
		if(intr!=null&&!intr.equals("null")&&!intr.equals("NULL")){
			return intr;
		}else{
			return "";
		}
	}
	public void setIntr(String intr) {
		this.intr = intr;
	}
	public String getDesc() {
		if(desc!=null&&!desc.equals("null")&&!desc.equals("NULL")){
			return desc;
		}else{
			return "";
		}
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
