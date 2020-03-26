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
        properties.put("mail.transport.protocol", "smtp");// ����Э��
        properties.put("mail.smtp.host", "smtp.qq.com");// ������
        properties.put("mail.smtp.port", 465);// �˿ں�
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// �����Ƿ�ʹ��ssl��ȫ���� ---һ�㶼ʹ��
        properties.put("mail.debug", "true");// �����Ƿ���ʾdebug��Ϣ true ���ڿ���̨��ʾ�����Ϣ
        // �õ��ػ�����
        Session session = Session.getInstance(properties);
        // ��ȡ�ʼ�����
        Message message = new MimeMessage(session);
        // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
        Multipart mainPart = new MimeMultipart();
        // ���÷����������ַ
        message.setFrom(new InternetAddress("874471992@qq.com"));
     // ����һ������HTML���ݵ�MimeBodyPart
        BodyPart html = new MimeBodyPart();
        
        html.setContent(this.s+name+this.s2+code+this.s3, "text/html; charset=utf-8");
        //String msg="������֤����:"+123456 ;
        String msg="������֤����:"+123456 ;
        // �����ռ��������ַ 
        //message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com")});
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));//һ���ռ���
        // �����ʼ�����
        message.setSubject("sinlo��̳");
        // �����ʼ�����
     //   message.setText(msg);
     // ��MiniMultipart��������Ϊ�ʼ�����
        mainPart.addBodyPart(html);
        message.setContent(mainPart);
        message.setSentDate(new Date()); //��������
//        message.set
        // �õ��ʲ����
        Transport transport = session.getTransport();
        // �����Լ��������˻�
        transport.connect("874471992@qq.com", "jcxajgelvanybaia");// ����ΪQQ���俪ͨ��stmp�����õ��Ŀͻ�����Ȩ��
        // �����ʼ�
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    String s = "<HTML>\n" +
            "\n" +
            "<head></head>\n" +
            "\n" +
            "<body>\n" +
            "    <table\n" +
            "        style=\"FONT-SIZE: 12px; OVERFLOW: hidden; WORD-WRAP: break-word; BORDER-TOP: #ddd 1px solid; HEIGHT: auto; FONT-FAMILY: 'Helvetica Neue Regular',Helvetica,Arial,Tahoma,'Microsoft YaHei','San Francisco','�\uE1BF��雅黑','Hiragino Sans GB',STHeitiSC-Light; BORDER-RIGHT: #ddd 1px solid; WIDTH: 600px; BORDER-BOTTOM: #ddd 1px solid; WORD-BREAK: break-all; COLOR: #555; TEXT-ALIGN: left; BORDER-LEFT: #ddd 1px solid; MARGIN: auto; border-radius: 3px\"\n" +
            "        cellSpacing=0 cellPadding=0 border=0>\n" +
            "        <tbody style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "            <tr\n" +
            "                style=\"HEIGHT: 60px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px; BACKGROUND-COLOR: #393d49\">\n" +
            "                <td style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "                    <div\n" +
            "                        style=\"COLOR: #5eb576; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px 0px 0px 30px; PADDING-RIGHT: 0px\">\n" +
            "                    sinlo��̳</div>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "            <tr style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "                <td\n" +
            "                    style=\"PADDING-BOTTOM: 30px; PADDING-TOP: 30px; PADDING-LEFT: 30px; MARGIN: 0px; PADDING-RIGHT: 30px\">\n" +
            "                    <P\n" +
            "                        style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px 0px 10px; LINE-HEIGHT: 20px; PADDING-RIGHT: 0px\">\n" +
            "                      Hi,<EM style=\"FONT-WEIGHT: 700\">";
            String s2="</EM>�����֤���ǣ� </P>\n" +
            "                    <h1>";
            
            String s3 ="</h1>\n" +
            "                    <P\n" +
            "                        style=\"FONT-SIZE: 12px; MARGIN-TOP: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; PADDING-LEFT: 10px; LINE-HEIGHT: 20px; PADDING-RIGHT: 10px; BACKGROUND-COLOR: #f2f2f2\">\n" +
            "                        ������ʼ��������㱾�˲�����������м������������佫�ᱻ���˰󶨡� </P>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "            <tr\n" +
            "                style=\"HEIGHT: 35px; COLOR: #999; PADDING-BOTTOM: 0px; TEXT-ALIGN: center; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px; BACKGROUND-COLOR: #fafafa\">\n" +
            "                <td style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\">\n" +
            "                    ϵͳ�ʼ�������ֱ�ӻظ���</td>\n" +
            "            </tr>\n" +
            "        </tbody>\n" +
            "    </table>\n" +
            "</BODY>\n" +
            "\n" +
            "</HTML>\n";

    
}