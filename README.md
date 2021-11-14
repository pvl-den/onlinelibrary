# onlinelibrary

## configserver
#### Сервер конфигурации (config-server, порт: 8888)
Хранит настройки всех приложений
Реализован с помощью Spring Config Server

## eureka-discovery-server
#### Реестр служб (service-discovery-server, порт: 8761)
Помогает службам (приложениям/микросервисам) находить друг-друга
Реализован с помощью Eureka

## library-server
#### Сервис хранения книг 
Реализован с помощью mongo

## main-server
####  (main-server, порт: 7101)
Основной сервис. Входная точка проекта. 
Реализован с помощью Spring Security + Zuul + Feign