package com.example.bwie.mydemo.bean;


public class DataBean {
    private Long cid;
    private String createtime;
    private String icon;
    private int ishome;
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIshome() {
        return this.ishome;
    }
    public void setIshome(int ishome) {
        this.ishome = ishome;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public Long getCid() {
        return this.cid;
    }
    public void setCid(Long cid) {
        this.cid = cid;
    }
    public DataBean(Long cid, String createtime, String icon, int ishome,
                    String name) {
        this.cid = cid;
        this.createtime = createtime;
        this.icon = icon;
        this.ishome = ishome;
        this.name = name;
    }
    public DataBean() {
    }
   

}
