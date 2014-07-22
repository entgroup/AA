package cn.test.common;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpUtils {

	/**
	 * ��ȡ����ͼƬ
	 * 
	 * @param url
	 * @return Bitmap
	 */
	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setConnectTimeout(0);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * ��÷������˵�����,��InputStream��ʽ����
	 * 
	 * @param URL_PATH
	 * @return InputStream
	 */
	public static InputStream getInputStream(String URL_PATH) {
		InputStream inputStream = null;
		HttpURLConnection httpURLConnection = null;
		try {
			URL url = new URL(URL_PATH);
			if (url != null) {
				httpURLConnection = (HttpURLConnection) url.openConnection();
				// ������������ĳ�ʱʱ��
				httpURLConnection.setConnectTimeout(8000);
				httpURLConnection.setDoInput(true);
				// ��ʾ���ñ���http����ʹ��GET��ʽ����
				httpURLConnection.setRequestMethod("GET");
				int responseCode = httpURLConnection.getResponseCode();
				if (responseCode == 200) {
					// �ӷ��������һ��������
					inputStream = httpURLConnection.getInputStream();
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 * ��һ��������ת����ָ��������ַ���
	 * 
	 * @param inputStream
	 * @param encode
	 * @return String
	 */
	private static String changeInputStream(InputStream inputStream,
			String encode) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int len = 0;
		String result = "";
		if (inputStream != null) {
			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��Get��ʽ��ȡָ��url��������
	 * 
	 * @param URL_PATH
	 * @param encode
	 * @return String
	 */
	public static String GetCnt(String URL_PATH, String encode) {
		InputStream inputStream = getInputStream(URL_PATH);
		return changeInputStream(inputStream, encode);
	}

	/**
	 * ��Post��ʽ��ȡָ��url��������
	 * 
	 * @param URL_PATH
	 * @param params
	 * @param encode
	 * @return String
	 */
	public static String GetCnt(String URL_PATH, Map<String, String> params,
			String encode) {
		String res = "";
		URL url;
		try {
			url = new URL(URL_PATH);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			res = "";
			return res;
		}
		// ��ΪStringBuffer��ʼ�����ַ���
		StringBuffer buffer = new StringBuffer();
		try {
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					// ���ת�����
					buffer.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(), encode))
							.append("&");
				}
				buffer.deleteCharAt(buffer.length() - 1);// ɾ��������һ��&
			}
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setReadTimeout(8000);
			urlConnection.setConnectTimeout(8000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);// ��ʾ�ӷ�������ȡ����
			urlConnection.setDoOutput(true);// ��ʾ�������д����
			// ����ϴ���Ϣ���ֽڴ�С�Լ�����
			byte[] mydata = buffer.toString().getBytes();
			// ��ʾ������������������ı�����
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length",
					String.valueOf(mydata.length));
			// ��������,��������������
			OutputStream outputStream = urlConnection.getOutputStream();
			outputStream.write(mydata, 0, mydata.length);
			outputStream.close();
			// ��÷�������Ӧ�Ľ����״̬��
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				res = changeInputStream(urlConnection.getInputStream(), encode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			res = "";
		} catch (IOException e) {
			e.printStackTrace();
			res = "";
		}
		return res;
	}

	/**
	 * ��Post��ʽ��ȡָ��url��������(����ҳ��)
	 * 
	 * @param URL_PATH
	 * @param params
	 * @param encode
	 * @return String
	 */
	public static String GetCnt2(String URL_PATH, Map<String, String> params,
			String encode) {
		String res = "";
		URL url;
		try {
			url = new URL(URL_PATH);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			res = "";
			return res;
		}
		// ��ΪStringBuffer��ʼ�����ַ���
		StringBuffer buffer = new StringBuffer();
		try {
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					// ���ת�����
					buffer.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(), "utf-8"))
							.append("&");
				}
				buffer.deleteCharAt(buffer.length() - 1);// ɾ��������һ��&
			}
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(8000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);// ��ʾ�ӷ�������ȡ����
			urlConnection.setDoOutput(true);// ��ʾ�������д����
			// ����ϴ���Ϣ���ֽڴ�С�Լ�����
			byte[] mydata = buffer.toString().getBytes();
			// ��ʾ������������������ı�����
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length",
					String.valueOf(mydata.length));
			// ��������,��������������
			OutputStream outputStream = urlConnection.getOutputStream();
			outputStream.write(mydata, 0, mydata.length);
			outputStream.close();
			// ��÷�������Ӧ�Ľ����״̬��
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				res = changeInputStream(urlConnection.getInputStream(), encode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			res = "";
		} catch (IOException e) {
			e.printStackTrace();
			res = "";
		}
		return res;
	}
}
