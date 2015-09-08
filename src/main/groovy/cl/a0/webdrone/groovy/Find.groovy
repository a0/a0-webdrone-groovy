package cl.a0.webdrone.groovy

import org.openqa.selenium.By

class Find {
  protected Browser a0

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
    xpath(args, UtilXpath.string_literal(UtilXpath.expr_link, text))
  }

  def button(text) {
    button([:], text)
  }

  def button(Map args, def text) {
    xpath(args, UtilXpath.string_literal(UtilXpath.expr_button, text))
  }

  def on(text) {
    on([:], text)
  }

  def on(Map args, def text) {
    xpath(args, UtilXpath.string_literal(UtilXpath.expr_link_or_button, text))
  }

  def xpath(text) {
    xpath([:], text)
  }

  def xpath(Map args, def text) {
    Params params = new Params(args)
    def items = a0.driver.findElements(By.xpath(text))
    choose(items, params)
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
