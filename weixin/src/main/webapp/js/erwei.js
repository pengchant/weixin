$(function(){
	// 点击生成
	$("#create").click(function(){
		// 获取到code
		let code = $("#code").val();
		// ajax访问后台
		$.ajax({
			url:'QRCodeServlet?op=queryErWei',
			type:'post',
			data:{
				code:code
			},
			dataType:"json"
		}).done(function(data){
			if(data=="-1"){
				alert("对不起，你输入的编号有误，请输入32位非0整型！");
			}else{
				$("#code").val("");
				$("#imgShower").attr("src",data);
				$("#downloader").attr("href","QRCodeServlet?op=ewDownload&url="+data);
			} 
		});
	});
});