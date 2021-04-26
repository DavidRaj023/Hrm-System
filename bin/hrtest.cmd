@ECHO OFF 
@rem Find java.exe
set HR_HOME=E:\hr
if defined JAVA_HOME goto findJavaFromJavaHome

echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_EXE=%JAVA_HOME%\bin\java.exe
if exist "%JAVA_EXE%" goto execute

echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%   
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line
   
java -cp "%HR_HOME%\libs\hr-1.0.jar;%HR_HOME%\libs\hrtest-1.0.jar;%HR_HOME%\libs\postgresql-42.2.18.jar;%HR_HOME%\config" com.infinira.hr.test.HrTest

goto end

:fail
echo failed

:end
PAUSE