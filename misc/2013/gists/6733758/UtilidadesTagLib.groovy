package es.com.blogspot.elblogdepicodev.grails.taglib;

import org.apache.log4j.Level

class UtilidadesTagLib {
    static namespace = 'util'

    def xlog = { attrs, body ->
	def level = (attrs.level)?Level.toLevel(attrs.level):Level.DEBUG
	def message = (attrs.message)?:body()
	def token = (attrs.token)?:''
        message = "[${token}] ${message}".toString()
	//		
	switch (level) {
		case Level.FATAL:
			log.fatal(message)
			break;
		case Level.ERROR:
			log.error(message)
			break;
		case Level.WARN:
			log.warn(message)
			break;
		case Level.INFO:
			log.info(message)
			break;
		case Level.DEBUG:
			log.debug(message)
			break;
		case Level.TRACE:
		default:
			log.trace(message)
			break;
	}
    }
}