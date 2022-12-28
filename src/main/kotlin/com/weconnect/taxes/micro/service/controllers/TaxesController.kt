package com.weconnect.taxes.micro.service.controllers

import com.weconnect.taxes.micro.service.StaticValues
import com.weconnect.taxes.micro.service.models.entities.Taxes
import com.weconnect.taxes.micro.service.models.services.taxes.ITaxesService
import com.weconnect.taxes.micro.service.models.services.validations.IValidationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/taxesController")
@Tag(
    name = "CONTROLADOR DE TAXES",
    description = "ESTE MICROSERVICIO CONTROLA TODAS LA PETICIONES QUE SE REALIZARAN A LA BASE DE DATOS DENTRO DE LA TABLA TAXES(IMPUESTOS)"
)
class TaxesController {

    @Autowired
    private val iTaxesService: ITaxesService? = null

    @Autowired
    private val iValidationService: IValidationService? = null

    @GetMapping("/findAllTaxes")
    @Operation(
        summary = "LISTAR TODO LOS IMPUESTOS EXISTENTES EN LA BASE DE DATOS",
        description = "ESTE SERVICIO SE ENCARGA DE LISTAR TODOS LOS IMPUESTOS EXISTENTES" +
                "EN LA BASE DE DATOS, SI EL RESULTADO DE LA BUSQUEDA ES CORRECTA RETORNARA" +
                "UNA LISTA DE IMPUESTOS, CASO CONTRARIO NO LISTARA NADA",
        method = "GET"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "SI EXISTEN IMPUESTOS DEVOLVERA UNA LISTA DE IMPUESTOS EXISTENTES EN LA BASE DE DATOS",
                content = [
                    (Content(
                        mediaType = "application/json",
                        schema = Schema(
                            allOf = arrayOf(Taxes::class)
                        )
                    ))
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "ESTE MENSAJE SE MOSTRARA EN CASO DE QUE NO EXISTAN DATOS DE IMPUESTOS EN LA BASE DE DATOS"
            ),
            ApiResponse(
                responseCode = "500",
                description = "ESTE MENSAJE SE MOSTRARA EN CASO DE QUE EXISTA UN  ERROR INTERNO DEL SERVIDOR"
            )
        ]
    )
    fun findAllTaxes(
    ): Any {
        val response = HashMap<String, Any>()
        return try {
            val taxesList = iTaxesService!!.findAllTaxes()
            run {
                response["message"] = StaticValues.MESSAGE_SUCCESS_FIND
                response["response"] = taxesList
                ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException) {
            iValidationService!!.getExceptionMessage(e)
        }
    }

    //CREAR IMPUESTO
    @PostMapping("/createTax")
    @Operation(
        summary = "CREAR UN NUEVO TAX(IMPUESTO)",
        description = "ESTE SERVICIO SE ENCARGA DE CREAR NUEVOS TAXES(IMPUESTOS) EN LA BASE DE DATOS",
        method = "POST",
        parameters = [Parameter(
            name = "TAX(IMPUESTO)",
            description = "PARAMETROS QUE POSEEN LOS OBJETOS DE TIPO TAX",
            required = true
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "SI LOS DATOS INGRESADOS SON CORRECTOS SE CREARA UN NUEVO TAX(IMPUESTO) EN LA BASE DE DATOS",
                content = [
                    (Content(
                        mediaType = "application/json",
                        schema = Schema(
                            allOf = arrayOf(Taxes::class)
                        )
                    ))
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "ESTE MENSAJE SE MOSTRARA EN CASO DE QUE LOS DATOS INGRESADOS SEAN INCORRECTOS"
            ),
            ApiResponse(
                responseCode = "500",
                description = "ESTE MENSAJE SE MOSTRARA EN CASO DE QUE EXISTA UN ERROR INTERNO DEL SERVIDOR"
            )
        ]
    )
    fun saveTax(
        @Valid @RequestBody taxes: Taxes, result: BindingResult
    ): Any {
        val response = HashMap<String, Any>()
        return try {
            if (result.hasErrors())
                return iValidationService!!.validationObject(result)

            val buscarTax = iTaxesService!!.findTaxesByTaxesID(
                taxID = taxes.taxID!!.toLong()
            )

            if (buscarTax.isPresent)
                run {
                    response["message"] = StaticValues.MESSAGE_ERROR_DUPLICATE_ID
                    response["response"] = buscarTax
                    ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
                }

            val newTax = iTaxesService!!.saveTaxes(
                taxes = taxes
            )

            run {
                response["message"] = StaticValues.MESSAGE_SUCCESS_SAVE
                response["response"] = newTax
                ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException) {
            iValidationService!!.getExceptionMessage(e)
        }
    }

    //ACTUALIZAR IMPUESTO
    @PutMapping("/updateTax/{taxID}")
    @Operation(
        summary = "ACTUALIZAR UN TAX(IMPUESTO) EXISTENTE",
        description = "ESTE SERVICIO SE ENCARGA DE ACTUALIZAR TAXES(IMPUESTOS) EXISTENTES DENTRO DE LA BASE DE DATOS",
        method = "PUT",
        parameters = [Parameter(
            name = "TAX(IMPUESTO)",
            description = "PARAMETROS QUE POSEEN LOS OBJETOS DE TIPO TAX",
            required = true
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "SI LOS DATOS INGRESADOS SON CORRECTOS SE ACTUALIZARA UN TAX(IMPUESTO) EN LA BASE DE DATOS",
                content = [
                    (Content(
                        mediaType = "application/json",
                        schema = Schema(
                            allOf = arrayOf(Taxes::class)
                        )
                    ))
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "ESTE MENSAJE SE MOSTRARA EN CASO DE QUE LOS DATOS INGRESADOS SEAN INCORRECTOS"
            ),
            ApiResponse(
                responseCode = "500",
                description = "ESTE MENSAJE SE MOSTRARA EN CASO DE QUE EXISTA UN ERROR INTERNO DEL SERVIDOR"
            )
        ]
    )
    fun updateTax(
        @PathVariable("taxID") taxID: Long,
        @Valid @RequestBody taxes: Taxes,
        result: BindingResult
    ): Any {
        val response = HashMap<String, Any>()
        return try {
            if (result.hasErrors())
                return iValidationService!!.validationObject(result)

            val buscarTax = iTaxesService!!.findTaxesByTaxesID(
                taxID = taxID
            )

            if (buscarTax.isPresent and taxes.taxID!!.equals(taxID)) {
                val updateTax = iTaxesService!!.saveTaxes(
                    taxes = taxes
                )
                run {
                    response["message"] = StaticValues.MESSAGE_SUCCESS_UPDATE
                    response["response"] = taxes
                    ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
                }
            } else {
                run {
                    response["message"] = StaticValues.MESSAGE_ERROR_FIND
                    ResponseEntity<Map<*, *>>(response, HttpStatus.OK)
                }
            }

        } catch (e: DataAccessException) {
            iValidationService!!.getExceptionMessage(e)
        }
    }

}