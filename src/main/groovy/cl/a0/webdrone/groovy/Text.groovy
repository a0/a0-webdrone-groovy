package cl.a0.webdrone.groovy

class Text {
  protected Browser a0

  Text(Browser a0) {
    this.a0 = a0
  }

  def id(text) {
    a0.find.id(text).text
  }

  def link(text) {
    link([:], text)
  }

  def link(Map args, text) {
    a0.find.link(args, text).text
  }

  def button(text) {
    button([:], text)
  }

  def button(Map args, text) {
    a0.find.button(args, text).text
  }

  def on(text) {
    on([:], text)
  }

  def on(Map args, text) {
    a0.find.on(args, text).text
  }

  def option(text) {
    option([:], text)
  }

  def option(Map args, text) {
    a0.find.option(args, text).text
  }

  def xpath(text) {
    xpath([:], text)
  }

  def xpath(Map args, text) {
    a0.find.xpath(args, text).text
  }
}
