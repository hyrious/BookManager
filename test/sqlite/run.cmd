@echo off
setlocal
pushd "%~dp0"
javac Sample.java
java -cp ".;../../lib/sqlite-jdbc-3.18.0.jar" Sample
popd