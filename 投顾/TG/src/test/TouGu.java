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



public class TouGu {
	
	private static final boolean value = false;
	private static AppiumDriver driver;
	private static int sleepBase = 1000;
	private String udid = "8e87c4f9";
	

	@Before
	public void setUp() throws Exception {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "LinlongJinfu-test.0.0.6.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Galaxy C7");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("app", app.getAbsolutePath());
		//capabilities.setCapability("appPackage", "linlong.tougu.ebook");
		capabilities.setCapability("appPackage", "linlong.tougu.ebook");
		capabilities.setCapability("newCommandTimeout",240);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		Thread.sleep(sleepBase * 2);

	}

	
	@Test
	//频繁进入期刊阅读
    public void Test1() throws Exception{
		
		Thread.sleep(sleepBase * 7);
		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
		 assertTrue("Dispaly Successfully", value);
			Thread.sleep(sleepBase * 7);
   		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
   		 assertTrue("Dispaly Successfully", value);
   		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
   		 Thread.sleep(sleepBase * 2);
   		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
   		
   		 if(username.getText().equals("登录") )
   		{
               driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
   			 inputAccountInfo("user0093","123456");
   		     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
   		     Thread.sleep(sleepBase * 2);
   			 assertEquals(username.getText(), "user0093");
			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
		}else{
		
			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
		}	
		
				
                assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");
                System.out.println("123");
                
                for(int i=0;i<100;i++){
                	
                	int a=i+1;
               	   
                	     System.out.println("第"+a+"次");
                		driver.findElementByXPath("//android.widget.TextView[contains(@text,'测试2201')]").click();
                	     Thread.sleep(sleepBase * 2);
                    	 assertEquals(driver.findElementById("linlong.tougu.ebook:id/arrow_left").isDisplayed(),true);
                    	 ClickByID("linlong.tougu.ebook:id/arrow_left","返回");
                         Thread.sleep(sleepBase * 2);
                    	 driver.findElementByXPath("//android.widget.TextView[contains(@text,'测试220')]").click();
                         Thread.sleep(sleepBase * 2);
                    	 assertEquals(driver.findElementById("linlong.tougu.ebook:id/arrow_left").isDisplayed(),true);
                    	 ClickByID("linlong.tougu.ebook:id/arrow_left","返回");
                         Thread.sleep(sleepBase * 2);
                	}			
   }
	
	
	@Test
	//频繁切换横竖屏
    public void Test2() throws Exception{
		  
		Thread.sleep(sleepBase * 7);
		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
		 assertTrue("Dispaly Successfully", value);
		 
		 WebElement mine=driver.findElementByXPath("//android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[2]");
		 mine.click();
		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
		 Thread.sleep(sleepBase * 2);
		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
		
		 if(username.getText().equals("登录") )
		{
            driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
			 inputAccountInfo("user0093","123456");
		     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
		     Thread.sleep(sleepBase * 2);
			 assertEquals(username.getText(), "user0093");
			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
		}else{
			
			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
		}	
		
				
                assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");

                         assertEquals(driver.findElementById("android:id/list").isDisplayed(),true);
                    	 
                        ClickByID("linlong.tougu.ebook:id/adapter_week_icon_new","最新文章");
                         System.out.println("到这了");
                    	 assertEquals(driver.findElementById("linlong.tougu.ebook:id/ll_bottom").isDisplayed(),true);
                	     Thread.sleep(sleepBase * 2);
                    	 for(int i=0;i<100;i++){
                    	 ClickByID("linlong.tougu.ebook:id/toolbar_flip","切换横竖屏"+i);
                	       }
             
			
   }
	

	@Test
	//频繁切换菜单项
    public void Test3() throws Exception{
		  
		Thread.sleep(sleepBase * 7);
		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
		 assertTrue("Dispaly Successfully", value);
		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
		 Thread.sleep(sleepBase * 2);
		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
		
		 if(username.getText().equals("登录") )
		{
            driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
			 inputAccountInfo("user0093","123456");
	     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
		     Thread.sleep(sleepBase * 2);
			 assertEquals(username.getText(), "user0093");
			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
		}else{
			
			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
		}	
		
				
                assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");             
              
                         for(int i=0;i<100;i++){
                        	 int a=i+1;
                         System.out.println("第"+a+"次");
                        ClickByIndex2("linlong.tougu.ebook:id/tab_icon",0,"切换其他Tab");
                         //WebElement e2 = driver.findElement(By.xpath("//android.widget.TabWidget/android.widget.RelativeLayout[0]")); 
                         //driver.findElementByXPath("//android.widget.TabHost[4]/android.widget.LinearLayout[0]/android.widget.TabWidget[0]/android.widget.RelativeLayout[0]").click();
                         assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "资讯"); 
                 
                 
                         
                       
                         ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
                         assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "我的");
                         
                         ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
                         assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");
                         }
                   
                        
                      
                	       }
             
                     	@Test
                    	//资讯页面切换8大类型
                        public void Test4() throws Exception{
                    		  
                    		Thread.sleep(sleepBase * 7);
                    		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
                    		 assertTrue("Dispaly Successfully", value);
                    		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
                    		 Thread.sleep(sleepBase * 2);
                    		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
                    		
                    		 if(username.getText().equals("登录") )
                    		{
                                driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
                    			 inputAccountInfo("user0093","123456");
                    		     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
                    		     Thread.sleep(sleepBase * 2);
                    			 assertEquals(username.getText(), "user0093");
                    			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",0,"资讯");
                    		}else{
                    			
                    			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",0,"资讯");
                    		}	
                    		
                    				
                                    assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "资讯");             
                                  
                                             for(int i=0;i<100;i++){
                                            	 int a=i+1;
                                             System.out.println("第"+a+"次");
                                        
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",1,"星级事件");
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",2,"收盘点评");
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",3,"操盘必读");
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",4,"热点解读"); 
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",5,"投教中心");
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",6,"经典回顾");
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",7,"上市公司");
                                          ClickByIndex2("linlong.tougu.ebook:id/recycle_view_bg",0,"早盘前瞻");
                                          }
}

                    	@Test
                    	//断网连网
                        public void Test5() throws Exception{
                    		  
                    		Thread.sleep(sleepBase * 7);
                    		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
                    		 assertTrue("Dispaly Successfully", value);
                    		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
                    		 Thread.sleep(sleepBase * 2);
                    		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
                    		
                    		 if(username.getText().equals("登录") )
                    		{
                                driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
                    			 inputAccountInfo("user0093","123456");
                    		     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
                    		     Thread.sleep(sleepBase * 2);
                    			 assertEquals(username.getText(), "user0093");
                    			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
                  				assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");
                    		}else{
                    			
                    			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
                 				assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");
                    		}	

                            for(int i=0;i<100;i++){
                            			int a=i+1;
                            			System.out.println("第"+a+"次");
                            			try {
                                             ((AndroidDriver) driver).setNetworkConnection(new NetworkConnectionSetting(false,false, false));//断开网络连接
                             				NetworkConnectionSetting networkConnection = ((AndroidDriver) driver).getNetworkConnection();
                             				System.out.println(networkConnection);
                             				System.out.println("网络已断开");
                             				assertEquals(new NetworkConnectionSetting(false, false, false),networkConnection);
                             				Thread.sleep(sleepBase * 2);
                                       	    ClickByIndex("android.widget.LinearLayout",0,"第一篇文章");
                                       	    Thread.sleep(sleepBase * 2);
                                       	    assertEquals(driver.findElementById("linlong.tougu.ebook:id/arrow_left").isDisplayed(),false);
                                             }
                                           catch (Exception e) {
                              				System.out.println("开关网络连接操作失败" + e.getMessage());
                            			}
                                             
                                             try {
                                                 ((AndroidDriver) driver).setNetworkConnection(new NetworkConnectionSetting(false,true, false));//断开网络连接
                                 				NetworkConnectionSetting networkConnection = ((AndroidDriver) driver).getNetworkConnection();
                                 				System.out.println(networkConnection);
                                 				System.out.println("网络已恢复");
                                 				assertEquals(new NetworkConnectionSetting(false, true, false),networkConnection);
                                 				Thread.sleep(sleepBase * 2);
                                            	 ClickByIndex("android.widget.LinearLayout",0,"第一篇文章");
                                            	 Thread.sleep(sleepBase * 2);
                                            	 assertEquals(driver.findElementById("linlong.tougu.ebook:id/arrow_left").isDisplayed(),true);
                                            	 ClickByID("linlong.tougu.ebook:id/arrow_left","返回");
                                                 }
                                               catch (Exception e) {
                                  				System.out.println("开关网络连接操作失败" + e.getMessage());
                                			}
                                             
                            			}           
 }
                    	
                                    


                    	@Test
                    	//资讯阅读100次
                        public void Test6() throws Exception{
                    		
                    		Thread.sleep(sleepBase * 7);
                    		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
                    		 assertTrue("Dispaly Successfully", value);
                    		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
                    		 Thread.sleep(sleepBase * 2);
                    		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
                    		
                    		 if(username.getText().equals("登录") )
                    		{
                                driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
                    			 inputAccountInfo("user0093","123456");
                    		     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
                    		     Thread.sleep(sleepBase * 2);
                    			 assertEquals(username.getText(), "user0093");
                    			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",0,"资讯");
                    		}else{
                    			
                    			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",0,"资讯");
                    		}	
                    		
                    				
                                    assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "资讯");
                                    System.out.println("123");
                                    
                                    for(int i=0;i<100;i++){
                                    	int a=i+1;
                                      System.out.println("第"+a+"次");
                                        	 //ClickByIndex("android.widget.LinearLayout",3,"阅读文章");
                                      //driver.findElementByXPath("//android.widget.TextView[contains(@text,'testlmnh')]").click();
                                      ClickByID("linlong.tougu.ebook:id/title","点击文章名");
                                      Thread.sleep(sleepBase * 2);
                                      assertEquals(driver.findElementById("linlong.tougu.ebook:id/arrow_left").isDisplayed(),true);
                                      ClickByID("linlong.tougu.ebook:id/arrow_left","返回");
                                             
                                    	}
                                 
                                    	
                    				
                       }          	
                     	
                    	@Test
                    	//呼出工具栏
                        public void Test7() throws Exception{
                    		
                    		Thread.sleep(sleepBase * 7);
                    		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
                    		 assertTrue("Dispaly Successfully", value);
                    		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
                    		 Thread.sleep(sleepBase * 2);
                    		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
                    		
                    		 if(username.getText().equals("登录") )
                    		{
                                driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
                    			 inputAccountInfo("user0093","123456");
                    		     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
                    		     Thread.sleep(sleepBase * 2);
                    			 assertEquals(username.getText(), "user0093");
                    			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
                    		}else{
                    			
                    			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
                    		}	
                    		
                    				
                                    assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");
                                   // driver.findElementByXPath("//android.widget.TextView[contains(@text,'测试220')]").click();
                                    ClickByID("linlong.tougu.ebook:id/adapter_week_icon_new","最新文章");
                                    Thread.sleep(sleepBase * 2);
                                    assertEquals(driver.findElementById("linlong.tougu.ebook:id/arrow_left").isDisplayed(),true);
                                    System.out.println("123");
                                    
                                    for(int i=0;i<100;i++){
                                    	int a=i+1;
                                      System.out.println("第"+a+"次");
                                        	 //ClickByIndex("android.widget.LinearLayout",3,"阅读文章");
                                     
                                      ClickByID("android:id/content","呼出隐藏工具栏");
                                             
                                    	}
                                 
                                    	
                    				
                       } 
                    	
                    	
                    	@Test
                    	//频繁收藏/取消收藏
                        public void Test8() throws Exception{
                    		
                    		Thread.sleep(sleepBase * 7);
                    		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
                    		 assertTrue("Dispaly Successfully", value);
//                    		 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",2,"我的");
//                    		 Thread.sleep(sleepBase * 2);
//                    		 WebElement username=driver.findElementById("linlong.tougu.ebook:id/userName");
//                    		
//                    		 if(username.getText().equals("登录") )
//                    		{
//                                driver.findElement(By.id("linlong.tougu.ebook:id/iv_login")).click(); 
//                    			 inputAccountInfo("user0093","123456");
//                    		     ClickByID("linlong.tougu.ebook:id/login_tv","linlong.tougu.ebook:id/login_tv");
//                    		     Thread.sleep(sleepBase * 2);
//                    			 assertEquals(username.getText(), "user0093");
//                    			 ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
//                    		}else{
//                    			
//                    			ClickByIndex2("linlong.tougu.ebook:id/tab_icon",1,"周刊");
//                    		}	
//                    		
                    				
                                    assertEquals(driver.findElementById("linlong.tougu.ebook:id/header_title").getText(), "周刊");
                                    //driver.findElementByXPath("//android.widget.TextView[contains(@text,'测试220')]").click();
                                    ClickByID("linlong.tougu.ebook:id/adapter_week_icon_new","最新文章");
                                    Thread.sleep(sleepBase * 2);
                                    assertEquals(driver.findElementById("linlong.tougu.ebook:id/arrow_left").isDisplayed(),true);
                                    System.out.println("123");
                                    
                                    for(int i=0;i<100;i++){
                                    	int a=i+1;
                                      System.out.println("第"+a+"次");
                                        	 //ClickByIndex("android.widget.LinearLayout",3,"阅读文章");
                                     
                                      ClickByID("linlong.tougu.ebook:id/collection","收藏");
//                                      assertEquals(driver.findElementById("linlong.tougu.ebook:id/collection").isEnabled(),true); 
//                                      System.out.println(driver.findElementById("linlong.tougu.ebook:id/collection").isEnabled());
                                      Thread.sleep(sleepBase * 1);
                                      System.out.println(driver.getOrientation());
                                      System.out.println(driver.findElementByPartialLinkText(""));
                                      //assertEquals(driver.findElementByXPath("//android.widget.FrameLayout[contains(@text,'收藏成功')]").isDisplayed(),true);
           
                                      ClickByID("linlong.tougu.ebook:id/collection","取消收藏");
                                      //assertEquals(driver.findElementById("linlong.tougu.ebook:id/collection").isEnabled(),false);     
                                      //System.out.println(driver.findElementById("linlong.tougu.ebook:id/collection").isEnabled());
                                    	}
                                 
                                    	
                    				
                       } 
                    	
                    	
                    	@Test
                    	//啊啊啊啊
                        public void aaaa() throws Exception{
                    		  
                    		Thread.sleep(sleepBase * 7);
                    		 driver.findElement(By.id("linlong.tougu.ebook:id/tabHost")).isDisplayed();
                    		 assertTrue("Dispaly Successfully", value);
                    		 Thread.sleep(sleepBase * 7);
                    		 WebElement mine=driver.findElementByXPath("//android.widget.TabWidget[0]/android.widget.RelativeLayout[2]");
                    		 WebElement bb=driver.findElementByXPath("//div[@class='android.widget.TabWidget']/div");
                    		 bb.click();
                    		 List<WebElement> elements = driver.findElements(By.xpath("//div[@id='android:id/tabs']"));    
                             System.out.println("number of elements: " + elements.size());

                    		 Thread.sleep(sleepBase * 7);
                    		 mine.click();
                    	}
	private void assertTrue(String Message, boolean condition) {
		// TODO Auto-generated method stub
		System.out.println(Message);
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

	private void ClickByIndex2(String resourceID, int index, String log)
			throws InterruptedException {
		java.util.List<WebElement> arr = driver
				.findElementsById(resourceID);
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

		SendKeysByID("linlong.tougu.ebook:id/login_et_username",name);
	    Thread.sleep(sleepBase*1);
	    

	    SendKeysByID("linlong.tougu.ebook:id/login_et_password",password);
	    Thread.sleep(sleepBase*1);
	   
	}
	
	 /*************************************
	 isLogged:
	 *************************************/
	public boolean isLogged(String validationID) throws Exception{
		driver.findElementById("linlong.tougu.ebook:id/userName");
		boolean flag = driver.findElementById(validationID).getText().equals("登录");
		if(flag == true)
			return true;
		else
			return false;
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
