package cl.a0.webdrone.groovy.tests

import cl.a0.webdrone.groovy.Browser
import cl.a0.webdrone.groovy.Webdrone
import spock.lang.Specification

class BrowserSpec extends Specification {
  void setup() {
  }

  void cleanup() {
  }

  def "can update form from xlsx"() {
    setup:
    String filename = this.getClass().getResource("/data.xlsx").file
    Browser a0 = Webdrone.create(create_outdir: true)
    a0.open.url 'http://getbootstrap.com/css/?#forms'
    a0.form.with_xpath('//label[contains(.,"%s")]/following-sibling::*[1][self::input | self::textarea | self::select]') {
      xlsx(sheet: 'sample data', filename: filename)
    }
    a0.shot.screen  'ya'

    cleanup:
    a0.quit()
  }

  def "can read and write an xlsx"() {
    setup:
    String filename = this.getClass().getResource("/data.xlsx").file
    Browser a0 = Webdrone.create()
    def dict = a0.xlsx.dict filename: filename
    println "dict: ${dict}"
    dict.name = "${dict.name} d"
    a0.xlsx.save()

    def rows = a0.xlsx.rows filename: file
    println "rows: ${rows}"
    rows[1][1] = "${rows[1][1]} r"
    a0.xlsx.save()

    def both = a0.xlsx.both filename: file
    println "rows: ${both}"
    println "rows: ${both[1]}"
    both[1].VALUE = "${both[1].VALUE} rd"
    a0.xlsx.save()

    cleanup:
    a0.quit()
  }

  def "can create an output directory"() {
    setup: "create directory"
    Browser a0 = Webdrone.create(create_outdir: true)
    a0.conf.timeout = 10
    a0.open.url     'http://www.google.cl/'
    a0.shot.screen  'homepage'      // screenshot saved in output directory
    a0.form.set     'q', "Download sample file filetype:xls\n"
    a0.wait.time    5
    a0.clic.xpath   '//h3'

    cleanup:
    a0.quit()
  }

  def "can create and then close a browser and open google"() {
    setup: "create browser"
      Browser a0 = Webdrone.create()
      a0.open.url     "http://www.google.cl"

    cleanup:
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

  def "can find a link and clic on it"() {
    setup:
      Browser a0 = Webdrone.create()
      a0.open.url     'http://www.microsoft.com/en-us'

    expect:
      a0.mark.link('Download Center', all: true) != null
      a0.find.link('Download Center') != null
      a0.find.link('Download Centers') == null
      a0.mark.link('Buy now', all: true) != null

    cleanup:
      a0.quit()
  }

  def "can execute javascript"() {
    setup:
      Browser a0 = Webdrone.create()

    expect:
      a0.exec.script    "document.write(arguments[0] + arguments[1]);", "hello", "there"

    cleanup:
      a0.quit()
  }
}
