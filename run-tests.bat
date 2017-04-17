rem set BROWSER=firefox
set BROWSER=chrome

call mvn clean install -Dbrowser=%BROWSER%

start chrome .\target\surefire-reports\html\index.html

