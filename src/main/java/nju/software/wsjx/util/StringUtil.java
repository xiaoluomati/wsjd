/**
 * created by 2010-7-2
 */
package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author zym
 * 
 */
public class StringUtil {

	/** 空字符串。 */
	public static final String EMPTY_STRING = "";

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 判空函数。 */
	/*                                                                              */
	/* 以下方法用来判定一个字符串是否为： */
	/* 1. null */
	/* 2. empty - "" */
	/* 3. blank - "全部是空白" - 空白由Character.isWhitespace所定义。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	/**
	 * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = false
	 * StringUtil.isEmpty("")        = false
	 * StringUtil.isEmpty(" ")       = true
	 * StringUtil.isEmpty("bob")     = true
	 * StringUtil.isEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果不为空, 则返回<code>true</code>
	 */
	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	/**
	 * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = false
	 * StringUtil.isBlank("")        = false
	 * StringUtil.isBlank(" ")       = false
	 * StringUtil.isBlank("bob")     = true
	 * StringUtil.isBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isNotBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 比较函数。 */
	/*                                                                              */
	/* 以下方法用来比较两个字符串是否相同。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 比较两个字符串（大小写敏感）。
	 * 
	 * <pre>
	 * StringUtil.equals(null, null)   = true
	 * StringUtil.equals(null, "abc")  = false
	 * StringUtil.equals("abc", null)  = false
	 * StringUtil.equals("abc", "abc") = true
	 * StringUtil.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * 
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		if (str2 == null) {
			return str1 == null;
		}
		str1 = str1.trim();
		str2 = str2.trim();
		return str1.equals(str2);
	}

	/**
	 * 比较两个字符串（大小写敏感,但剔除两边的空格）
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * 
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equalsIgnoreWhitespace(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		str1 = str1.trim();
		str2 = str2.trim();
		return str1.equals(str2);
	}

	/**
	 * 比较两个字符串（大小写不敏感）。
	 * 
	 * <pre>
	 * StringUtil.equalsIgnoreCase(null, null)   = true
	 * StringUtil.equalsIgnoreCase(null, "abc")  = false
	 * StringUtil.equalsIgnoreCase("abc", null)  = false
	 * StringUtil.equalsIgnoreCase("abc", "abc") = true
	 * StringUtil.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * 
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equalsIgnoreCase(str2);
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 去空白（或指定字符）的函数。 */
	/*                                                                              */
	/* 以下方法用来除去一个字串中的空白或指定字符。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * StringUtil.trim(null)          = null
	 * StringUtil.trim("")            = ""
	 * StringUtil.trim("     ")       = ""
	 * StringUtil.trim("abc")         = "abc"
	 * StringUtil.trim("    abc    ") = "abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str) {
		return trim(str, null, 0);
	}

	/**
	 * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.trim(null, *)          = null
	 * StringUtil.trim("", *)            = ""
	 * StringUtil.trim("abc", null)      = "abc"
	 * StringUtil.trim("  abc", null)    = "abc"
	 * StringUtil.trim("abc  ", null)    = "abc"
	 * StringUtil.trim(" abc ", null)    = "abc"
	 * StringUtil.trim("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为<code>null</code>表示除去空白字符
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str, String stripChars) {
		return trim(str, stripChars, 0);
	}

	/**
	 * 除去字符串头部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * StringUtil.trimStart(null)         = null
	 * StringUtil.trimStart("")           = ""
	 * StringUtil.trimStart("abc")        = "abc"
	 * StringUtil.trimStart("  abc")      = "abc"
	 * StringUtil.trimStart("abc  ")      = "abc  "
	 * StringUtil.trimStart(" abc ")      = "abc "
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimStart(String str) {
		return trim(str, null, -1);
	}

	/**
	 * 除去字符串头部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.trimStart(null, *)          = null
	 * StringUtil.trimStart("", *)            = ""
	 * StringUtil.trimStart("abc", "")        = "abc"
	 * StringUtil.trimStart("abc", null)      = "abc"
	 * StringUtil.trimStart("  abc", null)    = "abc"
	 * StringUtil.trimStart("abc  ", null)    = "abc  "
	 * StringUtil.trimStart(" abc ", null)    = "abc "
	 * StringUtil.trimStart("yxabc  ", "xyz") = "abc  "
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为<code>null</code>表示除去空白字符
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trimStart(String str, String stripChars) {
		return trim(str, stripChars, -1);
	}

	/**
	 * 除去字符串尾部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * StringUtil.trimEnd(null)       = null
	 * StringUtil.trimEnd("")         = ""
	 * StringUtil.trimEnd("abc")      = "abc"
	 * StringUtil.trimEnd("  abc")    = "  abc"
	 * StringUtil.trimEnd("abc  ")    = "abc"
	 * StringUtil.trimEnd(" abc ")    = " abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimEnd(String str) {
		return trim(str, null, 1);
	}

	/**
	 * 除去字符串尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.trimEnd(null, *)          = null
	 * StringUtil.trimEnd("", *)            = ""
	 * StringUtil.trimEnd("abc", "")        = "abc"
	 * StringUtil.trimEnd("abc", null)      = "abc"
	 * StringUtil.trimEnd("  abc", null)    = "  abc"
	 * StringUtil.trimEnd("abc  ", null)    = "abc"
	 * StringUtil.trimEnd(" abc ", null)    = " abc"
	 * StringUtil.trimEnd("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为<code>null</code>表示除去空白字符
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trimEnd(String str, String stripChars) {
		return trim(str, stripChars, 1);
	}

	/**
	 * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * StringUtil.trimToNull(null)          = null
	 * StringUtil.trimToNull("")            = null
	 * StringUtil.trimToNull("     ")       = null
	 * StringUtil.trimToNull("abc")         = "abc"
	 * StringUtil.trimToNull("    abc    ") = "abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToNull(String str) {
		return trimToNull(str, null);
	}

	/**
	 * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * StringUtil.trim(null, *)          = null
	 * StringUtil.trim("", *)            = null
	 * StringUtil.trim("abc", null)      = "abc"
	 * StringUtil.trim("  abc", null)    = "abc"
	 * StringUtil.trim("abc  ", null)    = "abc"
	 * StringUtil.trim(" abc ", null)    = "abc"
	 * StringUtil.trim("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为<code>null</code>表示除去空白字符
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToNull(String str, String stripChars) {
		String result = trim(str, stripChars);

		if ((result == null) || (result.length() == 0)) {
			return null;
		}

		return result;
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * StringUtil.trimToEmpty(null)          = ""
	 * StringUtil.trimToEmpty("")            = ""
	 * StringUtil.trimToEmpty("     ")       = ""
	 * StringUtil.trimToEmpty("abc")         = "abc"
	 * StringUtil.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToEmpty(String str) {
		return trimToEmpty(str, null);
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * StringUtil.trim(null, *)          = ""
	 * StringUtil.trim("", *)            = ""
	 * StringUtil.trim("abc", null)      = "abc"
	 * StringUtil.trim("  abc", null)    = "abc"
	 * StringUtil.trim("abc  ", null)    = "abc"
	 * StringUtil.trim(" abc ", null)    = "abc"
	 * StringUtil.trim("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToEmpty(String str, String stripChars) {
		String result = trim(str, stripChars);

		if (result == null) {
			return EMPTY_STRING;
		}

		return result;
	}

	/**
	 * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.trim(null, *)          = null
	 * StringUtil.trim("", *)            = ""
	 * StringUtil.trim("abc", null)      = "abc"
	 * StringUtil.trim("  abc", null)    = "abc"
	 * StringUtil.trim("abc  ", null)    = "abc"
	 * StringUtil.trim(" abc ", null)    = "abc"
	 * StringUtil.trim("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为<code>null</code>表示除去空白字符
	 * @param mode
	 *            <code>-1</code>表示trimStart，<code>0</code>表示trim全部，
	 *            <code>1</code>表示trimEnd
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	private static String trim(String str, String stripChars, int mode) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		// 扫描字符串头部
		if (mode <= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(start)) != -1)) {
					start++;
				}
			}
		}

		// 扫描字符串尾部
		if (mode >= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
					end--;
				}
			}
		}

		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}

		return str;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串类型判定函数。 */
	/*                                                                              */
	/* 判定字符串的类型是否为：字母、数字、空白等 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 判断字符串是否只包含unicode字母。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAlpha(null)   = false
	 * StringUtil.isAlpha("")     = true
	 * StringUtil.isAlpha("  ")   = false
	 * StringUtil.isAlpha("abc")  = true
	 * StringUtil.isAlpha("ab2c") = false
	 * StringUtil.isAlpha("ab-c") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode字母组成，则返回<code>true</code>
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母和空格<code>' '</code>。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAlphaSpace(null)   = false
	 * StringUtil.isAlphaSpace("")     = true
	 * StringUtil.isAlphaSpace("  ")   = true
	 * StringUtil.isAlphaSpace("abc")  = true
	 * StringUtil.isAlphaSpace("ab c") = true
	 * StringUtil.isAlphaSpace("ab2c") = false
	 * StringUtil.isAlphaSpace("ab-c") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode字母和空格组成，则返回<code>true</code>
	 */
	public static boolean isAlphaSpace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i)) && (str.charAt(i) != ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母和数字。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAlphanumeric(null)   = false
	 * StringUtil.isAlphanumeric("")     = true
	 * StringUtil.isAlphanumeric("  ")   = false
	 * StringUtil.isAlphanumeric("abc")  = true
	 * StringUtil.isAlphanumeric("ab c") = false
	 * StringUtil.isAlphanumeric("ab2c") = true
	 * StringUtil.isAlphanumeric("ab-c") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode字母数字组成，则返回<code>true</code>
	 */
	public static boolean isAlphanumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母数字和空格<code>' '</code>。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAlphanumericSpace(null)   = false
	 * StringUtil.isAlphanumericSpace("")     = true
	 * StringUtil.isAlphanumericSpace("  ")   = true
	 * StringUtil.isAlphanumericSpace("abc")  = true
	 * StringUtil.isAlphanumericSpace("ab c") = true
	 * StringUtil.isAlphanumericSpace("ab2c") = true
	 * StringUtil.isAlphanumericSpace("ab-c") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode字母数字和空格组成，则返回<code>true</code>
	 */
	public static boolean isAlphanumericSpace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))
					&& (str.charAt(i) != ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode数字。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNumeric(null)   = false
	 * StringUtil.isNumeric("")     = true
	 * StringUtil.isNumeric("  ")   = false
	 * StringUtil.isNumeric("123")  = true
	 * StringUtil.isNumeric("12 3") = false
	 * StringUtil.isNumeric("ab2c") = false
	 * StringUtil.isNumeric("12-3") = false
	 * StringUtil.isNumeric("12.3") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode数字和空格<code>' '</code>。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNumericSpace(null)   = false
	 * StringUtil.isNumericSpace("")     = true
	 * StringUtil.isNumericSpace("  ")   = true
	 * StringUtil.isNumericSpace("123")  = true
	 * StringUtil.isNumericSpace("12 3") = true
	 * StringUtil.isNumericSpace("ab2c") = false
	 * StringUtil.isNumericSpace("12-3") = false
	 * StringUtil.isNumericSpace("12.3") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode数字和空格组成，则返回<code>true</code>
	 */
	public static boolean isNumericSpace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode空白。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isWhitespace(null)   = false
	 * StringUtil.isWhitespace("")     = true
	 * StringUtil.isWhitespace("  ")   = true
	 * StringUtil.isWhitespace("abc")  = false
	 * StringUtil.isWhitespace("ab2c") = false
	 * StringUtil.isWhitespace("ab-c") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode空白组成，则返回<code>true</code>
	 */
	public static boolean isWhitespace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 大小写转换。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将字符串转换成大写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.toUpperCase(null)  = null
	 * StringUtil.toUpperCase("")    = ""
	 * StringUtil.toUpperCase("aBc") = "ABC"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toUpperCase(String str) {
		if (str == null) {
			return null;
		}

		return str.toUpperCase();
	}

	/**
	 * 将字符串转换成小写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.toLowerCase(null)  = null
	 * StringUtil.toLowerCase("")    = ""
	 * StringUtil.toLowerCase("aBc") = "abc"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toLowerCase(String str) {
		if (str == null) {
			return null;
		}

		return str.toLowerCase();
	}

	/**
	 * 将字符串的首字符转成大写（<code>Character.toTitleCase</code>），其它字符不变。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.capitalize(null)  = null
	 * StringUtil.capitalize("")    = ""
	 * StringUtil.capitalize("cat") = "Cat"
	 * StringUtil.capitalize("cAt") = "CAt"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 首字符为大写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String capitalize(String str) {
		int strLen;

		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		}

		return new StringBuffer(strLen)
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 将字符串的首字符转成小写，其它字符不变。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.uncapitalize(null)  = null
	 * StringUtil.uncapitalize("")    = ""
	 * StringUtil.uncapitalize("Cat") = "cat"
	 * StringUtil.uncapitalize("CAT") = "cAT"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 首字符为小写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String uncapitalize(String str) {
		int strLen;

		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		}

		return new StringBuffer(strLen)
				.append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 反转字符串的大小写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.swapCase(null)                 = null
	 * StringUtil.swapCase("")                   = ""
	 * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 大小写被反转的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String swapCase(String str) {
		int strLen;

		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		}

		StringBuffer buffer = new StringBuffer(strLen);

		char ch = 0;

		for (int i = 0; i < strLen; i++) {
			ch = str.charAt(i);

			if (Character.isUpperCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isTitleCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}

			buffer.append(ch);
		}

		return buffer.toString();
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串分割函数。 */
	/*                                                                              */
	/* 将字符串按指定分隔符分割。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将字符串按空白字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null)       = null
	 * StringUtil.split("")         = []
	 * StringUtil.split("abc def")  = ["abc", "def"]
	 * StringUtil.split("abc  def") = ["abc", "def"]
	 * StringUtil.split(" abc ")    = ["abc"]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * 
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	/**
	 * 将字符串按指定字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null, *)         = null
	 * StringUtil.split("", *)           = []
	 * StringUtil.split("a.b.c", '.')    = ["a", "b", "c"]
	 * StringUtil.split("a..b.c", '.')   = ["a", "b", "c"]
	 * StringUtil.split("a:b:c", '.')    = ["a:b:c"]
	 * StringUtil.split("a b c", ' ')    = ["a", "b", "c"]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChar
	 *            分隔符
	 * 
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str, char separatorChar) {
		if (str == null) {
			return null;
		}

		int length = str.length();

		if (length == 0) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}

		List<String> list = new ArrayList<String>();
		int i = 0;
		int start = 0;
		boolean match = false;

		while (i < length) {
			if (str.charAt(i) == separatorChar) {
				if (match) {
					list.add(str.substring(start, i));
					match = false;
				}

				start = ++i;
				continue;
			}

			match = true;
			i++;
		}

		if (match) {
			list.add(str.substring(start, i));
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 将字符串按指定字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null, *)                = null
	 * StringUtil.split("", *)                  = []
	 * StringUtil.split("abc def", null)        = ["abc", "def"]
	 * StringUtil.split("abc def", " ")         = ["abc", "def"]
	 * StringUtil.split("abc  def", " ")        = ["abc", "def"]
	 * StringUtil.split(" ab:  cd::ef  ", ":")  = ["ab", "cd", "ef"]
	 * StringUtil.split("abc.def", "")          = ["abc.def"]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChars
	 *            分隔符
	 * 
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str, String separatorChars) {
		return split(str, separatorChars, -1);
	}

	/**
	 * 将字符串按指定字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null, *, *)                 = null
	 * StringUtil.split("", *, *)                   = []
	 * StringUtil.split("ab cd ef", null, 0)        = ["ab", "cd", "ef"]
	 * StringUtil.split("  ab   cd ef  ", null, 0)  = ["ab", "cd", "ef"]
	 * StringUtil.split("ab:cd::ef", ":", 0)        = ["ab", "cd", "ef"]
	 * StringUtil.split("ab:cd:ef", ":", 2)         = ["ab", "cdef"]
	 * StringUtil.split("abc.def", "", 2)           = ["abc.def"]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChars
	 *            分隔符
	 * @param max
	 *            返回的数组的最大个数，如果小于等于0，则表示无限制
	 * 
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str, String separatorChars, int max) {
		if (str == null) {
			return null;
		}

		int length = str.length();

		if (length == 0) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}

		List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;

		if (separatorChars == null) {
			// null表示使用空白作为分隔符
			while (i < length) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		} else if (separatorChars.length() == 1) {
			// 优化分隔符长度为1的情形
			char sep = separatorChars.charAt(0);

			while (i < length) {
				if (str.charAt(i) == sep) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		} else {
			// 一般情形
			while (i < length) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		}

		if (match) {
			list.add(str.substring(start, i));
		}

		return (String[]) list.toArray(new String[list.size()]);
	}
	
	//取得状如 '21,13,4,4;12,3,56;12,3,5'字符串中第no个以separatorChars分割的子串
	//EG: getlist('21,13,4,4;12,3,56;12,3,5',';',3) = '12,3,5'
	public static String getsub(String str, String separatorChars, int index) {
		if (str == null) {
			return "";
		}
		int pos = 0, count =0, nextPos = 0;
		pos = str.indexOf(separatorChars);
		if (pos == -1 && index == 1) {
			return str;
		}
		if (pos == -1 && index > 1) {
			return "";
		}
		
		while (pos >= 0 && count != index - 1) {
			count++;
			if (count == index - 1) {
				break;
			}
			pos = str.indexOf(separatorChars, pos + separatorChars.length());
		}
		
		if (index == 1) {
			nextPos = pos;
			pos = 0 - separatorChars.length();
		}else {
			nextPos = str.indexOf(separatorChars, pos + separatorChars.length());
			if (nextPos == -1) {
				nextPos = str.length();
			}
		}
		
		return str.substring(pos + separatorChars.length(), nextPos);
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串查找函数 —— 字符或字符串。 */
	/*                                                                              */
	/* 在字符串中查找指定字符或字符串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)         = -1
	 * StringUtil.indexOf("", *)           = -1
	 * StringUtil.indexOf("aabaabaa", 'a') = 0
	 * StringUtil.indexOf("aabaabaa", 'b') = 2
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.indexOf(searchChar);
	}

	/**
	 * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf("", *, *)            = -1
	 * StringUtil.indexOf("aabaabaa", 'b', 0)  = 2
	 * StringUtil.indexOf("aabaabaa", 'b', 3)  = 5
	 * StringUtil.indexOf("aabaabaa", 'b', 9)  = -1
	 * StringUtil.indexOf("aabaabaa", 'b', -1) = 2
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * @param startPos
	 *            开始搜索的索引值，如果小于0，则看作0
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.indexOf(searchChar, startPos);
	}

	/**
	 * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf(*, null)          = -1
	 * StringUtil.indexOf("", "")           = 0
	 * StringUtil.indexOf("aabaabaa", "a")  = 0
	 * StringUtil.indexOf("aabaabaa", "b")  = 2
	 * StringUtil.indexOf("aabaabaa", "ab") = 1
	 * StringUtil.indexOf("aabaabaa", "")   = 0
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.indexOf(searchStr);
	}

	/**
	 * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf(*, null, *)          = -1
	 * StringUtil.indexOf("", "", 0)           = 0
	 * StringUtil.indexOf("aabaabaa", "a", 0)  = 0
	 * StringUtil.indexOf("aabaabaa", "b", 0)  = 2
	 * StringUtil.indexOf("aabaabaa", "ab", 0) = 1
	 * StringUtil.indexOf("aabaabaa", "b", 3)  = 5
	 * StringUtil.indexOf("aabaabaa", "b", 9)  = -1
	 * StringUtil.indexOf("aabaabaa", "b", -1) = 2
	 * StringUtil.indexOf("aabaabaa", "", 2)   = 2
	 * StringUtil.indexOf("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @param startPos
	 *            开始搜索的索引值，如果小于0，则看作0
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		// JDK1.3及以下版本的bug：不能正确处理下面的情况
		if ((searchStr.length() == 0) && (startPos >= str.length())) {
			return str.length();
		}

		return str.indexOf(searchStr, startPos);
	}

	/**
	 * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAny(null, *)                = -1
	 * StringUtil.indexOfAny("", *)                  = -1
	 * StringUtil.indexOfAny(*, null)                = -1
	 * StringUtil.indexOfAny(*, [])                  = -1
	 * StringUtil.indexOfAny("zzabyycdxx",['z','a']) = 0
	 * StringUtil.indexOfAny("zzabyycdxx",['b','y']) = 3
	 * StringUtil.indexOfAny("aba", ['z'])           = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAny(null, *)            = -1
	 * StringUtil.indexOfAny("", *)              = -1
	 * StringUtil.indexOfAny(*, null)            = -1
	 * StringUtil.indexOfAny(*, "")              = -1
	 * StringUtil.indexOfAny("zzabyycdxx", "za") = 0
	 * StringUtil.indexOfAny("zzabyycdxx", "by") = 3
	 * StringUtil.indexOfAny("aba","z")          = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length(); j++) {
				if (searchChars.charAt(j) == ch) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * 在字符串中查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
	 * <code>str.length()</code>
	 * 
	 * <pre>
	 * StringUtil.indexOfAny(null, *)                     = -1
	 * StringUtil.indexOfAny(*, null)                     = -1
	 * StringUtil.indexOfAny(*, [])                       = -1
	 * StringUtil.indexOfAny("zzabyycdxx", ["ab","cd"])   = 2
	 * StringUtil.indexOfAny("zzabyycdxx", ["cd","ab"])   = 2
	 * StringUtil.indexOfAny("zzabyycdxx", ["mn","op"])   = -1
	 * StringUtil.indexOfAny("zzabyycdxx", ["zab","aby"]) = 1
	 * StringUtil.indexOfAny("zzabyycdxx", [""])          = 0
	 * StringUtil.indexOfAny("", [""])                    = 0
	 * StringUtil.indexOfAny("", ["a"])                   = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStrs
	 *            要搜索的字符串集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}

		int sz = searchStrs.length;

		// String's can't have a MAX_VALUEth index.
		int ret = Integer.MAX_VALUE;

		int tmp = 0;

		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];

			if (search == null) {
				continue;
			}

			tmp = str.indexOf(search);

			if (tmp == -1) {
				continue;
			}

			if (tmp < ret) {
				ret = tmp;
			}
		}

		return (ret == Integer.MAX_VALUE) ? (-1) : ret;
	}

	/**
	 * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAnyBut(null, *)             = -1
	 * StringUtil.indexOfAnyBut("", *)               = -1
	 * StringUtil.indexOfAnyBut(*, null)             = -1
	 * StringUtil.indexOfAnyBut(*, [])               = -1
	 * StringUtil.indexOfAnyBut("zzabyycdxx",'za')   = 3
	 * StringUtil.indexOfAnyBut("zzabyycdxx", 'by')  = 0
	 * StringUtil.indexOfAnyBut("aba", 'ab')         = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAnyBut(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}

		outer: for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					continue outer;
				}
			}

			return i;
		}

		return -1;
	}

	/**
	 * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAnyBut(null, *)            = -1
	 * StringUtil.indexOfAnyBut("", *)              = -1
	 * StringUtil.indexOfAnyBut(*, null)            = -1
	 * StringUtil.indexOfAnyBut(*, "")              = -1
	 * StringUtil.indexOfAnyBut("zzabyycdxx", "za") = 3
	 * StringUtil.indexOfAnyBut("zzabyycdxx", "by") = 0
	 * StringUtil.indexOfAnyBut("aba","ab")         = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAnyBut(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			if (searchChars.indexOf(str.charAt(i)) < 0) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)         = -1
	 * StringUtil.lastIndexOf("", *)           = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'a') = 7
	 * StringUtil.lastIndexOf("aabaabaa", 'b') = 5
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.lastIndexOf(searchChar);
	}

	/**
	 * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf("", *,  *)           = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 8)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 4)  = 2
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 0)  = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 9)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", 'b', -1) = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'a', 0)  = 0
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * @param startPos
	 *            从指定索引开始向前搜索
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.lastIndexOf(searchChar, startPos);
	}

	/**
	 * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)         = -1
	 * StringUtil.lastIndexOf("", *)           = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'a') = 7
	 * StringUtil.lastIndexOf("aabaabaa", 'b') = 5
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.lastIndexOf(searchStr);
	}

	/**
	 * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf(*, null, *)          = -1
	 * StringUtil.lastIndexOf("aabaabaa", "a", 8)  = 7
	 * StringUtil.lastIndexOf("aabaabaa", "b", 8)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "ab", 8) = 4
	 * StringUtil.lastIndexOf("aabaabaa", "b", 9)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "b", -1) = -1
	 * StringUtil.lastIndexOf("aabaabaa", "a", 0)  = 0
	 * StringUtil.lastIndexOf("aabaabaa", "b", 0)  = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @param startPos
	 *            从指定索引开始向前搜索
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.lastIndexOf(searchStr, startPos);
	}

	/**
	 * 从字符串尾部开始查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
	 * <code>str.length()</code>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOfAny(null, *)                   = -1
	 * StringUtil.lastIndexOfAny(*, null)                   = -1
	 * StringUtil.lastIndexOfAny(*, [])                     = -1
	 * StringUtil.lastIndexOfAny(*, [null])                 = -1
	 * StringUtil.lastIndexOfAny("zzabyycdxx", ["ab","cd"]) = 6
	 * StringUtil.lastIndexOfAny("zzabyycdxx", ["cd","ab"]) = 6
	 * StringUtil.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
	 * StringUtil.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
	 * StringUtil.lastIndexOfAny("zzabyycdxx", ["mn",""])   = 10
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStrs
	 *            要搜索的字符串集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}

		int searchStrsLength = searchStrs.length;
		int index = -1;
		int tmp = 0;

		for (int i = 0; i < searchStrsLength; i++) {
			String search = searchStrs[i];

			if (search == null) {
				continue;
			}

			tmp = str.lastIndexOf(search);

			if (tmp > index) {
				index = tmp;
			}
		}

		return index;
	}

	/**
	 * 检查字符串中是否包含指定的字符。如果字符串为<code>null</code>，将返回<code>false</code>。
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)    = false
	 * StringUtil.contains("", *)      = false
	 * StringUtil.contains("abc", 'a') = true
	 * StringUtil.contains("abc", 'z') = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean contains(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}

		return str.indexOf(searchChar) >= 0;
	}

	/**
	 * 检查字符串中是否包含指定的字符串。如果字符串为<code>null</code>，将返回<code>false</code>。
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)     = false
	 * StringUtil.contains(*, null)     = false
	 * StringUtil.contains("", "")      = true
	 * StringUtil.contains("abc", "")   = true
	 * StringUtil.contains("abc", "a")  = true
	 * StringUtil.contains("abc", "z")  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean contains(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		}

		return str.indexOf(searchStr) >= 0;
	}

	/**
	 * 检查字符串是是否只包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>false</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsOnly(null, *)       = false
	 * StringUtil.containsOnly(*, null)       = false
	 * StringUtil.containsOnly("", *)         = true
	 * StringUtil.containsOnly("ab", '')      = false
	 * StringUtil.containsOnly("abab", 'abc') = true
	 * StringUtil.containsOnly("ab1", 'abc')  = false
	 * StringUtil.containsOnly("abz", 'abc')  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param valid
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsOnly(String str, char[] valid) {
		if ((valid == null) || (str == null)) {
			return false;
		}

		if (str.length() == 0) {
			return true;
		}

		if (valid.length == 0) {
			return false;
		}

		return indexOfAnyBut(str, valid) == -1;
	}

	/**
	 * 检查字符串是是否只包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>false</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsOnly(null, *)       = false
	 * StringUtil.containsOnly(*, null)       = false
	 * StringUtil.containsOnly("", *)         = true
	 * StringUtil.containsOnly("ab", "")      = false
	 * StringUtil.containsOnly("abab", "abc") = true
	 * StringUtil.containsOnly("ab1", "abc")  = false
	 * StringUtil.containsOnly("abz", "abc")  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param valid
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsOnly(String str, String valid) {
		if ((str == null) || (valid == null)) {
			return false;
		}

		return containsOnly(str, valid.toCharArray());
	}

	/**
	 * 检查字符串是是否不包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>true</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsNone(null, *)       = true
	 * StringUtil.containsNone(*, null)       = true
	 * StringUtil.containsNone("", *)         = true
	 * StringUtil.containsNone("ab", '')      = true
	 * StringUtil.containsNone("abab", 'xyz') = true
	 * StringUtil.containsNone("ab1", 'xyz')  = true
	 * StringUtil.containsNone("abz", 'xyz')  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param invalid
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsNone(String str, char[] invalid) {
		if ((str == null) || (invalid == null)) {
			return true;
		}

		int strSize = str.length();
		int validSize = invalid.length;

		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < validSize; j++) {
				if (invalid[j] == ch) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 检查字符串是是否不包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>true</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsNone(null, *)       = true
	 * StringUtil.containsNone(*, null)       = true
	 * StringUtil.containsNone("", *)         = true
	 * StringUtil.containsNone("ab", "")      = true
	 * StringUtil.containsNone("abab", "xyz") = true
	 * StringUtil.containsNone("ab1", "xyz")  = true
	 * StringUtil.containsNone("abz", "xyz")  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param invalidChars
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsNone(String str, String invalidChars) {
		if ((str == null) || (invalidChars == null)) {
			return true;
		}

		return containsNone(str, invalidChars.toCharArray());
	}

	/**
	 * 取得指定子串在字符串中出现的次数。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>或空，则返回<code>0</code>。
	 * 
	 * <pre>
	 * StringUtil.countMatches(null, *)       = 0
	 * StringUtil.countMatches("", *)         = 0
	 * StringUtil.countMatches("abba", null)  = 0
	 * StringUtil.countMatches("abba", "")    = 0
	 * StringUtil.countMatches("abba", "a")   = 2
	 * StringUtil.countMatches("abba", "ab")  = 1
	 * StringUtil.countMatches("abba", "xxx") = 0
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param subStr
	 *            子字符串
	 * 
	 * @return 子串在字符串中出现的次数，如果字符串为<code>null</code>或空，则返回<code>0</code>
	 */
	public static int countMatches(String str, String subStr) {
		if ((str == null) || (str.length() == 0) || (subStr == null)
				|| (subStr.length() == 0)) {
			return 0;
		}

		int count = 0;
		int index = 0;

		while ((index = str.indexOf(subStr, index)) != -1) {
			count++;
			index += subStr.length();
		}

		return count;
	}
	
	/*//added
	public static String getsub(String from, String div, int index){
		if(from==null || div == null) return null;
		StringTokenizer tokenizer = new StringTokenizer(from,div);
		
		String token = null;
		for(int i = 0; i<index; ++i){
			if(tokenizer.hasMoreTokens()){
				token = tokenizer.nextToken();
			}else{
				token = null;
				break;
			}
		}
		return token;
	}*/
	
	/**
	 * 判断一个字符串是否是数字类型
	 * <pre>
	 *1122.2.2:false
	 *111:true
     *111.2:true
     *111s:false
     *111.s:false
     *1s11:false
	 * </pre>
	 * @param str 要处理的字符串
	 * @return
	 */
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	public static String Decode(String str){
		if (str==null)
			return "";
		String re="";
		try{
			re=new String(str.getBytes("iso-8859-1"));
			
			return re;
		}catch(Exception e){
			return "";
		}
		
	}
	
	/**
	 * 若str为null，则返回""， 否则返回原字符串
	 * @return
	 */
	public static String NullToEmpty(String str) {
		if (StringUtil.isEmpty(str)) {
			return "";
		}else {
			return str;
		}
	}

	
	/**
	 * 替换指定的子串，替换所有出现的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
	 * ，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.replace(null, *, *)        = null
	 * StringUtil.replace("", *, *)          = ""
	 * StringUtil.replace("aba", null, null) = "aba"
	 * StringUtil.replace("aba", null, null) = "aba"
	 * StringUtil.replace("aba", "a", null)  = "aba"
	 * StringUtil.replace("aba", "a", "")    = "b"
	 * StringUtil.replace("aba", "a", "z")   = "zbz"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/**
	 * 替换指定的子串，替换指定的次数。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
	 * ，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.replace(null, *, *, *)         = null
	 * StringUtil.replace("", *, *, *)           = ""
	 * StringUtil.replace("abaa", null, null, 1) = "abaa"
	 * StringUtil.replace("abaa", null, null, 1) = "abaa"
	 * StringUtil.replace("abaa", "a", null, 1)  = "abaa"
	 * StringUtil.replace("abaa", "a", "", 1)    = "baa"
	 * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
	 * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
	 * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
	 * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * @param max
	 *            maximum number of values to replace, or <code>-1</code> if no
	 *            maximum
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replace(String text, String repl, String with, int max) {
		if ((text == null) || (repl == null) || (with == null)
				|| (repl.length() == 0) || (max == 0)) {
			return text;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;

		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}

		buf.append(text.substring(start));
		return buf.toString();
	}
	
	public static String delLastComma(String str) {
		if (isEmpty(str)) {
			return str;
		}
		
		if (lastIndexOf(str, "，") == str.length() - 1) {
			str = str.substring(0, str.length() - 1);
		}
		
		return str;
	}
	
	public static String addLastComma(String str) {
		if (isEmpty(str)) {
			return str + "，";
		}
		
		if (lastIndexOf(str, "，") != str.length() - 1) {
			str += "，";
		}
		
		return str;
	}
	
	public static int compareTo(String v1, String v2) {
		if (StringUtil.isBlank(v1)) {
    		v1 = "";
    	}
    	if (StringUtil.isBlank(v2)) {
    		v2 = "";
    	}
    	
    	if (v1.equals(v2)) {
    		return 0;
    	}
    	
    	return v1.compareTo(v2) > 0 ? 1 : -1;
	}
	
	
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
		
				c[i] = (char) (c[i] + 65248);

			
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {

		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}

	/**
	 * 转半角
	 * @param a
	 * @return
	 */
	public static char ToBj(char a) {
		if (a == '\u3000') {
			return ' ';
		} else if (a > '\uFF00' && a < '\uFF5F') {
			return (char) (a - 65248);

		}
		return a;
	}
	/**
	 * 去除括号
	 * @param input
	 * @return
	 */
	public static String removeBreak(String input){
		String temp = "";
		boolean flag = false;
		char[] chars = input.toCharArray();
		for(char c:chars){
			if(c=='（' && !flag){
				flag = true;
			}else if(c=='）' && flag){
				flag = false;
			}
			if(!flag&&c!='）'&&c!='（'){
				temp = temp+c;
			}
		}
		return temp;
	}

	/**
	 * 汉字日期转数字
	 * @param chineseDate 汉字日期
	 * @return
	 */
	public static String chineseDateToDigit(String chineseDate){
		String digit = new String(chineseDate);
		String regex1 = "[二三]十[一二三四五六七八九]";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(digit);
		while (matcher1.find()){
			String oldStr = digit.substring(matcher1.start(),matcher1.end());
			String newStr = new String(oldStr);
			newStr = newStr.replace("一","1");
			newStr = newStr.replace("二","2");
			newStr = newStr.replace("三","3");
			newStr = newStr.replace("四","4");
			newStr = newStr.replace("五","5");
			newStr = newStr.replace("六","6");
			newStr = newStr.replace("七","7");
			newStr = newStr.replace("八","8");
			newStr = newStr.replace("九","9");
			newStr = newStr.replace("十","");
			digit = digit.replace(oldStr,newStr);
		}

		regex1 = "[二三]十";
		pattern1 = Pattern.compile(regex1);
		matcher1 = pattern1.matcher(digit);
		while (matcher1.find()){
			String oldStr = digit.substring(matcher1.start(),matcher1.end());
			String newStr = new String(oldStr);
			newStr = newStr.replace("二十","20");
			newStr = newStr.replace("三十","30");
			digit = digit.replace(oldStr,newStr);
		}

		regex1 = "十[一二三四五六七八九]";
		pattern1 = Pattern.compile(regex1);
		matcher1 = pattern1.matcher(digit);
		while (matcher1.find()){
			String oldStr = digit.substring(matcher1.start(),matcher1.end());
			String newStr = new String(oldStr);
			newStr = newStr.replace("十","1");
			newStr = newStr.replace("一","1");
			newStr = newStr.replace("二","2");
			newStr = newStr.replace("三","3");
			newStr = newStr.replace("四","4");
			newStr = newStr.replace("五","5");
			newStr = newStr.replace("六","6");
			newStr = newStr.replace("七","7");
			newStr = newStr.replace("八","8");
			newStr = newStr.replace("九","9");
			digit = digit.replace(oldStr,newStr);
		}


		digit = digit.replace("一","1");
		digit = digit.replace("二","2");
		digit = digit.replace("三","3");
		digit = digit.replace("四","4");
		digit = digit.replace("五","5");
		digit = digit.replace("六","6");
		digit = digit.replace("七","7");
		digit = digit.replace("八","8");
		digit = digit.replace("九","9");
		digit = digit.replace("十","10");
		digit = digit.replace("〇","0");
		digit = digit.replace("年","/");
		digit = digit.replace("月","/");
		digit = digit.replace("日","");
		return digit;
	}

	/**
	 * 去除重复的值，即不允许list(i)是str的子集，也不允许str是list(i)的子集
	 *
	 * @param list
	 * @param str
	 * @return
	 */
	public static List<String> noDuplicate(List<String> list, String str) {
		Iterator iterator = list.iterator();
		int flag = 1;
		while (iterator.hasNext()) {
			String listi = (String) iterator.next();
			if (str.contains(listi) || listi.contains(str)) {
				flag = 0;
			}
		}
		if (flag == 1 && str != "" && str != null) {
			list.add(str);
		}

		return list;
	}

	/**
	 * 将全角空格转换为半角空格
	 * @param str
	 * @return
	 */
	public static String convertEntireSpace(String str){
		return str.replaceAll("\u3000"," ");
	}


//	public static boolean inNumber(String input){
//		if(isBlank(input)){
//			return false;
//		}else{
//			
//		}
//	}
}
