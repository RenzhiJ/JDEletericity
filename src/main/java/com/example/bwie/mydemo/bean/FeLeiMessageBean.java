package com.example.bwie.mydemo.bean;

import java.util.List;



public class FeLeiMessageBean {

    /**
     * msg : 获取子分类成功
     * code : 0
     * data : [{"cid":"2","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"手机","pcid":6,"pscid":39},{"icon":"http://120.27.23.105/images/icon.png","name":"笔记本","pcid":6,"pscid":40},{"icon":"http://120.27.23.105/images/icon.png","name":"平板电脑","pcid":6,"pscid":41},{"icon":"http://120.27.23.105/images/icon.png","name":"游戏机","pcid":6,"pscid":42},{"icon":"http://120.27.23.105/images/icon.png","name":"摄影摄像","pcid":6,"pscid":43}],"name":"数码家电","pcid":"6"},{"cid":"2","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"男表","pcid":7,"pscid":44},{"icon":"http://120.27.23.105/images/icon.png","name":"女表","pcid":7,"pscid":45},{"icon":"http://120.27.23.105/images/icon.png","name":"儿童手表","pcid":7,"pscid":46},{"icon":"http://120.27.23.105/images/icon.png","name":"运动手表","pcid":7,"pscid":47},{"icon":"http://120.27.23.105/images/icon.png","name":"电子表","pcid":7,"pscid":48},{"icon":"http://120.27.23.105/images/icon.png","name":"情侣表","pcid":7,"pscid":49},{"icon":"http://120.27.23.105/images/icon.png","name":"石英表","pcid":7,"pscid":50},{"icon":"http://120.27.23.105/images/icon.png","name":"机械表","pcid":7,"pscid":51}],"name":"钟表","pcid":"7"},{"cid":"2","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"奢品箱包","pcid":8,"pscid":52},{"icon":"http://120.27.23.105/images/icon.png","name":"珠宝饰品","pcid":8,"pscid":53},{"icon":"http://120.27.23.105/images/icon.png","name":"运动户外","pcid":8,"pscid":54}],"name":"服饰奢品","pcid":"8"},{"cid":"2","list":[{"icon":"http://120.27.23.105/images/icon.png","name":"面部护肤","pcid":9,"pscid":55},{"icon":"http://120.27.23.105/images/icon.png","name":"洗发护发","pcid":9,"pscid":56},{"icon":"http://120.27.23.105/images/icon.png","name":"身体护理","pcid":9,"pscid":57},{"icon":"http://120.27.23.105/images/icon.png","name":"口腔护理","pcid":9,"pscid":58},{"icon":"http://120.27.23.105/images/icon.png","name":"女性护理","pcid":9,"pscid":59},{"icon":"http://120.27.23.105/images/icon.png","name":"清洁用品","pcid":9,"pscid":60}],"name":"美妆个护","pcid":"9"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 2
         * list : [{"icon":"http://120.27.23.105/images/icon.png","name":"手机","pcid":6,"pscid":39},{"icon":"http://120.27.23.105/images/icon.png","name":"笔记本","pcid":6,"pscid":40},{"icon":"http://120.27.23.105/images/icon.png","name":"平板电脑","pcid":6,"pscid":41},{"icon":"http://120.27.23.105/images/icon.png","name":"游戏机","pcid":6,"pscid":42},{"icon":"http://120.27.23.105/images/icon.png","name":"摄影摄像","pcid":6,"pscid":43}]
         * name : 数码家电
         * pcid : 6
         */

        private String cid;
        private String name;
        private String pcid;
        private List<ListBean> list;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPcid() {
            return pcid;
        }

        public void setPcid(String pcid) {
            this.pcid = pcid;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * icon : http://120.27.23.105/images/icon.png
             * name : 手机
             * pcid : 6
             * pscid : 39
             */

            private String icon;
            private String name;
            private int pcid;
            private int pscid;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPcid() {
                return pcid;
            }

            public void setPcid(int pcid) {
                this.pcid = pcid;
            }

            public int getPscid() {
                return pscid;
            }

            public void setPscid(int pscid) {
                this.pscid = pscid;
            }
        }
    }
}
