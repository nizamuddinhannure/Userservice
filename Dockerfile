# To Run application we need JDK, so by this line we are saying Docker to download JDK 11 
#which is already available on DockerHub
FROM openjdk:11-jre-slim
EXPOSE 5000
# ADD src		dest
ADD build/libs/user-module.jar user-module.jar

ENTRYPOINT ["java","-jar","/user-module.jar"]


#EXPOSE 5000
#The EXPOSE instruction exposes the specified port and makes it available only for inter-container communication. 
#Let’s understand this with the help of an example.
#Let’s say we have two containers, a nodejs application and a redis server. 
#Our node app needs to communicate with the redis server for several reasons.
#For the node app to be able to talk to the redis server, the redis container needs to expose the port. 
#Have a look at the Dockerfile of official redis image and you will see a line saying EXPOSE 6379. 
#This is what helps the two containers to communicate with each other.
#So when your nodejs app container tries to connect to the 6379 port of the redis container, 
#the EXPOSE instruction is what makes this possible.
#Note: For the node app server to be able to communicate with the redis container, 
#it’s important that both the containers are running in the same docker network.



#ADD build/libs/user-module.jar user-module.jar
#COPY and ADD are both Dockerfile instructions that serve similar purposes. 
#They let you copy files from a specific location into a Docker image.
#COPY takes in a src and destination. It only lets you copy in a local file or directory from 
#your host (the machine building the Docker image) into the Docker image itself.
#ADD lets you do that too, but it also supports 2 other sources. First, 
#you can use a URL instead of a local file / directory. Secondly, 
#you can extract a tar file from the source directly into the destination.
#In most cases if you’re using a URL, you’re downloading a zip file and are then using the RUN command to extract it. 
#However, you might as well just use RUN with curl instead of ADD here so you chain everything into 1 RUN command 
#to make a smaller Docker image.
#A valid use case for ADD is when you want to extract a local tar file into a specific directory in your Docker image. 
#This is exactly what the Alpine image does with ADD rootfs.tar.gz /.
#If you’re copying in local files to your Docker image, always use COPY because it’s more explicit.
