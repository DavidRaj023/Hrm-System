package com.infinira.hr.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infinira.hr.model.Employee;

public class RestClient {

	public static int post(String postUrl, String postData) {
		HttpURLConnection conn = null;
		int output;
		try {
			conn = getConnectionForPostPut(postUrl, "POST");
			OutputStream os = conn.getOutputStream();
			os.write(postData.getBytes());
			os.flush();
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201 && conn.getResponseCode() != 203) {
				throw new RuntimeException(ERROR_HTTP + conn.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			output = Integer.parseInt(reader.readLine());

		} catch (Throwable th) {
			throw new RuntimeException(ERROR_POST, th);
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Throwable th) {
				}
			}
		}
		return output;
	}

	public static String get(String getUrl) {
		HttpURLConnection conn = null;
		String output = null;
		try {
			conn = getConnection(getUrl, "GET");
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 204) {
				throw new RuntimeException(ERROR_HTTP + conn.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			output = reader.readLine();
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_GET, th);
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Throwable th) {
				}
			}
		}
		return output;
	}

	public static int put(String putUrl, String putData) {
		HttpURLConnection conn = null;
		int output;
		try {
			conn = getConnectionForPostPut(putUrl, "PUT");
			OutputStream os = conn.getOutputStream();
			os.write(putData.getBytes());
			os.flush();
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201 && conn.getResponseCode() != 203) {
				throw new RuntimeException(ERROR_HTTP + conn.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			output = reader.read();
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_PUT, th);
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Throwable th) {
				}
			}
		}
		return output;
	}

	public static int delete(String deleteUrl) {
		HttpURLConnection conn = null;
		int output;
		try {
			conn = getConnection(deleteUrl, "DELETE");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException(ERROR_HTTP + conn.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			output = reader.read();
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_DELETE, th);
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Throwable th) {
				}
			}
		}
		return output;
	}

	public static List<Object> getAll(String getAllUrl) {
		HttpURLConnection conn = null;
		List<Object> ls = new ArrayList<Object>();
		try {
			conn = getConnection(getAllUrl, "GET");
			if (conn.getResponseCode() != 200 && conn.getResponseCode() != 204) {
				throw new RuntimeException(ERROR_HTTP + conn.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while (reader.readLine() != null) {
				ls.add(reader.readLine());
			}
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_GET, th);
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Throwable th) {
				}
			}
		}
		return ls;
	}

	public static void validate(String paramName, int paramValue) {
		if (paramValue <= 0) {
			throw new RuntimeException(MessageFormat.format(INVALID_ID, paramName, paramValue));
		}

	}

	public static String convertPojoToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (Throwable th) {
			throw new RuntimeException(FAILED_POJO_JSON, th);
		}
	}

	public static Employee convertJsonToPojo(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Employee.class);
		} catch (Throwable th) {
			throw new RuntimeException(FAILED_JSON_POJO, th);
		}
	}

	public static HttpURLConnection getConnection(String link, String methodType) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
			conn.setRequestMethod(methodType);
			conn.setRequestProperty(ACCEPT, APPLICATOIN_JSON);
			return conn;
		} catch (MalformedURLException ex) {
			throw new RuntimeException(MALFORMED_URL + link);
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_CONNECT, th);
		}
	}

	public static HttpURLConnection getConnectionForPostPut(String link, String methodType) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(methodType);
			conn.setRequestProperty(CONTENT, APPLICATOIN_JSON);
			return conn;
		} catch (MalformedURLException ex) {
			throw new RuntimeException(MALFORMED_URL + link);
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_CONNECT, th);
		}
	}

	private static final String ACCEPT = "Accept";
	private static final String CONTENT = "Content-Type";
	private static final String APPLICATOIN_JSON = "application/json";
	private static final String ERROR_HTTP = "Failed : HTTP error code : ";
	private static final String ERROR_POST = "Failed to create an employee";
	private static final String ERROR_GET = "Failed to get a record";
	private static final String ERROR_CONNECT = "Failed to connect";
	private static final String ERROR_DELETE = "Failed to delete a record";
	private static final String ERROR_PUT = "Failed to update an employee";
	private static final String INVALID_ID = "Invalid {0}: {1}";
	private static final String MALFORMED_URL = "Failed to get the response with malformed URL:";
	private static final String FAILED_JSON_POJO = "Failed to convert json to POJO";
	private static final String FAILED_POJO_JSON = "Failed to convert POJO to JSON";

}
