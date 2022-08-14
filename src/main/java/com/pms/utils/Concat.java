package com.pms.utils;

import java.util.List;

public class Concat {
	public static String concatString(String[] s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			if (i < s.length - 1)
				sb.append(s[i] + ",");
			else
				sb.append(s[i]);
		}
		return sb.toString();
	}

	public static String concatString(List<String> s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.size(); i++) {
			if (i < s.size() - 1)
				sb.append(s.get(i) + ",");
			else
				sb.append(s.get(i));
		}
		return sb.toString();
	}

}
