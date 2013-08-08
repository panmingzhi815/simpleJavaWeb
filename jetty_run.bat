echo [Pre-Requirement] Makesure install JDK 6.0+ and set the JAVA_HOME.
echo [Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH.

echo [INFO] Please wait a moment. When you see "[INFO] Started Jetty Server" in both 2 popup consoles, you can refresh below demo sites:
echo [INFO] http://localhost:8080
start http://localhost:8080

start mvn clean jetty:run