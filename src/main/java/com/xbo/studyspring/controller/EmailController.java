package com.xbo.studyspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {
    @Autowired
    private JavaMailSender mailSender;
    /**
     * 纯文本格式
     * @return
     */
    @GetMapping("/simple")
    public String simpleSend() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wangshyn@163.com");
        message.setTo("120337130@qq.com");
        message.setSubject("主题：来自xbo邮件");
        message.setText("测试java邮件发送");
        mailSender.send(message);
        return "发送成功!";
    }
    @GetMapping("/attach")
    public String attachSend() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("wangshyn@163.com");
        helper.setTo("120337130@qq.com");
        helper.setSubject("主题：来自xbo邮件(带附件)");
        helper.setText("(含附件)测试java邮件发送");
        //添加附件
        File qrCode = new File("emailimage.jpg");
        //建议文件带上后缀，可支持在线预览
        helper.addAttachment("测试图片.jpg", qrCode);
        mailSender.send(mimeMessage);
        return "附件邮件发送成功!";
    }

    /**
     * html格式
     * @return
     * @throws MessagingException
     */
    @GetMapping("/html")
    public String htmlSend() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("wangshyn@163.com");
        helper.setTo("120337130@qq.com");
        helper.setSubject("主题：来自xbo邮件(带附件)");
        helper.setText("<html><body><div>(含附件)java测试邮件发送</div><div><img src='cid:emailimage'></div></body></html>",true);
        //抄送人
//		helper.setCc("");
        //密送人
//		helper.setBcc("");
        //添加附件
        File qrCode = new File("emailimage.jpg");
        //建议文件带上后缀，可支持在线预览
        helper.addAttachment("测试图片.jpg", qrCode);
        helper.addInline("emailimage", qrCode);
        mailSender.send(mimeMessage);
        return "附件邮件发送成功!";
    }

    @GetMapping("/template")
    public String template(String userName) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("wangshyn@163.com");
        helper.setTo("wangshyn@163.com");
        helper.setSubject("主题：" + userName + ",你有一封来自xbo邮件(From模版)");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userName", StringUtils.isEmpty(userName) ? "wangshyn" : userName);
        String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("mail.html"), model);
        helper.setText(templateString,true);
        //抄送人
//		helper.setCc("");
        //密送人
//		helper.setBcc("");
        //添加附件
        File qrCode = new File("emailimage.jpg");
        //建议文件带上后缀，可支持在线预览
        helper.addAttachment("测试图片.jpg", qrCode);
        helper.addInline("emailimage", qrCode);
        mailSender.send(mimeMessage);
        return "模版文件发送成功!";
    }
    //自动注入
    @Autowired
    freemarker.template.Configuration freemarkerConfig;
}
