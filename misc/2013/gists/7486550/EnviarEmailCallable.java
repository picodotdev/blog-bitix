package com.blogspot.elblogdepicodev.services.commands;

import java.io.StringWriter;
import java.util.concurrent.Callable;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blogspot.elblogdepicodev.misc.Constantes;
import com.blogspot.elblogdepicodev.misc.Mensaje;
import com.blogspot.elblogdepicodev.services.Service;

public class EnviarEmailCallable implements Callable<Object> {

	private static Logger logger = LoggerFactory.getLogger(EnviarEmailCallable.class);

	private Service service;
	private Mensaje mensaje;

	public EnviarEmailCallable(Service service, Mensaje mensaje) {
		this.service = service;
		this.mensaje = mensaje;
	}

	@Override
	public Object call() {
		try {
			long start = System.currentTimeMillis();

			logger.info("Obteniendo sesión para enviar correos electrónicos");
			Context ic = new InitialContext();
			Session session = (Session) ic.lookup(Constantes.JNDI_MAIL);

			// Obtener el contenido del email
			StringWriter out = new StringWriter();
			service.getFreemarkerService().procesar(mensaje.getPlantilla(), mensaje.getLocale(), mensaje.getDatos(), out);
			String texto = out.toString();

			// Construir el mensaje a enviar
			MimeMessage mm = new MimeMessage(session);
			mm.setFrom(new InternetAddress(Constantes.EMAIL_REMITENTE));
			if (mensaje.getDesinatarios().size() == 1) {
				String destinatario = mensaje.getDesinatarios().iterator().next();
				mm.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			} else {
				mm.addRecipient(Message.RecipientType.TO, new InternetAddress(Constantes.EMAIL_REMITENTE));
				for (String d : mensaje.getDesinatarios()) {
					mm.addRecipient(Message.RecipientType.BCC, new InternetAddress(d));
				}
			}
			mm.setSubject(mensaje.getAsunto());
			mm.setText(texto, "utf-8", "html");

			// Enviar el mensaje
			logger.info("Enviando correo electrónico a {} destinatarios", mm.getAllRecipients().length);
			Transport.send(mm);

			long end = System.currentTimeMillis();
			logger.info("Correo electrónico enviado [{} ms]", end - start);
		} catch (MessagingException e) {
			logger.warn(e.getMessage(), e);
			// Devolver el comando al final de la cola para un posterior intento
			service.getCallableService().submit(this);
		} catch (Exception e) {
			// Ha ocurrido algo grave, descartar el mensaje
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

		return null;
	}
}