package shoeStorePkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;

public class Story2 {
	static WebDriver driver;
	static String monthName;
	//static Logger logger = Logger.getLogger("devpinoyLogger");
	static final Category logger = Category.getInstance(Story2.class);
	static final String LOG4J_PROPERTIES_FILE = "lib/log4j.properties";

	public static void main(String args[]) {
		new Story2().startTesting();
	}

	public void startTesting() {
		initializeLogger();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://sleepy-beach-51039.herokuapp.com");

		String home = "Home-Link";
		String RptIssue = "Report An Issue-Link";
		String issue = "Issues-Link";
		String problemDef = "Problem Definition-Link";
		checkMessageRemindMe(home);	

		List<WebElement> numOfMonths = driver.findElements(By.xpath("//html/body/header/div[1]/nav/ul/li"));

		//Iterates for every month tab (including AllShoes tab)
		for(int Month=1; Month<=numOfMonths.size(); Month++){
			monthName = driver.findElement(By.xpath("//html/body/header/div[1]/nav/ul/li["+Month+"]/a")).getText();
			driver.findElement(By.xpath("//html/body/header/div[1]/nav/ul/li["+Month+"]/a")).click();

			//Check for "Remind me of new shoes" Email textBox for each month
			try{
				WebElement TextBox = driver.findElement(By.id("remind_email_input"));
				if(TextBox.isDisplayed()){
					checkMessageRemindMe(monthName);						

				}else
				{
					System.out.println("Email text box is not present");
					logger.info("Email text box is not present");
				}			

				//check if "Notify me when this shoe is available" Email textBoxs are present

				List<WebElement> links;
				List<WebElement> submits;
				links=driver.findElements(By.className("notification_email_input"));
				submits = driver.findElements(By.className("notification_email_submit"));

				String emailID1 ="yes@hotmail.com";
				System.out.println("Num of Notify Email Boxes  :"+links.size());
				logger.info("Num of Notify Email Boxes  :"+links.size());
				//If there are Notify textboxes in that month, 
				//check for each of the boxes to make sure it displays the correct message on successful submission of email.
				if(links.size()>0)
				{
					for(int i=0; i<links.size(); i++){
						int totalNotifyBoxes = i+1;

						links.get(i).sendKeys(emailID1);					
						submits.get(i).click();

						String actualText1 = driver.findElement(By.id("flash")).getText();					
						String expectedText1 = "Thanks! We will notify you when this shoe is available at this email: " + emailID1;

						if(actualText1.equals(expectedText1)){
							System.out.println("Notify Email Box No."+totalNotifyBoxes+" Pass");
							logger.info("Notify Email Box No."+totalNotifyBoxes+" Pass");
							logger.info("Actual: "+i+"  " +actualText1);
							logger.info("Expted: "+ i +"  "+expectedText1);

						}else
						{
							System.out.println("Notify Email Box No."+totalNotifyBoxes+ ": Not displaying the expected text");
							logger.info("Notify Email Box No."+totalNotifyBoxes+ ": Not displaying the expected text");
						}
						links=driver.findElements(By.className("notification_email_input"));
						submits = driver.findElements(By.className("notification_email_submit"));
					}
				}
			}catch(Exception e)
			{
				System.out.println("Email Text Box not presesnt");
				logger.info("Email Text Box not presesnt");
			}		

		}
		driver.findElement(By.linkText("Report An Issue")).click();
		checkMessageRemindMe(RptIssue);
		driver.findElement(By.linkText("Issues")).click();
		checkMessageRemindMe(issue);
		driver.findElement(By.linkText("Problem Definition")).click();
		checkMessageRemindMe(problemDef);

		System.out.println("Done");
	}

	public void checkMessageRemindMe(String monthName){

		String emailID ="yes@hotmail.com";
		WebElement myElement = driver.findElement(By.id("remind_email_input"));
		myElement.sendKeys(emailID);
		driver.findElement(By.id("remind_email_submit")).click();
		String actualText = driver.findElement(By.id("flash")).getText();
		String expectedText = "Thanks! We will notify you of our new shoes at this email: " + emailID;
		System.out.println(monthName);
		logger.info(monthName);
		System.out.println("Email text box present: Pass");
		logger.info("Email text box present: Pass");
		if(actualText.equals(expectedText)){
			//System.out.println(monthName);
			//System.out.println("Remind Me Email Box: Pass");
			//logger.info(monthName);
			logger.info("Remind Me Email Box: Pass");
			logger.info("RemindTxBx Actual: " +actualText);
			logger.info("RemindTxBx Expted: "+expectedText);

		}else
		{
			System.out.println(monthName);
			System.out.println("Remind Me Email Box:  Not displaying the expected text");
		}

	}

	private void initializeLogger()
	{
		Properties logProperties = new Properties();

		try
		{
			logProperties.load(new FileInputStream(LOG4J_PROPERTIES_FILE));
			PropertyConfigurator.configure(logProperties);
		}
		catch(IOException e)
		{
			throw new RuntimeException("Unable to load logging property " + LOG4J_PROPERTIES_FILE);
		}
	}

}
