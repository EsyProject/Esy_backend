# Commands for start the project
***
### Reload the project
`mvn clean package -DskipTests` - Use this command every time you make any changes to the project
### Docker commands
`docker-compose build` - Use this command every time you change something in the file, and create compose again
`docker-compose up` - Use this command for subit the compose and create the containers.
<br/>
### Can expose the IP for access the API in the same network
`netsh interface portproxy add v4tov4 listenport=8000 listenaddress=0.0.0.0 connectport=8000 connectaddress=172.22.247.138` - Conect the port host:application with your IP