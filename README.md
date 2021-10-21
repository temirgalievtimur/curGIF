# Окружение 
Gradle версии 7.2
Java 13

# Эндпоинты
port/start - привествие + редирект на главную<br/>
port/main - главная страница, показывает все доступные валюты для анализа, содержит ссылки <br/>
port/result?curr=AFN - основной функционал, возвращает курсы и гифку<br/> 

# Properties
giphy.url=https://api.giphy.com/v1/gifs <br/>
giphy.apiKey=M1MoqZmUfDX0wUkPEYvfmfN79Hv3RWav <br/>
curr.apiKey=205846c1aad74ad5bfc580c04f09c610 <br/>
curr.url=https://openexchangerates.org <br/>
host.appResult=http://localhost:8080/result <br/>
host.appMainPage=http://localhost:8080/main <br/>
baseCurr=USD  --базовая валюта<br/>
server.port=8080 --порт<br/>

# Запуск
java -jar curGIF-1.0-SNAPSHOT.jar
# Сборка
gradle build
собирается файл build - libs
