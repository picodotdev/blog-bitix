// Modo binario
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);
props.put(CloudEventSerializer.ENCODING_CONFIG, Encoding.BINARY);

// Modo estructurado
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);
props.put(CloudEventSerializer.ENCODING_CONFIG, Encoding.STRUCTURED);