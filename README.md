# README #
* Author name : Aleksey Petrenko
* Travis CI: [![Build Status](https://travis-ci.org/AlexTheLion34/YADRO.svg?branch=master)](https://travis-ci.org/AlexTheLion34/YADRO)
### How to start? ###
* Use Linux operation system (ex. Ubuntu 16.04 LTS)
* Make sure that [Docker](https://www.docker.com) is installed
* Now you are ready to go!
### Installing service ###
* Open terminal
* Run: `docker pull alexthelion34/yadro_service`
* Check if the image was downloaded: `docker images`
### Running service ###
+ In terminal run commands:
    * Work with local machine interfaces: `docker run -p 8080:8080 --network="host" alexthelion34/yadro_service`
    * Work with container interfaces: `docker run -p 8080:8080 alexthelion34/yadro_service`
### Installing client ###
* Open terminal
* Run: `docker pull alexthelion34/yadro_client`
* Check if the image was downloaded: `docker images`
### Before running ###
* Run: `alias cli_net='docker run alexthelion34/yadro_client'`
* Check if alias was added: `alias`
### Running client ###
+ In terminal run commands:
    * `cli_net help`
    * `cli_net --version`
    * and etc.
### Output for some cli_net commands ###
* `cli_net help` <br/>

![cli_net_help_output](https://user-images.githubusercontent.com/37543158/54476346-2ab83200-480d-11e9-9747-2942de182064.png)
* `cli_net list --server 172.17.0.1 --port 8080` <br/>

![cli_net_list_out](https://user-images.githubusercontent.com/37543158/54476347-360b5d80-480d-11e9-92f8-e89ee10b3679.png)
* `cli_net show --name wlxe8cc18bc1213 --server 172.17.0.1 --port 8080` <br/>

![cli_net_show_out](https://user-images.githubusercontent.com/37543158/54476351-4cb1b480-480d-11e9-8674-03c04277a50b.png)

### Note ###
* 172.17.0.1 - is the address of docker0 
* You can find it with command: `ifconfig docker0`



