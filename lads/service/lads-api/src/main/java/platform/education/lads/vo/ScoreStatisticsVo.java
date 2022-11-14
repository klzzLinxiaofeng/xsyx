/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.vo;

/**
 *
 * 成绩统计饼图 vo
 *
 * @author Administrator
 */
public class ScoreStatisticsVo {

    private int total;
    //  x>=90分
    private int graterThanOrEqual90;
    //  80<=x<90分
    private int between80To90;
    //  60<=x<80分
    private int between60To80;
    //  x<60分
    private int lessThanOrEqual60;
    private int graterThanOrEqual90Percent;
    private int between80To90Percent;
    private int between60To80Percent;
    private int lessThanOrEqual60Percent;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getGraterThanOrEqual90() {
        return graterThanOrEqual90;
    }

    public void setGraterThanOrEqual90(int graterThanOrEqual90) {
        this.graterThanOrEqual90 = graterThanOrEqual90;
    }

    public int getBetween80To90() {
        return between80To90;
    }

    public void setBetween80To90(int between80To90) {
        this.between80To90 = between80To90;
    }

    public int getBetween60To80() {
        return between60To80;
    }

    public void setBetween60To80(int between60To80) {
        this.between60To80 = between60To80;
    }

    public int getLessThanOrEqual60() {
        return lessThanOrEqual60;
    }

    public void setLessThanOrEqual60(int lessThanOrEqual60) {
        this.lessThanOrEqual60 = lessThanOrEqual60;
    }

    public int getGraterThanOrEqual90Percent() {
        graterThanOrEqual90Percent = setPercent(graterThanOrEqual90, total);
        return graterThanOrEqual90Percent;
    }

    public int getBetween80To90Percent() {
        between80To90Percent = setPercent(between80To90, total);
        return between80To90Percent;
    }

    public int getBetween60To80Percent() {
        between60To80Percent = setPercent(between60To80, total);
        return between60To80Percent;
    }

    public int getLessThanOrEqual60Percent() {
        lessThanOrEqual60Percent = setPercent(lessThanOrEqual60, total);
        return lessThanOrEqual60Percent;
    }

    private int setPercent(int value, int tvalue) {
        return (int) Math.round(((double) value / tvalue) * 100);
    }
}
