@echo off
setlocal EnableDelayedExpansion
if "%1" equ "" call :default 2>nul && goto :EOF
:mainloop { handle args.shift }
if /i "%1" neq "" call :%~1 && shift && goto mainloop
                    rem ^ it's unsafe :/
goto :EOF

:default
:test hello world, lol
setlocal DisableDelayedExpansion
echo Hello world!
setlocal EnableDelayedExpansion
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
