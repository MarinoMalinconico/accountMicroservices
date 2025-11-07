# accountMicroservices
Microservizio per la gestione di account in un DB
___
## parte funzionale
Questo Microservizio consente il censimento, aggiornamento e eliminazione di posizioni bancarie all'interno di un DataBase
___
## parte tecnica
### struttura
+ il microservizio usa un pattern delegate
  + delegate permette di usare una classe apposita per un determinato compito e facilita il riutilizzo del codice
  + quindi in questo caso, invece di usare i metodi di AccountRepository,aggiungere i log e trasformare il risultato in una response, possiamo usare un metodo del delegate che fa gia tutto cio, senza il bisogno di riscriverlo ogni volta
+ presenta un db h2 in memory
  + con dependency
  ``` 
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
  ```
+ per interrogare il db sono stati esposti i seguenti endpoint
  + presenti anche nella collection **Account Microservices.postman_collection.json** nella cartella **Postman Collection**
    + con **baseURL:**
        ```
      localhost:8094/accountmicroservices/services/
        ```
    
    + **baseURL**accountDetailBasicResponse
      
      + Restituisce un json con i dati del soggetto richiesto
      + la richiesta si fa dichiarando il codice fiscale dell'interessato nel body in formato json
      + richiamabile con una request **POST** 
      + esempio:
        ```
        { 
          "cf":"DSTLCU89R52D761R"
        }
        ```
### configurazioni
       
**application.properties**

    server.port=8094
    server.servlet.context-path=/accountmicroservices/services
    
    info.app.name = @project.name@
    info.app.groupId = @project.groupId@
    info.app.artifactId = @project.artifactId@
    info.app.version = @project.version@
    
    spring.datasource.driver-class-name=org.h2.Driver
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.username=sa
    spring.datasource.password=
    spring.h2.console.enabled=true
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
___