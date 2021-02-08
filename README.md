# todo-manager-java


## About
This is a demo application for RCS java course which exposes todo manager with user signup option as a collection of rest enpoints which are documented and can be accessed and tested via Swagger.

By running the project locally(instruction below) Swagger documentation is under(note ``/`` at the end of URL)

```
http://localhost:8080/documentation/swagger-ui/
```

## Setup

Note that all commands are for *nix systems. On Windows use functional analogs. 
e.g. ``gradlew`` on Windows is ``gradlew.bat``

Get the project from github, either by downloading zip or using git clone. cmd example below. This will create ``todo-manager-java`` folder

```
git clone https://github.com/cxubrix/todo-manager-java.git
```

Setup eclipse project(optional) by executing gradle command below. Make sure that you are inside project folder when running the command. 

```
./gradlew eclipse
```

To run local server from command line user ``bootRun`` or just run it from Eclipse

```
./gradlew bootRun
```

## Demo data

There are 2 demo users with some demo data

```
username: user
password: password

username: test
password: password
```
*NOTE* this app uses basic authentication and needless to say is not meant for production use. Also there is no clear way to logout user. For testing use incognito mode.

## Contribution
If you find any issues/questions, please use github report function. Maven files are not maintained and will be removed.



