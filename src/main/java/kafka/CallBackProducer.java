package kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class CallBackProducer
{

	public static void main(String[] args) throws InterruptedException
	{
		Properties props = new Properties();
		// Kafka服务端的主机名和端口号
		props.put("bootstrap.servers", "linux01:9092,linux02:9092,linux03:9092");
		// 等待所有副本节点的应答
		props.put("acks", "all");
		// 消息发送最大尝试次数
		props.put("retries", 0);
		// 一批消息处理大小
		props.put("batch.size", 16384);
		// 增加服务端请求延时
		props.put("linger.ms", 1);
		// 发送缓存区内存大小
		props.put("buffer.memory", 33554432);
		// key序列化
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// value序列化
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 自定义分区
		// props.put("partitioner.class", "com.atguigu.kafka.CustomPartitioner");

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);

		for (int i = 0; i < 50; i++)
		{
			Thread.sleep(500);
			kafkaProducer.send(new ProducerRecord<String, String>("test1", "hh" + i), new Callback()
			{
				// 生产成功或者失败都会执行下面的代码。RecordMetadata是消息发送成功后元数据的信息。Exception是消息发送失败的信息
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception)
				{

					if (metadata != null)
					{
						// 输出消息发送成功之后。这条消息位于那个分区的，那个偏移量
						System.out.println(metadata.partition() + "---" + metadata.offset());
					}
				}
			});
		}

		kafkaProducer.close();

	}

}
