package com.test.demo.springbootKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jack
 * @date 2019/07/24 23:00
 *
 * Step 1: Download the 2.2.0 release and un-tar it.
 *   > wget http://us.mirrors.quenda.co/apache/kafka/2.2.0/kafka_2.12-2.2.0.tgz
 *   > tar -xzf kafka_2.12-2.2.0.tgz
 *   > cd kafka_2.12-2.2.0
 *
 * Step 2: Start the server
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
 * ----------------------------------------------
 *   1. Upload Project to GitHub
 *      VCS—>Import into Version Control—>Share Project on GitHub
 *
 *   2. Clone clones code on GitHub to local
 *      VCS—>Checkout from Version Control—>Git-> copy the link from github, modify code in local and push to github
 */
@SpringBootApplication
public class SpringbootKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaApplication.class, args);
	}

}
