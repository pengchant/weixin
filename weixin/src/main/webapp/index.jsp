<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>测试微信录音功能</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/weui.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery-weui.min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-weui.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<style>
	.imgicon{
		width:20px;
		height:20px;
	}
</style>	
</head>
<body> 

	<!-- 录制部分 -->
	<div class="weui-flex" style="margin-top:120px;">
	  <div class="weui-flex__item"></div>
	  <div class="weui-flex__item" style="text-align:center;">
	  	 <a href="javascript:;" class="weui-btn weui-btn_primary" id="voiceBtn">开始录音</a>	  
	  	 <label id="seconds">00.00</label>秒	 
	  </div>
	  <div class="weui-flex__item"></div>
	</div>
	
	<!-- 音频列表 -->
	<div class="weui-cells" id="audiolist">
	    <!-- 里面动态加载录音内容 -->
	</div>
	
	<%-- 交互的js --%>
	<script type="text/javascript">
		$(function(){  
			// 定义一个露营文件模板(name属性为localid)
			var $audiomuban = $("<div class=\"weui-cell\" name=''>"+
				    "<div class=\"weui-cell__hd\"><img class=\"imgicon\" src=\"<%=request.getContextPath()%>/img/audio.png\"/></div>"+
				    "<div class=\"weui-cell__bd\">"+
				     "<p class='audio_name'>录音文件1</p>"+
				    "</div>"+
				    "<div class=\"weui-cell__ft audio_length\">时长0秒</div>"+
				  "</div>"); 
			// 定义计时器
			var seconds = 0;
			// 定义定时器
			var interval;
			// 定义录制的数量
			var count = 0;
			
			
			/**
			*	页面加载向微信服务器注册该页面
			*/
			$.ajax({
				url:"<%=request.getContextPath()%>/JSSDKConfigServlet",
				type:"post",
				data:{
					"regUrl":"http://123.56.151.29/weixin/index.jsp"
				},
				dataType:"json"
			}).done(function(data){  
				wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: data.appId, // 必填，公众号的唯一标识
				    timestamp:data.timestamp, // 必填，生成签名的时间戳
				    nonceStr: data.nonceStr, // 必填，生成签名的随机串
				    signature: data.signature,// 必填，签名，见附录1 
				    jsApiList: ['startRecord','stopRecord','onVoiceRecordEnd','playVoice','pauseVoice','stopVoice','onVoicePlayEnd','uploadVoice','downloadVoice'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				}); 
				// 微信服务器注册完毕
				wx.ready(function(){   
					
					// 录音按钮
					$("#voiceBtn").click(function(){
						// 如果是停止录音
						if($(this).hasClass("weui-btn_loading")){  
							// 结束录音
							wx.stopRecord({
								success:function(res){
									var localId = res.localId;
									
									////////////////添加到录音列表，绑定播放事件/////////////////
									count++;
									// 设置列表项的内容
									var $new = $audiomuban.clone(); 
									$new.find("div.weui-cell").attr("name",localId); 
									$new.find("p.audio_name").html("录音文件"+count);
									$new.find("div.audio_length").html("时长"+seconds+"秒");
									// 绑定click事件
									$new.click(function(){
										$.toast("开始播放语音", "text");
										var localId = $(this).attr("name");
										play(localId);
									});
									// 添加到列表中
									$("#audiolist").append($new); 
									
									
									//////////////////////还原录制的页面///////////////////////////
									$.toptip('录制完成', 'warning'); 
									$(this).removeClass("weui-btn_loading").text("录音");
									seconds = 0;
									$("#seconds").html(seconds);
									clearInterval(interval);
									
									
									////////////////////上传录音///////////////////////
									wx.uploadVoice({
									    localId: localId, // 需要上传的音频的本地ID，由stopRecord接口获得
									    isShowProgressTips: 1, // 默认为1，显示进度提示
									        success: function (res) {
									        var serverId = res.serverId; // 返回音频的服务器端ID(mediaid)
									        alert("上传成功，上传之后的mediaid为："+serverId);
									    }
									});  
								}
							});
							
						}else{// 如果是开始录音 
							///////////////////开始录制音频页面效果，启动计时器///////////////////
							$.toptip('开始录制', 'success');
							interval = setInterval(() => {
								seconds++;
								$("#seconds").html(seconds);
							}, 1000);
							$(this).addClass("weui-btn_loading").text("录音中...");
							////////////////////////// 开始录音 //////////////////////////
							wx.startRecord();
						}
					});
					
					// 监听录音自动停止
					wx.onVoiceRecordEnd({
						complete:function(res){
							var localId = res.localId; 
							
							////////////////添加到录音列表，绑定播放事件/////////////////
							count++;
							// 设置列表项的内容
							var $new = $audiomuban.clone(); 
							$new.find("div.weui-cell").attr("name",localId); 
							$new.find("p.audio_name").html("录音文件"+count);
							$new.find("div.audio_length").html("时长"+seconds+"秒");
							// 绑定click事件
							$new.click(function(){
								$.toast("开始播放语音", "text");
								var localId = $(this).attr("name");
								play(localId);
							});
							// 添加到列表中
							$("#audiolist").append($new); 
							
							//////////////////////还原录制的页面///////////////////////////
							$.toptip('录制完成', 'warning');  
							// 取消录制
							$("#voiceBtn").removeClass("weui-btn_loading").text("录音");
							seconds = 0;
							$("#seconds").html(seconds);
							clearInterval(interval);
							
							////////////////////上传录音///////////////////////
							wx.uploadVoice({
							    localId: localId, // 需要上传的音频的本地ID，由stopRecord接口获得
							    isShowProgressTips: 1, // 默认为1，显示进度提示
							        success: function (res) {
							        var serverId = res.serverId; // 返回音频的服务器端ID(mediaid)
							        alert("上传成功，上传之后的mediaid为："+serverId);
							    }
							});  
						}
					});
					
					// 播放语音接口
					function play(localId){
						wx.playVoice({
							localId:localId  // 需要播放的音频的本地ID，由stopRecord接口获得
						});
					}
					
					// 暂停播放接口
					function pause(localId){
						wx.pauseVoice({
						    localId: localId // 需要暂停的音频的本地ID，由stopRecord接口获得
						});
					}
					
					// 停止播放
					function stop(localId){
						wx.stopVoice({
							localId:localId // 需要停止的音频的本地ID，由stopRecord接口获得
						});
					}
					
					// 监听语音播放完毕
					wx.onVoicePlayEnd({
					    success: function (res) {
					        var localId = res.localId; // 返回音频的本地ID
					        $.toast("播放完毕");
					    }
					});
					 
					
				});    
				// 加载失败
				wx.error(function(res){  
					console.log(res);
				});
			});  
			
		});
	</script>
</body>
</html>