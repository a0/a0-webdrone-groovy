package cl.a0.webdrone.groovy

import org.openqa.selenium.By

class Find {
  protected Browser a0

  class Params extends Expando {
    def n = 1
    def all = false
    def visible = true

    def propertyMissing(String name) { }
  }

  Find(Browser a0) {
    this.a0 = a0
  }

  def id(text) {
    a0.driver.findElement(By.id(text))
  }

  def link(text) {
    link([:], text)
  }

  def link(Map args, text) {
    xpath(args, UtilXpath.string_literal(UtilXpath.expr_link, text))
  }

  def button(text) {
    button([:], text)
  }

  def button(Map args, text) {
    xpath(args, UtilXpath.string_literal(UtilXpath.expr_button, text))
  }

  def on(text) {
    on([:], text)
  }

  def on(Map args, text) {
    xpath(args, UtilXpath.string_literal(UtilXpath.expr_link_or_button, text))
  }

  def option(text) {
    option([:], text)
  }

  def option(Map args, text) {
    xpath(args, UtilXpath.string_literal(UtilXpath.expr_option, text))
  }

  def xpath(text) {
    xpath([:], text)
  }

  def xpath(Map args, text) {
    Params params = new Params(args)
    def items = a0.driver.findElements(By.xpath(text))
    choose(items, params)
  }

  def propertyMissing(String name, value) { }

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
      list.size() >= params.n ? list.getAt(params.n - 1) : null
    }
  }
}
