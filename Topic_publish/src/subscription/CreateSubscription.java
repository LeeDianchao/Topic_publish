package subscription;

import javax.swing.JOptionPane;

import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.SubscriptionMeta;

public class CreateSubscription {
	CreateSubscription(){}
	//通过邮箱接收推送
	void createsub_Mail(MNSClient client,String topicname,String subname,String mail)
	{
        CloudTopic topic = client.getTopicRef(topicname);
        try {
        	//创建订阅
        	// 1. generate the mail endpoint
            String mailEndpoint = topic.generateMailEndpoint(mail);

            // 2. now subscribe to topic
            SubscriptionMeta subMeta = new SubscriptionMeta();
            subMeta.setSubscriptionName(subname);
            subMeta.setEndpoint(mailEndpoint);

            topic.subscribe(subMeta);

            JOptionPane.showMessageDialog(null, "创建成功，请登陆邮箱及时查看推送。", "消息", JOptionPane.INFORMATION_MESSAGE);
           
            System.out.println("create Subscription");
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "create Subscription error, " + e.getMessage(), "创建失败", JOptionPane.ERROR_MESSAGE);
            System.out.println("create Subscription error, " + e.getMessage());
        }
	}
	//通过队列接收推送
	void createsub_Queue(MNSClient client,String topicname,String subname)
	{
        CloudTopic topic = client.getTopicRef(topicname);
        try {
        	//创建订阅
            SubscriptionMeta subMeta = new SubscriptionMeta();
            subMeta.setSubscriptionName(subname);//订阅者名称
            subMeta.setNotifyContentFormat(SubscriptionMeta.NotifyContentFormat.SIMPLIFIED);
            subMeta.setEndpoint(topic.generateQueueEndpoint(topicname+"Queue"));
            subMeta.setFilterTag("filterTag");
            topic.subscribe(subMeta);
            JOptionPane.showMessageDialog(null, "创建成功", "消息", JOptionPane.INFORMATION_MESSAGE);
           
            System.out.println("create Subscription");
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "create Subscription error, " + e.getMessage(), "创建失败", JOptionPane.ERROR_MESSAGE);
            System.out.println("create Subscription error, " + e.getMessage());
        }
	}
}
