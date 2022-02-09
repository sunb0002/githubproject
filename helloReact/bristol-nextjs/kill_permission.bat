%1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)&&exit
cd /d "%~dp0"
net stop winnat
net start winnat
exit

:: https://stackoverflow.com/questions/67292618/eacces-permission-denied-0-0-0-03000-in-windows-next-js
