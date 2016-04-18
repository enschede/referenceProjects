## Docker commando's 

docker run --name some-cassandra -d -p 7000:7000 -p 7001:7001 -p 7199:7199 -p 9042:9042 -p 9160:9160 cassandra
docker run -it --link some-cassandra:cassandra --rm cassandra cqlsh cassandra
