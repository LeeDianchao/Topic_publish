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
        	// step 1 : ��������
            QueueMeta queueMeta = new QueueMeta();
            queueMeta.setQueueName(topicName+"Queue");
            CloudQueue queue = client.createQueue(queueMeta); 
            // step 2 : ��������
            TopicMeta topicMeta = new TopicMeta();
            topicMeta.setTopicName(topicName);
            CloudTopic topic = client.createTopic(topicMeta);
            JOptionPane.showMessageDialog(null, "�����ɹ�", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
            // step 3 : �������Զ���
            SubscriptionMeta subMeta = new SubscriptionMeta();
            subMeta.setSubscriptionName("TestFor"+topicName);//����������
            subMeta.setNotifyContentFormat(SubscriptionMeta.NotifyContentFormat.SIMPLIFIED);
            subMeta.setEndpoint(topic.generateQueueEndpoint(topicName+"Queue"));
            subMeta.setFilterTag("filterTag");
            topic.subscribe(subMeta);
            System.out.println("create topic");
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "create topic error, " + e.getMessage(), "����ʧ��", JOptionPane.ERROR_MESSAGE);
            System.out.println("create topic error, " + e.getMessage());
        }
    }
}

