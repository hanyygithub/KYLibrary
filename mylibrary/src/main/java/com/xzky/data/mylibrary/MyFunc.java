package com.xzky.data.mylibrary;


/**
 * @author benjaminwan
 *         数据转换工具
 */
public class MyFunc {

    //获取其中的一段
    public static byte[] getByteArry(byte[] bytes, int len) {
        byte[] res = new byte[len];
        for (int i = 0; i < 14; i++) {
            res[i] = bytes[i];
        }
        return res;
    }

    //-------------------------------------------------------
    // 判断奇数或偶数，位运算，最后一位是1则为奇数，为0是偶数
    static public int isOdd(int num) {
        return num & 0x1;
    }


    public static int byteToint(byte[] b, int index) {
        byte[] by = new byte[4];
        by[0] = b[index + 3];
        by[1] = b[index + 2];
        by[2] = b[index + 1];
        by[3] = b[index];

        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0xffff;
        l |= ((long) by[2] << 16);
        l &= 0xffffff;
        l |= ((long) by[3] << 24);
        l &= 0xffffffff;
        return l;
    }

    /**
     * 字节转换为浮点
     *
     * @param b     字节（至少4个字节）
     * @param index 开始位置
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        byte[] by = new byte[4];
        by[0] = b[index + 3];
        by[1] = b[index + 2];
        by[2] = b[index + 1];
        by[3] = b[index];
        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0xffff;
        l |= ((long) by[2] << 16);
        l &= 0xffffff;
        l |= ((long) by[3] << 24);
        return Float.intBitsToFloat(l);
    }

    public static int byte4Toint(byte[] b, int index) {
        byte[] by = new byte[4];
        by[0] = b[index + 3];
        by[1] = b[index + 2];
        by[2] = b[index + 1];
        by[3] = b[index];
        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0xffff;
        l |= ((long) by[2] << 16);
        l &= 0xffffff;
        l |= ((long) by[3] << 24);
        l &= 0xffffffff;
        return l;
    }


    //2字节byte转int  无符号
    public static int twobyteToint_(byte a, byte b) {
        int result = 0;
        int res1 = HexToInt(Byte2Hex(a)) * 256;
        int res2 = HexToInt(Byte2Hex(b));
        return res1 + res2;
    }

    /**
     * @Description ： 功率因数  无功功率  有公功率
     */
    public static int twoBytesToIntPower(byte[] bytes, int index) {
        byte[] by = new byte[4];
        by[0] = bytes[index + 1];
        by[1] = bytes[index];

        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0x7fff;

        if (by[0] < 0) {
            l = 0 - l;
        }
        return l;
    }


    /**
     * @Description ：带符号
     */
    public static int twoBytesToInt_(byte[] bytes, int index) {
        byte[] by = new byte[4];
        by[0] = bytes[index + 1];
        by[1] = bytes[index];

        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        return l;
    }


    /**
     * @Description ：不带符号
     */
    public static int twoBytesToInt(byte[] bytes, int index) {
        byte[] by = new byte[4];
        by[0] = bytes[index + 1];
        by[1] = bytes[index];

        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0xffff;
        return l;
    }

    /**
     * @Description ：不带符号
     */
    public static int twoBytesToInt_speed(byte[] bytes, int index) {
        byte[] by = new byte[4];
        by[0] = bytes[index];
        by[1] = bytes[index + 1];

        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0xffff;
        return l;
    }


    /**
     * @Description ：不带符号
     */
    public static int threeBytesToInt(byte[] bytes, int index) {
        byte[] by = new byte[6];
        by[0] = bytes[index + 2];
        by[1] = bytes[index + 1];
        by[2] = bytes[index];
        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0xffff;
        l |= ((long) by[2] << 16);
        l &= 0xffffff;
        return l;
    }

    //-------------------------------------------------------
    static public int HexToInt(String inHex)//Hex字符串转int
    {
        return Integer.parseInt(inHex, 16);
    }

    //-------------------------------------------------------
    static public byte HexToByte(String inHex)//Hex字符串转byte
    {
        return (byte) Integer.parseInt(inHex, 16);
    }

    //-------------------------------------------------------
    static public String Byte2Hex(Byte inByte)//1字节转2个Hex字符
    {
        return String.format("%02x", inByte).toUpperCase();
    }

    //-------------------------------------------------------
    static public String ByteArrToHex(byte[] inBytArr)//字节数组转转hex字符串
    {
        StringBuilder strBuilder = new StringBuilder();
        int j = inBytArr.length;
        for (int i = 0; i < j; i++) {
            strBuilder.append(Byte2Hex(inBytArr[i]));
        }
        return strBuilder.toString();
    }

    //-------------------------------------------------------
    static public String ByteArrToHex2(byte[] inBytArr)//字节数组转转hex字符串
    {
        StringBuilder strBuilder = new StringBuilder();
        int j = inBytArr.length;
        for (int i = 0; i < j; i++) {
            strBuilder.append(Byte2Hex(inBytArr[i]));
        }
        return strBuilder.toString();
    }

    //-------------------------------------------------------
    //字节数组转转hex字符串，可选长度
    //byteCount是位置  不是长度
    static public String ByteArrToHex(byte[] inBytArr, int offset, int byteCount) {
        StringBuilder strBuilder = new StringBuilder();
        int j = byteCount;
        for (int i = offset; i <= j; i++) {
            strBuilder.append(Byte2Hex(inBytArr[i]));
        }
        return strBuilder.toString();
    }

    //-------------------------------------------------------
    //转hex字符串转字节数组
    static public byte[] HexToByteArr(String inHex)//hex字符串转字节数组
    {
        int hexlen = inHex.length();
        byte[] result;
        if (isOdd(hexlen) == 1) {//奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {//偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = HexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }


    static public String asciiToString(int in) {

        String result = "";
        switch (in) {
            case 48:
                result = "0";
                break;
            case 49:
                result = "1";
                break;
            case 50:
                result = "2";
                break;
            case 51:
                result = "3";
                break;
            case 52:
                result = "4";
                break;
            case 53:
                result = "5";
                break;
            case 54:
                result = "6";
                break;
            case 55:
                result = "7";
                break;
            case 56:
                result = "8";
                break;
            case 57:
                result = "9";
                break;
            case 46:
                result = ".";
                break;
            default:
                break;

        }
        return result;
    }

//2字节首位代表符号位，其余不取反直接计算 功率箱的P、Q、COS、

    public static int twobyteToint_Sp(byte a, byte b) {

        int result = 0;
        int res1 = HexToInt(Byte2Hex(a)) * 256;
        int res2 = HexToInt(Byte2Hex(b));
        result = res1 + res2;
        if ((a & 0x80) == 0x80) {
            result = -(result - 32768);
        }
        return result;
    }

    /**
     * 两字节转int  有符号位
     * <p>
     * 功率箱 P Q  解析
     *
     * @param a ： 第一字节
     * @param b ： 第二字节
     * @return
     */
    public static int twoBytesToIntHave(byte a, byte b) {
        byte[] by = new byte[4];
        by[0] = b;
        by[1] = a;

        int l;
        l = by[0];
        l &= 0xff;
        l |= ((long) by[1] << 8);
        l &= 0xffff;
        l = l << 16;
        l = l / (int) Math.pow(2, 16);
        return l;
    }

    public static int twoByte2int(byte[] b, int index) {
        return b[index + 1] & 0xFF | (b[index] & 0xFF) << 8;
    }

}