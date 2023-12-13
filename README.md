
Вызов для внешнего сервиса с использованием сертификата на Spring Boot
Java 21.0.1, Spring Boot 3.2.0

1. Сохраняем сертификаты в формате .p12 и .crt в папке /keystore
2. Создаем truststore через .crt сертификат используя команду:
   #Для примера название сертификата test.kz.crt
   keytool -import -alias CERTPAYDALA -file test.kz.crt -destkeystore truststore -storepass changeit -noprompt
3. Настроить application.yml 