package cl.a0.webdrone.groovy

class Open {
  Browser   a0

  Open(Browser a0) {
    this.a0 = a0
  }

  def url(def url) {
    a0.driver.get(url)
  }
}
