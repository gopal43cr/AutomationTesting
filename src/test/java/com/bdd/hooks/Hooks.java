package com.bdd.hooks;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.automationexercise.tests.TestBasic;
import com.automationexercise.utils.BrowserManager;
import com.automationexercise.utils.PropertiesLoader;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends TestBasic {
	
	public static WebDriver driver;

    @Before
    public void setUp() throws IOException {
        String url = PropertiesLoader.loadProperty("url");
        WebDriver driver = BrowserManager.doBrowserSetup();
        tdriver.set(driver);
        getDriver().get(url);
    }

    @After
    public void tearDown() {
        getDriver().quit();
        tdriver.remove();
    }
}
