package com.sopp.wallet.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ShortDateSerializer : JsonSerializer<LocalDate>() {
    private val formatter = DateTimeFormatter.ofPattern("MM/yyyy")

    override fun serialize(
        value: LocalDate,
        gen: JsonGenerator,
        arg2: SerializerProvider
    ) {
        gen.writeString(formatter.format(value))
    }
}

class ShortDateDeserializer : JsonDeserializer<LocalDate?>() {
    private var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun deserialize(
        jp: JsonParser,
        ctxt: DeserializationContext?
    ): LocalDate {
        if (jp.currentToken.equals(JsonToken.VALUE_STRING)) {
            val text = jp.text.toString()
            val date = LocalDate.parse("01/$text", formatter)
            return date.withDayOfMonth(date.lengthOfMonth())
        } else {
            throw JsonParseException("Could not parse LocalDate from JSON")
        }
    }
}