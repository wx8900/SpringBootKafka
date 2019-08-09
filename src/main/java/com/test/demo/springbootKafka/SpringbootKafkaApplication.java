package com.test.demo.springbootKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jack
 * @date 2019/07/24 23:00
 *
 * Install kafka in local:
 * Step 1: Download the 2.2.0 release and un-tar it.
 *   > wget http://us.mirrors.quenda.co/apache/kafka/2.2.0/kafka_2.12-2.2.0.tgz
 *   > tar -xzf kafka_2.12-2.2.0.tgz
 *   > cd kafka_2.12-2.2.0
 *
 * Step 2: Go to path /usr/local/Cellar/kafka212, Start the server
 *   Kafka uses ZooKeeper so you need to first start a ZooKeeper server if you don't already have one.
 *   > bin/zookeeper-server-start.sh config/zookeeper.properties
 *
 *   Now start the Kafka server:
 *   > bin/kafka-server-start.sh config/server.properties
 *
 * Step 3: Create a topic
 *   Let's create a topic named "test" with a single partition and only one replica:
 *   > bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
 *   We can now see that topic if we run the list topic command:
 *   > bin/kafka-topics.sh --list --bootstrap-server localhost:9092
 *   test
 *
 * Step 4: Send some messages
 *   Kafka comes with a command line client that will take input from a file or from standard input and send it out as messages to the Kafka cluster. By default, each line will be sent as a separate message.
 *
 *   Run the producer and then type a few messages into the console to send to the server.
 *   > bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
 *   This is a message
 *   This is another message
 *
 * Step 5: Start a consumer
 *   Kafka also has a command line consumer that will dump out messages to standard output.
 *   > bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
 *   you will see the return info:
 *   This is a message
 *   This is another message
 *
 * Step 6: Setting up a multi-broker cluster
 *   So far we have been running against a single broker, but that's no fun. For Kafka,
 *   a single broker is just a cluster of size one, so nothing much changes other than starting
 *   a few more broker instances. But just to get feel for it, let's expand our cluster to
 *   three nodes (still all on our local machine).
 *
 *   First we make a config file for each of the brokers (on Windows use the copy command instead):
 *
 *   > cp config/server.properties config/server-1.properties
 *   > cp config/server.properties config/server-2.properties
 *   Now edit these new files and set the following properties:
 *
 * config/server-1.properties:
 *     broker.id=1
 *     listeners=PLAINTEXT://:9093
 *     log.dirs=/tmp/kafka-logs-1
 *
 * config/server-2.properties:
 *     broker.id=2
 *     listeners=PLAINTEXT://:9094
 *     log.dirs=/tmp/kafka-logs-2
 * The broker.id property is the unique and permanent name of each node in the cluster. We have to override the port
 * and log directory only because we are running these all on the same machine and we want to keep the brokers from
 * all trying to register on the same port or overwrite each other's data.
 *
 * We already have Zookeeper and our single node started, so we just need to start the two new nodes:
 *
 * > bin/kafka-server-start.sh config/server-1.properties &
 * ...
 * > bin/kafka-server-start.sh config/server-2.properties &
 * ...
 * Now create a new topic with a replication factor of three:
 *
 * > bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 1 --topic my-replicated-topic
 * Okay but now that we have a cluster how can we know which broker is doing what? To see that run the "describe topics" command:
 * > bin/kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic my-replicated-topic
 * Topic:my-replicated-topic   PartitionCount:1    ReplicationFactor:3 Configs:
 *     Topic: my-replicated-topic  Partition: 0    Leader: 1   Replicas: 1,2,0 Isr: 1,2,0
 * ----------------------------------------------------------------------------------------------------------------
 * shutdown kafka:
 *   bin/kafka-server-stop.sh
 *   bin/zookeeper-server-stop.sh
 *   -------------------------------------------
 * start kafka:
 *   cd /usr/local/Cellar/kafka212
 *   bin/zookeeper-server-start.sh config/zookeeper.properties
 *   bin/kafka-server-start.sh config/server.properties
 *   bin/kafka-server-start.sh config/server-1.properties &
 *   bin/kafka-server-start.sh config/server-2.properties &
 *
 *   create topic for 3 nodes:
 *   bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 1 --topic my-replicated-topic
 * ----------------------------------------------------------------------------
 *   1. Upload Project to GitHub
 *      VCS—>Import into Version Control—>Share Project on GitHub
 *
 *   2. Clone clones code on GitHub to local
 *      VCS—>Checkout from Version Control—>Git-> copy the link from github, modify code in local and push to github
 * ----------------------------------------------------------------------------
 */
@SpringBootApplication
public class SpringbootKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaApplication.class, args);
	}

}
