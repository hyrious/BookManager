@echo off
setlocal EnableDelayedExpansion
:mainloop { handle args.shift }
if /i "%1" neq "" call :%~1 && shift && goto mainloop
                    rem ^ it's unsafe :/
goto :EOF

:test hello world, lol
echo Hello world!
goto :EOF

:commit to repo
git add .
git commit
goto :EOF

:push to GitHub
call :commit
git push
goto :EOF

