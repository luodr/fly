package com.ldr.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSend {
    
    public  void sendMail(String mail,String name ,String code)throws AddressException,MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("874471992@qq.com"));
     // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        
        html.setContent(this.s+name+this.s2+code+this.s3, "text/html; charset=utf-8");
        //String msg="您的验证码是:"+123456 ;
        String msg="您的验证码是:"+123456 ;
        // 设置收件人邮箱地址 
        //message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com")});
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));//一个收件人
        // 设置邮件标题
        message.setSubject("sinlo论坛");
        // 设置邮件内容
     //   message.setText(msg);
     // 将MiniMultipart对象设置为邮件内容
        mainPart.addBodyPart(html);
        message.setContent(mainPart);
        message.setSentDate(new Date()); //发送日期
//        message.set
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("874471992@qq.com", "jcxajgelvanybaia");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    String s = "<HTML>\n" +
            "\n" +
            "<head></head>\n" +
            "\n" +
            "<body>\n" +
            "    <table\n" +
            "        style=\"FONT-SIZE: 12px; OVERFLOW: hidden; WORD-WRAP: break-word; BORDER-TOP: #ddd 1px solid; HEIGHT: auto; FONT-FAMILY: 'Helvetica Neue Regular',Helvetica,Arial,Tahoma,'Microsoft YaHei','San Francisco','寰\uE1BF蒋闆呴粦','Hiragino Sans GB',STHeitiSC-Light; BORDER-RIGHT: #ddd 1px solid; WIDTH: 600px; BORDER-BOTTOM: #ddd 1px solid; WORD-BREAK: break-all; COLOR: #555; TEXT-ALIGN: left; BORDER-LEFT: #ddd 1px solid; MARGIN: auto; border-radius: 3px\"\n" +
            "        cellSpacing=0 cellPadding=0 border=0>\n" +
            "        <tbody style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "            <tr\n" +
            "                style=\"HEIGHT: 60px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px; BACKGROUND-COLOR: #393d49\">\n" +
            "                <td style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "                    <div\n" +
            "                        style=\"COLOR: #5eb576; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px 0px 0px 30px; PADDING-RIGHT: 0px\">\n" +
            "                    sinlo论坛</div>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "            <tr style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "                <td\n" +
            "                    style=\"PADDING-BOTTOM: 30px; PADDING-TOP: 30px; PADDING-LEFT: 30px; MARGIN: 0px; PADDING-RIGHT: 30px\">\n" +
            "                    <P\n" +
            "                        style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px 0px 10px; LINE-HEIGHT: 20px; PADDING-RIGHT: 0px\">\n" +
            "                      Hi,<EM style=\"FONT-WEIGHT: 700\">";
            String s2="</EM>你的验证码是： </P>\n" +
            "                    <h1>";
            
            String s3 ="</h1>\n" +
            "                    <P\n" +
            "                        style=\"FONT-SIZE: 12px; MARGIN-TOP: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; PADDING-LEFT: 10px; LINE-HEIGHT: 20px; PADDING-RIGHT: 10px; BACKGROUND-COLOR: #f2f2f2\">\n" +
            "                        如果该邮件不是由你本人操作，请勿进行激活！否则你的邮箱将会被他人绑定。 </P>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "            <tr\n" +
            "                style=\"HEIGHT: 35px; COLOR: #999; PADDING-BOTTOM: 0px; TEXT-ALIGN: center; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px; BACKGROUND-COLOR: #fafafa\">\n" +
            "                <td style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "                    系统邮件，请勿直接回复。</td>\n" +
            "            </tr>\n" +
            "        </tbody>\n" +
            "    </table>\n" +
            "</BODY>\n" +
            "\n" +
            "</HTML>\n";

    
}