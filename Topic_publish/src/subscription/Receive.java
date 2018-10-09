package subscription;

import javax.swing.JOptionPane;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;

public class Receive {
	Receive(){}
	Message getreceive(MNSClient client,String topicname)
	{
		Message re=null;
        try {
        	//������Ϣ
        	//��ȡ�������
        	CloudQueue queue = client.getQueueRef(topicname+"Queue");
            Message msgReceive = queue.popMessage(10);
            System.out.println("ReceiveMessage From "+"Topic:"+topicname);
            System.out.println("message handle: " + msgReceive.getReceiptHandle());
            System.out.println("message body: " + msgReceive.getMessageBodyAsString());
            System.out.println("message id: " + msgReceive.getMessageId());
            System.out.println("message dequeue count:" + msgReceive.getDequeueCount());
            re =msgReceive;
            JOptionPane.showMessageDialog(null, "���ճɹ�", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("receive message=========================");
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "receive message error, " + e.getMessage(), "����ʧ��", JOptionPane.ERROR_MESSAGE);
            System.out.println("receive message, " + e.getMessage());
        }
        return re;
	}
}
