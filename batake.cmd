@echo off
setlocal EnableDelayedExpansion
:mainloop { handle args.shift }
if /i "%1" neq "" call :%~1 && shift && goto mainloop
                    rem ^ it's unsafe :/
goto :EOF

:test hello world, lol
echo Hello world!
goto :EOF

:push to GitHub
git add .
rem DATE is 2017/05/21 周日 # chinese order
set date=%DATE:~0,4%-%DATE:~5,2%-%DATE:~8,2%
echo `git commit -m "Updated at %date% %TIME%"`
choice /c YN /m "Are you sure? "
if errorlevel 1 ( git commit -m "Updated at %date% %TIME%" )
goto :EOF

