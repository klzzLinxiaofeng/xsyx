package platform.education.generalTeachingAffair.model;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/3/16 9:34
 *  @Description: 解析绑卡返回json
 */
public class HikvisonCardBindPojo {


    /**
     * code : 0
     * msg : success
     * data : [{"cardId":"b3285a64-8575-11eb-b162-63733a53d29c","personId":"y6a9fy89010yc92y9y19c09","orgIndexCode":"test1320000","cardNo":"12005600001"},{"cardId":"b32864b4-8575-11eb-b163-fb6644d8fe8f","personId":"sadasdasdf1232334","orgIndexCode":"code1320000","cardNo":"17878600001"}]
     */

    private String code;
    private String msg;
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * cardId : b3285a64-8575-11eb-b162-63733a53d29c
         * personId : y6a9fy89010yc92y9y19c09
         * orgIndexCode : test1320000
         * cardNo : 12005600001
         */

        private String cardId;
        private String personId;
        private String orgIndexCode;
        private String cardNo;
        private String certificateNo;

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public String getOrgIndexCode() {
            return orgIndexCode;
        }

        public void setOrgIndexCode(String orgIndexCode) {
            this.orgIndexCode = orgIndexCode;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "cardId='" + cardId + '\'' +
                    ", personId='" + personId + '\'' +
                    ", orgIndexCode='" + orgIndexCode + '\'' +
                    ", cardNo='" + cardNo + '\'' +
                    ", certificateNo='" + certificateNo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HikvisonCardBindPojo{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
