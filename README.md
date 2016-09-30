# Dubbott
* [Build dubbox](/README.md#build-dubbox)
* [Install zookeeper as the service registry](/README.md#install-zookeeper-as-the-service-registry)
* [Build dubbott](/README.md#build-dubbott)
* [Debug the junit test. Import to eclipse first](//README.md#debug-the-junit-test.-import-to-eclipse-first)

## Build dubbox
```bash
$ git clone https://github.com/dangdangdotcom/dubbox
$ cd dubbox
$ mvn install -Dmaven.test.skip=true
```

## Install zookeeper as the service registry
Download zookeeper http://www-us.apache.org/dist/zookeeper/zookeeper-3.4.8/zookeeper-3.4.8.tar.gz
Start zookeeper server:
```text
Neals-MacBook-Pro:zookeeper-3.4.6 nealhu$ cat conf/zoo.cfg 
# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial 
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between 
# sending a request and getting an acknowledgement
syncLimit=5
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just 
# example sakes.
dataDir=/tmp/zookeeper
# the port at which the clients will connect
clientPort=2181
# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the 
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1
```
```bash
$ cd zookeeper-3.4.6
$ ./bin/zkServer.sh start 
```

## Build dubbott
```bash
$ git clone git@github.com:nbqyqx/Dubbott.git 
$ cd Dubbott
$ mvn install -Dmaven.test.skip=true
```

## Debug the junit test. Import to eclipse first
Uncomment line:42 and debug with line:44 *ClientConfiguration config = WebClient.getConfig(userRestService)*
Dubbott/dubbott-demo/dubbott-demo-provider/src/test/java/com/tops001/dubbott/demo/user/facade/UserRestServiceTest.java 
