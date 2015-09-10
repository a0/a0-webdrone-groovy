package cl.a0.webdrone.groovy

class Vrfy {
  protected Browser a0

  Vrfy(Browser a0) {
    this.a0 = a0
  }

  def id(text) {
    id([:], text)
  }

  def id(Map args, text) {
    vrfy args, a0.find.id(text)
  }

  def link(text) {
    link([:], text)
  }

  def link(Map args, text) {
    vrfy args, a0.find.link(args, text)
  }

  def button(text) {
    button([:], text)
  }

  def button(Map args, text) {
    vrfy args, a0.find.button(args, text)
  }

  def on(text) {
    on([:], text)
  }

  def on(Map args, text) {
    vrfy args, a0.find.on(args, text)
  }

  def option(text) {
    option([:], text)
  }

  def option(Map args, text) {
    vrfy args, a0.find.option(args, text)
  }

  def xpath(text) {
    xpath([:], text)
  }

  def xpath(Map args, text) {
    vrfy args, a0.find.xpath(args, text)
  }

  def vrfy(Map args, item) {
    def r = false
    if (args.attr != null) {
      if (args.eq != null) {
        r = item.getAttribute(args.attr) == args.eq
      }
      if (args.contains != null) {
        r = item.getAttribute(args.attr).contains(args.contains)
      }
    } else if (args.eq != null) {
      r = item.text == args.eq
    } else if (args.contains != null) {
      r = item.text.contains(args.contains)
    }
    if (r == false) {
      def targ = ""
      if (args.eq) {
        targ += "eq: [${args.eq}]"
      }
      if (args.contains) {
        targ += "contains: [${args.contains}]"
      }
      if (args.attr != null) {
        throw new RuntimeException("ERROR: Attribute [${args.attr}] value [${item.getAttribute(args.attr)}] does not comply ${targ}")
      } else {
        throw new RuntimeException("ERROR: Value [${item.text}] does not comply ${targ}")
      }
    }
  }
}
