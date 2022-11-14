package platform.szxyzxx.web.pay.pojo;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import platform.szxyzxx.excelhelper.anno.DefaultColumnStyle;
import platform.szxyzxx.excelhelper.anno.DefaultTitleColumnStyle;
import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;

import java.math.BigDecimal;
import java.util.Date;
@DefaultTitleColumnStyle(boldweight= Font.BOLDWEIGHT_BOLD,alignment= CellStyle.ALIGN_CENTER)
@DefaultColumnStyle(alignment=CellStyle.ALIGN_CENTER)
public class PayOrder {
    @ExcelColumnExport(value = "姓名",width = 3000)
    private String empName;
    @ExcelColumnExport(value = "食堂卡号",width = 5000)
    private String empCard;
    @ExcelColumnExport(value = "支付状态",replace = {"1=成功","2=失败","0=未支付"},width = 4000 )
    private Integer payStatus;
    @ExcelColumnExport(value = "支付金额",width = 4000)
    private BigDecimal payAmount;
    @ExcelColumnExport(value = "是否发送到食堂",replace = {"1=是","0=否"},width = 5000)
    private Integer sendStatus;
    @ExcelColumnExport(value = "支付方式",replace = {"1=微信支付"},width = 4000)
    private Integer payMethod;
    @ExcelColumnExport(value = "订单号",width = 8000)
    private String orderNo;

    @ExcelColumnExport(value = "下单时间",dateFormat = "yyyy-MM-dd HH:mm:ss",width = 5000)
    private Date createTime;

    @ExcelColumnExport(value = "支付时间",dateFormat = "yyyy-MM-dd HH:mm:ss",width = 5000)
    private Date payTime;

    private String startDate;
    private String endDate;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
