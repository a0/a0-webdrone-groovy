package cl.a0.webdrone.groovy

class Webdrone {
  static Browser create() {
    create([:])
  }

  static Browser create(Map params) {
    create([:], null)
  }

  static Browser create(Closure closure) {
    create([:], closure)
  }

  static Browser create(Map params, Closure closure) {
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
