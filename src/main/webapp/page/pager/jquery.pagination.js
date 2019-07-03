/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.1
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
var perpageviewcount = 15;  //记录选择每页显示的条数
var maxentries;
jQuery.fn.pagination = function(opts){
	opts = jQuery.extend({
		items_per_page:10,//每页总数
		num_display_entries:3,
		current_page:0,
		//go_page:'',
		num_edge_entries:1,
		first_excute:false,
		link_to:"#",
		first_text:"|<",//首页
	    prev_text:"<",//上一页
        next_text:">",//下一页
		last_text:">|",//尾页
		ellipse_text:"...",//...
		perpageview:"10,20,50",
		prev_show_always:true,
		next_show_always:true,
		page:null,
		callback:function(){return false;}
	},opts||{});
	
	return this.each(function() {
		
		var page=opts.page;
		if(page!=null&&page!=''){
			//alert(0);
			opts.total_count = page.totalResult;
			opts.items_per_page = page.showCount;
			opts.current_page = page.currentPage-1;
			maxentries = page.totalResult;
			//数据记录数为0时，不显示分页组件
			if(page.totalResult==0){
				return false;
			}
		}else if(page==null){
			return false;
		}
		/**
		 * Calculate the maximum number of pages
		 */
		function numPages() {
			return Math.ceil(maxentries/opts.items_per_page);
		}
		
		/**
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @return {Array}
		 */
		function getInterval()  {
			var ne_half = Math.ceil(opts.num_display_entries/2);
			var np = numPages();
			var upper_limit = np-opts.num_display_entries;
			var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
			var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
			return [start,end];
		}
		
		/**
		 * This is the event handling function for the pagination links. 
		 * @param {int} page_id The new page number
		 */
		function pageSelected(page_id, evt){
			current_page = page_id;
			// alert(page_id);
			//drawLinks();
			var continuePropagation = opts.callback(page_id,opts.items_per_page,panel);
			if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}
			return continuePropagation;
		}
		

		//改变每页显示的条数
		var chagePerPageCount =function(value){
			current_page = 0;
			opts.items_per_page = value;
			//drawLinks();
			var continuePropagation = opts.callback(0,opts.items_per_page,panel);
			/*if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}*/
			perpageviewcount = value;
			// 
		}
		//跳转到XX页
		var goToPage =function(value){
			//opts.go_page = value;
			var r = /^\+?[1-9][0-9]*$/;//正整数 
		    //r.test(value);
			if(!r.test(value)){
				return;
			}
			//value = Math.ceil(value);
			var totalPage = numPages();
			if(totalPage<value){
				value = totalPage;
			}
			if(value<=1){
				value = 1;
			}
			current_page = value-1;
			opts.current_page=value-1;
			//alert('totalPage:'+totalPage+",current_page:"+current_page+",opts.go_page:"+opts.go_page);
			var continuePropagation = opts.callback(current_page,opts.items_per_page,panel);
			//drawLinks();
		}
				
		/**
		 * This function inserts the pagination links into the container element
		 */
		function drawLinks() {
			panel.empty();
			var interval = getInterval();
			var np = numPages();
			// This helper function returns a handler function that calls pageSelected with the right page_id
			var getClickHandler = function(page_id) {
				return function(evt){ return pageSelected(page_id,evt); }
			}
			
			// Helper function for generating a single link (or a span tag if it'S the current page)
			var appendItem = function(page_id, appendopts){
				page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
				appendopts = jQuery.extend({text:page_id+1, classes:""}, appendopts||{});
				if(page_id == current_page){
					/*var lnk = $("<span class='current' va='ttt'>"+(appendopts.text)+"</span>");*/
					if(appendopts.classes==""){
						var lnk = $("<div class='current'><div class='btncenter'>"+(appendopts.text)+"</div></div>");
					}
					else{
						var lnk = $("<div class='disabled'><div class='btncenter'>"+(appendopts.text)+"</div></div>");
					}
		 		}
				else
				{
					/*var lnk = $("<a>"+(appendopts.text)+"</a>")*/
					var lnk = $("<div><div class='btncenter'>"+(appendopts.text)+"</div></div>")
						.bind("click", getClickHandler(page_id))
						.attr('href', opts.link_to.replace(/__id__/,page_id));											
				}
				if(appendopts.classes){lnk.addClass(appendopts.classes);}
				panel.append(lnk);
			}

            // Radys Add
			// Generate "First"-Link
			if(opts.first_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(0,{text:opts.first_text, classes:"first"});
			}

			// Generate "Previous"-Link
			if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_text, classes:"prev"});
			}
			// Generate starting points
			if (interval[0] > 0 && opts.num_edge_entries > 0)
			{
				var end = Math.min(opts.num_edge_entries, interval[0]);
				for(var i=0; i<end; i++) {
					appendItem(i);
				}
				if(opts.num_edge_entries < interval[0] && opts.ellipse_text)
				{
					jQuery("<div class='ellipsis'><div class='btncenter'>"+opts.ellipse_text+"</div></div>").appendTo(panel);
				}
			}
			// Generate interval links
			for(var i=interval[0]; i<interval[1]; i++) {
				appendItem(i);
			}
			// Generate ending points
			if (interval[1] < np && opts.num_edge_entries > 0)
			{
				if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text)
				{
					jQuery("<div class='ellipsis'><div class='btncenter'>"+opts.ellipse_text+"</div></div>").appendTo(panel);
				}
				var begin = Math.max(np-opts.num_edge_entries, interval[1]);
				for(var i=begin; i<np; i++) {
					appendItem(i);
				}
				
			}
			// Generate "Next"-Link
			if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
				appendItem(current_page+1,{text:opts.next_text, classes:"next"});
			}

            // Radys Add
			// Generate "Last"-Link
			if(opts.last_text && (current_page < np-1 || opts.next_show_always)){
				appendItem(np-1,{text:opts.last_text, classes:"last"});
			}
			
			if(opts.perpageview!=''){
				
				var perpageviewObject = opts.perpageview;
				// alert(perpageviewObject);
				var  perpageOption = "";
				if($.trim(perpageviewObject).length>0){
					var perPageView_array = perpageviewObject.split(",");
					if(perPageView_array.length>0){
						for(var i =0;i<perPageView_array.length;i++){
							//perpageOption +="<option value='"+perPageView_array[i]+"'>"+perPageView_array[i]+"</option>";
							//修改为“每页显示”选中 页面最大记录数 wl 2012-11-21
							if(opts.items_per_page==perPageView_array[i]){
								perpageOption +="<option selected='selected' value='"+perPageView_array[i]+"'>"+perPageView_array[i]+"</option>";
							} else{
								perpageOption +="<option value='"+perPageView_array[i]+"'>"+perPageView_array[i]+"</option>";
							}
						}
					}
				}
				
				//创建显示的页数
			/*	var gotopage= jQuery("<select class='perpagecount' name='perpagecount'>" +
						perpageOption+
				"</select>").change(function(){
					var selectCount = $(this).val();
					chagePerPageCount(selectCount);
				});*/
				var gotopage= jQuery("<select class='perpagecount' name='perpagecount'>" +
						perpageOption+
				"</select>").change(function(){
					var selectCount = $(this).val();
					chagePerPageCount(selectCount);
				});
				
				//alert('opts.go_page:'+opts.go_page+",opts.current_page:"+opts.current_page);
				
				var inputObj = jQuery("<div><input type='text' value='"+(opts.current_page+1)+"'></div>");
				//绑定回车事件
				inputObj.children().eq(0).bind('keypress',function(event){
		            if(event.keyCode == "13")    
		            {
		            	var selectCount = inputObj.children().eq(0).val();
						goToPage(selectCount); 
		            }
		        });
				var gotopage = jQuery("<div  class='page_go'>go</div>").click(function(){
					var selectCount = inputObj.children().eq(0).val();
					goToPage(selectCount); 
				});
				//gotopage = jQuery(inputObj.append(gotopage));
				var gotoPagePanel = jQuery("<div class='total'><div>共"+opts.total_count+"条 </div> </div>").append(inputObj).append("<div>页</div>").append(gotopage);
				//var gotoPagePanel = "<div class='total'><label>共"+opts.total_count+"条 </label></div> 到"+inputHtml+ "页";
				panel.append(gotoPagePanel); 
				
			}
			
		// 	alert($(panel).html());

		}//end drawlinks
		
		// Extract current_page from options
		var current_page = opts.current_page;
		// Create a sane value for maxentries and items_per_page
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		// Store DOM element for easy access from all inner functions
		var panel = jQuery(this);
		// Attach control functions to the DOM element 
		this.selectPage = function(page_id){pageSelected(page_id);}
		this.prevPage = function(){ 
			if (current_page > 0) {
				pageSelected(current_page - 1);
				return true;
			}
			else {
				return false;
			}
		}
		
		this.nextPage = function(){ 
			if(current_page < numPages()-1) {
				pageSelected(current_page+1);
				return true;
			}
			else {
				return false;
			}
		}
		
		// When all initialisation is done, draw the links
		drawLinks();
		
		//$(".perpagecount>option[value='"+perpageviewcount+"']").attr("selected",true);//选中选择的数值
		
		//the first enter excute
		if(opts.first_excute){
	      opts.callback(0,opts.items_per_page,panel);
	    }
		
	});
}


