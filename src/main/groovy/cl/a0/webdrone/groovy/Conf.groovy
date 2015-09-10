package cl.a0.webdrone.groovy

import java.util.concurrent.TimeUnit

class Conf {
  protected Browser a0
  def timeout

  Conf(Browser a0) {
    this.a0 = a0
  }

  def setTimeout(val) {
    this.timeout = val
    a0.driver.manage().timeouts().implicitlyWait(val * 1000 as Long, TimeUnit.MILLISECONDS)
  }
}
