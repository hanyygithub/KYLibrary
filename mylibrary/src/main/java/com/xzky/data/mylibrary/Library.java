package com.xzky.data.mylibrary;

import android.graphics.Color;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vampire on 2018/3/28.
 */

public class Library {
    private DecimalFormat df2 = new DecimalFormat("#0.00");
    List<String> list_wsd = new ArrayList<>();
    /**
     * 温湿度大气压
     * @param comBean
     */
    private  List<String> getData_WSD(ComBean comBean) {
        list_wsd.clear();
        if (comBean.bRec[9] == 16) {
           String wdStr = df2.format(MyFunc.byte2float(comBean.bRec, 14));
            String sdStr = df2.format(MyFunc.byte2float(comBean.bRec, 18));
            String dqyStr = MyFunc.byte4Toint(comBean.bRec, 22) / 100f + "";
            list_wsd.add(wdStr);
            list_wsd.add(sdStr);
            list_wsd.add(dqyStr);
        }
        return list_wsd;
    }

    
}
