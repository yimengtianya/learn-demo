##RabbitMQ的基本使用

###p1:单发送单接收

###p2:单发送多接收
一个发送端，多个接收端，如分布式的任务派发。为了保证消息发送的可靠性，不丢失消息，使消息持久化了。同时为了防止接收端在处理消息时down掉，只有在消息处理完成后才发送ack消息。

###p3:发布订阅模式
发送端发送广播消息，多个接收端接收,即广播消息，不需要使用queue，发送端不需要关心谁接收。

###p4:按路线发送接收
发送端按routing key发送消息，不同的接收端按不同的routing key接收消息。(与模式3发布订阅的区别是多了routing key)

###p5:按topic发送接收
发送端不只按固定的routing key发送消息，而是按字符串“匹配”发送(由逗号隔开的单词)，接收端同样如此（*号和#进行匹配）。