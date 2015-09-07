package cl.a0.webdrone.groovy.tests

import cl.a0.webdrone.groovy.Browser
import cl.a0.webdrone.groovy.Webdrone
import spock.lang.Specification

class BrowserSpec extends Specification {
  void setup() {
  }

  void cleanup() {
  }

  def "can find a link"() {
    setup:
    Browser a0 = Webdrone.create()
    a0.open.url     'http://www.microsoft.com/en-us'

    expect:
      a0.find.link('Download Center') != null
      a0.find.link('Download Centers') == null
  }

  def "can create and then close a browser and open google"() {
    setup: "create browser"
    Browser a0 = Webdrone.create()
    a0.open.url     "http://www.google.cl"
    a0.quit()
  }

  def "can create and close a browser using a closure and open yahoo"() {
    setup: "create browser using closure"
    Webdrone.create() { Browser a0 ->
      a0.open.url   "http://www.yahoo.com"
    }
  }

  def "can take a screenshot"() {
    setup: "open google and take a screenshot"
    Webdrone.create() { Browser a0 ->
      a0.open.url       'http://www.google.com/'
      a0.shot.screen    'home_page'
    }
  }
}
