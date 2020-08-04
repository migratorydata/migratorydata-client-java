set CLASSPATH=./;lib/migratorydata-client-java.jar
javac src\*.java
cd src
jar cfm ../sample-client.jar ../manifest.txt *.class
del *.class
cd ..
