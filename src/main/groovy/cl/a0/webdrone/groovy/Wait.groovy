package cl.a0.webdrone.groovy

import com.google.common.base.Function
import org.openqa.selenium.InvalidSelectorException
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.NoSuchFrameException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.support.ui.FluentWait

import java.util.concurrent.TimeUnit

class Wait {
  protected Browser a0

  Wait(Browser a0) {
    this.a0 = a0
  }

  def til(Closure closure) {
    if (a0.conf.timeout) {
      FluentWait wait = new FluentWait(a0.driver)
        .withTimeout(a0.conf.timeout * 1000 as Long, TimeUnit.MILLISECONDS)
        .ignoring(StaleElementReferenceException.class)
        .ignoring(NoSuchElementException.class)
        .ignoring(NoSuchFrameException.class)
        .ignoring(InvalidSelectorException.class)

      wait.until(new Function() {
        Object apply(Object input) {
          closure.call()
        }
      })
    } else {
      closure.call()
    }
  }

  def time(val) {
    sleep(val * 1000 as Long)
  }
}
