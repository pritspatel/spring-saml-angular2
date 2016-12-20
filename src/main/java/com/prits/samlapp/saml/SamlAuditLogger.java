package com.prits.samlapp.saml;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.xml.util.XMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.log.SAMLLogger;
import org.springframework.security.saml.util.SAMLUtil;

public class SamlAuditLogger implements SAMLLogger {

	private static final Logger log = LoggerFactory.getLogger(SamlAuditLogger.class);
	
	private boolean logMessages = false;
    private boolean logErrors = true;
    
	@Override
	public void log(String operation, String result, SAMLMessageContext context) {
		log(operation, result, context, SecurityContextHolder.getContext().getAuthentication(), null);
	}

	@Override
	public void log(String operation, String result, SAMLMessageContext context, Exception e) {
		log(operation, result, context, SecurityContextHolder.getContext().getAuthentication(), e);
	}

	@Override
	public void log(String operation, String result, SAMLMessageContext context, Authentication a, Exception e) {
		if (!log.isInfoEnabled()) return;

        if (operation == null) operation = "";
        if (result == null) result = "";
        if (context == null) context = new SAMLMessageContext();

        // Log operation
        StringBuilder sb = new StringBuilder();
        sb.append(operation);

        // Log result
        sb.append(";");
        sb.append(result);

        // Log peer address
        sb.append(";");
        if (context.getInboundMessageTransport() != null) {
            HTTPInTransport transport = (HTTPInTransport) context.getInboundMessageTransport();
            sb.append(transport.getPeerAddress());
        }

        // Log local entity ID
        sb.append(";");
        if (context.getLocalEntityId() != null) {
            sb.append(context.getLocalEntityId());
        }

        // Log peer entity ID
        sb.append(";");
        if (context.getPeerEntityId() != null) {
            sb.append(context.getPeerEntityId());
        }

        // Log NameID or principal when available
        sb.append(";");
        if (a != null) {
            if (a.getCredentials() != null && a.getCredentials() instanceof SAMLCredential) {
                SAMLCredential credential = (SAMLCredential) a.getCredentials();
                if (credential.getNameID() != null) {
                    sb.append(credential.getNameID().getValue());
                } else {
                    sb.append(a.getPrincipal());
                }
            } else {
                sb.append(a.getPrincipal());
            }
        }

        // Log SAML message
        sb.append(";");
        if (logMessages) {
            try {
                if (context.getInboundSAMLMessage() != null) {
                    String messageStr = XMLHelper.nodeToString(SAMLUtil.marshallMessage(context.getInboundSAMLMessage()));
                    sb.append(messageStr);
                }
                if (context.getOutboundSAMLMessage() != null) {
                    String messageStr = XMLHelper.nodeToString(SAMLUtil.marshallMessage(context.getOutboundSAMLMessage()));
                    sb.append(messageStr);
                }
            } catch (MessageEncodingException e1) {
                log.warn("Error marshaling message during logging", e1);
            }
        }

        // Log error
        sb.append(";");
        if (logErrors && e != null) {
            StringWriter errorWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(errorWriter));
            sb.append(errorWriter.getBuffer());
        }

        log.info("---------------- AUDIT TRAIL START---------------");
        log.info(sb.toString());
        log.info("---------------- AUDIT TRAIL END---------------");

	}
	
	/**
     * @param logMessages when true whole message will get logged
     */
    public void setLogMessages(boolean logMessages) {
        this.logMessages = logMessages;
    }

    /**
     * @param logErrors when true exceptions will be logged
     */
    public void setLogErrors(boolean logErrors) {
        this.logErrors = logErrors;
    }

}
