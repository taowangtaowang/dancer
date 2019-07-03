(function(win) {
	var CONSTANTS = {
		GUIDE_BASE_URL_NO_PORT: "http://www.sczwfw.gov.cn", //图片地址基本url
		GUIDE_BASE_URL: "http://www.sczwfw.gov.cn:84", //办事指南基础地址
		ZZ_MANAGE_URL:"http://10.6.10.108:8080",//绵阳后台管理系统基础地址
		ZZ_APPROVAL_URL:"http://10.6.10.108:8088",//绵阳zzaproval
		ZWFW_BASE_URL: "http://rzsc.sczwfw.gov.cn", //政务服务大厅接口
		SOCKET_URL:"ws://10.6.10.108:10015/Evaluation/ws" ,//websoket长连接地址
		
		
		FLAG_REFRESH:"refresh",//长连接推送标识（刷新用户信息）
		FLAG_APPLAUSE:"applause"//长连接推送标识（叫号）
	};

	var CommonUtil = {

		log: function(obj) {
			for(var key in obj) {
				console.log(key + ":" + obj[key])
			}
		},

		debug: function(tag, txt) {
			console.log(tag + ":" + txt);
		},
		/**
		 * ajax请求
		 */
		ajaxPost: function(url, params, successFunc, errorFunc, beforeFunc, completeFunc, headers) {
			//console.log(url);
			mui.ajax(url, {
				data: params,
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				timeout: 10000, //超时时间设置为10秒；
				cache: false,
				headers: (headers || {}),
				beforeSend: function() {
					if(plus.networkinfo.getCurrentType() == plus.networkinfo.CONNECTION_NONE) {
						mui.toast("网络异常，请检查网络设置！");
						return false;
					}
					plus.nativeUI.showWaiting("加载中...");
					if(beforeFunc) {
						beforeFunc();
					}
				},
				complete: function() {
					plus.nativeUI.closeWaiting();
					if(completeFunc) {
						completeFunc();
					}
				},
				success: function(result) {
					console.info("success", result);
					//服务器返回响应，根据响应结果，分析是否登录成功；
					if(successFunc) {
						successFunc(result)
					}

				},
				error: function(xhr, type, errorThrown) {
					//console.info(errorThrown);
					//console.log(type)
					if(errorFunc) {
						errorFunc();
					}

					if(type == "timeout") {
						mui.toast("连接超时，请检查网络");
					} else if(type == "parsererror") {
						mui.toast("数据解析出错");
					} else {
						mui.toast("请求出现异常");
					}
				}
			});
		},
		ajaxGet: function(url, params, successFunc, errorFunc, beforeFunc, completeFunc) {
			//console.log(url);
			mui.ajax(url, {
				data: params,
				dataType: 'json', //服务器返回json格式数据
				type: 'get', //HTTP请求类型
				timeout: 10000, //超时时间设置为10秒；
				cache: false,
				beforeSend: function() {
					if(plus.networkinfo.getCurrentType() == plus.networkinfo.CONNECTION_NONE) {
						mui.toast("网络异常，请检查网络设置！");
						return false;
					}
					plus.nativeUI.showWaiting("加载中...");
					if(beforeFunc) {
						beforeFunc();
					}
				},
				complete: function() {
					plus.nativeUI.closeWaiting();
					if(completeFunc) {
						completeFunc();
					}
				},
				success: function(result) {
					//console.info("success", result);
					//服务器返回响应，根据响应结果，分析是否登录成功；
					if(successFunc) {
						successFunc(result)
					}

				},
				error: function(xhr, type, errorThrown) {
					//console.info(errorThrown);
					//console.log(type)
					if(errorFunc) {
						errorFunc();
					}

					if(type == "timeout") {
						mui.toast("连接超时，请检查网络");
					} else if(type == "parsererror") {
						mui.toast("数据解析出错");
					} else {
						mui.toast("请求出现异常");
					}
				}
			});
		},

		/**
		 * ajax请求
		 */
		ajaxPostNoProgress: function(url, params, successFunc, errorFunc, beforeFunc, completeFunc, headers) {
			//console.log(url);
			mui.ajax(url, {
				data: params,
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				timeout: 30000, //超时时间设置为10秒；
				cache: false,
				headers: (headers || {}),
				beforeSend: function() {
					if(plus.networkinfo.getCurrentType() == plus.networkinfo.CONNECTION_NONE) {
						mui.toast("网络异常，请检查网络设置！");
						return false;
					}
					if(beforeFunc) {
						beforeFunc();
					}
				},
				complete: function() {
					if(completeFunc) {
						completeFunc();
					}
				},
				success: function(result) {
					console.info("success", result);
					//服务器返回响应，根据响应结果，分析是否登录成功；
					if(successFunc) {
						successFunc(result)
					}

				},
				error: function(xhr, type, errorThrown) {
					//console.info(errorThrown);
					//console.log(type)
					if(errorFunc) {
						errorFunc();
					}

					if(type == "timeout") {
						mui.toast("连接超时，请检查网络");
					} else if(type == "parsererror") {
						mui.toast("数据解析出错");
					} else {
						mui.toast("请求出现异常");
					}
				}
			});
		},
		
		
		/**
		 * ajax请求提供给timer专用的
		 */
		ajaxPostTimer: function(url, params, successFunc,completeFunc, headers) {
			//console.log(url);
			mui.ajax(url, {
				data: params,
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				timeout: 30000, //超时时间设置为10秒；
				cache: false,
				headers: (headers || {}),
				success: function(result) {
					//服务器返回响应，根据响应结果，分析是否登录成功；
					if(successFunc) {
						successFunc(result)
					}

				},
				complete: function() {
					if(completeFunc) {
						completeFunc();
					}
				}
			});
		},
		/**
		 * 清除字符串首尾空格
		 * @param {Object} x
		 */
		trim: function(x) {
			return x.replace(/^\s+|\s+$/gm, '');
		},
		/**
		 * 验证是否为身份证
		 * @param{*}num :要验证的num
		 */
		isCardNum: function(num) {
			// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if(reg.test(num)) {
				return true;
			}

			return false;
		},

		/**
		 * 校验email
		 * @param {Object} email
		 */
		isEmail: function(email) {
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则
			if(reg.test(email)) {
				return true;
			}
			return false;
		},

		/**
		 * 隐藏软键盘(全部表单失去焦点)
		 */
		hideKeyBord: function() {
			mui("input,textarea").each(function(index, elem) {
				elem.blur();
			})
		},

		/**
		 * 打开软键盘
		 */
		openSoftKeyboard: function() {
			if(mui.os.ios) {
				var webView = plus.webview.currentWebview().nativeInstanceObject();
				webView.plusCallMethod({
					"setKeyboardDisplayRequiresUserAction": false
				});
			} else {
				var webview = plus.android.currentWebview();
				plus.android.importClass(webview);
				webview.requestFocus();
				var Context = plus.android.importClass("android.content.Context");
				var InputMethodManager = plus.android.importClass("android.view.inputmethod.InputMethodManager");
				var main = plus.android.runtimeMainActivity();
				var imm = main.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
			}
		},

		/**
		 * 验证是否为手机号码
		 * @param {Object} phone
		 */
		isPhone: function(phone) {
			if((/^1[34578]\d{9}$/.test(phone))) {
				return true;
			}
			return false;
		},

		/**
		 * 警告框
		 * @param {Object} msg
		 * @param {Object} callback
		 */
		alertWarning: function(msg, callback) {
			mui.alert("<span class='alert-warning'>" + msg + "</span>", "<span class='alert-warning'>系统提示</span>", "<span class='alert-warning'>确定</span>", function() {
				if(callback) {
					callback();
				}
			}, "div");

		},
		/**
		 * 成功对话框
		 * @param {Object} msg
		 * @param {Object} callback
		 */
		alertSuccess: function(msg, callback) {
			mui.alert(msg, "系统提示", "确定", function() {
				if(callback) {
					callback();
				}
			}, "div");

		},

		/**
		 * 确认对话框
		 * @param {Object} msg
		 * @param {Object} callback
		 */
		alertConfirm: function(msg, sureCallback, cancelCallback) {
			mui.confirm(msg, "系统提示", ["取消", "确定"], function(even) {

				//确定
				if(sureCallback && even.index == 1) {
					sureCallback();
				}
				//取消
				if(cancelCallback && even.index == 0) {
					cancelCallback();
				}
			}, "div");
		},

		/**
		 * 使用该方法打开新窗口 解决窗口传参丢失问题
		 * @param {Object} id
		 * @param {Object} params
		 */
		openWindow: function(id, params) {
			var view = plus.webview.getWebviewById(id);
			if(view) {
				mui.fire(view, "pageParams", {
					params: params
				})
				view.show();
			} else {
				mui.openWindow({
					id: id,
					url: id,
					extras: {
						params: params
					}
				})
			}
		},

		/**
		 * 使用该方法解决页面传参丢失问题
		 * @param {Object} callback
		 */
		initPageParams: function(callback) {
			var params = null;
			mui.plusReady(function() {
				console.log("plusReady-->");
				var self = plus.webview.currentWebview();
				params = self.params;
				if(callback) {
					callback(params);
				}
			})

			document.addEventListener("pageParams", function(even) {
				console.log("params call-->");
				params = event.detail.params;
				if(callback) {
					callback(params);
				}
			})
		},

		/**
		 * 将json转成对象
		 * @param {Object} json
		 */
		convertJsonToObj: function(json) {
			try {
				return JSON.parse(json);
			} catch(error) {
				console.log(error);
			}
			return null;
		},

		/**
		 * 将对象转成json数据
		 * @param {Object} obj
		 */
		converObjToJson: function(obj) {
			try {
				return JSON.stringify(obj);
			} catch(error) {
				console.log(error);
			}
			return "";
		},
		/**
		 * 将服务端带T(2018-01-16T14:21:33)格式的时间字符串转换成需要的(2017-05-09 10:23:10)
		 */
		translateDate: function(dateStr) {
			dateStr = dateStr || "";
			if(dateStr == "") {
				return "";
			}
			var dateee = new Date(dateStr).toJSON();
			try {
				return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '').split(" ")[0]
			} catch(error) {
				console.log(error);
			}
			return "";
		},

		/**
		 * 获取用户存储到本地的用户信息对象
		 */
		getUser: function() {
			var user = plus.storage.getItem("user");
			return this.convertJsonToObj(user);
		},

		/**
		 * 获取用户实名信息
		 */
		getRealUser: function() {
			var user = plus.storage.getItem("realUser");
			return this.convertJsonToObj(user);
		},

		/**
		 * 专户互动交流date
		 * @param {Object} date
		 */
		converMailBoxDate: function(date) {
			try {
				return date.split(" ")[0].replace("-", "年").replace("-", "月") + "日";
			} catch(error) {
				console.log(error);
			}
			return "";
		},

		/**
		 * 去除html中的指定标签
		 * @param {Object} htmlContent
		 * @param {Object} tagName 指定标签名
		 */
		removeHtmlTag: function(htmlContent, tagName) {
			if(!htmlContent) {
				return "";
			}
			var temp = document.createElement("div");
			temp.innerHTML = htmlContent;
			var imgs = temp.getElementsByTagName(tagName);
			for(var i = 0; i < imgs.length; i++) {
				imgs[i].outerHTML = "";
			}

			return temp.innerHTML;
		},

		/**
		 * 去除字符串中的所有html标签
		 * @param {Object} content
		 */
		removeAllHtmlTag: function(content) {
			if(!content) {
				return "";
			}
			return content.replace(/<[^>]+>/g, ""); //去掉所有的html标记
		},

		

		/**
		 * 判断字符串为空
		 * @param {Object} str
		 */
		isBlank: function(str) {
			if(str == "null") {
				return true;
			}
			if(!str){
				return true;
			}
			if(this.trim(str) == ""){
				return true;
			}
			return false;
		},
		
		/**
		 * 获取非空字符串
		 */
		getNotNullStr:function(str){
			if(this.isBlank(str)){
				return "";
			}
			return this.trim(str);
		}

	};

	win.CONSTANTS = CONSTANTS;
	win.CommonUtil = CommonUtil;
})(window)