set PLUGIN_DIR=C:\Users\green\AppData\Local\JetBrains\Toolbox\apps\IDEA-U\ch-0\201.7223.91\lib
set KOTLIN_JVM=C:\Program Files\kotlinc\bin\kotlinc
set DEPENDENCIES=..\lib\gson-2.8.6.jar;%PLUGIN_DIR%\platform-api.jar;%PLUGIN_DIR%\util.jar;%PLUGIN_DIR%\intellij-xml.jar;%PLUGIN_DIR%\platform-impl.jar;%PLUGIN_DIR%\platform-api.jar

cd ..\plugin\src
"%KOTLIN_JVM%" -cp %DEPENDENCIES% ru\** -d ..\translator-plugin.jar