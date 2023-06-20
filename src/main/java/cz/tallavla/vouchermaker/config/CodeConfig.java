package cz.tallavla.vouchermaker.config;

import java.util.Arrays;

public class CodeConfig {

	public final static char PATTERN_PLACEHOLDER = '#';

		public static final String ALPHABETIC   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

//		public static final String ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		public static final String NUMBERS      = "0123456789";


	private final String charset;
	private final String prefix;
	private final String postfix;
	private final String pattern;

	public CodeConfig(String prefix, String postfix, String pattern) {

		if (pattern == null) {
			char[] chars = new char[16];
			Arrays.fill(chars, PATTERN_PLACEHOLDER);
			pattern = new String(chars);
		}

		this.charset = ALPHABETIC;
		this.prefix = prefix;
		this.postfix = postfix;
		this.pattern = pattern;
	}

	public String getCharset() {
		return charset;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getPostfix() {
		return postfix;
	}

	public String getPattern() {
		return pattern;
	}

	@Override
	public String toString() {
		return "CodeConfig ["
				+ "charset=" + charset + ", "
				+ "prefix="  + prefix  + ", "
				+ "postfix=" + postfix + ", "
				+ "pattern=" + pattern + "]";
	}
}

