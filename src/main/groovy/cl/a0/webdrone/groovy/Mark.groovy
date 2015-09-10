package cl.a0.webdrone.groovy

class Mark {
  protected Browser a0

  Mark(Browser a0) {
    this.a0 = a0
  }

  def id(text) {
    id([:], text)
  }

  def id(Map args, text) {
    flash args, a0.find.id(text)
  }

  def link(text) {
    link([:], text)
  }

  def link(Map args, text) {
    flash args, a0.find.link(args, text)
  }

  def button(text) {
    button([:], text)
  }

  def button(Map args, text) {
    flash args, a0.find.button(args, text)
  }

  def on(text) {
    on([:], text)
  }

  def on(Map args, text) {
    flash args, a0.find.on(args, text)
  }

  def option(text) {
    option([:], text)
  }

  def option(Map args, text) {
    flash args, a0.find.option(args, text)
  }

  def xpath(text) {
    xpath([:], text)
  }

  def xpath(Map args, text) {
    flash args, a0.find.xpath(args, text)
  }

  def flash(item) {
    flash([:], item)
  }

  def flash(Map args, item) {
    def color = args?.color ?: 'red'
    3.times {
      border item, 'white'
      Thread.sleep(100)
      border item, 'blue'
      Thread.sleep(100)
      border item, color
      Thread.sleep(100)
    }
    item
  }

  protected def border(item, color) {
    if ([Collection, Object[]].any { it.isAssignableFrom(item.class) }) { // array or collection
      item.each { it ->
        a0.exec.script    "arguments[0].style.border = '2px solid ${color}'", it
      }
    } else {
      a0.exec.script    "arguments[0].style.border = '2px solid ${color}'", item
    }
  }
}
