**How to upgrade orbeon:**

 * unpack orbeon zip file and inside this folder, unpack orbeon.war
 * remove everything from _yforms/orbeonweb/web/webroot_ folder
 * unpack new version of orbeon to _web/webroot_ folder (user orbeon/WEB-INF to replace yforms/orbeonweb/web/webroot/WEB-INF)
 * put old license file to _web/webroot/WEB-INF/resources/config/license.xml_ and make sure it is still valid, if it is expired then you should ask orbeon guys for new license
 * create _web/webroot/WEB-INF/orbeonweb-web-spring.xml_:

```
<?xml version="1.0" encoding="UTF-8"?>
<!--
 [y] hybris Platform
 
 Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 
 This software is the confidential and proprietary information of SAP
 ("Confidential Information"). You shall not disclose such Confidential
 Information and shall use it only in accordance with the terms of the
 license agreement you entered into with SAP.
-->


<!-- 
  WARNING: This is the Spring WEB configuration file. If you want to replace
  items or managers you have to do this from the extensions core configuration 
  file (orbeonweb/resources/orbeonweb-spring.xml) 
  -->

<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    
</beans>
```
 
 * update _web/webroot/WEB-INF/web.xml_:

Please ensure the value of parameter `oxf.initialize-logging` is `false`. The original value is `true`, we need to change it to `false` so that orbeon won't init its own logging.

```
<web-app>
    [...]
    <context-param>
        <param-name>oxf.resources.priority.0</param-name>
        <param-value>org.orbeon.oxf.resources.FilesystemResourceManagerFactory</param-value>
    </context-param>
    <context-param>
        <param-name>oxf.resources.priority.0.oxf.resources.filesystem.sandbox-directory</param-name>
        <param-value>${HYBRIS_CONFIG_DIR}/orbeon/</param-value>
    </context-param>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>WEB-INF/orbeonweb-web-spring.xml</param-value>
    </context-param>
    <context-param>
        <param-name>oxf.initialize-logging</param-name>
        <!-- the original value is true, we need to change it to false so that orbeon won't init its own logging -->
        <param-value>false</param-value>
    </context-param>
    <!-- Url Rewriter to Forbid URLS -->
    <filter>
        <filter-name>UrlRewriteFilter</filter-name>
        <filter-class>org.tuckey.web.filters.urlrewrite.UrlRewriteFilter</filter-class>
        <init-param>
            <param-name>logLevel</param-name>
            <param-value>LOG4J</param-value>
        </init-param>
        <init-param>
            <param-name>confReloadCheckInterval</param-name>
            <param-value>-1</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>UrlRewriteFilter</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
    </filter-mapping>
</web-app>
```

* add _web/webroot/WEB-INF/external-dependencies.xml_:

```
<!--
 [y] hybris Platform

 Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.

 This software is the confidential and proprietary information of SAP
 ("Confidential Information"). You shall not disclose such Confidential
 Information and shall use it only in accordance with the terms of the
 license agreement you entered into with SAP.
-->

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>de.hybris.platform</groupId>
    <artifactId>orbeonweb-web</artifactId>
    <version>21.05.0-SNAPSHOT</version>

    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>org.tuckey</groupId>
            <artifactId>urlrewritefilter</artifactId>
            <version>4.0.4</version>
        </dependency>
    </dependencies>
</project>
```
* remove `log4j2.xml` from _web/webroot/WEB-INF/resources/config_ (if they exist):
* remove Bouncy Castle libraries from _web/webroot/WEB-INF/lib_ (if they exist):
    * _itext-bcmail-jdk14-138.jar_
    * _itext-bcprov-jdk14-138.jar_
    * _itext-bcprov-jdk14-138.jar_
* remove `slf4j-api-xxx.jar,log4j-core-xxx.jar,log4j-slf4j-impl-xxx.jar,log4j-api-xxx.jar,log4j-1.2-api-xxx.jar` library from _web/webroot/WEB-INF/lib_ (If it exists. Because platform has it already and same binding will cause error)
* update _apache-batik-1.7_ to _apache-batik-1.9.1_ (if it exists)
* follow the following steps https://doc.orbeon.com/configuration/advanced/production-war (don’t need to remove form builder and point to an external eXist database)
* make sure _.classpath_ file contains correct lib versions
