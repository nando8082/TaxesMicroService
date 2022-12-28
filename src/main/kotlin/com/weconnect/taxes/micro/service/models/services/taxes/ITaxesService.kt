package com.weconnect.taxes.micro.service.models.services.taxes

import com.weconnect.taxes.micro.service.models.entities.Taxes
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface ITaxesService {

    @Transactional(readOnly = true)
    fun findAllTaxes(): List<Taxes>

    @Transactional(readOnly = false)
    fun saveTaxes(taxes: Taxes)

    @Transactional(readOnly = true)
    fun findTaxesByTaxesID(taxID: Long): Optional<Taxes>

}