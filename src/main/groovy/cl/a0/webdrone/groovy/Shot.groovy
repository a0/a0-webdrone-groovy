package cl.a0.webdrone.groovy

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot

class Shot {
  Browser   a0
  protected counter = 0

  Shot(Browser a0) {
    this.a0 = a0
  }

  def screen(def name) {
    counter++
    def filename = sprintf "screenshot-%04d-%s.png", counter, name
    TakesScreenshot takesScreenshot = a0.driver
    byte[] data = takesScreenshot.getScreenshotAs(OutputType.BYTES)
    new File(filename).withOutputStream {
      it.write data
    }
  }
}
