package com.imademethink_galen_automation;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;


public class GalenExample4_ImageCompare
{

	//	Testing image comparison
    String galenSampleUrl 		= "http://help.websiteos.com/websiteos/example_of_a_simple_html_page.htm";
    
    @Test
    public void homePageLayoutTest() throws IOException
    {
    	// testing done at maximum browser size which can be customised per user needs
        delay(3000);
        driver.manage().window().maximize();
        delay(3000);
        //Create a layoutReport object
        //checkLayout function checks the layout and returns a LayoutReport object
        LayoutReport objLayoutReport = 
        		Galen.checkLayout(driver, specFilePath, Arrays.asList("desktop_galen_image_compare"));

        //Create a galen test info list
        List<GalenTestInfo> objGalentestsList	= new LinkedList<GalenTestInfo>();
        //Create a GalenTestInfo object
        GalenTestInfo objSingleGalenTest 		= GalenTestInfo.fromString("ImageCompare main title here");
        //Get layoutReport and assign to test object
        objSingleGalenTest.getReport().layout(objLayoutReport, "ImageCompare single test title here");
        //Add test object to the tests list
        objGalentestsList.add(objSingleGalenTest);
        //Create a htmlReportBuilder object
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
        //Create a report under specified folder based on tests list
        htmlReportBuilder.build(objGalentestsList, "ReportFolder_ImageCompare");
        //If layoutReport has errors Assert Fail
        if (objLayoutReport.errors() > 0)
        {
        	System.out.println("Layout test failed for ImageCompare");
            Assert.fail();
        }
        System.out.println("Layout test PASSED for ImageCompare");
    }

    private WebDriver driver  = null;
    String specFilePath 			= "galen_spec_gspec/GalenImageCompare.gspec";
    int browserSizeLargeW 	= 1600; int browserSizeLargeH 	= 1200;
    int browserSizeMediumW= 1152; int browserSizeMediumH = 864;
    int browserSizeSmallW 	= 800; int browserSizeSmallH 		= 600;

    @Before
    public void setUp()
    {
		String browserDriverBasePath 				= new File("").getAbsoluteFile().toString() + File.separator + "Resources" + File.separator + "BrowserDriver" + File.separator; 
	    String browserDriverBasePathChrome 	= browserDriverBasePath + "chromedriver.exe";
		File chromeDriverExecutable 					= new File(browserDriverBasePathChrome);
	    chromeDriverExecutable.setExecutable(true);
		System.setProperty("webdriver.chrome.driver",browserDriverBasePathChrome);		
        driver 															= new ChromeDriver();
        driver.get(galenSampleUrl);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
    
	public void delay(int milliSec){
		try{Thread.sleep(milliSec);}catch(Exception d){}
	}
	
}