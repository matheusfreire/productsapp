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
 

## Authors

* **Matheus Freire** - *Initial work* - [MATHEUSFREIRE](https://github.com/matheusfreire)


## Acknowledgments

* Inspiration: Avenue Code challenge

