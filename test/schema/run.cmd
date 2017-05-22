@echo off
setlocal
pushd "%~dp0"
if exist library.db del library.db
sqlite3 library.db < library.schema.sql
sqlite3 library.db ".schema"
popd