package com.xzky.data.mylibrary;

import com.xzky.data.mylibrary.bean.BeanBrakingForce;
import com.xzky.data.mylibrary.bean.BeanCurrent;
import com.xzky.data.mylibrary.bean.BeanDiffPressure;
import com.xzky.data.mylibrary.bean.BeanDip;
import com.xzky.data.mylibrary.bean.BeanDisplacement;
import com.xzky.data.mylibrary.bean.BeanFlameproofShell;
import com.xzky.data.mylibrary.bean.BeanFlexibleCurrent;
import com.xzky.data.mylibrary.bean.BeanFlow;
import com.xzky.data.mylibrary.bean.BeanLaser;
import com.xzky.data.mylibrary.bean.BeanOilPressureOne;
import com.xzky.data.mylibrary.bean.BeanOilPressureTwo;
import com.xzky.data.mylibrary.bean.BeanOverSpeed;
import com.xzky.data.mylibrary.bean.BeanPower;
import com.xzky.data.mylibrary.bean.BeanPressureOne;
import com.xzky.data.mylibrary.bean.BeanPressureTwo;
import com.xzky.data.mylibrary.bean.BeanSpeed;
import com.xzky.data.mylibrary.bean.BeanTempOne;
import com.xzky.data.mylibrary.bean.BeanTempTwo;
import com.xzky.data.mylibrary.bean.BeanTime;
import com.xzky.data.mylibrary.bean.BeanVane;
import com.xzky.data.mylibrary.bean.BeanWSD;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

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
    BeanPower beanPower = new BeanPower();
    BeanSpeed beanSpeed = new BeanSpeed();
    BeanDisplacement beanDisplacement = new BeanDisplacement();
    BeanCurrent beanCurrent = new BeanCurrent();
    BeanFlexibleCurrent beanFlexibleCurrent = new BeanFlexibleCurrent();
    BeanFlameproofShell beanFlameproofShell = new BeanFlameproofShell();
    BeanOilPressureOne beanOilPressureOne = new BeanOilPressureOne();
    BeanOilPressureTwo beanOilPressureTwo = new BeanOilPressureTwo();
    BeanTime beanTime = new BeanTime();
    BeanBrakingForce beanBrakingForce = new BeanBrakingForce();
    BeanOverSpeed beanOverSpeed = new BeanOverSpeed();
    BeanDip beanDip = new BeanDip();
    BeanLaser beanLaser = new BeanLaser();
    BeanVane beanVane = new BeanVane();
    BeanDiffPressure beanDiffPressure = new BeanDiffPressure();
    BeanFlow beanFlow = new BeanFlow();

    ArrayList<String> list_PressureOne = new ArrayList<>();
    ArrayList<String> list_PressureTwo = new ArrayList<>();
    ArrayList<Double> list_Speed = new ArrayList<>();
    ArrayList<Double> list_Displacement = new ArrayList<>();
    ArrayList<Double> list_Current = new ArrayList<>();
    ArrayList<Double> list_FlexibleCurrent = new ArrayList<>();
    ArrayList<Double> list_FlameproofShell = new ArrayList<>();
    ArrayList<Double> list_OilPressureOne = new ArrayList<>();
    ArrayList<Double> list_OilPressureTwo = new ArrayList<>();

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

        if (type == 36) {
            if (comBean.bRec[10] == 1) {
                list_PressureOne.clear();
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

        if (type == 36) {
            if (comBean.bRec[10] == 2) {
                list_PressureTwo.clear();

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
     * 温度：二级温度
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

    /**
     * 功率
     *
     * @param comBean
     * @param U0
     * @param I0      500A-500,20A-100
     * @param UBB     电压变比
     * @param IBB     电流变比
     * @param style   1单瓦特，2双瓦特,3三瓦特
     * @return
     */

    public BeanPower getData_Power(ComBean comBean, int U0, int I0, double UBB, double IBB, int style) {

        int type = comBean.bRec[9] & 0xff;
        if (type == 128 && comBean.bRec.length == 36) {
            float cos = MyFunc.twobyteToint_Sp(comBean.bRec[30],
                    comBean.bRec[31]) / 10000;

            double a_voltage = sqrt(3) * (MyFunc.twobyteToint_(comBean.bRec[14],
                    comBean.bRec[15]) / 10000 * U0 * UBB);
            beanPower.AB_plv = df2.format(a_voltage);
            double a_current = MyFunc.twobyteToint_(comBean.bRec[16],
                    comBean.bRec[17]) / 10000 * I0 * IBB;
            beanPower.A_phase_current = df2.format(a_current);

            if (style == 1) {
                if (a_voltage / (Math.sqrt(3) * UBB) > 150) {
                    beanPower.active_power = MyFunc.twoBytesToIntHave(comBean.bRec[26], comBean.bRec[27]) / 10000000f * 3 * 500 * 500 * UBB * IBB * 3;
                    beanPower.reactive_power = MyFunc.twoBytesToIntHave(comBean.bRec[28], comBean.bRec[29]) * 0.075f * UBB * IBB * 3;
                } else {
                    beanPower.active_power = Math.sqrt(3) * a_current * a_voltage * cos / 1000f;
                    beanPower.reactive_power = 1.732f * a_current * a_voltage * (float) Math.sqrt(1 - cos * cos) / 1000f;
                }
                // 平均电压
                beanPower.average_voltage = df2.format(a_voltage);
                // 平均电流
                beanPower.average_current = df2.format(a_current);
                beanPower.BC_plv = "0";
                beanPower.B_phase_current = "0";
                beanPower.CA_plv = "0";
                beanPower.C_phase_current = "0";
            } else if (style == 2 || style == 3) {
                // B相电压、B相电流
                double b_voltage = sqrt(3) * (MyFunc.twobyteToint_(comBean.bRec[18],
                        comBean.bRec[19]) / 10000 * U0 * UBB);
                beanPower.BC_plv = df2.format(b_voltage);
                double b_current = MyFunc.twobyteToint_(comBean.bRec[20],
                        comBean.bRec[21]) / 10000 * I0 * IBB;
                beanPower.B_phase_current = df2.format(b_current);

                // C相电压、C相电流
                double c_voltage = sqrt(3) * (MyFunc.twobyteToint_(comBean.bRec[22],
                        comBean.bRec[23]) / 10000 * U0 * UBB);
                beanPower.CA_plv = df2.format(c_voltage);
                double c_current = MyFunc.twobyteToint_(comBean.bRec[24],
                        comBean.bRec[25]) / 10000 * I0 * IBB;
                beanPower.C_phase_current = df2.format(c_current);
                // 平均电压
                beanPower.average_voltage = df2.format((a_voltage + b_voltage + c_voltage) / 3);
                // 平均电流
                beanPower.average_current = df2.format((a_current + b_current + c_current) / 3);

                // 直接取
                if (((a_voltage + b_voltage + c_voltage) / 3)
                        / (Math.sqrt(3) * UBB) > 150) {
                    // 有功功率
                    beanPower.active_power = MyFunc.twoBytesToIntHave(comBean.bRec[26],
                            comBean.bRec[27]) / 10000000 * 3 * U0 * I0 * UBB * IBB;

                    // 无功功率
                    beanPower.reactive_power = MyFunc.twoBytesToIntHave(comBean.bRec[28],
                            comBean.bRec[29]) / 10000000 * 3 * U0 * I0 * UBB * IBB;
                } else {
                    // 有功功率
                    beanPower.active_power = Math.sqrt(3)
                            * ((a_current + b_current + c_current) / 3)
                            * ((a_voltage + b_voltage + c_voltage) / 3)
                            * cos / 1000;
                    // 无功功率
                    beanPower.reactive_power = Math.sqrt(3)
                            * ((a_current + b_current + c_current) / 3)
                            * ((a_voltage + b_voltage + c_voltage) / 3)
                            * sqrt(1 - cos * cos) / 1000;
                }
            }
            beanPower.power_factor = df2
                    .format(cos);
            beanPower.signal = comBean.bRec[34];
            beanPower.elec = MyFunc.twoBytesToInt(comBean.bRec, 32);
        }
        return beanPower;
    }

    /**
     * 速度
     *
     * @param comBean
     */
    public BeanSpeed getData_Speed(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 96 && comBean.bRec.length == 38) {
            list_Speed.clear();

            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    14));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    16));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    18));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    20));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    22));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    24));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    26));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    28));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    30));
            list_Speed.add((double) MyFunc.twoBytesToInt_speed(comBean.bRec,
                    32));

            beanSpeed.list = (ArrayList) list_Speed.clone();
            beanSpeed.signal = comBean.bRec[36];
            beanSpeed.elec = MyFunc.twoBytesToInt(comBean.bRec, 34);
        }
        return beanSpeed;
    }

    /**
     * 位移
     *
     * @param comBean
     */
    public BeanDisplacement getData_Displacement(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 166 && comBean.bRec.length == 38) {
            list_Displacement.clear();

            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    14) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    16) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    18) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    20) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    22) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    24) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    26) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    28) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    30) / 100);
            list_Speed.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                    32) / 100);

            beanDisplacement.list = (ArrayList) list_Displacement.clone();
            beanDisplacement.signal = comBean.bRec[36];
            beanDisplacement.elec = MyFunc.twoBytesToInt(comBean.bRec, 34);
        }
        return beanDisplacement;
    }

    /**
     * 电流 75mv
     *
     * @param comBean
     * @param param_max_current
     * @return
     */
    public BeanCurrent getData_Current(ComBean comBean, float param_max_current) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 167 && comBean.bRec.length == 33) {
            list_Current.clear();

            list_Current.add(((double) MyFunc.threeBytesToInt(comBean.bRec,
                    14) / 4096000) * param_max_current * 2 - param_max_current);
            list_Current.add(((double) MyFunc.threeBytesToInt(comBean.bRec,
                    17) / 4096000) * param_max_current * 2 - param_max_current);
            list_Current.add(((double) MyFunc.threeBytesToInt(comBean.bRec,
                    20) / 4096000) * param_max_current * 2 - param_max_current);
            list_Current.add(((double) MyFunc.threeBytesToInt(comBean.bRec,
                    23) / 4096000) * param_max_current * 2 - param_max_current);
            list_Current.add(((double) MyFunc.threeBytesToInt(comBean.bRec,
                    26) / 4096000) * param_max_current * 2 - param_max_current);

            beanCurrent.list = (ArrayList) list_Current.clone();
            beanCurrent.signal = comBean.bRec[31];
            beanCurrent.elec = MyFunc.twoBytesToInt(comBean.bRec, 29);
        }
        return beanCurrent;
    }

    /**
     * 柔性电流钳
     *
     * @param comBean
     * @param param_current_transformation_ratio
     * @return
     */
    public BeanFlexibleCurrent getData_Flexible_Current(ComBean comBean, float param_current_transformation_ratio) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 170 && comBean.bRec.length == 28) {
            list_FlexibleCurrent.clear();
            list_FlexibleCurrent.add(((double) MyFunc.twoBytesToInt(comBean.bRec,
                    14) / 100) * param_current_transformation_ratio);
            list_FlexibleCurrent.add(((double) MyFunc.twoBytesToInt(comBean.bRec,
                    16) / 100) * param_current_transformation_ratio);
            list_FlexibleCurrent.add(((double) MyFunc.twoBytesToInt(comBean.bRec,
                    18) / 100) * param_current_transformation_ratio);
            list_FlexibleCurrent.add(((double) MyFunc.twoBytesToInt(comBean.bRec,
                    20) / 100) * param_current_transformation_ratio);
            list_FlexibleCurrent.add(((double) MyFunc.twoBytesToInt(comBean.bRec,
                    22) / 100) * param_current_transformation_ratio);

            beanFlexibleCurrent.list = (ArrayList) list_FlexibleCurrent.clone();
            beanFlexibleCurrent.signal = comBean.bRec[26];
            beanFlexibleCurrent.elec = MyFunc.twoBytesToInt(comBean.bRec, 24);
        }
        return beanFlexibleCurrent;
    }

    /**
     * 隔爆壳
     *
     * @param comBean
     * @return
     */
    public BeanFlameproofShell getData_FlameproofShell(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 172 && comBean.bRec.length == 42) {
            if (comBean.bRec[13] == 1 || comBean.bRec[13] == 2) {
                list_FlameproofShell.clear();
                list_FlameproofShell.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                        24));
                list_FlameproofShell.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                        26));
                list_FlameproofShell.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                        28));
                list_FlameproofShell.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                        30));
                list_FlameproofShell.add((double) MyFunc.twoBytesToInt(comBean.bRec,
                        32));

                beanFlameproofShell.list = (ArrayList) list_FlameproofShell.clone();
                beanFlameproofShell.signal = comBean.bRec[40];
                beanFlameproofShell.elec = MyFunc.twoBytesToInt(comBean.bRec, 38);
            } else if (comBean.bRec[13] == 2) {
                beanFlameproofShell.startTime = MyFunc.byte4Toint(comBean.bRec, 34) / 10000f;
            }
        }
        return beanFlameproofShell;
    }

    /**
     * 一级油压
     *
     * @param comBean
     */
    public BeanOilPressureOne getData_OilPressureOne(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 32 && comBean.bRec.length == 33) {
            if (comBean.bRec[10] == 1) {
                list_OilPressureOne.clear();

                list_OilPressureOne.add((double) MyFunc.twoBytesToInt(comBean.bRec, 15) / 1000f);
                list_OilPressureOne.add((double) MyFunc.twoBytesToInt(comBean.bRec, 18) / 1000f);
                list_OilPressureOne.add((double) MyFunc.twoBytesToInt(comBean.bRec, 21) / 1000f);
                list_OilPressureOne.add((double) MyFunc.twoBytesToInt(comBean.bRec, 24) / 1000f);
                list_OilPressureOne.add((double) MyFunc.twoBytesToInt(comBean.bRec, 27) / 1000f);

                beanOilPressureOne.list = (ArrayList) list_OilPressureOne.clone();
                beanOilPressureOne.signal = comBean.bRec[31];
                beanOilPressureOne.elec = MyFunc.twoBytesToInt(comBean.bRec, 29);
            }
        }
        return beanOilPressureOne;
    }

    /**
     * 二级油压
     *
     * @param comBean
     */
    public BeanOilPressureTwo getData_OilPressureTwo(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 32 && comBean.bRec.length == 33) {
            if (comBean.bRec[10] == 2) {
                list_OilPressureTwo.clear();

                list_OilPressureTwo.add((double) MyFunc.twoBytesToInt(comBean.bRec, 15) / 1000f);
                list_OilPressureTwo.add((double) MyFunc.twoBytesToInt(comBean.bRec, 18) / 1000f);
                list_OilPressureTwo.add((double) MyFunc.twoBytesToInt(comBean.bRec, 21) / 1000f);
                list_OilPressureTwo.add((double) MyFunc.twoBytesToInt(comBean.bRec, 24) / 1000f);
                list_OilPressureTwo.add((double) MyFunc.twoBytesToInt(comBean.bRec, 27) / 1000f);

                beanOilPressureTwo.list = (ArrayList) list_OilPressureTwo.clone();
                beanOilPressureTwo.signal = comBean.bRec[31];
                beanOilPressureTwo.elec = MyFunc.twoBytesToInt(comBean.bRec, 29);
            }
        }
        return beanOilPressureTwo;
    }

    /**
     * 时间
     *
     * @param comBean
     */
    public BeanTime getData_Time(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 81) {
            if (comBean.bRec.length == 18) {
                beanTime.signal = comBean.bRec[16];
                beanTime.elec = MyFunc.twoBytesToInt(comBean.bRec, 14);
            } else if (comBean.bRec.length == 22) {
                beanTime.time = MyFunc.twobyteToint_(comBean.bRec[14], comBean.bRec[15])
                        + MyFunc.twobyteToint_(comBean.bRec[16], comBean.bRec[17]) * 0.1f / 1000f;
            }
        }
        return beanTime;
    }

    /**
     * 拉力
     *
     * @param comBean
     */
    public BeanBrakingForce getData_BrakingForce(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 64) {
            float res0 = (float) MyFunc.twoBytesToInt_(comBean.bRec, 16) / 100f;
            float res1 = (float) MyFunc.twoBytesToInt_(comBean.bRec, 14) / 100f;
            float res = (res0 + res1) / 2f;
            beanBrakingForce.brakingForce = res;
            beanBrakingForce.signal = comBean.bRec[20];
            beanBrakingForce.elec = MyFunc.twoBytesToInt(comBean.bRec, 18);

        }
        return beanBrakingForce;
    }

    /**
     * 过速
     *
     * @param comBean
     */
    public BeanOverSpeed getData_OverSpeed(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 174 && comBean.bRec.length == 22) {

            beanOverSpeed.ARate = MyFunc.twoBytesToInt(comBean.bRec, 14);

        }
        return beanOverSpeed;
    }

    /**
     * 倾角
     *
     * @param comBean
     */
    public BeanDip getData_Dip(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 162 && comBean.bRec.length == 24) {
            beanDip.XStr = df2.format((float) MyFunc.twobyteToint_(comBean.bRec[14], comBean.bRec[15]) / 100);
            beanDip.YStr = df2.format((float) MyFunc.twobyteToint_(comBean.bRec[16], comBean.bRec[17]) / 100);
            beanDip.ZStr = df2.format((float) MyFunc.twobyteToint_(comBean.bRec[18], comBean.bRec[19]) / 100);

            beanDip.signal = comBean.bRec[22];
            beanDip.elec = MyFunc.twoBytesToInt(comBean.bRec, 20);
        }
        return beanDip;
    }

    /**
     * 激光测距
     *
     * @param comBean
     */
    public BeanLaser getData_Laser(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 161 && comBean.bRec.length == 25) {
            byte[] LbufferLA = new byte[7];
            LbufferLA[0] = comBean.bRec[14];
            LbufferLA[1] = comBean.bRec[15];
            LbufferLA[2] = comBean.bRec[16];
            LbufferLA[3] = comBean.bRec[17];
            LbufferLA[4] = comBean.bRec[18];
            LbufferLA[5] = comBean.bRec[19];
            LbufferLA[6] = comBean.bRec[20];
            // 距离
            beanLaser.distanceStr = (new String(LbufferLA, 0, 7,
                    Charset.forName("ASCII")));

            beanLaser.signal = comBean.bRec[23];
            beanLaser.elec = MyFunc.twoBytesToInt(comBean.bRec, 21);
        }
        return beanLaser;
    }

    /**
     * 风杯
     *
     * @param comBean
     */
    public BeanVane getData_Vane(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 80 && comBean.bRec.length == 20) {
            float windSpeed = ((float) MyFunc.HexToInt(MyFunc.ByteArrToHex(
                    comBean.bRec, 14, 16)) / 100);
            if (windSpeed > 0 || windSpeed < 50) {
                beanVane.windSpeedStr = df2.format(windSpeed);

            }
            beanVane.signal = comBean.bRec[18];
            beanVane.elec = MyFunc.twoBytesToInt(comBean.bRec, 16);
        }
        return beanVane;
    }

    /**
     * 差压
     *
     * @param comBean
     */
    public BeanDiffPressure getData_DiffPressure(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 48 && comBean.bRec.length == 33) {
            String jingya = df3.format((float) MyFunc.twoByte2int(comBean.bRec, 27));
            int FuHAo = comBean.bRec[26] & 0x00;
            if (FuHAo == 0x00) {
                beanDiffPressure.diffPressureStr = jingya;
            } else {
                beanDiffPressure.diffPressureStr = "- " + jingya;
            }
            beanDiffPressure.signal = comBean.bRec[31];
            beanDiffPressure.elec = MyFunc.twoBytesToInt(comBean.bRec, 29);
        }
        return beanDiffPressure;
    }

    /**
     * 超声波流量温度
     *
     * @param comBean
     */
    public BeanFlow getData_Flow(ComBean comBean) {
        int type = comBean.bRec[9] & 0xff;
        if (type == 160 && comBean.bRec.length == 51) {

            beanFlow.llVel = MyFunc.byte2float(comBean.bRec, 14);
            beanFlow.lsVel = MyFunc.byte2float(comBean.bRec, 18);
            beanFlow.njVel = MyFunc.byte2float(comBean.bRec, 22);
            beanFlow.jkwdVel = MyFunc.byte2float(comBean.bRec, 26);
            beanFlow.ckwdVel = MyFunc.byte2float(comBean.bRec, 30);
            beanFlow.xhchshbVel = MyFunc.byte2float(comBean.bRec, 43);

            beanFlow.xhqdVel = MyFunc.HexToInt(MyFunc.Byte2Hex(comBean.bRec[36]));

            //安装距离
            float azhjl = Math.abs(MyFunc.byteToint(comBean.bRec, 39));
            switch ((int) comBean.bRec[38]) {
                case 1:
                    beanFlow.azhjlVel = azhjl / 10;
                    break;
                case 2:
                    beanFlow.azhjlVel = azhjl / 100;
                    break;
                case 3:
                    beanFlow.azhjlVel = azhjl / 1000;
                    break;
                case 4:
                    beanFlow.azhjlVel = azhjl / 10000;
                    break;
                case 5:
                    beanFlow.azhjlVel = azhjl / 100000;
                    break;
                default:
                    break;
            }
            beanFlow.signal = comBean.bRec[49];
            beanFlow.elec = MyFunc.twoBytesToInt(comBean.bRec, 47);
        }
        return beanFlow;
    }


}