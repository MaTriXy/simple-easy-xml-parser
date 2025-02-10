# 🛑 THIS REPOSITORY IS OFFICIALLY NO LONGER UNDER MAINTENANCE since 10/02/2022 🛑

# simple-easy-xml-parser [![](https://ci.novoda.com/buildStatus/icon?job=simple-easy-xml-parser)](https://ci.novoda.com/job/simple-easy-xml-parser/lastBuild/console) [![Download](https://api.bintray.com/packages/novoda/maven/simple-easy-xml-parser/images/download.svg) ](https://bintray.com/novoda/maven/simple-easy-xml-parser/_latestVersion) [![Apache 2.0 Licence](https://img.shields.io/github/license/novoda/simple-easy-xml-parser.svg)](https://github.com/novoda/simple-easy-xml-parser/blob/master/LICENSE.txt)

XML parsing is now sexy!


## Description

A simple, high performance, typed XML parser based upon Android sax parser but written in pure Java. SEXP gives callbacks for all parsing events and being written in pure java allows faster and more comprehensive testability.

Moreover SEXP is a pull-parsing XML deserialiser which affects both performance (no reflection is required) and low memory footprint (unncessary objects are not allocated). Our testing shows that SEXP performs:

- 1.7x faster than [Jackson](https://github.com/FasterXML/jackson-dataformat-xml) 
- 2.5x faster than [Simple-Framework](http://simple.sourceforge.net/). 

You can refer to the following [micro-benchmarking](https://github.com/novoda/simple-easy-xml-parser/tree/master/benchmark) for further information.


## Adding to your project

To start using this library, add these lines to the `build.gradle` of your project:

```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'com.novoda:sexp:1.0.8'
}
```

## Simple usage

```java
String XML =
            "<novoda>" +
              "<favouriteColour>Blue</favouriteColour>" +
            "</novoda>";

ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();
elementFinder = factory.getStringFinder();
Streamer<String> streamer = new SimpleStreamer(elementFinder);
String favouriteColour = SimpleEasyXmlParser.parse(XML, streamer);

private static class SimpleStreamer implements Streamer<String> {

    private final ElementFinder<String> elementFinder;
    private final String elementTag;

    public SimpleStreamer(ElementFinder<String> elementFinder, String elementTag) {
        this.elementFinder = elementFinder;
        this.elementTag = elementTag;
    }

    @Override
    public RootTag getRootTag() {
        return RootTag.create("novoda");
    }

    @Override
    public void stream(RootElement rootElement) {
        elementFinder.find(rootElement, elementTag);
    }

    @Override
    public String getStreamResult() {
        return elementFinder.getResultOrThrow();
    }
}
```
(See the `demo` and `demoAndroid` modules for more.)

## Links

Here are a list of useful links:

 * We always welcome people to contribute new features or bug fixes, [here is how](https://github.com/novoda/novoda/blob/master/CONTRIBUTING.md)
 * If you have a problem check the [Issues Page](https://github.com/novoda/simple-easy-xml-parser/issues) first to see if we are working on it
 * For further usage or to delve more deeply checkout the [Project Wiki](https://github.com/novoda/simple-easy-xml-parser/wiki)
 * Looking for community help, browse the already asked [Stack Overflow Questions](http://stackoverflow.com/questions/tagged/support-sexp) or use the tag: `support-sexp` when posting a new question
