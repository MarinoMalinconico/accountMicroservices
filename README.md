# accountMicroservices
Microservizio per la gestione di account in un DB
___
## parte funzionale
Questo Microservizio consente il censimento, aggiornamento e eliminazione di posizioni bancarie all'interno di un DataBase
___
## parte tecnica
### versioni
+ Java 17
  + Amazon corretto 17.0.17
+ Spring
  + Spring Boot 3.5.7
### struttura
+ il microservizio usa un pattern delegate
  + delegate permette di usare una classe apposita per un determinato compito e facilita il riutilizzo del codice
  + quindi in questo caso, invece di usare i metodi di AccountRepository,aggiungere i log e trasformare il risultato in una response, possiamo usare un metodo del delegate che fa gia tutto cio, senza il bisogno di riscriverlo ogni volta
+ presenta un db h2 in memory che è possibile usare per dei test
  + con dependency
  ``` 
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
  ```
+ presenta anche un db postgres per un uso con dei dati permanenti
    + con dependency
  ``` 
        <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.8</version>
        </dependency>
  ```
+ per usare un db piuttosto che un altro, commentare con dei '#' la parte di configurazioni del db che non vogliamo usare
    + ricordare che in PaymentMicroservicesApplication ci sono delle insert che devono essere commentate in caso di database permanente, perchè altrimenti verrebbero eseguie ad ogni avvio
    + se si imposta `spring.jpa.hibernate.ddl-auto=create-drop`, create-drop farà ricreare la tabella ad ogni avvio
+ per interrogare il db sono stati esposti i seguenti endpoint
  + presenti anche nella collection **Account Microservices.postman_collection.json** nella cartella **Postman Collection**
    + con **baseURL:**
        ```
      localhost:8095/accountMicroservices/services/
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

    #spring.application.name=accountMicroservices
    
    server.port=8095
    server.servlet.context-path=/accountmicroservices/services
    
    info.app.name = @project.name@
    info.app.groupId = @project.groupId@
    info.app.artifactId = @project.artifactId@
    info.app.version = @project.version@
    
    #h2 usato per test con db in memory
    #spring.datasource.driver-class-name=org.h2.Driver
    #spring.datasource.url=jdbc:h2:mem:testdb
    #spring.datasource.username=sa
    #spring.datasource.password=
    #spring.h2.console.enabled=true
    #spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    
    #postgres
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=postDBA
    #spring.postgres.console.enabled=true ?
    #spring.jpa.database-platform=org.hibernate.dialect.H2Dialect ?
    spring.jpa.hibernate.ddl-auto=create-drop
    # opzioni: none | validate | update (aggiorna la tabella se esiste)| create | create-drop (ricrea la tabella ogni volta)
    
    ##postgres conf di giovanni
    #spring.datasource.driver-class-name=org.postgresql.Driver
    #spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    #spring.datasource.username=postgres
    #spring.datasource.password=postDBA
    #spring.jpa.hibernate.ddl-auto=update
    ## opzioni: none | validate | update | create | create-drop
    #spring.jpa.properties.hibernate.format_sql=true
    #spring.jpa.open-in-view=false
    #spring.sql.init.mode=never
    #spring.logging.level.org.hibernate.SQL=debug
    #
    ## mostra i parametri bindati (utile in dev)
    #spring.logging.level.org.hibernate.orm.jdbc.bind=trace
___