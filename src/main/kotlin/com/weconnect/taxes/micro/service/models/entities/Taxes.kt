package com.weconnect.taxes.micro.service.models.entities

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


@Entity(name = "taxes")
@Schema(
    name = "Taxes",
    description = "ENTIDAD GESTIONADA EN ESTE MICREOSERVICIO, PERMITE  LA CREACION DE LA TABLA CON TODOS LOS ATRIBUTOS QUE TIENEN LOS " +
            "TAXES(IMPUESTOS) EN LA BASE DE DATOS"
)
class Taxes : Serializable {

    @Id
    @TableGenerator(name = "taxes_sequential_value", initialValue = 163)
    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "taxes_sequential_value"
    )
    @Column(name = "ta_id", unique = true, nullable = false, updatable = false)
    @Schema(
        name = "ta_id",
        description = "ES EL IDENTIFICADOR UNICO DE CADA TAX(IMPUESTO) DENTRO DE LA BASE DE DATOS",
        example = "1",
        readOnly = true
    )
    var taxID: Long? = 0

    @NotEmpty(message = "EL CAMPO NO PUEDE ESTAR VACIO")
    @Column(name = "tax_type", nullable = false)
    @Schema(
        name = "TIPO DE IMPUESTO",
        description = "ES EL NUMERO QUE CORRESPONDE AL TIPO DE IMPUESTO DENTRO DE LA BASE DE DATOS",
        example = "1",
        readOnly = true
    )
    var taxType: String? = ""

    @NotEmpty(message = "EL CAMPO NO PUEDE ESTAR VACIO")
    @Column(name = "tax_name", nullable = false)
    @Schema(
        name = "NOMBRE IMPUESTO",
        description = "ES EL NOMBRE O IDENTIFICADOR DE CADA IMPUESTO DENTRO DE LA BASE DE DATOS",
        example = "323H",
        readOnly = true
    )
    var taxName: String? = ""

    @NotEmpty(message = "EL CAMPO NO PUEDE ESTAR VACIO")
    @Column(name = "tax_detail", nullable = false)
    @Schema(
        name = "DETALLE IMPUESTO",
        description = "ES EL DETALLE DE CADA IMPUESTO DENTRO DE LA BASE DE DATOS",
        example = "HONORARIOS PROFESIONALES Y DEMAS PAGOS POR SERVICIOS RELACIONADOS CON EL TITULO PROFESIONAL",
        readOnly = true
    )
    var taxDetail: String? = ""

    @NotNull(message = "ESTE CAMPO NO PUEDE ESTAR VACIO")
    @Column(name = "tax_value", nullable = false)
    @Schema(
        name = "VALOR IMPUESTO",
        description = "ES EL VALOR DE CADA IMPUESTO DENTRO DE LA BASE DE DATOS",
        example = "100",
        readOnly = true
    )
    var taxValue: Double? = 0.0

    @NotEmpty(message = "EL CAMPO NO PUEDE ESTAR VACIO")
    @Column(name = "tax_name_desc", nullable = false)
    @Schema(
        name = "NOMBRE DE DESCRIPCI[ON DEL IMPUESTO",
        description = "ES LA DESCRIPCION DE CADA IMPUESTO DENTRO DE LA BASE DE DATOS",
        example = "IMPUESTO A LA RENTA",
        readOnly = true
    )
    var taxNameDesc: String? = ""
}