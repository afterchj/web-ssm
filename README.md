## Tomcat 激活spring profile
1. springboot打包war部署到外部tomcat的时候指定profile启动
>1. windows 在%tomcat%/bin下创建setenv.bat文件
>2. linux 在%tomcat%/bin下创建setenv.sh文件
2. 使用JVM参数:
>1. windows set "JAVA_OPTS=%JAVA_OPTS% -Dspring.profiles.active=dev"
>2. inux JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=dev"
## 或者使用CATALINA_OPTS参数：
> CATALINA_OPTS="-Dspring.profiles.active=prod"