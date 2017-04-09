package com.handkoo.test;

import javax.mail.Flags.Flag;

import com.handkoo.util.CommonUtil;
import com.handkoo.util.MenuUtil;
import com.handkoo.weixin.menu.entity.Button;
import com.handkoo.weixin.menu.entity.ClickButton;
import com.handkoo.weixin.menu.entity.ComplexButton;
import com.handkoo.weixin.menu.entity.Menu;
import com.handkoo.weixin.menu.entity.ViewButton;

/**
 * 创建菜单测试
 * @author cp
 *
 */
public class MenuTest {
//	public static void main(String[] args){
//		// 创建菜单
//		Menu menu = new Menu();
//		Button[] button_all = new Button[2];
//		
//		// 今日歌曲按钮
//		ClickButton clickButton = new ClickButton();
//		clickButton.setType("click");
//		clickButton.setName("今日歌曲");
//		clickButton.setKey("VOICE_HANDKOO");
//		button_all[0] = clickButton;
//		
//		// 复杂按钮
//		ComplexButton complexButton = new ComplexButton();
//		complexButton.setName("菜单");
//		
//		Button[] buttons =new Button[3];
//		// 创建子按钮
//		ViewButton viewButton_sousuo = new ViewButton();
//		viewButton_sousuo.setName("搜索");
//		viewButton_sousuo.setType("view");
//		viewButton_sousuo.setUrl("http://www.soso.com/");
//		buttons[0] = viewButton_sousuo;
//		
//		ViewButton viewButton_shiping =new ViewButton();
//		viewButton_shiping.setType("view");
//		viewButton_shiping.setName("视频");
//		viewButton_shiping.setUrl("http://v.qq.com");
//		buttons[1] = viewButton_shiping;
//		
//		ClickButton clickButton_zan=new ClickButton();
//		clickButton_zan.setType("click");
//		clickButton_zan.setName("赞一下我们");
//		clickButton_zan.setKey("VHANDKOO_ZAN");
//		buttons[2] = clickButton_zan;
//		
//		complexButton.setSub_button(buttons);
//		
//		button_all[1] = complexButton;
//		 
//		menu.setButton(button_all); 
//		
//	 
//		// 添加按钮
//		MenuUtil.createMenu(menu, "oaBpJxr7QrD0uXWMkh-y4kwZrd0CgYjz4mdcBIPlhyiVGLQAZpgRLGPrjCaAwrc5R4MLN6CwigNUubHnJAV1gEyGQu23zlvdFCzUa8hZ2GOpgn9WJJGw5kCSuNM5ibniFTPfACAIWS");
//	}
	
	// 删除菜单
//	public static void main(String[] args){
//		boolean flag = MenuUtil.deleteMenu("oaBpJxr7QrD0uXWMkh-y4kwZrd0CgYjz4mdcBIPlhyiVGLQAZpgRLGPrjCaAwrc5R4MLN6CwigNUubHnJAV1gEyGQu23zlvdFCzUa8hZ2GOpgn9WJJGw5kCSuNM5ibniFTPfACAIWS");
//		System.out.println(flag);
//	}
	
	// 查询菜单
	public static void main(String[] args){
		String jsonstr = MenuUtil.getMenu("oaBpJxr7QrD0uXWMkh-y4kwZrd0CgYjz4mdcBIPlhyiVGLQAZpgRLGPrjCaAwrc5R4MLN6CwigNUubHnJAV1gEyGQu23zlvdFCzUa8hZ2GOpgn9WJJGw5kCSuNM5ibniFTPfACAIWS");
		System.out.println(jsonstr);
	}
}
