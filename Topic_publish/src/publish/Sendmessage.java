package publish;
import javax.swing.JOptionPane;

import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Base64TopicMessage;
import com.aliyun.mns.model.MailAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

public class Sendmessage {
	Sendmessage(){}
	public void send(MNSClient client,String Topicname,String message)
	{
        try {
        	//��ȡ����
        	CloudTopic topic = client.getTopicRef(Topicname);
            TopicMessage msg = new Base64TopicMessage();
        	msg.setMessageBody(message);
            msg.setMessageTag("filterTag");
            //������Ϣ������
            msg = topic.publishMessage(msg);
            JOptionPane.showMessageDialog(null, "���ͳɹ�", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("send succeed");
            RawTopicMessage msg1 = new RawTopicMessage();
            msg1.setMessageBody(message);

            //set the necessary attributes for mail
            MessageAttributes messageAttributes = new MessageAttributes();
            MailAttributes mailAttributes = new MailAttributes();
            mailAttributes.setAccountName("lidianchao@aliyunmns.xyz");
            mailAttributes.setSubject("Message from Topic:"+Topicname);
            messageAttributes.setMailAttributes(mailAttributes);
            //������Ϣ������
            TopicMessage msg2 = topic.publishMessage(msg1, messageAttributes);
            System.out.println(msg2.getMessageId());
            System.out.println(msg2.getMessageBodyMD5());
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "unsucceed, " + e.getMessage(), "����ʧ��", JOptionPane.ERROR_MESSAGE);
            System.out.println("unsucceed, " + e.getMessage());
        }
	}
}
