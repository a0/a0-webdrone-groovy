package cl.a0.webdrone.groovy

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class Find {
  protected Browser a0
  private final String xpath_link = ".//a[./@href][(((./@id = %s or contains(normalize-space(string(.)), %s)) or contains(./@title, %s)) or .//img[contains(./@alt, %s)])]"
  private final String xpath_button = ".//input[./@type = 'submit' or ./@type = 'reset' or ./@type = 'image' or ./@type = 'button'][((./@id = %s or contains(./@value, %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)] | .//button[(((./@id = %s or contains(./@value, %s)) or contains(normalize-space(string(.)), %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)]"
  private final String xpath_link_or_button = ".//a[./@href][(((./@id = %s or contains(normalize-space(string(.)), %s)) or contains(./@title, %s)) or .//img[contains(./@alt, %s)])] | .//input[./@type = 'submit' or ./@type = 'reset' or ./@type = 'image' or ./@type = 'button'][((./@id = %s or contains(./@value, %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)] | .//button[(((./@id = %s or contains(./@value, %s)) or contains(normalize-space(string(.)), %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)]"


  class Params {
    def n = 1
    def all = false
    def visible = true
  }

  Find(Browser a0) {
    this.a0 = a0
  }

  def id(def id) {
    a0.driver.findElement(By.id(id))
  }

  def link(text) {
    link([:], text)
  }

  def link(Map args, def text) {
    xpath(args, xpath_string_literal(xpath_link, text))
  }

  def button(text) {
    button([:], text)
  }

  def button(Map args, def text) {
    xpath(args, xpath_string_literal(xpath_button, text))
  }

  def on(text) {
    on([:], text)
  }

  def on(Map args, def text) {
    xpath(args, xpath_string_literal(xpath_link_or_button, text))
  }

  def xpath(Map args, def text) {
    Params params = new Params(args)
    def items = a0.driver.findElements(By.xpath(text))
    choose(items, params)
  }

  protected def xpath_string_literal(String xpath, def text) {
    text = text.toString()
    if (text.contains("'")) {
      text = text.split("'").collect {
        "'${it}'"
      }.join('"\'"')
      text = "concat(${text})"
    } else {
      text = "'${text}'"
    }
    xpath.replaceAll('%s', text)
  }

  protected def choose(List list, Params params) {
    list = list.findAll {
      if (params.visible == true) {
        it.isDisplayed()
      } else if (params.visible == false) {
        !it.isDisplayed()
      } else {
        true
      }
    }
    if (params.all) {
      list
    } else {
      list.size() <= params.n ? list.getAt(params.n - 1) : null
    }
  }
}
