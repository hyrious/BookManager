@echo off
setlocal
pushd "%~dp0"
javac Sample.java
java Sample
popd