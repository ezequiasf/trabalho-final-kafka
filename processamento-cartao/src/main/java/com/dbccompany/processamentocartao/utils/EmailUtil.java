package com.dbccompany.processamentocartao.utils;

import com.dbccompany.processamentocartao.enumTemplates.TemplateSituations;
import com.dbccompany.processamentocartao.exceptions.EmailSimplesException;
import com.dbccompany.processamentocartao.templateObjects.TemplateObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailUtil {

    private final JavaMailSender carteiro;
    private final Configuration configuracaoTemplate;

    public void enviarEmailSimples(String remetente, String destinatario,
                                   String assunto, String mensagem) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(remetente);
        msg.setTo(destinatario);
        msg.setSubject(assunto);
        msg.setText(mensagem);
        carteiro.send(msg);
    }

    public void enviarEmailSimples(String remetente, String destinatario,
                                   File conteudoMensagem) {
        try {
            FileInputStream fileInputStream = new FileInputStream(conteudoMensagem);
            InputStreamReader input = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(input);
            String linha = reader.readLine();
            if (!linha.contains("assunto")) {
                throw new EmailSimplesException("Não foi identificado assunto no corpo do texto");
            }
            String assunto = linha.split("assunto:")[0];
            StringBuilder builder = new StringBuilder();
            while (linha != null) {
                linha = reader.readLine();
                builder.append(linha).append(" ");
            }
            String mensagem = builder.toString();
            enviarEmailSimples(remetente, destinatario, assunto, mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmailSimplesException e) {
            log.error(e.getMessage() + "arquivo:" + conteudoMensagem.getPath());
        }
    }


    public void mandarMensagemComAnexo(String remetente, String destinatario,
                                       String assunto, String mensagem, File anexo) {
        try {
            MimeMessageHelper construtorMime = construtorAuxiliarMimeMessage(remetente, destinatario, assunto);
            construtorMime.setText(mensagem);

            FileSystemResource arquivoMontado = new FileSystemResource(anexo);
            construtorMime.addAttachment(anexo.getName(), arquivoMontado);
            carteiro.send(construtorMime.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailTemplate(String remetente
            , String destinatario, String assunto, TemplateObject templateObject, TemplateSituations situacao) {
        try {
            MimeMessageHelper helper = construtorAuxiliarMimeMessage(remetente, destinatario, assunto);
            helper.setText(construirTemplate(templateObject, situacao), true);
            carteiro.send(helper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private MimeMessageHelper construtorAuxiliarMimeMessage(String remetente, String destinatario, String assunto) {
        MimeMessage mimeMessage = carteiro.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(remetente);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject(assunto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mimeMessageHelper;
    }

    private String construirTemplate(TemplateObject templateObj, TemplateSituations situacao) throws IOException, TemplateException {
        configuracaoTemplate.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        Template template = configuracaoTemplate.getTemplate(templateObj.getTemplate(situacao));
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateObj.getDADOS());
    }
}
