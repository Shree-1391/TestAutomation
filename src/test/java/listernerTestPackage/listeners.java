package listernerTestPackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.main.optimus.BaseClass;

public class listeners extends BaseClass implements ITestListener
{	
//	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		 try{
		        driver.quit();
		        System.out.println("Application closed out successfully");
		        }catch(Exception e){
		            System.out.println(e.getMessage());
		        }
		    }		

//	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
//	@Override
	public void onTestFailure(ITestResult Result) {
		String res = Result.getName();
		try {
			if(driver==null)
			{
				driver = setupDriver();
			}
			getScreenshot(driver,res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}	

}
