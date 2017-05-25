@echo off
setlocal EnableDelayedExpansion
:mainloop { handle args.shift }
if /i "%1" neq "" call :%~1 && shift && goto mainloop
                    rem ^ it's unsafe :/
goto :EOF

:clean
cmd /c test\clean
goto :EOF

:commit to repo
call :clean
git add .
git commit
goto :EOF

:push to GitHub
call :commit
git push
goto :EOF

:test this project
echo [ Cleaning.. ]
del /s/f/q bin\*.class 2>nul
rd /s/q bin 2>nul
echo [ Running.. ]
md bin
javac -cp sqlite-jdbc-3.18.0.jar;bin -d bin src\*.java
ruby classes.rb bin
:test_feed me!
set /p class="Feed me the main Class name: "
if "!class!" == "" goto :test_feed
echo ------------- Output -------------
java -cp sqlite-jdbc-3.18.0.jar;bin !class!
echo ------------ Finished ------------
goto :EOF
