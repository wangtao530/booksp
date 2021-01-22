package book.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialTokenFilter {
	/**@author wt
	 * @param str 原字符串
	 * @return 清除所有特殊字符的字符串
	 * */
	public static String StringFilter(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;\",\\[\\].<>/?！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
