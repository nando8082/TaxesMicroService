package com.weconnect.taxes.micro.service.models.services.validations

import com.weconnect.taxes.micro.service.StaticValues
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataAccessException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.validation.BindingResult
import java.util.stream.Collectors

@Service
class ValidationServiceImpl :IValidationService {

    override fun validationObject(result: BindingResult): Any {
        val response = HashMap<String, Any>()
        val errors = result.fieldErrors
            .stream().map { err -> "'FIELD ${err.field} ' ${err.defaultMessage}" }
            .collect(Collectors.toList())
        response["errors"] = errors
        return ResponseEntity<Map<String, Any>>(response, HttpStatus.BAD_REQUEST)
    }

    override fun getExceptionMessage(exception: Exception): Any {
        println(exception.printStackTrace())
        println(exception.cause!!.message)
        println(exception.printStackTrace())
        val response = HashMap<String, Any>()
        response["error"] = "${exception.cause} : ${exception.message} "
        return if (exception.cause!!.toString().contains(ConstraintViolationException::class.java.canonicalName)) {
            response["message"] = StaticValues.MESSAGE_ERROR_DUPLICATE_KEY
            ResponseEntity<Map<String, Any>>(response, HttpStatus.CONFLICT)
        } else if (exception.cause!!.toString().contains(DataAccessException::class.java.canonicalName)) {
            response["message"] = StaticValues.MESSAGE_ERROR_SERVER
            ResponseEntity<Map<*, *>>(response, HttpStatus.BAD_REQUEST)
        } else if (exception.cause!!.toString().contains(DataIntegrityViolationException::class.java.canonicalName)) {
            response["message"] = StaticValues.MESSAGE_ERROR_DUPLICATE_ID
            return ResponseEntity<Map<String, Any>>(response, HttpStatus.BAD_REQUEST)
        } else {
            response["message"] = StaticValues.MESSAGE_ERROR_SERVER
            ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}