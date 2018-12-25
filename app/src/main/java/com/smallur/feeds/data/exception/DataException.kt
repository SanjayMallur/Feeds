package com.smallur.feeds.data.exception


/**
 * Created by Sanjay
 * Data class to handle error response from server
 * @param statusCode status code of the response
 * @param message message from the server
 * @param error error response
* */
class DataException(private val statusCode: Int?,  message: String?, val error: Error?) : Exception(message)