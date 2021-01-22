package book.util.bak;

import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidateCodeUtils {
	/** @param length 生成随机数字和字母的长度
	 * @return 生成的字符串 */
	public String getStringRandom(int length) {

		String val = "";
		Random random = new Random();
		
		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
//
//	public static void main(String[] args) {
//		ValidateCodeUtils test = new ValidateCodeUtils();
//		// 测试
//		System.out.println(test.getStringRandom(8));
//		test.image();
//	}
	void image() {
		BufferedImage bi = new BufferedImage(20,10,BufferedImage.TYPE_3BYTE_BGR);
		//bi.
		System.out.println(bi);
	}

}
