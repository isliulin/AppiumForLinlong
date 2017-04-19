package test;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Network {
	
	private static AppiumDriver driver;
	//AndroidDriver
	//private static AppiumDriver driver2;
	
	private static int sleepBase = 1000;
	private String udid = "8e87c4f9";
	private static String status;
	private static int build_id = 142;//版本号id
	private static int tester_id = 8;//测试员id
	private static int testplan_id = 1005;//测试计划id



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
//	capabilities.setCapability("unicodeKeyboard", "True");  //for input Chinese 
	capabilities.setCapability("newCommandTimeout",240);//Appium的默认的命令间隔时间是60s,此处设置为240s
	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
	Thread.sleep(sleepBase * 2);

}

@Test
public void network() throws Exception {
	//int tcversion_id = 1571;
	int i=0;

	Thread.sleep(12000);

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
	    	   System.out.println("进入大师成功");
	       }else{
	    	   System.out.println("进入我的menu失败"); 
	       }
            
		} catch (Exception e) {
			System.out.println("开关网络连接操作失败" + e.getMessage());
				
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
			    	 System.out.println("进入大师成功");
				   
			    }else{
			    	 System.out.println("进入我的menu失败"); 
			    	
			    }	
		} catch (Exception e) {
			  
			System.out.println("开关网络连接操作失败" + e.getMessage());
			status = "f";	 
		}
	
		    
		    if(i==99){
				   status = "p";
				  // sql.InserData(build_id, tester_id, status, testplan_id, tcversion_id);
			   }
   }

   
}

private void ClickByID(String id, String log) throws InterruptedException {
	System.out.println("Click " + log);
	driver.findElementById(id).click();
	Thread.sleep(sleepBase * 1);
//	PrintScreen(udid + "_" + log + ".jpg");
}
}


