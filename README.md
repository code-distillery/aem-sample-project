# AEM Sample Project

This sample project showcases what the maven setup of a typical AEM project could look like.
The main focus is on the usage of maven plugins and on inheriting the optional build profiles
`installBundle` and `installPackage`.

There are lots of comments in the pom files further explain the approach taken.

The project structure is as follows:

    aem-sample-project-setup
    +- aem-parent
    |  +- pom.xml     # the parent pom defines shared plugin configurations
    +- bundles
    |  +- commons
    |  |  +- pom.xml  # an OSGi bundle that could contain utility code
    |  +- core
    |  |  +- pom.xml  # an OSGi bundle that could contain the core functionality (or a specific feature)
    +- package
    |  +- pom.xml     # a content-package that embeds the two bundles

## Prerequisites

Make sure you have the following installed:

 * Java 7 or higher (Java 6 may work)
 * Maven 3.2.3 or higher (lower versions may work)
 * Running AEM 6 instance (other versions should work)

## Building

The maven build should run successfully and without side-effects:

    mvn clean install

## Optional: install content-packages

You can install the content-package produced by the build into your local AEM instance
(http://localhost:4502 by default):

    mvn clean install -P installPackage

To install on a local publish instance (http://localhost:4503):

    mvn clean install -P installPackage -Dserver.port=4503

Other parameters can also be used for maximum flexibility, e.g.

    mvn clean install -P installPackage -Dserver.url=http://example.local:8080 -Dpassword=secret

To only build the content-package use:

    mvn clean install -PinstallPackage -pl package

For more details, take a look at the properties section in `aem-parent/pom.xml`.

## Optional: install bundles

After installing the content-package it is possible to install the bundles individually. This can
speed up development when only code in a single bundle is affected. E.g. to install only the core
bundle run the following command:

    mvn clean install -PinstallBundle -pl bundles/core

Of course it is also possible to install all bundles by running without the `-pl` option:

    mvn clean install -PinstallBundle

Or pick the two bundles you want to build and install

    mvn clean install -PinstallBundle -pl bundles/commons,bundles/core

Hopefully this project is helpful and instructional. If you have any comments or problems, feel
free to open an issue.
