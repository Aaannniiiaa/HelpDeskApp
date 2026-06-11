HelpDeskApp

Aplikacja służy do obsługi zgłoszeń helpdesk. Umożliwia zarządzanie użytkownikami, kategoriami oraz zgłoszeniami.

W projekcie wykorzystano:

* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* PostgreSQL
* Flyway
* Maven
* Swagger
* Docker
* JUnit i Mockito

Struktura projektu:

* entity - encje powiązane z tabelami w bazie danych
* repository - warstwa dostępu do bazy danych
* service - logika aplikacji
* controller - endpointy REST API
* config - konfiguracja zabezpieczeń
* strategy - obsługa wzorca Strategy

Aplikacja posiada dwie role:

* USER
* ADMIN

Dane testowe:

* admin / admin123
* user / user123

Administrator ma dostęp do użytkowników i kategorii. Użytkownik oraz administrator mają dostęp do zgłoszeń.

Baza danych jest tworzona za pomocą Flyway. Plik migracji znajduje się w:
src/main/resources/db/migration/V1__init.sql

W projekcie zastosowano wzorzec Strategy do wyznaczania czasu reakcji na zgłoszenie w zależności od priorytetu:

* LOW - 72 godziny
* MEDIUM - 24 godziny
* HIGH - 4 godziny

Najważniejsze endpointy:

* GET /api/users
* POST /api/users
* GET /api/categories
* POST /api/categories
* GET /api/tickets
* POST /api/tickets
* PATCH /api/tickets/{id}/status
* GET /api/tickets/{id}/response-time

Swagger jest dostępny pod adresem:
http://localhost:8080/swagger-ui.html

Uruchomienie testów:
.\mvnw.cmd test

Build projektu:
.\mvnw.cmd clean package

Uruchomienie przez Docker:
docker compose up --build

Zatrzymanie kontenerów:
docker compose down
