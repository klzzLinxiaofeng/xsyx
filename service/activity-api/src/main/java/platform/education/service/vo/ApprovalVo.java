package platform.education.service.vo;

import java.io.Serializable;

public class ApprovalVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private Integer count;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
