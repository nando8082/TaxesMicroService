package com.weconnect.taxes.micro.service.models.services.taxes

import com.weconnect.taxes.micro.service.models.dao.ITaxesDAO
import com.weconnect.taxes.micro.service.models.entities.Taxes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaxesServiceImpl : ITaxesService{

    @Autowired
    private val iTaxesDAO: ITaxesDAO? = null

    override fun findAllTaxes(): List<Taxes> {
        return iTaxesDAO!!.findAll() as List<Taxes>
    }

    override fun saveTaxes(taxes: Taxes) {
        iTaxesDAO!!.save(taxes)
    }

    override fun findTaxesByTaxesID(taxID: Long): Optional<Taxes> {
        return iTaxesDAO!!.findById(taxID)
    }


}