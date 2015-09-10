# Webdrone

Yet another selenium webdriver wrapper, groovy version.

There is a ruby version available, at https://github.com/a0/a0-webdrone-ruby.

## Installation

FIXME

## Usage

Create a browser:

```groovy
import cl.a0.webdrone.groovy.Webdrone

a0 = Webdrone.create()
a0.open.url         'http://www.google.com/'
a0.quit()

# or
Webdrone.create() { a0 ->
  a0.open.url       'http://www.google.com/'
}
```

Take a screenshot:

```groovy
a0.shot.name        'login'        # saves to screenshot-0001-login.png
a0.shot.name        'home'         # saves to screenshot-0002-home.png
```

Filling a form:

```groovy
a0.form.set         'q', 'A0::WebDrone'
a0.form.submit
end

# or
a0.form.with_xpath '//label[contains(.,"%s")]/following-sibling::*/*[self::input | self::textarea | self::select]' do
  set               'label', 'value'
end
a0.form.submit
```

Config:

```groovy
a0.conf.timeout   10
```

Context, text and verification:

```groovy
a0.ctxt.create_tab()
a0.open.url     'http:://example.com/'
a0.ctxt.with_frame 'iframe_name' do
  a0.find.on  'Some link or button'
end
puts  a0.text.id('something')

a0.vrfy.id    'another', contains: 'SOME TEXT'
a0.vrfy.id    'another', eq: 'EXACT TEXT'
a0.vrfy.link  'link', attr: 'disabled', eq: 'true'
```

## Development

FIXME

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/a0/a0-webdrone-ruby.