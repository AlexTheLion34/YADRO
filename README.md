# README #
* Author name : Aleksey Petrenko
* Travis CI: [![Build Status](https://travis-ci.org/AlexTheLion34/YADRO.svg?branch=master)](https://travis-ci.org/AlexTheLion34/YADRO)
### How to start? ###
* Use Linux operation system (ex. Ubuntu 16.04 LTS)
* Make sure that [Docker](https://www.docker.com) is installed
* Now you are ready to go!
### Installing service ###
* Open terminal
* Run: 'docker pull alexthelion34/yadro_service'
* Check if the image was downloaded: 'docker images'
### Running service ###
+ In terminal run commands:
    * Work with local machine interfaces: 'docker run -p 8080:8080 --network="host" alexthelion34/yadro_service'
    * Work with container interfaces: 'docker run -p 8080:8080 alexthelion34/yadro_service'
### Installing client ###
* Open terminal
* Run: 'docker pull alexthelion34/yadro_client'
* Check if the image was downloaded: 'docker images'
### Before running ###
* Run - 'alias cli_net='docker run alexthelion34/yadro_client''
* Check if alias was added: 'alias'
### Running client ###
+ In terminal run commands:
    * cli_net help
    * cli_net --version
    * and etc.
### Output for some cli_net commands ###
