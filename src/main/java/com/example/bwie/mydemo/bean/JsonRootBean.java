
package com.example.bwie.mydemo.bean;
import java.util.List;


public class JsonRootBean {

    private String msg;
    private String code;
    private List<Data> data;
    private Tuijian tuijian;
    private MiaoshaBean miaosha;
    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setTuijian(Tuijian tuijian) {
         this.tuijian = tuijian;
     }
     public Tuijian getTuijian() {
         return tuijian;
     }

    public void setMiaosha(MiaoshaBean miaosha) {
         this.miaosha = miaosha;
     }
     public MiaoshaBean getMiaosha() {
         return miaosha;
     }

}