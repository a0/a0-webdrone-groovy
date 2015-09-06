package cl.a0.webdrone.groovy

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.safari.SafariDriver

class Browser {
  String    browser = "firefox"
  WebDriver driver

  protected Browser(Map map) {
    map?.each { k, v ->
      try {
        this[k] = v
      } catch (Throwable t) {
        println "Ignoring key '${k}': '${v}'"
      }
    }

    if (browser == "chrome") {
      driver = new ChromeDriver()
    } else if (browser == "firefox") {
      driver = new FirefoxDriver()
    } else if (browser == "safari") {
      driver = new SafariDriver()
    } else {
      throw new RuntimeException("Unknown browser: [${browser}]")
    }
  }

  def quit() {
    driver.quit()
  }
}
