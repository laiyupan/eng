package com.eng.one.nine.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * String共通类
 */
public class StringUtil {
	private static MessageDigest digest = null;
	public static final String empty = "";

	/**
	 * 判断字符串是否为null和空
	 * @param strs
	 * @return
	 */
	public static boolean isAnyNullOrEmpty(String... strs) {
		for (String str : strs) {
			if (str == null || str.trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否所有字符串均为Null或者空
	 * @param strs
	 * @return
	 */
	public static boolean isAllNullOrEmpty(String... strs) {
		boolean result = false;
		for (String str : strs) {
			if (str == null || str.trim().length() == 0) {
				result = true;
			} else {
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 字符串截取，同javascript slice方法
	 * @param start 参数可以为负数
	 */
	public static String slice(String str, int start) {
		if(isAnyNullOrEmpty(str)) {
			return empty;
		}
		int length = str.length();
		while(start < 0) {
			start = length + start;
		}
		if(start >= length) {
			return empty;
		}
		return str.substring(start);
	}
	
	/**
	 * 字符串截取，同javascript slice方法
	 * @param start 参数可以为负数
	 * @param end 参数可以为负数
	 */
	public static String slice(String str, int start, int end) {
		if(isAnyNullOrEmpty(str)) {
			return empty;
		}
		int length = str.length();
		while(start < 0) {
			start = length + start;
		}
		if(start >= length) {
			return empty;
		}
		while(end < 0) {
			end = length + end;
		}
		if(end > length) {
			end = length - 1;
		}
		if(end < start) {
			return empty;
		}
		return str.substring(start, end);
		
	}

	/**
	 * 字符串格式化 format("He{0}{0}o W{1}rld", "l", "r") = Hello World
	 */
	public static String format(String format, String... args) {
		if (isAnyNullOrEmpty(format) || args == null || args.length == 0) {
            return format;
        }
		StringBuilder builder = new StringBuilder();
		int i = 0;
		while(true) {
			int open = format.indexOf('{', i);
			int close = format.indexOf('}', i);
			if ((open < 0) && (close < 0)) {
				builder.append(slice(format, i));
                break;
            }
			if ((close > 0) && ((close < open) || (open < 0))) {
                if (format.charAt(close + 1) != '}') {
                    throw new RuntimeException("如果想加入{}字符需成双出现，如{{ 或者 }}!");
                }
                builder.append(slice(format, i, close + 1));
                i = close + 2;
                continue;
            }
			builder.append(slice(format, i, open));
			i = open + 1;
            if (format.charAt(i) == '{') {
                builder.append('{');
                i++;
                continue;
            }
            if (close < 0)
                throw new RuntimeException("格式化字符串缺少闭合标记，不符合规则!");
            String brace = format.substring(i, close);
            int index = 0;
            try {
            	index = Integer.parseInt(brace, 10);
            } catch(NumberFormatException nfe) {
            	throw new RuntimeException("占位符必须为数字!");
            }
            String arg = args[index];
            if(arg == null)
            	arg = empty;
            builder.append(arg);
            i = close + 1;
		}
		return builder.toString();
	}
	
	/**
	 * 首字母大写
	 */
	public static String firstToUpper(String str) {
		String first = str.substring(0, 1).toUpperCase();
		String other = str.substring(1);
		return first + other;
	}
	
	/**
	 * 首字母小写
	 */
	public static String firstToLower(String str) {
		String first = str.substring(0, 1).toLowerCase();
		String other = str.substring(1);
		return first + other;
	}

	/**
	 * 获取给定字符串的MD5值（HASH）
	 * @param paramString
	 * @return
	 */
	public static final synchronized String hash(String paramString) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
				System.err
						.println("Failed to load the MD5 MessageDigest. Jive will be unable to function normally.");
				localNoSuchAlgorithmException.printStackTrace();
			}
		}
		digest.update(paramString.getBytes());
		return encodeHex(digest.digest());
	}

	/**
	 * 编码给定的字节数组为十六进制字符串
	 * @param paramArrayOfByte
	 * @return
	 */
	public static final String encodeHex(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer(
				paramArrayOfByte.length * 2);
		for (int i = 0; i < paramArrayOfByte.length; ++i) {
			if ((paramArrayOfByte[i] & 0xFF) < 16) {
				localStringBuffer.append("0");
			}
			localStringBuffer.append(Long.toString(paramArrayOfByte[i] & 0xFF,
					16));
		}
		return localStringBuffer.toString();
	}

	/**
	 * 解码给定的十六进制字符串为字节数组
	 * @param paramString
	 * @return
	 */
	public static final byte[] decodeHex(String paramString) {
		char[] arrayOfChar = paramString.toCharArray();
		byte[] arrayOfByte = new byte[arrayOfChar.length / 2];
		int i = 0;
		for (int j = 0; j < arrayOfChar.length; j += 2) {
			int k = 0;
			k = (byte) (k | hexCharToByte(arrayOfChar[j]));
			k = (byte) (k << 4);
			k = (byte) (k | hexCharToByte(arrayOfChar[(j + 1)]));
			arrayOfByte[i] = (byte) k;
			++i;
		}
		return arrayOfByte;
	}

	private static final byte hexCharToByte(char paramChar) {
		switch (paramChar) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		case ':':
		case ';':
		case '<':
		case '=':
		case '>':
		case '?':
		case '@':
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case '[':
		case '\\':
		case ']':
		case '^':
		case '_':
		case '`':
		}
		return 0;
	}

	/**
	 * 字符串BASE64编码
	 * @param paramString
	 * @return
	 */
	public static String encodeBase64(String paramString) {
		return encodeBase64(paramString.getBytes());
	}

	/**
	 * 以BASE64编码给定的字节数组
	 * @param paramArrayOfByte
	 * @return
	 */
	public static String encodeBase64(byte[] paramArrayOfByte) {
		int j = paramArrayOfByte.length;
		StringBuffer localStringBuffer = new StringBuffer((j / 3 + 1) * 4);
		for (int k = 0; k < j; ++k) {
			int i = paramArrayOfByte[k] >> 2 & 0x3F;
			localStringBuffer
					.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
							.charAt(i));
			i = paramArrayOfByte[k] << 4 & 0x3F;
			if (++k < j) {
				i |= paramArrayOfByte[k] >> 4 & 0xF;
			}
			localStringBuffer
					.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
							.charAt(i));
			if (k < j) {
				i = paramArrayOfByte[k] << 2 & 0x3F;
				if (++k < j) {
					i |= paramArrayOfByte[k] >> 6 & 0x3;
				}
				localStringBuffer
						.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
								.charAt(i));
			} else {
				++k;
				localStringBuffer.append('=');
			}

			if (k < j) {
				i = paramArrayOfByte[k] & 0x3F;
				localStringBuffer
						.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
								.charAt(i));
			} else {
				localStringBuffer.append('=');
			}
		}
		return localStringBuffer.toString();
	}

	/**
	 * 以BASE64解码字符串
	 * @author ZhangNing 
	 * @date 2012-8-24
	 * @param paramString
	 * @return
	 */
	public static String decodeBase64(String paramString) {
		return decodeBase64(paramString.getBytes());
	}

	/**
	 * 以BASE64解码给定的字节数组
	 * @param paramArrayOfByte
	 * @return
	 */
	public static String decodeBase64(byte[] paramArrayOfByte) {
		int k = paramArrayOfByte.length;
		StringBuffer localStringBuffer = new StringBuffer(k * 3 / 4);
		for (int l = 0; l < k; ++l) {
			int i = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.indexOf(paramArrayOfByte[l]);
			++l;
			int j = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.indexOf(paramArrayOfByte[l]);
			i = i << 2 | j >> 4 & 0x3;
			localStringBuffer.append((char) i);
			if (++l < k) {
				i = paramArrayOfByte[l];
				if (61 == i) {
					break;
				}
				i = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
						.indexOf((char) i);
				j = j << 4 & 0xF0 | i >> 2 & 0xF;
				localStringBuffer.append((char) j);
			}

			if (++l < k) {
				j = paramArrayOfByte[l];
				if (61 == j) {
					break;
				}
				j = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
						.indexOf((char) j);
				i = i << 6 & 0xC0 | j;
				localStringBuffer.append((char) i);
			}
		}
		return localStringBuffer.toString();
	}

	/**
	 * 删除前后全半角空格
	 * @param strIn 输入的将要被删除前后空格的字符串。
	 * @param bFullSpaceDel 判断前后的全角空格是否被删除
	 * @return 删除前后空格以后的字符串
	 */
	public static String deleteSpace(String strIn, boolean bFullSpaceDel) {
		if (strIn == null) {
			return "";
		}
		boolean bFlag = true;
		strIn = strIn.trim();
		while (bFlag && bFullSpaceDel) {
			if (strIn.length() <= 0) {
				break;
			}
			String strStart = strIn.substring(0, 1);
			String strEnd = strIn.substring(strIn.length() - 1);

			if (strEnd.equalsIgnoreCase("　") || strEnd.equalsIgnoreCase(" ")) {
				strIn = strIn.substring(0, strIn.length() - 1);
			} else if (strStart.equalsIgnoreCase("　")
					|| strStart.equalsIgnoreCase(" ")) {
				strIn = strIn.substring(1, strIn.length());
			} else {
				bFlag = false;
			}
		}
		return strIn;
	}
	
	/**
	 * 取得一个字符串的字节数
	 * @param s 输入的字符串
	 * @param encoding 字符串的编码（如：GB2312）
	 * @return 字符串的字节数
	 */
	public static int getStringByteLength(String s, String encoding) {
		if (isAnyNullOrEmpty(s)) {
			return 0;
		}
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			try {
				byte[] b = null;
				if (!isAnyNullOrEmpty(encoding)) {
					b = s.substring(i, i + 1).getBytes(encoding);
				} else {
					b = s.substring(i, i + 1).getBytes();
				}
				len = len + b.length;
			} catch (UnsupportedEncodingException e) {
				continue;
			}
		}
		return len;
	}
	
	/**
	 * 删除字符串中所有的空格，包括（中间和前后的全角和半角）
	 * @param strIn 输入的字符串
	 * @param bFullSpaceDel 判断是否删除全角空格
	 * @return 删除所有空格后的字符串
	 */
	public static String delAllSpace(String strIn, boolean bFullSpaceDel) {
		StringBuffer sb = new StringBuffer();
		strIn = deleteSpace(strIn, bFullSpaceDel);
		for (int i = 0; i < strIn.length(); i++) {
			if (bFullSpaceDel) {
				if (strIn.charAt(i) != '　' || strIn.charAt(i) != ' ') {
					sb.append(strIn.charAt(i));
				}
			} else if (strIn.charAt(i) != ' ') {
				sb.append(strIn.charAt(i));
			}
		}
		strIn = new String(sb);
		return strIn;
	}
	
	/**
	 * 替换某个数据中的某个字符串
	 * @param strIn 包含被替换数据的数据
	 * @param strRe 被替换的数据（原数据)
	 * @param strBy 用来替换的数据（替换后的数据）
	 * @return 替换完成后的整个数据
	 */
	public static String replace(String strIn, String strRe, String strBy) {
		int iPos;
		String strTemp = "";
		iPos = strIn.indexOf(strRe);
		while (iPos != -1) {
			strTemp = strTemp + strIn.substring(0, iPos) + strBy;
			strIn = strIn.substring(iPos + strRe.length());
			iPos = strIn.indexOf(strRe);
		}
		strIn = strTemp + strIn;
		return strIn;
	}
	
	/**
	 * 将null转换成""
	 * @param value
	 * @return
	 */
	public static String replaceNull(String value) {
		return value == null ? "" : value;
	}
	
	/**
	 * 用特定的字符补充一个字符串到一个指定的总长度
	 * @param strIn 需要被填充的字符串
	 * @param cIn 填充到里面的字符(只能是单个字符，否则不能保证填充后的长度）
	 * @param iLen 填充后的总长度
	 * @param iType 0：从前面补充字符；1：从后面补充字符
	 * @return 填充后的数据
	 */
	public static String getLenString(String strIn, char cIn, int iLen,
			int iType) {
		int iNum;
		String strRet = strIn;
		iNum = iLen - strIn.length();
		for (int i = 1; i <= iNum; i++) {
			if (iType == 0) {
				strRet = String.valueOf(cIn) + strRet;
			} else {
				strRet += String.valueOf(cIn);
			}
		}
		return strRet;
	}
	
	/**
	 * 返回一个字符在一个字符串中的个数
	 * @param strIn 包含要查找字符的字符串
	 * @param cIn 要查找的字符
	 * @return 字符在字符串中的个数
	 */
	public static int getStringCnt(String strIn, char cIn) {
		char c;
		int iRet = 0;
		if (strIn == null || strIn.length() == 0
				|| String.valueOf(cIn).length() == 0) {
			return 0;
		}
		if (strIn.indexOf(String.valueOf(cIn)) == -1) {
			return 0;
		} else {
			for (int i = 0; i < strIn.length(); i++) {
				c = strIn.charAt(i);
				if (c == cIn) {
					iRet++;
				}
			}
		}
		return iRet;
	}
	
	/**
	 * 将unicode解码成中文
	 * @param value
	 * @return
	 */
	public static String unicode2Chinese(String value) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(value);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			value = value.replace(matcher.group(1), ch + "");
		}
		return value;
	}
	
	/**
	 * 将中文编码成unicode
	 * @param value
	 * @return
	 */
	public static String chinese2Unicode(String value) {
		String strRet = "";
		for (int i = 0; i < value.length(); i++) {
			strRet += "\\u" + Integer.toHexString(value.charAt(i));
		}
		return strRet;
	}
	
	/**
	 * 判断数据是否超长（GB2312编码）
	 * @param strIn 输入的字符
	 * @param iMaxLength 最大长度
	 * @return 超长时返回TRUE
	 */
	public static boolean isOverLength(String strIn, int iMaxLength) {
		return getStringByteLength(strIn, "gb2312") > iMaxLength;
	}
	
	/**
	 * 将英文/数字/特殊记号由全角转成半角
	 * @param s 输入的数据
	 * @return 转换后的数据
	 */
	public static String FullToHalf(String s) {
		char c;
		if (s == null) {
			return s;
		}
		if (s.length() == 0) {
			return s;
		}
		int iLen = s.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < iLen; i++) {
			c = s.charAt(i);
			if (c >= 0xff01 && 0xff5e > c) {
				c -= 0xfee0;
			} else if (c == 0xffe5) {
				c -= 0xff89;
			} else if (c == 0x2019) {
				c -= 0x1ff2;
			} else if (c == 0x201d) {
				c -= 0x1ffb;
			} else if (c == 0x301c) {
				c -= 0x2f9e;
			} else if (c == 0x2212) {
				c -= 0x21e5;
			} else if (c == 0x2018) {
				c -= 0x1fb8;
			} else if (c == 0x3002) {
				c -= 0x2fd4;
			} else if (c == 0x3001) {
				c -= 0x2fd5;
			} else if (c == 0x3000) {
				c -= 0x2fe0;
			}
			sb.append(c);
		}
		return new String(sb);
	}

	/**
	 * 将英文/数字/特殊记号由半角转换成全角
	 * @param s 输入的数据
	 * @return 转换后的数据
	 */
	public static String HalfToFull(String s) {
		char c;
		if (s == null) {
			return s;
		}
		if (s.length() == 0) {
			return s;
		}
		int iLen = s.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < iLen; i++) {
			c = s.charAt(i);
			if (c >= 0x0021 && 0x007e >= c) {
				c += 0xfee0;
			} else if (c == 0x005c) {
				c += 0xff89;
			} else if (c == 0x0027) {
				c += 0x1ff2;
			} else if (c == 0x0022) {
				c += 0x1ffb;
			} else if (c == 0x007e) {
				c += 0x2f9e;
			} else if (c == 0x002d) {
				c += 0x21e5;
			} else if (c == 0x0060) {
				c += 0x1fb8;
			} else if (c == 0x002e) {
				c += 0x2fd4;
			} else if (c == 0x002c) {
				c += 0x2fd5;
			} else if (c == 0x0020) {
				c += 0x2fe0;
			}
			sb.append(c);
		}
		return new String(sb);
	}
	
	/**
	 * 转换成bool数据类型 
	 * @param strInner
	 * @return
	 */
	public static boolean changeStringDataType(String strInner) {
		return !("0".equals(strInner) || StringUtil.isAnyNullOrEmpty(strInner));
	}
	
	/**
	 * 随机生成一个初始密码
	 * @param l 密码长度（不得超过32位）
	 * @return
	 */
	public static String generatePassword(int l) {
		try {
			synchronized (StringUtil.class) {
				String password = "";
				long current = System.currentTimeMillis();
				byte[] now = new Long(current).toString().getBytes();
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(now);
				byte[] buffer = md.digest();
				StringBuffer sb = new StringBuffer(buffer.length * 2);
				for (int i = 0; i < buffer.length; i++) {
					sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
					sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
				}
				password = sb.toString();
				l = l > 32 ? 32 : l;
				if (password.length() > l) {
					password = password.substring(0, l);
				}
				return password;
			}
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
