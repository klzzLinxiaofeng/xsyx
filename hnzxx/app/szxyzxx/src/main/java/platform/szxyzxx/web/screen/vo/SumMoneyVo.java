package platform.szxyzxx.web.screen.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/2/2 17:43
 * @Description: 食堂消费
 */
public class SumMoneyVo {

    private int statusCode;
    private boolean result;
    private String error;
    private List<DataBean> data;


    public static class DataBean implements Comparable<DataBean> {

        private String key;
        private double value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        @Override
        public int compareTo(DataBean user) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date date1 = null;
            try {
                date1 = sdf.parse(this.key);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date2 = null;
            try {
                date2 = sdf.parse(user.key);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return (int) ((date1.getTime() - date2.getTime()) / 1000);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


}

