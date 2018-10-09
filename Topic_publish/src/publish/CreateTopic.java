package publish;
import javax.swing.JOptionPane;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.QueueMeta;
import com.aliyun.mns.model.SubscriptionMeta;
import com.aliyun.mns.model.TopicMeta;
public class CreateTopic {
	CreateTopic(){}
	public void newCreateTopic(MNSClient client,String topicName){
        try {
        	// step 1 : 创建队列
            QueueMeta queueMeta = new QueueMeta();
            queueMeta.setQueueName(topicName+"Queue");
            CloudQueue queue = client.createQueue(queueMeta); 
            // step 2 : 创建主题
            TopicMeta topicMeta = new TopicMeta();
            topicMeta.setTopicName(topicName);
            CloudTopic topic = client.createTopic(topicMeta);
            JOptionPane.showMessageDialog(null, "创建成功", "消息", JOptionPane.INFORMATION_MESSAGE);
            // step 3 : 创建测试订阅
            SubscriptionMeta subMeta = new SubscriptionMeta();
            subMeta.setSubscriptionName("TestFor"+topicName);//订阅者名称
            subMeta.setNotifyContentFormat(SubscriptionMeta.NotifyContentFormat.SIMPLIFIED);
            subMeta.setEndpoint(topic.generateQueueEndpoint(topicName+"Queue"));
            subMeta.setFilterTag("filterTag");
            topic.subscribe(subMeta);
            System.out.println("create topic");
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "create topic error, " + e.getMessage(), "创建失败", JOptionPane.ERROR_MESSAGE);
            System.out.println("create topic error, " + e.getMessage());
        }
    }
}

