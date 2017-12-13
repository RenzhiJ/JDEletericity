package com.example.bwie.mydemo.bean;

import com.google.gson.annotations.SerializedName;


public class qqBean {

//     nameValuePairs : {"ret":0,"msg":"","is_lost":0,"nickname":"Mr.","gender":"男","province":"河南","city":"鹤壁","figureurl":"http://qzapp.qlogo.cn/qzapp/1105602574/546B69AEFAFBFC10CD9BB57876BD238E/30","figureurl_1":"http://qzapp.qlogo.cn/qzapp/1105602574/546B69AEFAFBFC10CD9BB57876BD238E/50","figureurl_2":"http://qzapp.qlogo.cn/qzapp/1105602574/546B69AEFAFBFC10CD9BB57876BD238E/100","figureurl_qq_1":"http://q.qlogo.cn/qqapp/1105602574/546B69AEFAFBFC10CD9BB57876BD238E/40","figureurl_qq_2":"http://q.qlogo.cn/qqapp/1105602574/546B69AEFAFBFC10CD9BB57876BD238E/100","is_yellow_vip":"0","vip":"0","yellow_vip_level":"0","level":"0","is_yellow_year_vip":"0"}

    private NameValuePairsBean nameValuePairs;

    public NameValuePairsBean getNameValuePairs() {
        return nameValuePairs;
    }

    public void setNameValuePairs(NameValuePairsBean nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }

    public static class NameValuePairsBean {

        private int ret;
        private String msg;
        private int is_lost;
        private String nickname;
        private String gender;
        private String province;
        private String city;
        private String figureurl;
        @SerializedName("figureurl_1")
        private String figureurl1;
        @SerializedName("figureurl_2")
        private String figureurl2;
        @SerializedName("figureurl_qq_1")
        private String figureurlQQ1;
        @SerializedName("figureurl_qq_2")
        private String figureurlQQ2;
        private String is_yellow_vip;
        private String vip;
        private String yellow_vip_level;
        private String level;
        private String is_yellow_year_vip;

        public int getRet() {
            return ret;
        }

        public void setRet(int ret) {
            this.ret = ret;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getIs_lost() {
            return is_lost;
        }

        public void setIs_lost(int is_lost) {
            this.is_lost = is_lost;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getFigureurl() {
            return figureurl;
        }

        public void setFigureurl(String figureurl) {
            this.figureurl = figureurl;
        }

        public String getFigureurl1() {
            return figureurl1;
        }

        public void setFigureurl1(String figureurl1) {
            this.figureurl1 = figureurl1;
        }

        public String getFigureurl2() {
            return figureurl2;
        }

        public void setFigureurl2(String figureurl2) {
            this.figureurl2 = figureurl2;
        }

        public String getFigureurlQQ1() {
            return figureurlQQ1;
        }

        public void setFigureurlQQ1(String figureurlQQ1) {
            this.figureurlQQ1 = figureurlQQ1;
        }

        public String getFigureurlQQ2() {
            return figureurlQQ2;
        }

        public void setFigureurlQQ2(String figureurlQQ2) {
            this.figureurlQQ2 = figureurlQQ2;
        }

        public String getIs_yellow_vip() {
            return is_yellow_vip;
        }

        public void setIs_yellow_vip(String is_yellow_vip) {
            this.is_yellow_vip = is_yellow_vip;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getYellow_vip_level() {
            return yellow_vip_level;
        }

        public void setYellow_vip_level(String yellow_vip_level) {
            this.yellow_vip_level = yellow_vip_level;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getIs_yellow_year_vip() {
            return is_yellow_year_vip;
        }

        public void setIs_yellow_year_vip(String is_yellow_year_vip) {
            this.is_yellow_year_vip = is_yellow_year_vip;
        }
    }
}
