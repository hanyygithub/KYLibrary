package com.xzky.data.mylibrary;

import android.graphics.Color;

import com.xzky.data.mylibrary.bean.BeanPressureOne;
import com.xzky.data.mylibrary.bean.BeanPressureTwo;
import com.xzky.data.mylibrary.bean.BeanTempOne;
import com.xzky.data.mylibrary.bean.BeanTempTwo;
import com.xzky.data.mylibrary.bean.BeanWSD;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vampire on 2018/3/28.
 */

public class Library {
    DecimalFormat df2 = new DecimalFormat("0.00");
    DecimalFormat df3 = new DecimalFormat("0.000");
    BeanWSD beanWSD = new BeanWSD();
    BeanPressureOne beanPressureOne = new BeanPressureOne();
    BeanPressureTwo beanPressureTwo = new BeanPressureTwo();
    BeanTempOne beanTempOne = new BeanTempOne();
    BeanTempTwo beanTempTwo = new BeanTempTwo();


    ArrayList<String> list_PressureOne = new ArrayList<>();
    ArrayList<String> list_PressureTwo = new ArrayList<>();

    /**
     * 温湿度大气压
     *
     * @param comBean
     */
    public BeanWSD getData_WSD(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 16) {
            beanWSD.wdStr = df2.format(MyFunc.byte2float(comBean.bRec, 14));
            beanWSD.sdStr = df2.format(MyFunc.byte2float(comBean.bRec, 18));
            beanWSD.dqyStr = MyFunc.byte4Toint(comBean.bRec, 22) / 100f + "";
            beanWSD.signal = comBean.bRec[28];
            beanWSD.elec = MyFunc.twoBytesToInt(comBean.bRec, 26);
        }
        return beanWSD;
    }

    /**
     * 压力：一级压力
     *
     * @param comBean
     */
    public BeanPressureOne getData_PressureOne(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        list_PressureOne.clear();
        if (type == 36) {
            if (comBean.bRec[10] == 1) {
                String p1 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[15], comBean.bRec[16]) / 10000);
                if ((comBean.bRec[14] | 0x00) == 1)
                    p1 = "-" + p1;

                String p2 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[18], comBean.bRec[19]) / 10000);
                if ((comBean.bRec[17] | 0x00) == 1)
                    p2 = "-" + p2;

                String p3 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[21], comBean.bRec[22]) / 10000);
                if ((comBean.bRec[20] | 0x00) == 1)
                    p3 = "-" + p3;

                String p4 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[24], comBean.bRec[25]) / 10000);
                if ((comBean.bRec[23] | 0x00) == 1)
                    p4 = "-" + p4;

                String p5 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[27], comBean.bRec[28]) / 10000);
                if ((comBean.bRec[26] | 0x00) == 1)
                    p5 = "-" + p5;

                list_PressureOne.add(p1);
                list_PressureOne.add(p2);
                list_PressureOne.add(p3);
                list_PressureOne.add(p4);
                list_PressureOne.add(p5);

                beanPressureOne.list = (ArrayList) list_PressureOne.clone();
                beanPressureOne.signal = comBean.bRec[31];
                beanPressureOne.elec = MyFunc.twoBytesToInt(comBean.bRec, 29);

            }
        }
        return beanPressureOne;
    }

    /**
     * 压力：二级压力
     *
     * @param comBean
     */
    public BeanPressureTwo getData_PressureTwo(ComBean comBean) {

        int type = comBean.bRec[9] & 0xff;
        list_PressureTwo.clear();
        if (type == 36) {
            if (comBean.bRec[10] == 2) {
                String p1 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[15], comBean.bRec[16]) / 10000);
                if ((comBean.bRec[14] | 0x00) == 1)
                    p1 = "-" + p1;

                String p2 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[18], comBean.bRec[19]) / 10000);
                if ((comBean.bRec[17] | 0x00) == 1)
                    p2 = "-" + p2;

                String p3 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[21], comBean.bRec[22]) / 10000);
                if ((comBean.bRec[20] | 0x00) == 1)
                    p3 = "-" + p3;

                String p4 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[24], comBean.bRec[25]) / 10000);
                if ((comBean.bRec[23] | 0x00) == 1)
                    p4 = "-" + p4;

                String p5 = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[27], comBean.bRec[28]) / 10000);
                if ((comBean.bRec[26] | 0x00) == 1)
                    p5 = "-" + p5;
                list_PressureTwo.add(p1);
                list_PressureTwo.add(p2);
                list_PressureTwo.add(p3);
                list_PressureTwo.add(p4);
                list_PressureTwo.add(p5);

                beanPressureTwo.list = (ArrayList) list_PressureTwo.clone();
                beanPressureTwo.signal = comBean.bRec[31];
                beanPressureTwo.elec = MyFunc.twoBytesToInt(comBean.bRec, 29);
            }
        }
        return beanPressureTwo;
    }

    /**
     * 温度：一级温度
     *
     * @param comBean
     */
    public BeanTempOne getData_TempOne(ComBean comBean) {

        int type = comBean.bRec[9] & 0xff;
        if (type == 17) {
            if (comBean.bRec[10] == 1) {
                beanTempOne.firstTemperature = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[14], comBean.bRec[15]) / 100);
                beanTempOne.signal = comBean.bRec[18];
                beanTempOne.elec = MyFunc.twoBytesToInt(comBean.bRec, 16);
            }
        }
        return beanTempOne;
    }

    /**
     * 温度：一级温度
     *
     * @param comBean
     */
    public BeanTempTwo getData_TempTwo(ComBean comBean) {

        int type = comBean.bRec[9] & 0xff;
        if (type == 17) {
            if (comBean.bRec[10] == 2) {
                beanTempTwo.twoStageTemperature = df3
                        .format((float) MyFunc.twobyteToint_(
                                comBean.bRec[14], comBean.bRec[15]) / 100);
                beanTempTwo.signal = comBean.bRec[18];
                beanTempTwo.elec = MyFunc.twoBytesToInt(comBean.bRec, 16);
            }
        }
        return beanTempTwo;
    }


}
