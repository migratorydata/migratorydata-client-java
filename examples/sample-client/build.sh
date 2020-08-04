#!/bin/bash
CLASSPATH=./:lib/migratorydata-client-java.jar
export CLASSPATH
javac src/*.java
cd src
jar cfm ../sample-client.jar ../manifest.txt *.class
rm *.class
cd ..
