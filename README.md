# README #
* Author name : Aleksey Petrenko
* Travis CI: [![Build Status](https://travis-ci.org/AlexTheLion34/YADRO.svg?branch=master)](https://travis-ci.org/AlexTheLion34/YADRO)
### How to start? ###
* Use Linux operation system (ex. Ubuntu 16.04 LTS)
* Make sure that [Docker](https://www.docker.com) is installed
* Now you are ready to go!
### Installing service ###
* Open terminal
* Run - docker pull yadroservice/service:latest
* Check if the image was downloaded - docker ps
### Running service ###
+ In terminal run commands:
    * docker run -p 8080:8080 --network="host" yadroservice/service - to see network interfaces from local machine
    * docker run -p 8080:8080 yadroservice/service - to see network interfaces from docker container
### Installing client ###
* Open terminal
* Run - docker pull yadroclient/client:latest
* Check if the image was downloaded - docker ps
### Before running ###
* In terminal run - alias cli_net='docker run yadroclient/client'
* Check if alias was added - run alias
### Running client ###
+ In terminal run commands:
    * cli_net help
    * cli_net --version
    * and etc.
