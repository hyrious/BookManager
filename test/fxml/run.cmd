@echo off
setlocal
pushd "%~dp0"
javac Sample.java
javac MainController.java
java Sample
popd