# Spring Boot Believer REST service

This is a Spring Boot service example for believers crud operations.

This project was made for learning purposes but you are free to use it. This project uses Swagger for API documentation, so you will find all the documentation by doing all the following steps. 

![Swagger documentation screenshot](https://github.com/urielemak/believer-rest-service/blob/main/src/main/resources/documentation/believer-service-doc-screenshot.png?raw=true)

## Requirements
Make sure you have installed this packages:

 * JDK 17
 * Maven 3.6 (only tested in 3.6.3)

## Building

You need to download all the dependecies with maven

```
mvn install
```

## Running

```
./mvnw spring-boot:run
```

## Documentation

Once you have done all previous steps, you can go to the api documentation by going to `http://localhost:8080/openapi/swagger-ui/index.html`.

Copyright 2022 urielemak

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
>    http://www.apache.org/licenses/LICENSE-2.0
>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
