package platform.education.generalTeachingAffair.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 充值金额和反馈趋势
 *
 * @author: yhc
 * @Date: 2020/12/6 11:51
 * @Description:
 */
public class AmountTrendPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private String date;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 反馈人数
     */
    private Integer total;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
