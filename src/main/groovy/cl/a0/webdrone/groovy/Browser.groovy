package cl.a0.webdrone.groovy

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.safari.SafariDriver

class Browser {
  String    browser = "chrome"
  boolean   create_outdir = false
  String    outdir = null
  WebDriver driver


  protected Browser(Map map) {
    map?.each { k, v ->
      try {
        this[k] = v
        println "Setting '${k}': '${v}'"
      } catch (Throwable t) {
        println "Ignoring key '${k}': '${v}'"
      }
    }

    if (create_outdir || outdir) {
      outdir = outdir ?: "webdrone_output_${new Date().format('Ymd_HM')}"
      this.conf = new Conf(this)
      this.conf.outdir = outdir
    }

    if (browser == "chrome") {
      if (outdir != null) {
        ChromeOptions chromeOptions = new ChromeOptions()
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", outdir);
        chromeOptions.setExperimentalOption("prefs", prefs)
        driver = new ChromeDriver(chromeOptions)
      } else {
        driver = new ChromeDriver()
      }
    } else if (browser == "firefox") {
      if (outdir != null) {
        FirefoxProfile firefoxProfile = new FirefoxProfile()
        firefoxProfile.setPreference("browser.download.dir", outdir)
        firefoxProfile.setPreference("browser.download.folderList", 2)
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false)
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "images/jpeg, application/pdf, application/octet-stream")
        driver = new FirefoxDriver(firefoxProfile)
      } else {
        driver = new FirefoxDriver()
      }
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

  private Wait wait
  Wait getWait() {
    wait = wait ?: new Wait(this)
  }

  private Text text
  Text getText() {
    text = text ?: new Text(this)
  }

  private Vrfy vrfy
  Vrfy getVrfy() {
    vrfy = vrfy ?: new Vrfy(this)
  }

  def quit() {
    driver.quit()
  }
}
