package com.example.ui;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import cn.test.common.CommonUtil;
import com.example.aa.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class WebViewAc extends Activity {

	WebView myWebView;
	final String mimeType = "text/html; charset=UTF-8";
	final String encoding = "utf-8";

	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;

	Button btn_1;
	Button btn_2;
	Button btn_3;
	Button btn_4;
	Button btn_5;

	private Handler handler = null;
	private int screen_width;
	View webviewLoading;

	int flg = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_webview);

		// ��ʼ��ҳ��ؼ�
		initPage();

		// ����ҳ��
		flg = 1;
		Bind();

		// ע���¼�
		RegisterEvent();
	}

	/**
	 * ����ҳ��
	 */
	private void Bind() {
		Runnable runnable = new Runnable() {
			public void run() {
				switch (flg) {
				case 1: {
					Document doc = null;
					try {
						doc = Jsoup.connect(
								"http://blog.csdn.net/dongxiaohui2008").get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					handler.sendMessage(handler.obtainMessage(1, doc));
					break;
				}
				case 2: {
					Document doc = null;
					try {
						doc = Jsoup.connect(
								"http://blog.csdn.net/dongxiaohui2008").get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					handler.sendMessage(handler.obtainMessage(2, doc));
					break;
				}
				case 3: {
					Document doc = null;
					try {
						doc = Jsoup.connect(
								"http://blog.csdn.net/dongxiaohui2008").get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					handler.sendMessage(handler.obtainMessage(3, doc));
					break;
				}
				case 4: {
					Document doc = null;
					try {
						doc = Jsoup.connect(
								"http://blog.csdn.net/dongxiaohui2008").get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					handler.sendMessage(handler.obtainMessage(4, doc));
					break;
				}
				case 5: {
					Document doc = null;
					try {
						doc = Jsoup
								.connect(
										"http://blog.csdn.net/dongxiaohui2008/article/details/9224623")
								.get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					handler.sendMessage(handler.obtainMessage(5, doc));
					break;
				}
				}
			}
		};

		try {
			new Thread(runnable).start();
			handler = new Handler() {
				public void handleMessage(Message msg) {
					if (msg.what == 1) {// ��������
						@SuppressWarnings("unchecked")
						Document doc = (Document) msg.obj;
						Element ele = doc.getElementById("panel_Profile");
						String cnt = ele.html();

						try {
							webviewLoading.setVisibility(View.GONE);
							myWebView.setVisibility(View.VISIBLE);
							myWebView.loadDataWithBaseURL(null, cnt,
									"text/html", "utf-8", null);
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "���ݼ��س���",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 2) {// ���·���
						@SuppressWarnings("unchecked")
						Document doc = (Document) msg.obj;
						Element ele = doc.getElementById("panel_Category");
						String cnt = ele.html();

						try {
							webviewLoading.setVisibility(View.GONE);
							myWebView.setVisibility(View.VISIBLE);
							myWebView.loadDataWithBaseURL(null, cnt,
									"text/html", "utf-8", null);
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "���ݼ��س���",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 3) {// ���´浵
						@SuppressWarnings("unchecked")
						Document doc = (Document) msg.obj;
						Element ele = doc.getElementById("panel_Archive");
						String cnt = ele.html();

						try {
							webviewLoading.setVisibility(View.GONE);
							myWebView.setVisibility(View.VISIBLE);
							myWebView.loadDataWithBaseURL(null, cnt,
									"text/html", "utf-8", null);
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "���ݼ��س���",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 4) {// �����б�
						@SuppressWarnings("unchecked")
						Document doc = (Document) msg.obj;
						Element ele = doc.getElementById("article_list");
						String cnt = ele.html();

						// Elements ele_itms = ele.select("div.list_item");
						// Element itm = ele_itms.get(14);

						try {
							cnt = cnt.replace(
									"<img",
									"<img width='"
											+ CommonUtil.px2dip(
													getApplicationContext(),
													screen_width) + "'");
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
						try {
							webviewLoading.setVisibility(View.GONE);
							myWebView.setVisibility(View.VISIBLE);
							myWebView.loadDataWithBaseURL(null, cnt,
									"text/html", "utf-8", null);
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "���ݼ��س���",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 5) {// ����Ԕ��
						@SuppressWarnings("unchecked")
						Document doc = (Document) msg.obj;
						Element ele = doc.getElementById("article_details");

						String cnt = ele.html();
						try {
							cnt = cnt.replace(
									"<img",
									"<img width='"
											+ CommonUtil.px2dip(
													getApplicationContext(),
													screen_width) + "'");
						} catch (NullPointerException e) {
							e.printStackTrace();
						}

						try {
							webviewLoading.setVisibility(View.GONE);
							myWebView.setVisibility(View.VISIBLE);

							myWebView.loadDataWithBaseURL(null, cnt,
									"text/html", "utf-8", null);
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "���ݼ��س���",
									Toast.LENGTH_LONG).show();
						}
					}
				}

				/**
				 * ��ȡ���ࣨ���·��ࡢ���´浵��
				 * 
				 * @param cnt
				 */
				private void CollClass(String cnt) {
					// ��ȡ���˼��
					String str_desc = "";
					try {
						str_desc = cnt.substring(cnt.indexOf("panel_Profile"));
						str_desc = str_desc.substring(0,
								str_desc.indexOf("panel_Search"));
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "���ݽ�ȡ����",
								Toast.LENGTH_LONG).show();
					}

					String addr = "";// ͼƬ��ַ
					String uname = "";// �û���
					String fw_num = "";// ���ʴ���
					String jf_num = "";// ����
					String pm_num = "";// ����
					String yc_num = "";// ԭ��
					String zz_num = "";// ת��
					String yw_num = "";// ����
					String pl_num = "";// ����

					StringBuilder res_desc = new StringBuilder("");
					Pattern p0 = Pattern.compile("src=\\\"(.*?)\\\"");
					Matcher m0 = p0.matcher(str_desc);
					if (m0.find()) {
						addr = m0.group(1);// ͼƬ��ַ
						res_desc.append("��ַ��" + addr + "\n");
					}

					p0 = Pattern
							.compile("user_name\\\" target=\\\"_blank\\\">(.*?)</a>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						uname = m0.group(1);// �û���
						res_desc.append("�û�����" + uname + "\n");
					}

					p0 = Pattern.compile("���ʣ�<span>(.*?)��</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						fw_num = m0.group(1);// ���ʴ���
						res_desc.append("���ʴ�����" + fw_num + "\n");
					}

					p0 = Pattern.compile("���֣�<span>(.*?)��</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						jf_num = m0.group(1);// ����
						res_desc.append("���֣�" + jf_num + "\n");
					}

					p0 = Pattern.compile("������<span>��(.*?)��</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						pm_num = m0.group(1);// ����
						res_desc.append("������" + pm_num + "\n");
					}

					p0 = Pattern.compile("ԭ����<span>(.*?)ƪ</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						yc_num = m0.group(1);// ԭ��
						res_desc.append("ԭ����" + yc_num + "\n");
					}

					p0 = Pattern.compile("ת�أ�<span>(.*?)ƪ</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						zz_num = m0.group(1);// ת��
						res_desc.append("ת�أ�" + zz_num + "\n");
					}

					p0 = Pattern.compile("���ģ�<span>(.*?)ƪ</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						yw_num = m0.group(1);// ����
						res_desc.append("���ģ�" + yw_num + "\n");
					}

					p0 = Pattern.compile("���ۣ�<span>(.*?)��</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						pl_num = m0.group(1);// ����
						res_desc.append("���ۣ�" + pl_num + "\n");
					}
					Toast.makeText(getApplicationContext(), res_desc,
							Toast.LENGTH_LONG).show();

					// ��ȡ����
					String str_cat = "";
					try {
						str_cat = cnt.substring(cnt.indexOf("panel_Category"));
						str_cat = str_cat.substring(0,
								str_cat.indexOf("archive_list"));
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "���ݽ�ȡ����",
								Toast.LENGTH_LONG).show();
					}

					str_cat = str_cat.replace("\n", "").replace("\r", "");
					Pattern p = Pattern.compile("<li>(.*?)</li>");
					Matcher m = p.matcher(str_cat);
					ArrayList<String> arr = new ArrayList<String>();
					while (m.find()) {
						arr.add(m.group(1));
					}

					StringBuilder res = new StringBuilder("");
					int total = 0;
					for (String s : arr) {
						String catName = "";
						String num = "";
						String catID = "";
						// csdn������
						Pattern p1 = Pattern.compile("category/(.*?)\\\"");
						Matcher m1 = p1.matcher(s);
						if (m1.find()) {
							catID = m1.group(1);
						}

						// ������
						p1 = Pattern.compile(">(.*?)<");
						m1 = p1.matcher(s);
						if (m1.find()) {
							catName = m1.group(1);
						}

						// ������
						p1 = Pattern.compile("<span>\\((.*?)\\)</span>");
						m1 = p1.matcher(s);
						if (m1.find()) {
							num = m1.group(1);
						}

						res.append(catName + catID + "(" + num + ")" + "\n");
					}

					res.append("����������" + total);
					Toast.makeText(getApplicationContext(), res,
							Toast.LENGTH_LONG).show();
				}

			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ע���¼�
	 */
	private void RegisterEvent() {
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				webHtml();
			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				webImage();
			}
		});

		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				localHtmlZh();
			}
		});

		btn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				localHtmlBlankSpace();
			}
		});

		btn5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				localHtml();
			}
		});

		btn6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				localImage();
			}
		});

		btn7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				localHtmlImage();
			}
		});

		btn_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ���ü���Ч��
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// ��������
				flg = 1;
				Bind();
			}
		});
		btn_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ���ü���Ч��
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// ���·���
				flg = 2;
				Bind();
			}
		});
		btn_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ���ü���Ч��
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// ���´浵
				flg = 3;
				Bind();
			}
		});
		btn_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ���ü���Ч��
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// �����б�
				flg = 4;
				Bind();
			}
		});
		btn_5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ���ü���Ч��
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// ��������
				flg = 5;
				Bind();
			}
		});
	}

	/**
	 * ��ʼ��ҳ��ؼ�
	 */
	private void initPage() {
		myWebView = (WebView) findViewById(R.id.mywebview1);
		myWebView.getSettings().setJavaScriptEnabled(true);

		btn1 = (Button) this.findViewById(R.id.btn1);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		btn4 = (Button) this.findViewById(R.id.btn4);
		btn5 = (Button) this.findViewById(R.id.btn5);
		btn6 = (Button) this.findViewById(R.id.btn6);
		btn7 = (Button) this.findViewById(R.id.btn7);

		btn_1 = (Button) this.findViewById(R.id.btn_1);
		btn_2 = (Button) this.findViewById(R.id.btn_2);
		btn_3 = (Button) this.findViewById(R.id.btn_3);
		btn_4 = (Button) this.findViewById(R.id.btn_4);
		btn_5 = (Button) this.findViewById(R.id.btn_5);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screen_width = dm.widthPixels;

		webviewLoading = this.findViewById(R.id.webview_loading);
	}

	/**
	 * loadUrl ��ʾ������ҳ
	 */
	private void webHtml() {
		try {
			myWebView.loadUrl("http://news.entgroup.cn/movie/2420919.shtml");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadUrl ��ʾ����ͼƬ
	 */
	private void webImage() {
		try {
			myWebView
					.loadUrl("http://img1.gtimg.com/2014/pics/hv1/216/191/1633/106234746.jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadData ������ʾ(���Կո�������)
	 */
	private void localHtmlZh() {
		try {
			String cnt = "���Ժ������ĵ�Html ����";
			// utf-8 ���봦��(��SDK1.5 ģ��������ʵ�豸�϶�����������,SDK1.6 ����������ʾ)
			// ���Կո�������
			myWebView.loadData(URLEncoder.encode(cnt, encoding), mimeType,
					encoding);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadData ������ʾ(�ո�Ĵ���)
	 */
	private void localHtmlBlankSpace() {
		try {
			String data = " ���Ժ��пո��Html ����";
			// �Կո�������(��SDK1.5 �汾��)
			myWebView.loadData(
					URLEncoder.encode(data, encoding).replaceAll("\\+", " "),
					mimeType, encoding);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadUrl ��ʾ����ͼƬ�ļ�
	 */
	private void localImage() {
		try {
			// �����ļ�����(����ļ������пո���Ҫ��+�����)
			myWebView.loadUrl("file:///android_asset/WebView_Test/banner.jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadUrl ��ʾ������ҳ�ļ�
	 */
	private void localHtml() {
		try {
			// �����ļ�����(����ļ������пո���Ҫ��+�����)
			myWebView.loadUrl("file:///android_asset/WebView_Test/test2.html");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadDataWithBaseURL ��ʾ����ͼƬ�����ֻ�ϵ�Html����
	 */
	private void localHtmlImage() {
		try {
			String data = "���Ա���ͼƬ�����ֻ����ʾ,����APK ���ͼƬ";
			// SDK1.5 �����ļ�����(������ʾͼƬ)
			// WebView.loadData(URLEncoder.encode(data, encoding), mimeType,
			// encoding);
			// SDK1.6 ���Ժ�汾
			// WebView.loadData(data, mimeType, encoding);
			// �����ļ�����(����ʾͼƬ)
			myWebView.loadDataWithBaseURL("about��blank", data, mimeType,
					encoding, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
