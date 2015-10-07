package cl.a0.webdrone.groovy

import org.openqa.selenium.By

class Form {
  protected Browser a0

  Form(Browser a0) {
    this.a0 = a0
  }

  private String xpath
  def with_xpath(text, Closure closure) {
    this.xpath = text
    this.with(closure)
  }

  def set(key, val) {
    def item = this.find_item(key)
    if (item != null && item.tagName == 'select') {
      def option = item.findElement(By.xpath(UtilXpath.string_literal(UtilXpath.expr_option, val)))
      item.click()
      option.click()
    } else if (item) {
      item.clear()
      item.sendKeys(val)
    }
  }

  def get(key) {
    this.find_item(key).getAttribute("value")
  }

  def clic(key) {
    this.find_item(key).click()
  }

  def mark(key) {
    mark([:], key)
  }

  def mark(Map args, key) {
    a0.mark.flash(args, this.find_item(key))
  }

  def submit(key = null) {
    if (key) {
      this.find_item(key)
    }
    this.lastitem.submit()
  }

  def xlsx() {
    xlsx([:])
  }

  def xlsx(Map args) {
    a0.xlsx.dict(args).each { k, v ->
      set(k, v)
    }
  }

  private Object lastitem
  protected def find_item(key) {
    if (this.xpath != null) {
      this.lastitem = a0.driver.findElement(By.xpath(sprintf(this.xpath, key)))
    } else {
      this.lastitem = a0.find.xpath(UtilXpath.string_literal(UtilXpath.expr_field, key))
    }
  }
}
