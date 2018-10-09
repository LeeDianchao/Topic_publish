package publish;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.utils.ServiceSettings;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Panel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Publisher extends JFrame {

	private JPanel contentPane;
	private JTextField titletext;
	private CloudAccount account = new CloudAccount(
			ServiceSettings.getMNSAccessKeyId(),
            ServiceSettings.getMNSAccessKeySecret(),
            ServiceSettings.getMNSAccountEndpoint());
	private MNSClient client = account.getMNSClient();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Publisher frame = new Publisher();
					frame.setTitle("发布者程序");
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
	public Publisher() {
		setBounds(100, 100, 446, 391);
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        exit();
		      }
		    });
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("创建新主题");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("创建主题");
		mnNewMenu.add(mntmNewMenuItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextArea messtext = new JTextArea();
		
		Panel panel_2 = new Panel();
		
		JPanel panel = new JPanel();
		panel_2.add(panel);
		
		JButton sendButton = new JButton("发送");
		panel.add(sendButton);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=messtext.getText();
				System.out.println(s);
			}
		});
		
		JPanel panel_1 = new JPanel();
		
		JLabel label = new JLabel("信息编辑");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
						.addComponent(messtext)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 426, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(messtext, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblNewLabel = new JLabel("输入主题");
		
		titletext = new JTextField();
		titletext.setColumns(10);
		
		JButton createButton = new JButton("创建");
		createButton.setVisible(false);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=titletext.getText();
				CreateTopic c=new CreateTopic();
				c.newCreateTopic(client,s);
				createButton.setVisible(false);
			}
		});
		//发送响应
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mess=messtext.getText();
				String title=titletext.getText();
				Sendmessage sen=new Sendmessage();
				sen.send(client,title, mess);
				System.out.println(mess);
			}
		});
		//创建响应
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createButton.setVisible(true);
			}
		});
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(titletext, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(createButton)
					.addGap(20))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(titletext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(createButton)))
		);
		panel_1.setLayout(gl_panel_1);
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
