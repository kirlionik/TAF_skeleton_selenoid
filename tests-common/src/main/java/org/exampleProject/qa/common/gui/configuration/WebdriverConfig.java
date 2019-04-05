package org.exampleProject.qa.common.gui.configuration;
/*
* Copyright 2002 - 2017 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.exampleProject.qa.common.gui.services.webdriver.WrappedWebdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import static javaslang.API.Case;
import static javaslang.API.Match;

/**
 * Created by Anton_Shapin on 5/23/17.
 */
@Configuration
@Slf4j
public class WebdriverConfig {
    private final String CHROME = "Chrome";
    private final String FIREFOX = "Firefox";
    private final String CHROME_SELENOID = "Chrome_Selenoid";
    private final String FIREFOX_SELENOID = "Firefox_Selenoid";

    @Value("${WEBDRIVER_BROWSER:"+CHROME+"}")
    private String browserName;

    @Value("${SELENOID_URL:127.0.0.1:4444}")
    private String selenoidUrl;

    @Value("${FIREFOX_VERSION:latest}")
    private String firefoxVersion;

    @Value("${CHROME_VERSION:latest}")
    private String chromeVersion;

    @Bean(destroyMethod = "quit")
    public WrappedWebdriver webDriver() throws IOException {
        log.debug("Start initialization browser: {}", browserName);
        return Match(browserName).of(
                Case(CHROME::equalsIgnoreCase, this::initChrome),
                Case(FIREFOX::equalsIgnoreCase, this::initFirefox),
                Case(CHROME_SELENOID::equalsIgnoreCase, this::initSelenoidChrome),
                Case(FIREFOX_SELENOID::equalsIgnoreCase, this::initSelenoidFirefox)
        );
    }

    private WrappedWebdriver initSelenoidFirefox() {
        log.debug("Start Firefox initialization");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion(firefoxVersion);
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);

        RemoteWebDriver driver = null;
        try {
            log.debug("SELENOID URL is {}", selenoidUrl);
            driver = new RemoteWebDriver(
                    URI.create("http://" + selenoidUrl + "/wd/hub").toURL(),
                    capabilities
            );
        } catch (Exception e) {
            log.error("Error in Firefox initialization. Error message: {}", e.getMessage());
        }
        return new WrappedWebdriver(driver);
    }

    private WrappedWebdriver initSelenoidChrome() {
        log.debug("Start Chrome initialization");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion(chromeVersion);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        RemoteWebDriver driver = null;
        try {
            log.debug("SELENOID URL is {}", selenoidUrl);
            driver = new RemoteWebDriver(
                    URI.create("http://" + selenoidUrl + "/wd/hub").toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
            log.error("Error in chrome initialization. Error message: {}", e.getMessage());
        }
        return new WrappedWebdriver(driver);
    }

    private WrappedWebdriver initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return new WrappedWebdriver(driver);
    }

    private WrappedWebdriver initChrome() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return new WrappedWebdriver(driver);
    }
}
