package cz.tallavla.vouchermaker.utils;

import cz.tallavla.vouchermaker.config.CodeConfig;

import java.util.Random;


public final class VoucherCodeGenerator {

	private static final Random RND = new Random(System.currentTimeMillis());

	/**
	 * Generates a random code according to given config.
	 *
	 * @param config
	 *
	 * @return Generated code.
	 */
	public static String generateCode(CodeConfig config) {
		StringBuilder sb = new StringBuilder();
		char[] chars = config.getCharset().toCharArray();
		char[] pattern = config.getPattern().toCharArray();

		if (config.getPrefix() != null) {
			sb.append(config.getPrefix());
		}

		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == CodeConfig.PATTERN_PLACEHOLDER) {
				sb.append(chars[RND.nextInt(chars.length)]);
			} else {
				sb.append(pattern[i]);
			}
		}

		if (config.getPostfix() != null) {
			sb.append(config.getPostfix());
		}

		return sb.toString();
	}
}
