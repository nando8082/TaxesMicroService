package com.weconnect.taxes.micro.service

class StaticValues {

    companion object {
        const val MESSAGE_SUCCESS_FIND = "THE REQUESTED INFORMATION HAS BEEN FOUND IN THE DATABASE"
        const val MESSAGE_SUCCESS_SAVE = "THE INFORMATION HAS BEEN SAVE IN THE DATABASE"
        const val MESSAGE_SUCCESS_UPDATE = "THE UPDATE REQUIRED HAS BEEN SUCCESSFULLY COMPLETED"

        //        ERROR MESSAGES
        const val MESSAGE_ERROR_FIND = "ERROR !!!, IN THE DATABASE THERE IS NO REQUIRED INFORMATION WITH THE CODE"
        const val MESSAGE_ERROR_FIND_LIST = "ERROR !!!, IN THE DATABASE THERE IS NO INFORMATION"
        const val MESSAGE_ERROR_SAVE = "ERROR !!!, THE INFORMATION HAS NOT BEEN SAVE IN THE DATABASE"
        const val MESSAGE_ERROR_DUPLICATE_ID = "ERROR !!!, DUPLICATED INFORMATION"
        const val MESSAGE_ERROR_SERVER = "SERVER ERROR !!!, COMMUNICATE WITH THE SYSTEM ADMINISTRATOR"
        const val MESSAGE_ERROR_DUPLICATE_KEY = "THE KEY HAS BEEN REGISTERED ON THE SERVER"

        //        AUTHORIZATION BILL MESSAGE
        const val BILL_AUTHORIZATION_OK = "THE INVOICE HAS BEEN SUCCESSFULLY AUTHORIZED"
        const val BILL_AUTHORIZATION_BAD_REQUEST = "THE INVOICE HAS NOT BEEN SUCCESSFULLY AUTHORIZED"
    }
}