package com.infinira.hr.util;

import java.io.FileInputStream;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class HrUtil {

	private static final String JSON_FILE = "config/hr.json";
	private static final String READ_FAILED = "Failed to read json file";
	private static final String JSON_EMPTY = "Json file cannot be empty";
	public static String hrUrl;
	static Map<String, Object> jsonMap = null;

	static {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(JSON_FILE);
			jsonMap = new ObjectMapper().readValue(stream, Map.class);
			if (jsonMap.isEmpty()) {
				throw new RuntimeException(JSON_EMPTY);
			}
			hrUrl = (String) jsonMap.get("HR_URL");
		} catch (Throwable th) {
			throw new RuntimeException(READ_FAILED, th);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Throwable th) {
				}
			}
		}
	}
}
