package subscription;

import javax.swing.JOptionPane;

import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.SubscriptionMeta;

public class CreateSubscription {
	CreateSubscription(){}
	//ͨ�������������
	void createsub_Mail(MNSClient client,String topicname,String subname,String mail)
	{
        CloudTopic topic = client.getTopicRef(topicname);
        try {
        	//��������
        	// 1. generate the mail endpoint
            String mailEndpoint = topic.generateMailEndpoint(mail);

            // 2. now subscribe to topic
            SubscriptionMeta subMeta = new SubscriptionMeta();
            subMeta.setSubscriptionName(subname);
            subMeta.setEndpoint(mailEndpoint);

            topic.subscribe(subMeta);

            JOptionPane.showMessageDialog(null, "�����ɹ������½���估ʱ�鿴���͡�", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
           
            System.out.println("create Subscription");
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "create Subscription error, " + e.getMessage(), "����ʧ��", JOptionPane.ERROR_MESSAGE);
            System.out.println("create Subscription error, " + e.getMessage());
        }
	}
	//ͨ�����н�������
	void createsub_Queue(MNSClient client,String topicname,String subname)
	{
        CloudTopic topic = client.getTopicRef(topicname);
        try {
        	//��������
            SubscriptionMeta subMeta = new SubscriptionMeta();
            subMeta.setSubscriptionName(subname);//����������
            subMeta.setNotifyContentFormat(SubscriptionMeta.NotifyContentFormat.SIMPLIFIED);
            subMeta.setEndpoint(topic.generateQueueEndpoint(topicname+"Queue"));
            subMeta.setFilterTag("filterTag");
            topic.subscribe(subMeta);
            JOptionPane.showMessageDialog(null, "�����ɹ�", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
           
            System.out.println("create Subscription");
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "create Subscription error, " + e.getMessage(), "����ʧ��", JOptionPane.ERROR_MESSAGE);
            System.out.println("create Subscription error, " + e.getMessage());
        }
	}
}
