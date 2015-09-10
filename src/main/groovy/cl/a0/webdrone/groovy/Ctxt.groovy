package cl.a0.webdrone.groovy

class Ctxt {
  protected Browser a0

  Ctxt(Browser a0) {
    this.a0 = a0
  }

  def create_tab() {
    a0.exec.script "function a0_ctx_create_tab() { var w = window.open(); w.document.open(); w.document.write('A0 CTXT CREATE TAB'); w.document.close(); } a0_ctx_create_tab();"
    a0.driver.switchTo().window(a0.driver.windowHandles.last())
  }

  def close_tab() {
    a0.driver.close()
    a0.driver.switchTo().window(a0.driver.windowHandles.last())
  }

  protected current_frame
  def with_frame(name, Closure closure) {
    def old_frame = current_frame
    a0.driver.switchTo().frame(name)
    current_frame = name
    if (closure) {
      closure.call()
      a0.driver.switchTo().parentFrame()
      current_frame = old_frame
    }
    current_frame
  }

  def with_alert(Closure closure) {
    a0.wait.til {
      closure.call(a0.driver.switchTo().alert())
    }
  }

  def ignore_alert() {
    a0.exec.script 'alert = function(message){return true;};'
  }
}
