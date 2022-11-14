package platform.szxyzxx.web.moral.vo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.constants.CellValueContants;

import java.io.Serializable;

/**
 * @author: yhc
 * @Date: 2021/4/19 16:30
 * @Description: 37列 如果分数为空就按0计算
 */
public class StarWeekStuVo implements Serializable {
    private static final long serialVersionUID = 1L;

    public StarWeekStuVo() {
    }

    /**
     * 车牌
     */
    @ExcelColumnImport(index = 0, ignoreValue = CellValueContants.BLANK)
    private String name;
    /**
     * 产生时间
     */
    @ExcelColumnImport(index = 1)
    private String number;

    /**
     * 安全自护--班长（向班任汇报）
     */
    @ExcelColumnImport(index = 2)
    private int a1;
    @ExcelColumnImport(index = 3)
    private int a2;
    @ExcelColumnImport(index = 4)
    private int a3;
    @ExcelColumnImport(index = 5)
    private int a4;
    @ExcelColumnImport(index = 6)
    private int a5;
    @ExcelColumnImport(index = 7)
    private int a6;

    /**
     * 遵规守纪--纪律委员（向班任汇报）
     */
    @ExcelColumnImport(index = 8)
    private int b1;
    @ExcelColumnImport(index = 9)
    private int b2;
    @ExcelColumnImport(index = 10)
    private int b3;
    @ExcelColumnImport(index = 11)
    private int b4;
    @ExcelColumnImport(index = 12)
    private int b5;
    @ExcelColumnImport(index = 13)
    private int b6;
    @ExcelColumnImport(index = 14)
    private int b7;


    /**
     * 团结合作
     */
    @ExcelColumnImport(index = 15)
    private int c1;
    @ExcelColumnImport(index = 16)
    private int c2;
    @ExcelColumnImport(index = 17)
    private int c3;
    @ExcelColumnImport(index = 18)
    private int c4;
    @ExcelColumnImport(index = 19)
    private int c5;


    /**
     * 勤学好问--学习委员（向班任汇报）
     */
    @ExcelColumnImport(index = 20)
    private int d1;
    @ExcelColumnImport(index = 21)
    private int d2;
    @ExcelColumnImport(index = 22)
    private int d3;
    @ExcelColumnImport(index = 23)
    private int d4;
    @ExcelColumnImport(index = 24)
    private int d5;
    @ExcelColumnImport(index = 25)
    private int d6;
    @ExcelColumnImport(index = 26)
    private int d7;
    @ExcelColumnImport(index = 27)
    private int d8;


    /**
     * 卫生保健--卫生委员（向班任汇报）
     */
    @ExcelColumnImport(index = 28)
    private int e1;
    @ExcelColumnImport(index = 29)
    private int e2;
    @ExcelColumnImport(index = 30)
    private int e3;
    @ExcelColumnImport(index = 31)
    private int e4;
    @ExcelColumnImport(index = 32)
    private int e5;


    /**
     * 活动礼仪--副班长 （向班任汇报）
     */
    @ExcelColumnImport(index = 33)
    private int f1;
    @ExcelColumnImport(index = 34)
    private int f2;
    @ExcelColumnImport(index = 35)
    private int f3;
    @ExcelColumnImport(index = 36)
    private int f4;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

    public int getA3() {
        return a3;
    }

    public void setA3(int a3) {
        this.a3 = a3;
    }

    public int getA4() {
        return a4;
    }

    public void setA4(int a4) {
        this.a4 = a4;
    }

    public int getA5() {
        return a5;
    }

    public void setA5(int a5) {
        this.a5 = a5;
    }

    public int getA6() {
        return a6;
    }

    public void setA6(int a6) {
        this.a6 = a6;
    }

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        this.b1 = b1;
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        this.b2 = b2;
    }

    public int getB3() {
        return b3;
    }

    public void setB3(int b3) {
        this.b3 = b3;
    }

    public int getB4() {
        return b4;
    }

    public void setB4(int b4) {
        this.b4 = b4;
    }

    public int getB5() {
        return b5;
    }

    public void setB5(int b5) {
        this.b5 = b5;
    }

    public int getB6() {
        return b6;
    }

    public void setB6(int b6) {
        this.b6 = b6;
    }

    public int getB7() {
        return b7;
    }

    public void setB7(int b7) {
        this.b7 = b7;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public int getC4() {
        return c4;
    }

    public void setC4(int c4) {
        this.c4 = c4;
    }

    public int getC5() {
        return c5;
    }

    public void setC5(int c5) {
        this.c5 = c5;
    }

    public int getD1() {
        return d1;
    }

    public void setD1(int d1) {
        this.d1 = d1;
    }

    public int getD2() {
        return d2;
    }

    public void setD2(int d2) {
        this.d2 = d2;
    }

    public int getD3() {
        return d3;
    }

    public void setD3(int d3) {
        this.d3 = d3;
    }

    public int getD4() {
        return d4;
    }

    public void setD4(int d4) {
        this.d4 = d4;
    }

    public int getD5() {
        return d5;
    }

    public void setD5(int d5) {
        this.d5 = d5;
    }

    public int getD6() {
        return d6;
    }

    public void setD6(int d6) {
        this.d6 = d6;
    }

    public int getD7() {
        return d7;
    }

    public void setD7(int d7) {
        this.d7 = d7;
    }

    public int getD8() {
        return d8;
    }

    public void setD8(int d8) {
        this.d8 = d8;
    }

    public int getE1() {
        return e1;
    }

    public void setE1(int e1) {
        this.e1 = e1;
    }

    public int getE2() {
        return e2;
    }

    public void setE2(int e2) {
        this.e2 = e2;
    }

    public int getE3() {
        return e3;
    }

    public void setE3(int e3) {
        this.e3 = e3;
    }

    public int getE4() {
        return e4;
    }

    public void setE4(int e4) {
        this.e4 = e4;
    }

    public int getE5() {
        return e5;
    }

    public void setE5(int e5) {
        this.e5 = e5;
    }

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        this.f2 = f2;
    }

    public int getF3() {
        return f3;
    }

    public void setF3(int f3) {
        this.f3 = f3;
    }

    public int getF4() {
        return f4;
    }

    public void setF4(int f4) {
        this.f4 = f4;
    }

    @Override
    public String toString() {
        return "StarWeekStuVo{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", a1=" + a1 +
                ", a2=" + a2 +
                ", a3=" + a3 +
                ", a4=" + a4 +
                ", a5=" + a5 +
                ", a6=" + a6 +
                ", b1=" + b1 +
                ", b2=" + b2 +
                ", b3=" + b3 +
                ", b4=" + b4 +
                ", b5=" + b5 +
                ", b6=" + b6 +
                ", b7=" + b7 +
                ", c1=" + c1 +
                ", c2=" + c2 +
                ", c3=" + c3 +
                ", c4=" + c4 +
                ", c5=" + c5 +
                ", d1=" + d1 +
                ", d2=" + d2 +
                ", d3=" + d3 +
                ", d4=" + d4 +
                ", d5=" + d5 +
                ", d6=" + d6 +
                ", d7=" + d7 +
                ", d8=" + d8 +
                ", e1=" + e1 +
                ", e2=" + e2 +
                ", e3=" + e3 +
                ", e4=" + e4 +
                ", e5=" + e5 +
                ", f1=" + f1 +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", f4=" + f4 +
                '}';
    }
}
