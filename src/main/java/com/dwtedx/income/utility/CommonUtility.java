package com.dwtedx.income.utility;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtility {

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
    
    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDateTime() {
    	return Calendar.getInstance().getTime();
    }

    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getCurrentTimeHms
     * @Description: 当前时间
     * @author qinyl http://dwtedx.com
     */
    public static String getCurrentDate() {

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
        return formatDate.format(new Date());

		/*Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH);// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR);// 小时
		int second = ca.get(Calendar.SECOND);// 秒

		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;*/
    }

    /**
     * 时间戳转化成时间
     *
     * @param time
     * @return
     */
    public static String longToDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));

    }

    /**
     * 时间戳转化成时间
     * 精确到分
     *
     * @param time
     * @return
     */
    public static String longToDateS(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date(time));

    }

    /**
     * Date转String
     *
     * @param date
     * @return
     */
    public static String stringDateFormart(Date date) {
    	if(null == date){
    		return getCurrentTime();
    	}
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "CN"));
        return formatDate.format(date);
    }
    
    /**
     * Date转String
     *
     * @param date
     * @return
     */
    public static String stringDateFormartyyyyMMdd(Date date) {
    	if(null == date){
    		return getCurrentTime();
    	}
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
        return formatDate.format(date);
    }

    /**
     * Date转String MM-dd
     *
     * @param date
     * @return
     */
    public static String stringDateFormartMMdd(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("MM月dd日", new Locale("zh", "CN"));
        String value = formatDate.format(date);
        return value;
    }

    /**
     * Date转String
     *
     * @param date
     * @return
     */
    public static String stringDateFormart(String date, String formart) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "CN"));
        SimpleDateFormat formatDate1 = new SimpleDateFormat(formart, new Locale("zh", "CN"));
        Date time = null;
        try {
            time = formatDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate1.format(time);
    }

    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }
    
    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    /**
     * @param @param  day
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: getDateByCondition
     * @Description: 当前时间相对的某一天
     * @author qinyl http://dwtedx.com
     */
    public static Date getDateByCondition(int day) {
        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, day);    //得到前一天
        return calendar.getTime();
    }

    /**
     * @param @param  str_input
     * @param @param  rDateFormat
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: isDate
     * @Description: 判断是否为合法的日期时间字符串
     * @author qinyl http://dwtedx.com
     */
    public static boolean isDate(String str_input, String rDateFormat) {
        if (!isNull(str_input)) {
            SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat, new Locale("zh", "CN"));
            formatter.setLenient(false);
            try {
                formatter.format(formatter.parse(str_input));
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    /** 
     * 生成随机数<br> 
     * GUID： 即Globally Unique Identifier（全球唯一标识符） 也称作 UUID(Universally Unique 
     * IDentifier) 。 
     *  
     * 所以GUID就是UUID。 
     *  
     * GUID是一个128位长的数字，一般用16进制表示。算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID。 
     *  
     * 从理论上讲，如果一台机器每秒产生10000000个GUID，则可以保证（概率意义上）3240年不重复。 
     *  
     * @return 
     */  
    public static String randomUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    }

    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getTempFileName
     * @Description: 当前时间的jpg名称
     * @author qinyl http://dwtedx.com
     */
    public static String getTempImageName(int userid) {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);// 获取年份
        int month = ca.get(Calendar.MONTH) + 1;// 获取月份
        int day = ca.get(Calendar.DATE);// 获取日
        int minute = ca.get(Calendar.MINUTE);// 分
        int hour = ca.get(Calendar.HOUR);// 小时
        int second = ca.get(Calendar.SECOND);// 秒
        return "IMG_" + year + month + day + "_" + hour + minute + second + "_" + userid + ".jpg";
    }
    
    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getTempFileName
     * @Description: 当前时间的jpg名称
     * @author qinyl http://dwtedx.com
     */
    public static String getTempImageName(int code, String suffix) {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);// 获取年份
        int month = ca.get(Calendar.MONTH) + 1;// 获取月份
        int day = ca.get(Calendar.DATE);// 获取日
        //int minute = ca.get(Calendar.MINUTE);// 分
        //int hour = ca.get(Calendar.HOUR);// 小时
        //int second = ca.get(Calendar.SECOND);// 秒
        return randomUUID() + "_" + year + month + day + "_" + code + "." + suffix;
    }

    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getTempFileName
     * @Description: 当前时间的mp4名称
     * @author qinyl http://dwtedx.com
     */
    public static String getTempVideoName() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);// 获取年份
        int month = ca.get(Calendar.MONTH) + 1;// 获取月份
        int day = ca.get(Calendar.DATE);// 获取日
        int minute = ca.get(Calendar.MINUTE);// 分
        int hour = ca.get(Calendar.HOUR);// 小时
        int second = ca.get(Calendar.SECOND);// 秒
        return "VODEO_" + year + month + day + "_" + hour + minute + second + ".mp4";
    }

    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getTempFileName
     * @Description: 当前时间的amr名称
     * @author qinyl http://dwtedx.com
     */
    public static String getTempVoiceName() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);// 获取年份
        int month = ca.get(Calendar.MONTH) + 1;// 获取月份
        int day = ca.get(Calendar.DATE);// 获取日
        int minute = ca.get(Calendar.MINUTE);// 分
        int hour = ca.get(Calendar.HOUR);// 小时
        int second = ca.get(Calendar.SECOND);// 秒
        return "VODEO_" + year + month + day + "_" + hour + minute + second + ".amr";
    }

    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 生成0-9的随机数
     *
     * @param count 生成几位数（6位）
     * @return random 随机数
     */

    public static String getRandomNumber(int count) {
        String random = "";
        for (int i = 0; i < count; i++) {
            String str = String.valueOf((int) (Math.random() * 10 - 1));
            random = random + str;
        }
        return random;
    }

    /**
     * @param @param  phoneNumber
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: isPhoneNumberValid
     * @Description: 验证号码 手机号 固话均可
     * @author qinyl
     * @date 2014年6月20日 下午3:16:03
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (null == phoneNumber) {
            return false;
        }
        String expression = "((^(13|15|18|17)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|"
                + "(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|"
                + "(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * @param @param phoneNumber
     * @return boolean 返回类型
     * @throws
     * @Title: isHttpURLValid
     * @Description: 验证 url
     * @author qinyl
     * @date 2015年10月20日 下午3:16:03
     */
    public static boolean isHttpURLValid(String httpUrl) {
        if (null == httpUrl) {
            return false;
        }
        String expression = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.," +
                "@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
        CharSequence inputStr = httpUrl;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /**
     * 判断输入是否是中文
     *
     * @param input
     * @return
     */
    public static boolean isInputChinese(String input) {
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();//true全部为汉字，否则是false
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
            return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * @param @param  src
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: bytesToHexString
     * @Description: byte 转 hexString
     * @author qinyl http://dwtedx.com
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * @param @param  value
     * @param @param  formart
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: floatToStr
     * @Description: format as "##0.00"
     * @author qinyl http://dwtedx.com
     */
    public static String floatToStr(float value, String formart) {
        DecimalFormat fnum = new DecimalFormat(formart);
        return fnum.format(value);
    }

    /**
     * @param @param  value
     * @param @param  bit
     * @param @return 设定文件
     * @return float    返回类型
     * @throws
     * @Title: floatFormart
     * @Description: floatFormart
     * @author qinyl http://dwtedx.com
     */
    public static float floatFormart(float value, int bit) {
        BigDecimal b = new BigDecimal(value);
        return b.setScale(bit, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * @param @param  value
     * @param @param  bit
     * @param @return 设定文件
     * @return float    返回类型
     * @throws
     * @Title: floatFormart
     * @Description: floatFormart
     * @author qinyl http://dwtedx.com
     */
    public static Float StrToFloat(String floatStr) {
        float value = 0.0f;

        if (null != floatStr && floatStr.length() > 0) {
            try {
                value = Float.parseFloat(floatStr);
            } catch (NumberFormatException e) {
                value = 0.0f;
            }
        }

        return value;
    }

    /**
     * @param @param  date
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: stringToDate
     * @Description: Date转String
     * @author qinyl http://dwtedx.com
     */
    public static Date dateToString(String date, String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format, new Locale("zh", "CN"));
        Date time = null;
        try {
            time = formatDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    
    /**
     * @param @param  date
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: stringToDate
     * @Description: Date转String
     * @author qinyl http://dwtedx.com
     */
    public static Date dateToString(String date) {
    	if(isEmpty(date)) {
    		return null;
    	}
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "CN"));
        Date time = null;
        try {
            time = formatDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @param @param  str
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: isNull
     * @Description: 是否为null
     * @author qinyl http://dwtedx.com
     */
    public static boolean isNull(String str) {
        if (str == null)
            return true;
        else
            return false;
    }

    /**
     * @param @param  str
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: isNull
     * @Description: 是否为空字符串
     * @author qinyl http://dwtedx.com
     */
    public static boolean isEmpty(String str) {
        if (!isNull(str) && !"".equals(str))
            return false;
        else
            return true;
    }

    /**
     * @param @param  userName
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: hideUserName
     * @Description: 隐藏用户名
     * @author qinyl http://dwtedx.com
     */
    public static String hideUserName(String userName) {
        String result = "";

        if (userName.length() > 1) {
            result = userName.substring(0, 1);
            for (int i = 0; i < userName.length(); i++) {
                result += "*";
            }
        } else {
            result = userName;
        }

        return result;
    }

    /**
     * @param @param  userName
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: hideUserName
     * @Description: 隐藏手机号
     * @author qinyl http://dwtedx.com
     */
    public static String hidePhone(String phone) {
        String result = "";

        if (phone.length() > 3) {
            result = phone.substring(0, 3);
            for (int i = 3; i < phone.length() && i < 7; i++) {
                result += "*";
            }

            if (phone.length() > 7) {
                result += phone.substring(7, phone.length());
            }

        } else {
            result = phone;
        }

        return result;
    }

    public static String twoPlaces(float mfloat) {
        //构造方法的字符格式这里如果小数不足2位,会以0补足
        //DecimalFormat decimalFormat = new DecimalFormat("#.00");
        //format 返回的是字符串
        return String.format("%.2f", mfloat);
    }

    public static String twoPlaces(double mdouble) {

        return String.format("%.2f", mdouble);
    }


    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 某一个月第一天和最后一天
     *
     * @param date
     * @return
     */
    public static Map<String, String> getFirstday_Lastday_Month(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();

        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        day_first = str.toString();

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        day_last = endStr.toString();

        Map<String, String> map = new HashMap<String, String>();
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }

    /**
     * 当月第一天
     *
     * @return
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();

    }

    /**
     * 当月最后一天
     *
     * @return
     */
    public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }

    //千分位方法
    public static String fmtMicrometer(String text) {
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    public static String tenThousand(String val) {
        double price = Double.parseDouble(val) / 10000;
        return CommonUtility.twoPlaces(price);
    }

    public static String tenThousand(float val) {
        double price = val / 10000;
        return CommonUtility.twoPlaces(price);
    }

    public static String hundred(String val) {
        double prices = Double.parseDouble(val) / 100;
        return CommonUtility.twoPlaces(prices);
    }

    /**
     * 限定输入框内的文字不能含有特殊字符
     *
     * @param str
     * @return
     */
    public static boolean hasSpecialCharacter(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
    /**
     * 判断该字符串是否为字母和数字
     * @param str
     * @return
     */
    public static boolean isNumericOrABC(String str){
        String regEx="[A-Z,a-z,0-9,-]*";
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(str).matches();
    }
    
    public static String zeroPlaces(float mfloat) {
    	DecimalFormat df = new DecimalFormat("#");
    	return df.format(mfloat);
    }
}
