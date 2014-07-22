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

		// 初始化页面控件
		initPage();

		// 加载页面
		flg = 1;
		Bind();

		// 注册事件
		RegisterEvent();
	}

	/**
	 * 加载页面
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
					if (msg.what == 1) {// 个人资料
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
							Toast.makeText(getApplicationContext(), "数据加载出错！",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 2) {// 文章分类
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
							Toast.makeText(getApplicationContext(), "数据加载出错！",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 3) {// 文章存档
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
							Toast.makeText(getApplicationContext(), "数据加载出错！",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 4) {// 文章列表
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
							Toast.makeText(getApplicationContext(), "数据加载出错！",
									Toast.LENGTH_LONG).show();
						}
					} else if (msg.what == 5) {// 文章詳情
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
							Toast.makeText(getApplicationContext(), "数据加载出错！",
									Toast.LENGTH_LONG).show();
						}
					}
				}

				/**
				 * 获取分类（文章分类、文章存档）
				 * 
				 * @param cnt
				 */
				private void CollClass(String cnt) {
					// 获取个人简介
					String str_desc = "";
					try {
						str_desc = cnt.substring(cnt.indexOf("panel_Profile"));
						str_desc = str_desc.substring(0,
								str_desc.indexOf("panel_Search"));
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "数据截取出错！",
								Toast.LENGTH_LONG).show();
					}

					String addr = "";// 图片地址
					String uname = "";// 用户名
					String fw_num = "";// 访问次数
					String jf_num = "";// 积分
					String pm_num = "";// 排名
					String yc_num = "";// 原创
					String zz_num = "";// 转载
					String yw_num = "";// 译文
					String pl_num = "";// 评论

					StringBuilder res_desc = new StringBuilder("");
					Pattern p0 = Pattern.compile("src=\\\"(.*?)\\\"");
					Matcher m0 = p0.matcher(str_desc);
					if (m0.find()) {
						addr = m0.group(1);// 图片地址
						res_desc.append("网址：" + addr + "\n");
					}

					p0 = Pattern
							.compile("user_name\\\" target=\\\"_blank\\\">(.*?)</a>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						uname = m0.group(1);// 用户名
						res_desc.append("用户名：" + uname + "\n");
					}

					p0 = Pattern.compile("访问：<span>(.*?)次</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						fw_num = m0.group(1);// 访问次数
						res_desc.append("访问次数：" + fw_num + "\n");
					}

					p0 = Pattern.compile("积分：<span>(.*?)分</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						jf_num = m0.group(1);// 积分
						res_desc.append("积分：" + jf_num + "\n");
					}

					p0 = Pattern.compile("排名：<span>第(.*?)名</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						pm_num = m0.group(1);// 排名
						res_desc.append("排名：" + pm_num + "\n");
					}

					p0 = Pattern.compile("原创：<span>(.*?)篇</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						yc_num = m0.group(1);// 原创
						res_desc.append("原创：" + yc_num + "\n");
					}

					p0 = Pattern.compile("转载：<span>(.*?)篇</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						zz_num = m0.group(1);// 转载
						res_desc.append("转载：" + zz_num + "\n");
					}

					p0 = Pattern.compile("译文：<span>(.*?)篇</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						yw_num = m0.group(1);// 译文
						res_desc.append("译文：" + yw_num + "\n");
					}

					p0 = Pattern.compile("评论：<span>(.*?)条</span>");
					m0 = p0.matcher(str_desc);
					if (m0.find()) {
						pl_num = m0.group(1);// 评论
						res_desc.append("评论：" + pl_num + "\n");
					}
					Toast.makeText(getApplicationContext(), res_desc,
							Toast.LENGTH_LONG).show();

					// 获取分类
					String str_cat = "";
					try {
						str_cat = cnt.substring(cnt.indexOf("panel_Category"));
						str_cat = str_cat.substring(0,
								str_cat.indexOf("archive_list"));
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "数据截取出错！",
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
						// csdn分类编号
						Pattern p1 = Pattern.compile("category/(.*?)\\\"");
						Matcher m1 = p1.matcher(s);
						if (m1.find()) {
							catID = m1.group(1);
						}

						// 分类名
						p1 = Pattern.compile(">(.*?)<");
						m1 = p1.matcher(s);
						if (m1.find()) {
							catName = m1.group(1);
						}

						// 文章数
						p1 = Pattern.compile("<span>\\((.*?)\\)</span>");
						m1 = p1.matcher(s);
						if (m1.find()) {
							num = m1.group(1);
						}

						res.append(catName + catID + "(" + num + ")" + "\n");
					}

					res.append("总文章数：" + total);
					Toast.makeText(getApplicationContext(), res,
							Toast.LENGTH_LONG).show();
				}

			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册事件
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
				// 重置加载效果
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// 个人资料
				flg = 1;
				Bind();
			}
		});
		btn_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 重置加载效果
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// 文章分类
				flg = 2;
				Bind();
			}
		});
		btn_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 重置加载效果
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// 文章存档
				flg = 3;
				Bind();
			}
		});
		btn_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 重置加载效果
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// 文章列表
				flg = 4;
				Bind();
			}
		});
		btn_5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 重置加载效果
				webviewLoading.setVisibility(View.VISIBLE);
				myWebView.setVisibility(View.GONE);
				myWebView.loadData("", mimeType, encoding);

				// 文章详情
				flg = 5;
				Bind();
			}
		});
	}

	/**
	 * 初始化页面控件
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
	 * loadUrl 显示网络网页
	 */
	private void webHtml() {
		try {
			myWebView.loadUrl("http://news.entgroup.cn/movie/2420919.shtml");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadUrl 显示网络图片
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
	 * loadData 中文显示(不对空格做处理)
	 */
	private void localHtmlZh() {
		try {
			String cnt = "测试含有中文的Html 数据";
			// utf-8 编码处理(在SDK1.5 模拟器和真实设备上都将出现乱码,SDK1.6 上能正常显示)
			// 不对空格做处理
			myWebView.loadData(URLEncoder.encode(cnt, encoding), mimeType,
					encoding);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadData 中文显示(空格的处理)
	 */
	private void localHtmlBlankSpace() {
		try {
			String data = " 测试含有空格的Html 数据";
			// 对空格做处理(在SDK1.5 版本中)
			myWebView.loadData(
					URLEncoder.encode(data, encoding).replaceAll("\\+", " "),
					mimeType, encoding);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadUrl 显示本地图片文件
	 */
	private void localImage() {
		try {
			// 本地文件处理(如果文件名中有空格需要用+来替代)
			myWebView.loadUrl("file:///android_asset/WebView_Test/banner.jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadUrl 显示本地网页文件
	 */
	private void localHtml() {
		try {
			// 本地文件处理(如果文件名中有空格需要用+来替代)
			myWebView.loadUrl("file:///android_asset/WebView_Test/test2.html");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * loadDataWithBaseURL 显示本地图片和文字混合的Html内容
	 */
	private void localHtmlImage() {
		try {
			String data = "测试本地图片和文字混合显示,这是APK 里的图片";
			// SDK1.5 本地文件处理(不能显示图片)
			// WebView.loadData(URLEncoder.encode(data, encoding), mimeType,
			// encoding);
			// SDK1.6 及以后版本
			// WebView.loadData(data, mimeType, encoding);
			// 本地文件处理(能显示图片)
			myWebView.loadDataWithBaseURL("about：blank", data, mimeType,
					encoding, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
