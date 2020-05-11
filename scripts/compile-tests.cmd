set KOTLIN_JVM=C:\Program Files\kotlinc\bin\kotlinc
set DEPENDENCIES=..\translator-plugin.jar;..\lib\hamcrest-core-1.3.jar;..\lib\junit-4.12.jar;..\lib\kotlin-test-junit-1.3.72.jar;..\lib\kotlin-test.jar

cd ..\plugin\test
"%KOTLIN_JVM%" -cp %DEPENDENCIES% ru\** -d ..\test-translator-plugin.jar