package shoeStorePkg;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Story1 {
	
	public static void main(String[] args){
		new Story1().startTesting();
	}

	public void startTesting(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);

		driver.get("https://sleepy-beach-51039.herokuapp.com");

		List<WebElement> numOfMonths = driver.findElements(By.xpath("//html/body/header/div[1]/nav/ul/li"));


		for(int Month=1; Month<numOfMonths.size(); Month++){//Iterates for every month
			String monthName = driver.findElement(By.xpath("//html/body/header/div[1]/nav/ul/li["+Month+"]/a")).getText();
			driver.findElement(By.xpath("//html/body/header/div[1]/nav/ul/li["+Month+"]/a")).click();
			List<WebElement> shoeList = driver.findElements(By.xpath("//html/body/ul/li"));
			System.out.println(monthName+": Num of shoes are  : " + shoeList.size());

			if(shoeList.size()!= 0){
				for(int rNum=1; rNum<=shoeList.size(); rNum++){//checks for price, description and image for each shoe in the month
					int priceRow =3;
					int descRow = 4;
					int imgRow = 6;
					List<WebElement> priceRowCells=driver.findElements(By.xpath("//html/body/ul/li["+rNum+"]/div/table/tbody/tr["+priceRow+"]/td"));
					System.out.println("Shoe no  : "+ rNum);
					String priceText = priceRowCells.get(1).getText();

					if(priceText.length()==0){
						//System.out.println(monthName+ ": Shoe no - " + rNum+": price is missing.");
						System.out.println("price: not Dispalyed");
					}else{
						System.out.println("price is:  "+ priceText);
					}

					List<WebElement> descRowCells=driver.findElements(By.xpath("//html/body/ul/li[1]/div/table/tbody/tr["+descRow+"]/td"));
					String descText = descRowCells.get(1).getText();
					//
					if(descText.length()==0){
						//System.out.println(monthName+ ": Shoe no - " + rNum+": Description is missing.");
						System.out.println("Description: not Dispalyed");
					}else{
						System.out.println("Description is:   "+ descText);
						//System.out.println("descText is not empty");
					}

					List<WebElement> imgRowCells=driver.findElements(By.xpath("//html/body/ul/li[1]/div/table/tbody/tr["+imgRow+"]/td/img"));
					WebElement img = imgRowCells.get(0);
					String value = img.getAttribute("src");
					if(imgRowCells.get(0).isDisplayed()){
						System.out.println("Image src is:   "+ value);
						//System.out.println("src is not empty");
					}else
					{
						System.out.println("Image src is: Empty");
					}

					if(priceText.length()!=0 && descText.length()!=0 && imgRowCells.get(0).isDisplayed()){
						System.out.println("Shoe no."+ rNum+ ": Pass");
					}else
					{
						System.out.println("Shoe no."+ rNum+ ": Fail");
					}


					/*if(value.length()==0)//working

				{
					//System.out.println(monthName+ ": Shoe no - " + rNum+": Image is missing.");
					System.out.println("Image: not Dispalyed");
				}else{
					System.out.println("Image src: "+ value);
				}*/

				}
			}
		}
		
		System.out.println("Done");
	}

}
