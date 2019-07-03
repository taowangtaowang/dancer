(function($) {
	
	if(typeof $ == "undefined"){
		throw new Error("请先引入jquery")
	}
	
	/**
	 * 定时滚动
	 * @param {*} time 
	 */
	$.prototype.initScroll = function(time, showCount) {

		//item数量
		var itemCount = $(this).children().size();
		if(itemCount == 0){
			return;
		}
		//console.log("size:" + itemCount);

		//获取item高度
		var itemHeight = $(this).children().first().css("height");
		itemHeight = parseInt(itemHeight.replace("px", ""));
		var paddingTop = $(this).children().first().css("paddingTop");
		paddingTop = parseInt(paddingTop.replace("px", ""));
		var paddingBottom = $(this).children().first().css("paddingBottom");
		paddingBottom = parseInt(paddingBottom.replace("px", ""));
		itemHeight += paddingTop + paddingBottom;

		//设置wrapper的高度
		$(this).parent().css({
			height: showCount * itemHeight + "px",
			overflow: "hidden"
		});

		$(this).css("marginTop", "0px")

		function scroll() {
			setTimeout(function() {
				
				var size = $(this).children().size();

				if(itemCount <= showCount || size <= showCount) {

					return;
				}

				$(this).animate({
					"margin-top": "-" + itemHeight + "px"
				}, 800, function complete() {
					var $first = $(this).children().first();
					var firstHtml = $first[0].outerHTML;
					$first.remove();
					$(this).append(firstHtml);
					$(this).css("marginTop", "0px")
					
					scroll.call(this, time);
					
				})
			}.bind(this), time)

		}

		scroll.apply(this, arguments);
	}

})(jQuery)