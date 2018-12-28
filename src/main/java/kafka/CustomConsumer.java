package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class CustomConsumer
{
	public static void main(String[] args)
	{
        String TOPIC = "riskDeviceFingerprint";

		Properties props = new Properties();
		// 定义kakfa 服务的地址，不需要将所有broker指定上
		props.put("bootstrap.servers", "192.168.25.10:9092,192.168.25.11:9092,192.168.25.12:9092");
		// 制定consumer group
		props.put("group.id", "first");
		// 是否自动确认offset
		props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
		// 自动确认offset的时间间隔 。即多久去查看一次要消费的位置
		props.put("auto.commit.interval.ms", "1000");
		// key的反序列化类，官方的文档里面找不到对应的类，需要去导入的jar包里面去找
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		// value的反序列化类
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// 定义consumer
		final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		// 当虚拟机关闭的时候则执行下面的钩子，用于关闭资源
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				if (consumer != null)
				{
					consumer.close();
				}
			}
		}));
		// 消费者订阅的topic, 可同时订阅多个
		consumer.subscribe(Arrays.asList(TOPIC));

		while (true)
		{
			// 读取数据，读取超时时间为100ms。因为100ms可以读取许多的数据，所以
			ConsumerRecords<String, String> records = consumer.poll(100);

			int count = 0;
			for (ConsumerRecord<String, String> record : records){
				count++;
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
				System.out.println(count);
			}
		}


       /* while (true) {
            ConsumerRecords<String, String> records = consumer.poll(5000);
            System.out.println("topic: "+TOPIC + "pool return records size: "+ records.count());
            for (ConsumerRecord<String, String> record : records){
                System.out.println(record.toString());
                //手动提交已消费数据的offset
                if("false".equalsIgnoreCase("true")){
                    consumer.commitSync();
                }

            }

        }*/
	}
}
