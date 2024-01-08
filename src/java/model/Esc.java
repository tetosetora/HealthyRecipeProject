package model;

import org.apache.commons.text.StringEscapeUtils;

// セキュリティ対策。html上での変数出力の際に、エスケープ処理を行う。
public class Esc { // Escapeの略
	public static String html(String text) {
		return StringEscapeUtils.escapeHtml4(text);
	}
}
