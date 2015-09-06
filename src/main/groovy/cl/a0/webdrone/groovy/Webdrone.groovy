package cl.a0.webdrone.groovy

class Webdrone {
  def static create() {
    create([:])
  }

  def static create(Map params) {
    create([:], null)
  }

  def static create(Closure closure) {
    create([:], closure)
  }

  def static create(Map params, Closure closure) {
    if (closure != null) {
      def a0 = new Browser(params)
      try {
        closure.call(a0)
      } finally {
        a0.quit()
      }
    } else {
      return new Browser(params)
    }
  }
}
