package platform.education.generalTeachingAffair.model;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/3/15 16:26
 * @Description: 海康添加接口返回数据json
 */
public class HikvisionResponseVo {

    /**
     {
         "code": "0",
         "msg": "success",
         "data": {
             "successes": [{
                 "clientId": 0,
                 "personId": "y69009" 学生/教师id
             }],
             "failures": [{
                 "clientId": 1,
                 "code": "0x00052102",
                 "message": "PersonId Already In Db"
             }]
         }
     }
     */
    private String code;
    private String msg;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<Successes> successes;
        private List<Failures> failures;

        public List<Successes> getSuccesses() {
            return successes;
        }

        public void setSuccesses(List<Successes> successes) {
            this.successes = successes;
        }

        public List<Failures> getFailures() {
            return failures;
        }

        public void setFailures(List<Failures> failures) {
            this.failures = failures;
        }

        public static class Successes {

            private int clientId;
            private String personId;
            private String certificateNo;

            public String getCertificateNo() {
                return certificateNo;
            }

            public void setCertificateNo(String certificateNo) {
                this.certificateNo = certificateNo;
            }

            public int getClientId() {
                return clientId;
            }

            public void setClientId(int clientId) {
                this.clientId = clientId;
            }

            public String getPersonId() {
                return personId;
            }

            public void setPersonId(String personId) {
                this.personId = personId;
            }

            @Override
            public String toString() {
                return "Successes{" +
                        "clientId=" + clientId +
                        ", personId='" + personId + '\'' +
                        '}';
            }
        }

        public static class Failures {
            private int clientId;
            private String code;
            private String message;

            public int getClientId() {
                return clientId;
            }

            public void setClientId(int clientId) {
                this.clientId = clientId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            @Override
            public String toString() {
                return "Failures{" +
                        "clientId=" + clientId +
                        ", code='" + code + '\'' +
                        ", message='" + message + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                    "successes=" + successes +
                    ", failures=" + failures +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HikvisionResponseVo{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
