# Topic_publish
阿里云主题操作参考：
https://help.aliyun.com/document_detail/32450.html?spm=a2c4g.11186623.6.654.6f2599a3bjJfro

阿里云邮件推送参考：
https://help.aliyun.com/document_detail/27433.html?spm=a2c4g.11186623.6.597.4b0a250aWXgHhS

注：package publish
class Sendmessage
 // 4.1 set the necessary attributes for mail
    MessageAttributes messageAttributes = new MessageAttributes();
    MailAttributes mailAttributes = new MailAttributes();
    mailAttributes.setAccountName("direct_mail_account_name@aliyun-inc.com");//改为你的发信地址
    mailAttributes.setSubject("TestMailSubject");
    messageAttributes.setMailAttributes(mailAttributes);
