package cl.a0.webdrone.groovy

import org.openqa.selenium.JavascriptExecutor

class Exec {
  protected Browser a0

  Exec(Browser a0) {
    this.a0 = a0
  }

  def script(script, Object...args) {
    JavascriptExecutor javascriptExecutor = a0.driver
    javascriptExecutor.executeScript(script, args)
  }
}
