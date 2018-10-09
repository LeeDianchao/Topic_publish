package subscription;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.utils.ServiceSettings;
import com.aliyun.mns.model.Message;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Subscription extends JFrame {

	private JPanel contentPane;
	private JTextField topictext;
	private JTextField subtext;
	private CloudAccount account = new CloudAccount(
			ServiceSettings.getMNSAccessKeyId(),
            ServiceSettings.getMNSAccessKeySecret(),
            ServiceSettings.getMNSAccountEndpoint());
	private MNSClient client = account.getMNSClient();
	private int type=0;
	private JTextField mailtext;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subscription frame = new Subscription();
					frame.setTitle("订阅者程序");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Subscription() {
		
		setBounds(100, 100, 470, 414);
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        exit();
		      }
		    });
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Submenu = new JMenu("新建订阅");
		menuBar.add(Submenu);
		
		JMenuItem SubmenuItem = new JMenuItem("以队列接收推送");
		Submenu.add(SubmenuItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("以邮件接收推送");
		Submenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JTextArea messagetext = new JTextArea();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(messagetext, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(messagetext, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
		);
		
		JLabel topiclabel = new JLabel("主题名称");
		
		topictext = new JTextField();
		topictext.setColumns(10);
		
		JLabel sublabel = new JLabel("收到信息:");
		subtext = new JTextField();
		subtext.setColumns(10);
		subtext.setVisible(false);
		JButton button = new JButton("接收消息");
		
		JLabel maillabel = new JLabel("邮箱地址");
		maillabel.setVisible(false);
		mailtext = new JTextField();
		mailtext.setColumns(10);
		mailtext.setVisible(false);
		
		//创建响应
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(type==1)
				{
					CreateSubscription cs =new CreateSubscription();
					cs.createsub_Queue(client,topictext.getText(), subtext.getText());
					System.out.println("1");
				}
				if(type==2)
				{
					CreateSubscription cs =new CreateSubscription();
					cs.createsub_Mail(client,topictext.getText(), subtext.getText(),mailtext.getText());
					System.out.println("2");
				}
				if(type==0)
				{
					Receive re=new Receive();
					Message s=re.getreceive(client, topictext.getText());
					messagetext.setText("ReceiveMessage From "+"Topic:"+topictext.getText()+"\n"+s.getMessageBodyAsString());
				}
				subtext.setVisible(false);
				sublabel.setText("收到信息:");
				maillabel.setVisible(false);
				mailtext.setVisible(false);
				button.setText("接收消息");
				type=0;
			}
		});
		
		SubmenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setText("创建订阅");
				maillabel.setVisible(false);
				mailtext.setVisible(false);
				sublabel.setText("订阅名称");
				subtext.setVisible(true);
				type=1;
			}
		});
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setText("创建订阅");
				sublabel.setText("订阅名称");
				subtext.setVisible(true);
				maillabel.setVisible(true);
				mailtext.setVisible(true);
				type=2;
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(topiclabel)
						.addComponent(sublabel)
						.addComponent(maillabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(topictext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(subtext, 249, 249, 249))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button))
						.addComponent(mailtext))
					.addGap(32))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(topictext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(topiclabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(sublabel)
								.addComponent(subtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mailtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(maillabel))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	public void exit() 
	{    
		Object[] options = { "确定", "取消" };    
		JOptionPane pane2 = new JOptionPane("真想退出吗?", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION, null, options, options[1]);    
		JDialog dialog = pane2.createDialog(this, "警告");    
		dialog.setVisible(true);    
		Object selectedValue = pane2.getValue();    
		if (selectedValue == null || selectedValue == options[1]) 
		{      
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		} 
		else if (selectedValue == options[0]) 
		{      
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			client.close();
		}  
	}
}
