package platform.szxyzxx.schoolbus.pojo;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/9 14:44
 * @Description: 门禁打卡返回信息
 */
public class HikDoorResponseData {


    /**
     * code : 0
     * data : {"list":[{"cardNo":"3262484621","doorIndexCode":"21f871baa449431eb4a8e0999900bb46","doorName":"doorxsaveModifyAccessDevicexx","doorRegionIndexCode":"c654234f-61d4-4dcd-9d21-e7a45e0f1334","eventId":"68a557e2-1e9d-4628-b753-863144824312","eventName":"acs.acs.eventType.wrongNoSuchCard","eventTime":"2018-08-14T19:49:10+08:00","eventType":197634,"identityCardUri":"/pic?=d62i7f6e*6a7i125-c838b9--a8c67dea96e65icb1*=sd*=5dpi*=1dpi*m2i1t=4ed35444bb4s=-39z422d3","inAndOutType":1,"orgIndexCode":"4db7c89d-0ce6-4826-9146-6b71f037d81e","orgName":"部门-1","personId":"b7b097a6-7136-4098-84f1-81ba3b4fb1d9","personName":"xxx","picUri":"/pic?=d62i7f6e*6a7i125-c838b9--a8c67dea96e65icb1*=sd*=5dpi*=1dpi*m2i1t=4ed35444bb4s=-39z422d3","svrIndexCode":"a517ab45-09bf-423c-83fe-fea3b6332292"}],"pageNo":1,"pageSize":10,"total":100,"totalPage":10}
     * msg : success
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private int pageNo;
        private int pageSize;
        private int total;
        private int totalPage;
        private List<GatePickInfo> list;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<GatePickInfo> getList() {
            return list;
        }

        public void setList(List<GatePickInfo> list) {
            this.list = list;
        }
    }
}
