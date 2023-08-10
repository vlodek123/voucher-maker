Voucher-maker
=============

Voucher Maker is ***practise project*** handling vouchers. Running with **Java 11**.


Compile:
--------

    mvn clean install

Running locally:
----------------

To run using the `default` profile launch following command from the `voucher-maker` folder:

    mvn spring-boot:run

URLs and Ports:
---------------
Root when running locally:

    http://localhost:8085/

Swagger:
--------

     http://localhost:8085/swagger-ui/index.html

Actuator:
---------

    http://localhost:8085/actuator

    http://localhost:8085/actuator/metrics/http.server.requests
    http://localhost:8085/actuator/health

Liquibase:
----------

To execute set  property liquibase.enabled to true  
