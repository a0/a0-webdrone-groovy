package cl.a0.webdrone.groovy

import spock.lang.Specification

class BrowserSpec extends Specification {
  void setup() {
  }

  void cleanup() {
  }

  def "can create and then close a browser"() {
    setup: "create browser"
    def a0 = Webdrone.create()
    a0.quit()
  }

  def "can create and close a browser, using a closure"() {
    setup: "create browser using closure"
    Webdrone.create() {
      println "Hello"
    }
  }
}
