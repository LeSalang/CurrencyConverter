package com.lesa.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ExchangeRatesSerializer : Serializer<ExchangeRatesDB> {
    override val defaultValue: ExchangeRatesDB
        get() = ExchangeRatesDB.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ExchangeRatesDB {
        try {
            return ExchangeRatesDB.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: ExchangeRatesDB, output: OutputStream) {
        t.writeTo(output)
    }
}

val Context.exchangeRatesDataStore by dataStore<ExchangeRatesDB>(
    fileName = "ExchangeRatesDB.pb",
    serializer = ExchangeRatesSerializer
)
