package test;

import static org.junit.Assert.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//import android.content.pm.ActivityInfo;　

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;



public class zoom {
	
	private static AppiumDriver driver;
	//AndroidDriver
	//private static AppiumDriver driver2;
	
	private static int sleepBase = 1000;
	private String udid = "8e87c4f9";
	private static String status;
	private static int build_id = 142;//版本号id
	private static int tester_id = 8;//测试员id
	private static int testplan_id = 1005;//测试计划id
	
	
	/*************************************
	 setUp:用户准备工作，设置测试apk，OS，版本，报名等。改变测试设备或apk等时，可相应修改设置项，或使用mac系统的appium录制功能。
	 *************************************/
	@Before
	public void setUp() throws Exception {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "ssaV0.26.58.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Galaxy C7");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.linlong.ssa");
//		capabilities.setCapability("unicodeKeyboard", "True");  //for input Chinese 
		capabilities.setCapability("newCommandTimeout",240);//Appium的默认的命令间隔时间是60s,此处设置为240s
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		Thread.sleep(sleepBase * 2);

	}

	/***频繁切换tab***/
	@Test
    public void Test1() throws Exception{
		   
		int tcversion_id = 5390;
		File filename = new File("Smoke4_JinGu.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke4_JinGu.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
		try{
		for( int i = 0;i<100;i++){
			System.out.println("eeeee");
			driver.findElementsById("com.linlong.ssa:id/klineTabMenu");
			System.out.println("haha");
			 ClickByIndex("android.support.v7.app.ActionBar$Tab",1, "切换技术");
			 ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换大师");
			 ClickByIndex("android.support.v7.app.ActionBar$Tab",3, "切换F10");
			 ClickByIndex("android.support.v7.app.ActionBar$Tab",0, "切换实时");
		 System.out.println(i);
     } 
		  WritetoFile(filename,bf,"切换tab成功!");
			status = "p";
			sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		}catch (Exception e){
			System.out.println(e.getLocalizedMessage());
			  WritetoFile(filename,bf,"切换tab失败!");
				status = "f";
				sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		}
		
   }
	
	/*************************************
	 Smoke4_JinGu(软件And-136):用于测试进入金股tab是否正常
	 *************************************/	 
	@Test
	public void Smoke4_JinGu() throws Exception {
		int tcversion_id = 5390;
		File filename = new File("Smoke4_JinGu.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke4_JinGu.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",1, "切换至金股");
	    Thread.sleep(sleepBase * 1);    
	    boolean flag = (!isElementExsitByID("tvShangzhengPrice")) && (isElementExsitByText("股票名称"));
	    System.out.print("flag为" + flag);
	    if (flag == true){
	        WritetoFile(filename,bf,"切换至金股成功!");
			status = "p";
			sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    }
	    else{
		    WritetoFile(filename,bf,"切换至金股失败!");
			status = "f";
			sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    }
	    PrintScreen("Smoke4_JinGu.png");
	    assertTrue("切换至金股失败!",flag);
		}
	
	
	/**********************/
	@Test
	public void QiehuanApp() throws Exception {
		int tcversion_id = 5390;
		File filename = new File("Smoke4_JinGu.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke4_JinGu.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",1, "切换至金股");
	    Thread.sleep(sleepBase * 1);    
	    boolean flag = (!isElementExsitByID("tvShangzhengPrice")) && (isElementExsitByText("股票名称"));
	    System.out.print("flag为" + flag);
	    if (flag == true){
	        WritetoFile(filename,bf,"切换至金股成功!");
			status = "p";
			sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    }
	    else{
		    WritetoFile(filename,bf,"切换至金股失败!");
			status = "f";
			sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    }
	    PrintScreen("Smoke4_JinGu.png");
	    assertTrue("切换至金股失败!",flag);
		}
	
	
	
	

	
	/*************************************
	 Smoke9_SwitchStock(软件And-136):连续切换股票
	 *************************************/	 
	@Test
	public void  Smoke9_SwitchStock() throws Exception {
		int tcversion_id = 1583;
		//int tcversion_id = 00000;
		File filename = new File("Smoke9_SwitchStock.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke9_SwitchStock.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		  
		}
	    Thread.sleep(sleepBase * 7);
	    Thread.sleep(12000);
	    ClickByIndex("android.widget.RadioButton",1,"进入行情");
		driver.findElementById("com.linlong.ssa:id/title_name").click();
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "个股页面");//点击上证行情列表中任一股票进入其个股页面
	
		  
  for(int j=1;j<10;j++){
	  System.out.println(j);
	  
	  

	  String code1 = driver.findElementById("tvStockCode").getText();//获取当前的股票名称或代码
      System.out.println("code1:"+ code1);
      for (int n = 0; n  <10 ;n++){
    	  ClickByID("toNextStock", "切换下一支股票");//连续点击>，切换至下一支
                                }
    String code2 = driver.findElementById("tvStockCode").getText();//获取当前的股票名称或代码	
    System.out.println("code2:"+ code2);
    int flag1 = code1.compareTo(code2);//两次股票代码比较，若code1<code2，说明按照股票代码顺序切换成功
    boolean flag11 = false;
    if (flag1 == -1){
    	flag11 = true;
    	WritetoFile(filename,bf,"连续切换下一支股票成功!");
    	for (int a = 0; a<10 ;a++){
	    	ClickByID("toPrevStock", "切换上一支股票");//连续切换下一支成功后，继续测试；连续点击>，切换至上一支
	    }
    	String code3 = driver.findElementById("tvStockCode").getText();//获取当前的股票名称或代码
    	System.out.println("code3:"+ code3);
    	int flag2 = code2.compareTo(code3);//两次股票代码比较，若code2>code3，说明按照股票代码顺序切换成功
    	System.out.println("flag2:"+ flag2);
    	boolean flag22 = false;
    	if (flag2 == 1||flag2 ==2){
    	   flag22 = true;
    	   WritetoFile(filename,bf,"连续切换上一支股票成功!");
           status = "p";
 		  sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
    	}
    	else{
    		 flag22 = false;
    		 WritetoFile(filename,bf,"连续切换上一支股票失败!");
             status = "f";
	 		 sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
    	}
    	System.out.println("code2与code3的大小比较结果为：" + flag22);
    	assertTrue("连续切换上一支股票失败!",flag22);
    }	    
    else{
    	 flag11 = false;
    	 WritetoFile(filename,bf,"连续切换下一支股票失败!");
         status = "f";
 		 sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);	    		
    	}	    
    System.out.println("code1与code2的大小比较结果为：" + flag11);
    PrintScreen("Smoke9_SwitchStock.png");
    assertTrue("连续切换下一支股票失败!",flag11);

	  
	  
	  
  }
	   
	  
	
	}
	
	/*************************************
	 Smoke2222 
	 *************************************/	 
	@Test
	public void  Smoke2222_SwitchStock() throws Exception {
		int tcversion_id = 1577;
		File filename = new File("Smoke9_SwitchStock.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke9_SwitchStock.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		    
		}
	    
	    Thread.sleep(12000);
	    ClickByIndex("android.widget.RadioButton",1,"进入行情");
//	    
		for( int i = 0;i<100;i++){
			
			
			System.out.println("频繁进入个股");
			ClickByIndex("android.widget.LinearLayout",2, "进入个股页面");
		   Thread.sleep(2000);
            driver.findElement(By.id("com.linlong.ssa:id/back")).click();
   
            System.out.println(i);
		}
	}
	/*************************************
	 Smoke10_AddtoSelf(软件And-136):用于进行添加自选股测试
	 *************************************/	 
//	@Test
	public void Smoke10_AddtoSelf() throws Exception {
		int tcversion_id = 1796;
		File filename = new File("Smoke10_AddtoSelf.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke10_AddtoSelf.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至上证");//点切换至上证
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面
	    String name1 = driver.findElementById("tvStockName").getText();//获取当前的股票名称
	    boolean flag1 = isElementExsitByID("btzx");//通过判断是否有添加按钮来判定是否进入个股页面
	    if (flag1 == true){
//	    	ClickByID("btzx","点击添加自选按钮");//点击添加自选按钮	  
	    	driver.tap(1, driver.findElementById("btzx"),1);
		    ClickBack(1,"返回报价页面");//点击返回键，返回报价页面
		    Thread.sleep(sleepBase * 1);
		    ClickByIndex("android.support.v7.app.ActionBar$Tab",0, "切换至自选");//点切换至自选
		    Thread.sleep(sleepBase * 1);
		    boolean flag2 = isElementExsitByText(name1);
		    System.out.println("flag:"+ flag2);
		    if (flag2 == true){
		    	WritetoFile(filename,bf,"添加自选成功!");	    	
	            status = "p";
		 		sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		 		//添加自选成功后，继续测试，是否可删除
		 		ClickByIndex("android.widget.LinearLayout",0, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面
		 		Thread.sleep(sleepBase * 1);
		 		ClickByID("btzx","点击删除自选按钮");//点击删除自选按钮	 
		 		ClickByID("confirm","点击确认键");//弹出是否确认删除后，点击是
		 		ClickBack(1,"返回至自选页面");//返回至自选页面
			    boolean flag3 = isElementExsitByText(name1);
			    System.out.println("flag:"+ flag3);
			    if (!flag3 == true){
			    	WritetoFile(filename,bf,"删除自选成功!");
			        status = "p";
				 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
			    }
			    else{
			    	WritetoFile(filename,bf,"删除自选失败!");
			        status = "b";
				 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
			    }
			    assertTrue("删除自选失败!",flag3);
		    }
		    else{	    		 
		    	WritetoFile(filename,bf,"添加自选失败!");
		        status = "f";
			 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		    	}   
		    assertTrue("添加自选失败!",flag2);
	    }
	    else{
	    	WritetoFile(filename,bf,"进入个股页面失败!");	    	
            status = "f";
	 		sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    }
	    
        PrintScreen("Smoke10_AddtoSelf");
	    assertTrue("进入个股页面失败!",flag1);
		}
	


	/*************************************
	 Smoke12_EnterIndicator(软件And-136):用于进行进入技术指标页面测试；需要截图来判定是否成功
	 *************************************/	 
	@Test
	public void Smoke12_EnterIndicator() throws Exception {
		int tcversion_id = 5416;
		File filename = new File("Smoke12_EnterIndicator.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke12_EnterIndicator.txt");	
		driver.findElementById("com.linlong.ssa:id/cancel").click();	
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.widget.RadioButton",1,"进入行情");
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至上证");//点切换至上证
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面	 
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",1, "切换至技术");//点切换至技术
	    Thread.sleep(sleepBase * 7);	 
	    boolean flag = isElementExsitByText("指标");
	    System.out.println("flag:"+ flag);
	    if (flag == true){
	    	WritetoFile(filename,bf,"进入技术页面成功!");	    	
            status = "p";
	 		sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    }
	    else{	    		 
	    	WritetoFile(filename,bf,"进入技术页面失败!");
	        status = "f";
		 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    	} 
	    PrintScreen("Smoke12_EnterIndicator。png");
	    assertTrue("进入技术页面失败!",flag);
		}

	
	
	/*************************************
	 Smoke13_ChangeIndicator(软件And-136):用于进行切换指标测试；需要截图来判定是否成功
	 *************************************/	 
	@Test
	public void Smoke13_ChangeIndicator() throws Exception {
		int tcversion_id = 5419;
		File filename = new File("Smoke13_ChangeIndicator.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke13_ChangeIndicator.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至上证");//点切换至上证
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面	  
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",1, "切换至技术");//点切换至技术
	    Thread.sleep(sleepBase * 7);	    
	    boolean flag = isElementExsitByText("指标");
	    System.out.println("flag:"+ flag);
	    if (flag == true){
	    	WritetoFile(filename,bf,"进入技术页面成功!");	    	
            ClickByID("btnIndicatorParam","点击指标按钮进入指标页面");
            Thread.sleep(sleepBase * 1);
            boolean flag1 = isElementExsitByText("主图指标");//通过判断“主图指标”是否存在，判定是否进入到指标设置页面
            if (flag1 == true){
            	WritetoFile(filename,bf,"进入指标设置页面成功!");
                ClickByID("rbBollline","勾选布林线");
                Thread.sleep(sleepBase * 1);
                ClickByID("btnSure","点击确定按钮");
                Thread.sleep(sleepBase * 7);
                PrintScreen("切换指标后.png");
    	        status = "p";
    		 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
            }
            else{
            	WritetoFile(filename,bf,"进入指标设置页面失败!");
    	        status = "f";
    		 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
            }
            assertTrue("进入指标设置页面失败!",flag1);
	    }
	    else{	    		 
	    	WritetoFile(filename,bf,"进入技术页面失败!");
	        status = "f";
		 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    	}   
	    PrintScreen("Smoke13_ChangeIndicator。png");
	    assertTrue("进入技术页面失败!",flag);
		}
	
	
	
	/*************************************
	 Smoke14_EnterRealtime(软件And-136):用于进行实时页面测试；若委比、买入、分笔价格不为空，则表示进入实时页面正确
	 *************************************/	 
	@Test
	public void Smoke14_EnterRealtime() throws Exception {
		int tcversion_id = 5423;
		File filename = new File("Smoke14_EnterRealtime.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke14_EnterRealtime.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至上证");//点切换至上证
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面	  
	    Thread.sleep(sleepBase * 7);
	    boolean flag1 = driver.findElementById("weibi_1").getText().isEmpty();//判断委比数据是否为空
	    boolean flag2 = driver.findElementById("purchase_percent").getText().isEmpty();//判断买入数据是否为空
//	    boolean flag3 = isElementExsitByID("price");//判断分笔数据中价格数据是否为空
	    boolean flag  = !flag1 && ! flag2;
	    if (flag == true){
	    	WritetoFile(filename,bf,"进入实时页面成功!");	    	
            PrintScreen("实时页面.png");
            status = "p";
		 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
           }
        else{
           	WritetoFile(filename,bf,"进入实时页面失败!");
   	        status = "f";
   		 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
           }	    
        PrintScreen("Smoke14_EnterRealtime.png");
	    assertTrue("进入实时页面失败!",flag);
		}
	
	
	/*************************************
	 Smoke15_EnterDetail(软件And-136):实时页面，点击右下角的分笔，进入分笔明细页面，与分价分量切换
	 *************************************/	 
	@Test
	public void Smoke15_EnterDetail() throws Exception {
		int tcversion_id = 5426;
		File filename = new File("Smoke15_EnterDetail.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke15_EnterDetail.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至上证");//点切换至上证
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面	  
	    Thread.sleep(sleepBase * 7);
	    ClickByID("price","点击右下角的分笔数据");
        boolean flag1 = isElementExsitByID("change");//判断是否存在↑或↓箭头来判定是否进入分时明细页面
        if (flag1 == true){
 	       WritetoFile(filename,bf,"进入分时明细成功!");	    	
 	       ClickByIndex("android.support.v7.app.ActionBar$Tab",1, "切换至分价分量");//点切换至分价分量
 		   Thread.sleep(sleepBase * 1);
 		   boolean flag2 = isElementExsitByID("barVolPercent");//判断是否显示百分比来判定是否进入到分价分量页面
 		   if (flag2 == true){
 		       WritetoFile(filename,bf,"进入分价分量页面成功!");	    	
 	           status = "p";
 			   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
 		   }
 		   else {
 	          	WritetoFile(filename,bf,"进入分价分量页面失败!");
 	  	        status = "f";
 	  		 	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
 		   }
 		  assertTrue("进入分价分量页面失败!",flag2);
        }
        else{
        	WritetoFile(filename,bf,"进入分时明细页面失败!");
	  	    status = "f";
	  		sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
        }
        PrintScreen("Smoke15_EnterDetail.png");
	    assertTrue("进入分时明细面失败!",flag1);
		}
	
	
	/*************************************
	 Smoke16_EnterF10(软件And-136):实时页面，点击右下角的分笔，进入分笔明细页面，与分价分量切换
	 *************************************/	 
	@Test
	public void Smoke16_EnterF10() throws Exception {
		int tcversion_id = 5430;
		File filename = new File("Smoke16_EnterF10.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke16_EnterF10.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至上证");//点切换至上证
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面	  
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",3, "切换至F10");//点切换至F10
	    Thread.sleep(sleepBase * 15);
	    boolean flag = isElementExsitByIndex("android.view.View", 0);//判断此页面是否存在网页视图来判定是否进入F10页面
        if (flag == true){
	       WritetoFile(filename,bf,"进入F10成功!");	    	
	       status = "p";
		   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		}
		else {
	       WritetoFile(filename,bf,"进入F10失败!");
	  	   status = "f";
	  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		   }
        PrintScreen("Smoke16_EnterF10.png");
	   assertTrue("进入F10失败!",flag);
	}	
	
	
	/*************************************
	 Smoke17_EnterDaShi(软件And-136):实时页面，点击右下角的分笔，进入分笔明细页面，与分价分量切换
	 *************************************/	 
	@Test
	public void Smoke17_EnterDaShi() throws Exception {
		int tcversion_id = 5434;
		File filename = new File("Smoke17_EnterDaShi.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke17_EnterDaShi.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至上证");//点切换至上证
	    Thread.sleep(sleepBase * 1);
	    ClickByIndex("android.widget.LinearLayout",1, "进入个股页面");//点击上证行情列表中任一股票进入其个股页面	  
	    Thread.sleep(sleepBase * 7);
	    ClickByIndex("android.support.v7.app.ActionBar$Tab",2, "切换至大师");//点切换至F10
	    Thread.sleep(sleepBase * 2);
	    PrintScreen("大师页面.png");
	    boolean flag = isElementExsitByID("message") && isElementExsitByID("grade");//判断此页面是否存在网页视图来判定是否进入F10页面
        if (flag == true){
	       WritetoFile(filename,bf,"进入大师页面成功!");	    	
	       status = "p";
		   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		}
		else {
	       WritetoFile(filename,bf,"进入大师页面失败!");
	  	   status = "f";
	  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		   }
       PrintScreen("Smoke17_EnterDaShi.png");
	   assertTrue("进入大师页面失败!",flag);
	}	
	
	
	
	/*************************************
	 Smoke18_EnterDaShiMenu(软件And-136):实时页面，点击右下角的分笔，进入分笔明细页面，与分价分量切换
	 *************************************/	 
//	@Test
	public void Smoke18_EnterDaShiMenu() throws Exception {
		int tcversion_id = 1796;
		File filename = new File("Smoke18_EnterDaShiMenu.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke18_EnterDaShiMenu.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByID("rbMenu1", "切换至大师menu");//点切换至大师menu
	    Thread.sleep(sleepBase * 1);
	    boolean flag = isElementExsitByID("input_search");//判断此页面是否存在大师搜索框来判定是否进入大师页面
       if (flag == true){
	       WritetoFile(filename,bf,"进入大师menu成功!");	    
	       String input = "600078";
	       SendKeysByID("input_search",input);
	       driver.hideKeyboard();
	       Thread.sleep(sleepBase * 1);	     
	       boolean flag1 = isElementExsitByID("message") && isElementExsitByID("grade");//判断此页面是否存在网页视图来判定是否进入F10页面
	        if (flag1 == true){
		       WritetoFile(filename,bf,"大师观点搜索成功!");	    	
		       status = "p";
			   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
			}
			else {
		       WritetoFile(filename,bf,"大师观点搜索失败!");
		  	   status = "f";
		  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
			   }
	        assertTrue("大师观点搜索失败!",flag1);
		}
		else {
	       WritetoFile(filename,bf,"进入大师menu失败!");
	  	   status = "f";
	  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		   }
       PrintScreen("Smoke18_EnterDaShiMenu.png");
	   assertTrue("进入大师menu失败!",flag);
	}	
	
	
	
	/*************************************
	 Smoke19_EnterGuanJia(软件And-136):实时页面，点击右下角的分笔，进入分笔明细页面，与分价分量切换
	 *************************************/	 
//	@Test
	public void Smoke19_EnterGuanJia() throws Exception {
		int tcversion_id = 5441;
		File filename = new File("Smoke19_EnterGuanJia.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke19_EnterGuanJia.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByID("rbMenu2", "切换至管家menu");//点切换至大师menu
	    Thread.sleep(sleepBase * 1);
	    boolean flag = driver.findElementById("tvTitle").getText().equals("管家");//判断此页面title是否为管家来判定是否进入大师页面
        if (flag == true){
	       WritetoFile(filename,bf,"进入管家menu成功!");	  
	       ClickByID("steward_extra","点击添加股票按钮");
	       Thread.sleep(sleepBase * 1);
	       String inputString = "60061";//设置要输入的字符串
	       SendKeysByID("etStockCode",inputString);//输入要添加的股票代码或名称
	       Thread.sleep(sleepBase * 1);
	       SendKeysByID("etPrice","60");//输入价格条件
	       Thread.sleep(sleepBase * 1);
	       ClickBack(1, "收起软键盘");
	       ClickByID("btnOK","点击确定按钮");//点击确定
	       Thread.sleep(sleepBase * 1);
	       String getString = driver.findElementById("stock_code").getText();
	       System.out.println("添加的股票代码为："+ getString);
	       boolean flag1 = getString.contains(inputString);
	       if (flag1 == true){
	    	   WritetoFile(filename,bf,"添加股票至管家成功!");
	    	   status = "p";
			   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	       }	
	       else{
	    	   WritetoFile(filename,bf,"添加股票至管家失败!");
	    	   status = "f";
			   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	       }
	       assertTrue("添加股票至管家失败!",flag1);
		}
		else {
	       WritetoFile(filename,bf,"添加股票至管家失败!");
	  	   status = "f";
	  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		   }
       PrintScreen("Smoke19_EnterGuanJia.png");
	   assertTrue("进入管家menu失败!",flag);
	}	
	

	
	
	/*************************************
	 Smoke20_Logout(软件And-136):实时页面，点击右下角的分笔，进入分笔明细页面，与分价分量切换
	 *************************************/	 
	@Test
	public void Smoke20_Logout() throws Exception {
		int tcversion_id = 5445;
		File filename = new File("Smoke20_Logout.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("Smoke20_Logout.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	    ClickByID("rbMenu3", "切换至我的menu");//点切换至大师menu
	    Thread.sleep(sleepBase * 1);
	    boolean flag = driver.findElementById("tvTitle").getText().equals("我的");//判断此页面title是否为管家来判定是否进入大师页面
       if (flag == true){
	       WritetoFile(filename,bf,"进入我的menu成功!");	  
	       ClickByID("btnLogout","点击退出账号");//若进入我的menu成功，则继续测试，点击退出账号
	       Thread.sleep(sleepBase * 1);
	       ClickByID("confirm","点击确定按钮");//点击确定
	       Thread.sleep(sleepBase * 1);
	       boolean flag1 = isElementExsitByID("btnLogin");
	       if (flag1 == true){
	    	   WritetoFile(filename,bf,"退出账号成功!");
	    	   status = "p";
			   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	       }	
	       else{
	    	   WritetoFile(filename,bf,"退出账号失败!");
	    	   status = "f";
			   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	       }
	       assertTrue("退出账号失败!",flag1);
		}
		else {
	       WritetoFile(filename,bf,"进入我的menu失败!");
	  	   status = "f";
	  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		   }
       PrintScreen("Smoke20_Logout.png");
	   assertTrue("进入我的menu失败!",flag);
	}	
	/***断网联网***/
	@Test
	public void network() throws Exception {
		//int tcversion_id = 1571;
		int i=0;
		File filename = new File("network.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("network.txt");		
		Thread.sleep(12000);
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	   for (i=0;i<100;i++){
		    System.out.println(i);
		   
		    
			try {
				((AndroidDriver) driver).setNetworkConnection(new NetworkConnectionSetting(false,false, false));//断开网络连接
				NetworkConnectionSetting networkConnection = ((AndroidDriver) driver).getNetworkConnection();
				System.out.println(networkConnection);
				System.out.println("网络已断开");
				assertEquals(new NetworkConnectionSetting(false, false, false),networkConnection);
				Thread.sleep(sleepBase * 7);
			    ClickByID("rbMenu3", "切换至我的");
			    Thread.sleep(sleepBase * 1);
			    boolean flag1 = driver.findElementById("tvTitle").getText().equals("我的");
		       if (flag1 == true){
			       WritetoFile(filename,bf,"进入我的menu成功!");	  
		       }else{
		    	   WritetoFile(filename,bf,"进入我的menu失败");
		    	  // PrintScreen("Smoke19_EnterGuanJia.png");
		    	 //  status = "f";
			  	  // sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		       }
                
			} catch (Exception e) {
				System.out.println("开关网络连接操作失败" + e.getMessage());
				//status = "f";
			  	  // sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
				
			}
		
			try {
				  
				((AndroidDriver) driver).setNetworkConnection(new NetworkConnectionSetting(false,true, false));//恢复网络连接
				driver.findElement(By.id("android:id/button1")).click();
				NetworkConnectionSetting networkConnection = ((AndroidDriver) driver).getNetworkConnection();
				System.out.println(networkConnection);
				System.out.println("网络已连接");
				assertEquals(new NetworkConnectionSetting(false, true, false),networkConnection);
				 ClickByID("rbMenu1", "切换至大师menu");//点切换至大师menu
				  Thread.sleep(sleepBase * 1);
				    boolean flag2 = driver.findElementById("tvTitle").getText().equals("大师");//判断此页面title是否为管家来判定是否进入大师页面
				    if (flag2 == true){
					       WritetoFile(filename,bf,"进入大师menu成功!");	  
					   
				    }else{
				    	WritetoFile(filename,bf,"进入大师menu失败!");	 
				    	 PrintScreen("Smoke19_EnterGuanJia.png");
				    	 status = "f";
					  	  // sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
				    }	
			} catch (Exception e) {
				  
				System.out.println("开关网络连接操作失败" + e.getMessage());
				status = "f";
				//sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
				 
			}
		
			    
			    if(i==99){
					   status = "p";
					  // sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
				   }
	   }
	
	   
	}
	
	
	@Test
	public void swithBackground() throws Exception {
		int tcversion_id = 1;
		int i=0;
		File filename = new File("network.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("network.txt");		
		boolean flag_login = isLogged("account");//首先判断是否已登录过；若未登录过，先输入账号密码；
		System.out.println("是否已登录过：" + flag_login);
		if (flag_login == false){
			inputAccountInfo("21136902","342375");
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}else{
			
		    ClickByID("btnLogin","Login_login result");//点击登录按钮
		}
	    Thread.sleep(sleepBase * 7);
	try{
	    for (i=0;i<100;i++){
		    System.out.println(i);
		    System.out.println("切换home");
				 ((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.HOME);
				 Thread.sleep(5000);
					//System.out.println("3秒后程序启动");
					driver.runAppInBackground(2);
					//System.out.println("程序启动成功");
					//ClickByID("com.linlong.ssa:id/cancel","取消更新");
					//driver.launchApp();
					
					//driver.resetApp();	
					Thread.sleep(5000);
			}
	    status = "p";
	  	sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	    
	}
	catch (Exception e) {
		System.out.println("切换后台失败！"+ e.getMessage());
		status = "f";
	  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
	}
	   
       
		
			    

	   
	}
	

	
	

    @Test
	public void SwithHSScreen() throws Exception {
		int tcversion_id = 1;
		int i=0;
		File filename = new File("network.txt");
		BufferedWriter bf = new BufferedWriter(new PrintWriter(filename));
	    FunctionConnectMysql conn=new FunctionConnectMysql();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    UnitSql sql=new UnitSql();
		conn.ConnectMysql();
		CaptureLog("network.txt");		
		Thread.sleep(5000);
	    //PORTRAIT,LANDSCAPE
	    //driver.rotate(ScreenOrientation.LANDSCAPE);

	    ClickByID("rbMenu3", "切换至我的");
		  Thread.sleep(sleepBase * 1);
		    boolean flag2 = driver.findElementById("tvTitle").getText().equals("我的");
		    if (flag2 == true){
			       WritetoFile(filename,bf,"进入我的menu成功!");	  
			   
		    }else{
		    	WritetoFile(filename,bf,"进入我的menu失败!");	 
		    	 PrintScreen("Smoke19_EnterGuanJia.png");
		    	 status = "f";
			  	   sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
		    }	
	    

		    System.out.println( driver.getOrientation());  
		    driver.rotate(ScreenOrientation.LANDSCAPE);
		    Thread.sleep(1000);
	    System.out.println("切换横屏"+ driver.getOrientation());
	    
	    driver.rotate(ScreenOrientation.PORTRAIT);
	    Thread.sleep(1000);
		//driver.rotate(ScreenOrientation.PORTRAIT);
		 System.out.println("切换竖屏"+ driver.getOrientation());
		 
		 //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	
	
	
	
	
	// basic operation
	
	
	 /*************************************
	   IfisAppInstalled:判断app是否安装成功；不成功则再次安装
	 *************************************/
	   private	void IfisAppInstalled(String appPackage) throws Exception{
	    	boolean  install = driver.isAppInstalled("appPackage");
	    	if(!install == true ){
	    		driver.installApp("app");
	    	}
	    	else{ 
	    		driver.resetApp();
	    		System.out.print("App is exist");
	    		}
	   }
	    	
	    	
	    	
	
	 /*************************************
	   CaptureLog:截取adb log
	 *************************************/
	static void CaptureLog(String logname) {
		Runtime r = Runtime.getRuntime();
		try {

			Process p = r.exec("cmd.exe /c adb logcat -s SSA >"
							+ logname);

		} catch (Exception e) {
			System.out.println("error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	
	 /*************************************
	   endCaptureLog:停止截取adb log
	 *************************************/
	public static void endCaptureLog() {

		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
			} catch (IOException e) {
			e.printStackTrace();
		}
	}


	 /*************************************
	 ClickByID:根据元素ID进行点击操作，并输出log
	 *************************************/
	private void ClickByID(String id, String log) throws InterruptedException {
		System.out.println("Click " + log);
		driver.findElementById(id).click();
		Thread.sleep(sleepBase * 1);
//		PrintScreen(udid + "_" + log + ".jpg");
	}

	
	
	 /*************************************
	 ClickByID:根据元素ID进行点击操作，并输出log
	 *************************************/
	private void ClickByName(String name, String log) throws InterruptedException {
		System.out.println("Click " + log);
		driver.findElementByName(name);
		Thread.sleep(sleepBase * 1);
//		PrintScreen(udid + "_" + log + ".jpg");
	}
	
	
	
	 /*************************************
	 ClickByIndex:根据元素class及index进行点击操作，并输出log
	 *************************************/
	private void ClickByIndex(String className, int index, String log)
			throws InterruptedException {
		java.util.List<WebElement> arr = driver
				.findElementsByClassName(className);
		arr.get(index).click();
		System.out.println("Click " + log);
		Thread.sleep(sleepBase * 2);
//		PrintScreen(udid + "_" + log + ".jpg");
	}

	
	 /*************************************
	 SendKeysByID:根据元素ID进行输入，
	 *************************************/
	private static void SendKeysByID(String id, String keys)
			throws InterruptedException {
		System.out.println("Send " + keys);
		driver.findElementById(id).sendKeys(keys);
		Thread.sleep(sleepBase * 2);
	}

	
	 /*************************************
	  PrintScreen:输入文件名称，截屏保存
	 *************************************/
	private void PrintScreen(String fileName) throws InterruptedException {
		System.out.println(fileName);

		File file = null;
		try {
			file = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	 /*************************************
	   swipeScreen:输入起始坐标，进行屏幕滑动操作
	 *************************************/
	public static void swipeScreen(Double startX, Double startY, Double endX,
			Double endY, Double duration, int repeat, AndroidDriver driver) {
		JavascriptExecutor js = driver;
		java.util.HashMap<String, Double> swipeObj = new java.util.HashMap<String, Double>();
		swipeObj.put("startX", startX);
		swipeObj.put("startY", startY);
		swipeObj.put("endX", endX);
		swipeObj.put("endY", endY);
		for (int i = 0; i < repeat; i++) {
			try {
				js.executeScript("mobile: flick", swipeObj);
			} catch (Exception ex) {
				System.out.println("swipe failed");
			}
		}

	}

	
	 /*************************************
	   scroll:输入滚动方向，进行屏幕滚动操作
	 *************************************/
	public static void scroll(String direction, AndroidDriver driver) {
		JavascriptExecutor js = driver;
		java.util.HashMap<String, String> scrollObject = new java.util.HashMap<String, String>();
		scrollObject.put("direction", direction);
		js.executeScript("mobile: scroll", scrollObject);
	}

	
	
	 /*************************************
	   ClickBack:根据输入次数，点击back键
	 *************************************/
	private void ClickBack(int num, String log) throws InterruptedException {
		// Thread.sleep(2000);
		int n = 0;
		while (n < num) {
			((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.BACK);
			n = n + 1;
			// System.out.println("Tap  back button " + log);
			Thread.sleep(sleepBase * 1);
		}
		// PrintScreen(udid+ "_" + log + ".jpg");
	}

	
	 /*************************************
	   ClickHome:根据输入次数，点击home键
	 *************************************/
	private void ClickHome(int num, String log) throws InterruptedException {
		// Thread.sleep(2000);
		int n = 0;
		while (n < num) {
			((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.HOME);
			n = n + 1;
		}
		System.out.println("Tap  back button " + log);
		Thread.sleep(sleepBase * 1);
		PrintScreen(udid + "_" + log + ".jpg");
	}

	
	 /*************************************
	   isElementExsitID:根据输入的ID判断元素是否存在
	 *************************************/
	@SuppressWarnings("finally")
	public static boolean isElementExsitByID(String ID) {
		boolean flag = false;
		try {
			WebElement element = driver.findElementById(ID);
			flag = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + ID.toString() + " is not exsit!");
			flag = false;
		} finally {
			return flag;
		}
	}

	
	 /*************************************
	   isElementExsitByText:根据输入的text判断元素是否存在
	 *************************************/
	@SuppressWarnings("finally")
	public boolean isElementExsitByText(String text) {
		boolean flag = false;
		try {
			WebElement element = driver.findElementByName(text);
			flag = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + text.toString() + " is not exsit!");
			flag = false;
		} finally {
			return flag;
		}
	}


	 /*************************************
	   isElementExsitByClass:根据输入的text判断元素是否存在
	 *************************************/
	@SuppressWarnings({ "finally", "unchecked" })
	public boolean isElementExsitByIndex(String classname,int index) {
		boolean flag = false;
		try {
			java.util.List<WebElement> element = (List<WebElement>) driver.findElementsByClassName(classname).get(index);
			flag = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + classname.toString() + " is not exsit!");
			flag = false;
		} finally {
			return flag;
		}
	}

	
	 /*************************************
	 inputAccountInfo:输入账号名称及密码
	 *************************************/
	public static void inputAccountInfo(String name,String password) throws Exception{
//	    driver.findElementById("account").clear();
		SendKeysByID("account",name);
	    Thread.sleep(sleepBase*1);
	    ((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.BACK);
	    Thread.sleep(sleepBase*1);
//	    driver.findElementById("password").clear();
	    SendKeysByID("password",password);
	    Thread.sleep(sleepBase*1);
	    ((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.BACK);
	}
	
	 /*************************************
	 isLogged:判断当前是否已登录；通过account区域是否有值来判断；有则表已登录过；无则表未登录过
	 *************************************/
	public boolean isLogged(String validationID) throws Exception{
		boolean flag = driver.findElementById(validationID).getText().equals("用户名");
		if(flag == true)
			return false;
		else
			return true;
	}
	
	
	
	 /*************************************
	 WritetoFile():向文件filename中添加内容，内容为inputStrings
	 *************************************/
	public void WritetoFile(File filename, BufferedWriter bf, String inputStrings) throws Exception{
		if(!filename.exists()){
			filename.createNewFile();
		}		
		bf.append(inputStrings);	
		bf.newLine();
	}
	
	
	 /*************************************
	 CloseFile():关闭文件
	 *************************************/
	public void CloseFile(BufferedWriter bf) throws Exception{
		bf.flush();
		bf.close();
	}
}
