package cl.a0.webdrone.groovy

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.safari.SafariDriver

class Browser {
  String    browser = "chrome"
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

  private Open open
  Open getOpen() {
    open = open ?: new Open(this)
  }

  private Shot shot
  Shot getShot() {
    shot = shot ?: new Shot(this)
  }

  private Find find
  Find getFind() {
    find = find ?: new Find(this)
  }

  private Clic clic
  Clic getClic() {
    clic = clic ?: new Clic(this)
  }

  private Exec exec
  Exec getExec() {
    exec = exec ?: new Exec(this)
  }

  private Mark mark
  Mark getMark() {
    mark = mark ?: new Mark(this)
  }

  private Form form
  Form getForm() {
    form = form ?: new Form(this)
  }

  private Conf conf
  Conf getConf() {
    conf = conf ?: new Conf(this)
  }

  def quit() {
    driver.quit()
  }
}
