# Gige

Gige is the Cross Compiler testing Framework.

Gige supports...

* Oracle JDK compiler
* Eclipse compiler for Java

Gige also works with JUnit very well.

## Getting Started

### Add dependency to your build.gradle

```groovy
apply plugin: 'java'

repositories.jcenter()

dependencies {
    testCompile 'io.gige:gige:0.1.0'
}

sourceCompatibility = targetCompatibility = 1.8
```

# License

Apache License, Version 2.0

# Lean from

https://github.com/seasarorg/aptina

# Badges
[![wercker status](https://app.wercker.com/status/b61caeec2c22ee5147590de508904961/m "wercker status")](https://app.wercker.com/project/bykey/b61caeec2c22ee5147590de508904961)

