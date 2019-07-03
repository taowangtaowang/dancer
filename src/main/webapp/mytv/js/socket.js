(function() {

	var ws = null;
	var callback = null; //注册的回调接口
	var mac = "";//mac地址
	var url = "";
	var off = false;


	/**
	 * 初始化websocket
	 */
	function initSocket() {
		if(typeof WebSocket != 'undefined') {
			ws = new WebSocket(url);
			ws.onopen = function(evt) {
				//console.log("onopen--------------->")
				var params = {
					mine:mac,
					type:"register",    
					callback:"initMsg",
					msg:""
				}
				ws.send(JSON.stringify(params));
			};

			ws.onmessage = function(evt) {
				var data = evt.data;
				//console.log("payloadstring:"+data);
				if(callback) {
					try{
						data = JSON.parse(data);
					}catch(error){
						data = {};
						console.error("payload json解析错误")
					}
					callback(data);
				}
			};

			ws.onerror = function(evt) {
				//console.log("onerror-------->");
			}

			ws.onclose = function(evt) {
				//console.log("evt:"+evt)
				//console.log(JSON.stringify(evt))
				//console.log("Connection closed."+evt);    
			};

		}
	}

	/*得到手机MAC地址*/
	function getMac() {
		var uuid = plus.device.uuid || "";
		//console.log("uuid:"+uuid.toUpperCase());
		return uuid.toUpperCase();
	}
	
	var timer = null;

	/**
	 * 连接保活(15秒测试一次)
	 */
	function keepHeart() {
		if(off){
			return;
		}
		timer = setTimeout(function() {
			//console.log("发送心跳")
			//发送空包消息保活
			if(ws.readyState == WebSocket.CLOSING || ws.readyState == WebSocket.CLOSED) {
				ws = null;
				//console.log("链接断开：重新连接")
				initSocket();
			} else {
				if(ws.readyState == WebSocket.OPEN){
					//console.log("open 发送心跳")
					ws.send("\n\r")
				}
			}

			keepHeart();

		}, 15 * 1000)
	}

	window.WSocket = {
		/**
		 * 初始化websocket
		 */
		init: function(url1) {
			url = url1;
			if(!url){
				throw new Error("websocket长连接协议不正确:"+url);
			}
			mac = getMac();   
			keepHeart();
			return this;
		},
		
		/**
		 * 注册消息回调
		 * @param {Object} msgCallback
		 */
		registerHandler: function(msgCallback) {
			callback = null;
			callback = msgCallback;
			initSocket()
		},
		
		/**
		 * 获取WSokcet实例
		 */
		getInstance:function(){
			return this;
		},
		
		/**
		 * 关闭websocket
		 */
		close:function(){
			off = true;
			clearTimeout(timer);
			if(ws){
				ws.close();
			}
		}

	}
})()