package cl.a0.webdrone.groovy

class UtilXpath {
  protected final static String expr_link = ".//a[./@href][(((./@id = %s or contains(normalize-space(string(.)), %s)) or contains(./@title, %s)) or .//img[contains(./@alt, %s)])]"
  protected final static String expr_button = ".//input[./@type = 'submit' or ./@type = 'reset' or ./@type = 'image' or ./@type = 'button'][((./@id = %s or contains(./@value, %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)] | .//button[(((./@id = %s or contains(./@value, %s)) or contains(normalize-space(string(.)), %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)]"
  protected final static String expr_link_or_button = ".//a[./@href][(((./@id = %s or contains(normalize-space(string(.)), %s)) or contains(./@title, %s)) or .//img[contains(./@alt, %s)])] | .//input[./@type = 'submit' or ./@type = 'reset' or ./@type = 'image' or ./@type = 'button'][((./@id = %s or contains(./@value, %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)] | .//button[(((./@id = %s or contains(./@value, %s)) or contains(normalize-space(string(.)), %s)) or contains(./@title, %s))] | .//input[./@type = 'image'][contains(./@alt, %s)]"
  protected final static String expr_field = ".//*[self::input | self::textarea | self::select][not(./@type = 'submit' or ./@type = 'image' or ./@type = 'hidden')][(((./@id = %s or ./@name = %s) or ./@placeholder = %s) or ./@id = //label[contains(normalize-space(string(.)), %s)]/@for)] | .//label[contains(normalize-space(string(.)), %s)]//.//*[self::input | self::textarea | self::select][not(./@type = 'submit' or ./@type = 'image' or ./@type = 'hidden')]"
  protected final static String expr_option = ".//option[contains(normalize-space(string(.)), %s)]"

  protected static def string_literal(String xpath, def text) {
    text = text.toString()
    if (text.contains("'")) {
      text = text.split("'").collect {
        "'${it}'"
      }.join('"\'"')
      text = "concat(${text})"
    } else {
      text = "'${text}'"
    }
    xpath.replaceAll('%s', text)
  }
}
