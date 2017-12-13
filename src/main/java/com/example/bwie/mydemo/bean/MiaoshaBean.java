
package com.example.bwie.mydemo.bean;
import java.util.List;


public class MiaoshaBean {

    private List<DataList> list;
    private String name;
    private int time;
    public void setList(List<DataList> list) {
         this.list = list;
     }
     public List<DataList> getList() {
         return list;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setTime(int time) {
         this.time = time;
     }
     public int getTime() {
         return time;
     }

}