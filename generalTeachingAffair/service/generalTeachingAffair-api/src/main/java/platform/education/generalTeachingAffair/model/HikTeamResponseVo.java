package platform.education.generalTeachingAffair.model;

import java.io.Serializable;
import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/4/14 16:56
 *  @Description: 海康组织添加
 */
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
        private List<SuccessesBean> successes;

        public List<SuccessesBean> getSuccesses() {
            return successes;
        }

        public void setSuccesses(List<SuccessesBean> successes) {
            this.successes = successes;
        }


        public static class SuccessesBean {
            /**
             * clientId : 1
             * orgIndexCode : qyc8c894ch1y94c19y4c2
             */

            private Integer clientId;
            private String orgIndexCode;

            public Integer getClientId() {
                return clientId;
            }

            public void setClientId(Integer clientId) {
                this.clientId = clientId;
            }

            public String getOrgIndexCode() {
                return orgIndexCode;
            }

            public void setOrgIndexCode(String orgIndexCode) {
                this.orgIndexCode = orgIndexCode;
            }
        }

    }
}
