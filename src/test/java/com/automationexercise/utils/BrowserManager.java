package com.automationexercise.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel; // ← CORRECT IMPORT
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserManager {
    
    static {
        // Suppress logs
        System.setProperty("webdriver.firefox.logfile", "NUL");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NUL");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }
    
    public static WebDriver doBrowserSetup() throws IOException {
        WebDriver driver = null;
        String name = PropertiesLoader.loadProperty("browser.name");
        
        if (name.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            
            // Create Firefox profile with ad-blocking
            FirefoxProfile profile = new FirefoxProfile();
            
            // === COMPREHENSIVE AD BLOCKING ===
            
            // 1. Block notifications and popups
            profile.setPreference("dom.webnotifications.enabled", false);
            profile.setPreference("dom.push.enabled", false);
            profile.setPreference("dom.disable_beforeunload", true);
            profile.setPreference("dom.popup_allowed_events", "");
            
            // 2. Block ALL images (ads often load as images)
            profile.setPreference("permissions.default.image", 2);
            
            // 3. Block iframes (many ads load in iframes)
            profile.setPreference("permissions.default.subdocument", 2);
            
            // 4. Block third-party cookies (tracking)
            profile.setPreference("network.cookie.cookieBehavior", 1);
            
            // 5. Enable tracking protection (STRICT mode)
            profile.setPreference("privacy.trackingprotection.enabled", true);
            profile.setPreference("privacy.trackingprotection.socialtracking.enabled", true);
            profile.setPreference("privacy.trackingprotection.fingerprinting.enabled", true);
            profile.setPreference("privacy.trackingprotection.cryptomining.enabled", true);
            
            // 6. Block Flash and plugins
            profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
            profile.setPreference("plugin.state.flash", 0);
            
            // 7. Block WebRTC (prevents some ad networks)
            profile.setPreference("media.peerconnection.enabled", false);
            
            // 8. DNS over HTTPS (blocks some ad domains)
            profile.setPreference("network.trr.mode", 2);
            
            // 9. Disable prefetching (prevents pre-loading ads)
            profile.setPreference("network.dns.disablePrefetch", true);
            profile.setPreference("network.prefetch-next", false);
            
            // Suppress console logs
            profile.setPreference("devtools.console.stdout.content", false);
            profile.setPreference("browser.dom.window.dump.enabled", false);
            
            // Allow unsigned extensions (for development)
            profile.setPreference("xpinstall.signatures.required", false);
            
            firefoxOptions.setProfile(profile);
            firefoxOptions.setLogLevel(FirefoxDriverLogLevel.FATAL); // ← FIXED: Use FirefoxDriverLogLevel
            
            // Create driver instance
            driver = new FirefoxDriver(firefoxOptions);
            
            // ===== INSTALL UBLOCK ORIGIN EXTENSION =====
            installFirefoxExtension((FirefoxDriver) driver);
            
            // Wait for extension to load
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        } else if (name.equalsIgnoreCase("Chrome")) {
            String pathExtension = PropertiesLoader.loadProperty("chrome.extension.adblock.path");
            WebDriverManager.chromedriver().setup();
            System.setProperty("webdriver.chrome.silentOutput", "true");
            
            ChromeOptions chromeOptions = new ChromeOptions();
            
            // Suppress logs
            chromeOptions.addArguments("--log-level=3");
            chromeOptions.addArguments("--silent");
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-logging"});
            
            // Block ads
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-notifications");
            
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("profile.managed_default_content_settings.images", 2);
            
            chromeOptions.setExperimentalOption("prefs", prefs);
            
            // Load ad blocker extension if available
            if (pathExtension != null && !pathExtension.isEmpty() && new File(pathExtension).exists()) {
                chromeOptions.addArguments("load-extension=" + pathExtension);
            }
            
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }
    
    /**
     * Installs uBlock Origin extension in Firefox
     */
    private static void installFirefoxExtension(FirefoxDriver driver) {
        try {
            // Path to your uBlock Origin XPI file
            String xpiFileName = "ublock_origin.xpi";
            
            // Try multiple possible locations
            String[] possiblePaths = {
                "src/test/resources/" + xpiFileName,
                "src/main/resources/" + xpiFileName,
                "extensions/" + xpiFileName,
                xpiFileName
            };
            
            File xpiFile = null;
            for (String pathStr : possiblePaths) {
                File file = new File(pathStr);
                if (file.exists()) {
                    xpiFile = file;
                    System.out.println("✓ Found uBlock Origin at: " + pathStr);
                    break;
                }
            }
            
            if (xpiFile != null && xpiFile.exists()) {
                Path xpiPath = xpiFile.toPath();
                String extensionId = driver.installExtension(xpiPath, true); // true = temporary install
                //System.out.println("✓ uBlock Origin installed successfully! Extension ID: " + extensionId);
            } else {
                System.out.println("⚠ uBlock Origin XPI file not found. Continuing without extension.");
                System.out.println("  Searched in: " + String.join(", ", possiblePaths));
            }
            
        } catch (Exception e) {
            System.out.println("⚠ Could not install uBlock Origin extension: " + e.getMessage());
        }
    }
}
