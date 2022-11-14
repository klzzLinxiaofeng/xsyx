/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.vo;

/**
 * 学习人数数据统计饼图vo
 * @author Administrator
 */
public class LearnerStatisticsVo {

    private int finished;
    private int notFinished;
    private int total;
    private int finishedPercent;
    private int notFinishedPercent;

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getNotFinished() {
        this.notFinished = total - this.finished;
        return notFinished;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFinishedPercent() {
        finishedPercent = (int)Math.round(((double)finished/total)* 100);
        return finishedPercent;
    }

    public int getNotFinishedPercent() {
        notFinished = total - finished;
        notFinishedPercent = (int)Math.round(((double)notFinished/total)* 100);
        return notFinishedPercent;
    }
}
