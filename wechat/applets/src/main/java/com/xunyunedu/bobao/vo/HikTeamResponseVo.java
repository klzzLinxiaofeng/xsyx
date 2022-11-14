package com.xunyunedu.bobao.vo;

import java.io.Serializable;
import java.util.List;

public class HikTeamResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * code : 0
     * msg : success
     * data : {"successes":[{"clientId":1,"orgIndexCode":"qyc8c894ch1y94c19y4c2"}],"failures":[{"clientId":22,"code":"-1","msg":"error"}]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private Integer pageSize;
        private Integer total;
        private Integer totalPage;
        private Integer pageNo;
        private List<SuccessesBean> list;

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(Integer totalPage) {
            this.totalPage = totalPage;
        }

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public List<SuccessesBean> getList() {
            return list;
        }

        public void setList(List<SuccessesBean> list) {
            this.list = list;
        }

        public static class SuccessesBean {
            /**
             * "eventId": "207dd3b1-37a7-4d6c-8e4d-c8bfd343051b",
             * "eventName": "acs.acs.eventType.successCard",
             * "eventTime": "2019-11-16T15:44:33+08:00",
             * "personId": "216e2ba145824269a1cbb423cdc85cb1",
             *  "cardNo": "3891192334",
             *  "personName": "sdk人员1zzzcb",
             * "orgIndexCode": "root000000",
             * "orgName": "默认组织",
             * "doorName": "10.40.239.69new_test2_门_1",
             * "doorIndexCode": "f0b50050d3434f15b4e34f885d5dacfe",
             * "doorRegionIndexCode": "fd2df06b-1afb-4c9b-b058-5740c2c00076",
             * "picUri": "no-pcnvr",
             * "svrIndexCode": "/pic?=d62i7f6e*6a7i125-c838b9--a8c67dea96e65icb1*=sd*=5dpi*=1dpi*m2i1t=4ed35444bb4s=-39",
             * "eventType": 198914,
             * "inAndOutType": 1,
             * "readerDevIndexCode": "378e563bf3e84d5ba6ef5742bbaa8933",
             * "readerDevName": "读卡器_1",
             *"devIndexCode": "dcff422aad9c4d60a47b8b2fe2757b71",
             *"devName": "10.40.239.69new_test2",
             *"identityCardUri": "/pic?=d62i7f6e*6a7i125-c838b9--a8c67dea96e65icb1*=sd*=5dpi*=1dpi*m2i1t=4ed35444bb4s=-39z422d3",
             *"receiveTime": "2019-11-16T15:45:13.525+08:00",
             * "jobNo": "23333",
             * "studentId": "201900001",
             * "certNo": "320826199012110005"
             */
            private String personName;  //名称
            private String personId;    //标识
            private Integer inAndOutType; //出校 1：进 0：出 -1:未知

            public String getPersonName() {
                return personName;
            }

            public void setPersonName(String personName) {
                this.personName = personName;
            }

            public String getPersonId() {
                return personId;
            }

            public void setPersonId(String personId) {
                this.personId = personId;
            }

            public Integer getInAndOutType() {
                return inAndOutType;
            }

            public void setInAndOutType(Integer inAndOutType) {
                this.inAndOutType = inAndOutType;
            }
        }

    }
}
