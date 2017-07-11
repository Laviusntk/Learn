# Learn
## 1. Setting Up Fedora Commons 3.8.1 Repository 
More details on how to set-up fedora commons can be found on [Dura Space](https://wiki.duraspace.org/display/FEDORA38/Installation+and+Configuration) and [Abhishek Singh's](http://asingh.com.np/blog/fedora-commons-installation-and-configuration-guide/) website.

### 1.1 Setting Fedora database

install MySQL server.
```
$ udo apt-get install mysql-server mysql-client
```
next set-up a database for Fedora Commons [see Dura Space](https://wiki.duraspace.org/display/FEDORA38/Installation+and+Configuration#InstallationandConfiguration-MySQL)

### 1.2 Install Java.
Copy and pase the following commands to install Java.
```
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt update; 
$ sudo apt install oracle-java8-installer
$ sudo apt install oracle-java8-set-default
```

### 1.3 Set environment variables.

Set up the JAVA_HOME, FEDORA_HOME, CATALINA_HOME, JAVA_OPTS and CLASSPATH. Run the following command to set the environment variables.

```
$ gedit /etc/environment
```

Copy and paste the following text at the end of the file. Make sure that the JAVA_HOME path matches the path of your Java home directory. Save and close the editor. For detailed instructions see [Abhishek Singh's](http://asingh.com.np/blog/fedora-commons-installation-and-configuration-guide/) website.
```
FEDORA_HOME=/opt/fedora
JAVA_HOME=/opt/jdk1.<version>/   
CLASSPATH=$JAVA_HOME/jre/lib
CATALINA_HOME=$FEDORA_HOME/tomcat/
JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.trustStore=$FEDORA_HOME/server/truststore"
JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.trustStorePassword=tomcat"
JAVA_OPTS="$JAVA_OPTS -Xmx512m"
PATH="$PATH:$FEDORA_HOME/server/bin:$FEDORA_HOME/client/bin"
export JAVA_HOME CLASSPATH CATALINA_HOME JAVA_OPTS FEDORA_HOME PATH
```

### 1.2 Install Fedora Commons

Download [Fedora-installer.3.8.1.jar](https://sourceforge.net/projects/fedora-commons/?source=typ_redirect), using the terminal direct to the diretory where the installer is downloaded and run the following command.

```
$ java -jar fcrepo-installer-3.8.1.jar
```

Select custom install. For more [see](http://asingh.com.np/blog/fedora-commons-installation-and-configuration-guide/) website.


## 2. Learn Application
