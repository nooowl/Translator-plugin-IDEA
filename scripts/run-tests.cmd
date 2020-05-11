cd ..\plugin
java -cp lib\*;test-translator-plugin.jar;translator-plugin.jar  org.junit.runner.JUnitCore ru.itmo.plugins.translator.testCases.SmartTranslatorTests
java -cp lib\*;test-translator-plugin.jar;translator-plugin.jar  org.junit.runner.JUnitCore ru.itmo.plugins.translator.testCases.YandexTranslatorApiTests