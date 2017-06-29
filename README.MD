# Project about Products


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run this app 100%, you need a Java installed in your computer. See below how to install in your pc. 

[Install java - Oracle](https://www.java.com/en/download/help/download_options.xml)

### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
git clone https://matheusfreire@bitbucket.org/matheusfreire/app.git
```
and now go to your folder and execute the maven update
```
mvn clean install -U
```

## Running the tests

To run test use the maven to perfome a Junit test case.
Execute the following command to run

```
mvn test -Dtest=TesteSuite
```

## Deployment

To deploy the app use the follow command.

```
mvn exec:java 
```

## Built With

* [Jersey](https://github.com/jersey/jersey) - Java RESTful Web Services Framework and JAX-RS Reference Implementation
* [Maven](https://maven.apache.org/) - Dependency Management
* [Grizzly](https://github.com/javaee/grizzly) - Java EE Server Application

## Endpoints

* To create a product 
```
curl -X POST 'http://localhost:8080/products/new?name=Product%201&description=Awesome'
```

* To update an specific product
```
curl -X PUT 'http://localhost:8080/products/1?name=Product%201&description=Awesome'
```

* To delete an specific product
```
curl -X DELETE http://localhost:8080/products/1
```

* To create a child_product to specific product
```
curl -X POST 'http://localhost:8080/products/1/newChild?name=Product%20Child%201&description=Awesome'
```

* To create a image to specific product
```
curl -X POST 'http://localhost:8080/products/1/newImage?type=jpg'
```

* To get all products without relationships
```
curl -X GET 'http://localhost:8080/products'
```

* To get all products with relationships
```
curl -X GET 'http://localhost:8080/products/detailed'
```

* To get a product without relationships
```
curl -X GET 'http://localhost:8080/products/1'
```

* To get a product with relationships
```
curl -X GET 'http://localhost:8080/products/1/detailed'
```

* To get a product without relationships
```
curl -X GET 'http://localhost:8080/products/1'
```

* To get a list of childrens_products of the an specific product 
```
curl -X GET 'http://localhost:8080/products/1/products'
```

* To get a list of images of the an specific product 
```
curl -X GET 'http://localhost:8080/products/1/images'
```

* To create a image 
```
curl -X POST 'http://localhost:8080/images/new?type=jpg'
```

* To update an specific image
```
curl -X PUT 'http://localhost:8080/images/1?type=png'
```

* To delete an specific image
```
curl -X DELETE http://localhost:8080/images/1
```



## Authors

* **Matheus Freire** - *Initial work* - [MATHEUSFREIRE](https://github.com/matheusfreire)


## Acknowledgments

* Inspiration: Avenue Code challenge

