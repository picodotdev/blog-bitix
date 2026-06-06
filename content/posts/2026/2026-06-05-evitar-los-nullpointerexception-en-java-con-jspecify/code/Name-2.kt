val name: String? = getName()
name.length        // No compila, name puede ser ser null
name?.length       // Llamada segura, devuelve null si name es null
name!!.length      // Fuerza el unwrap, NPE si es null el error es responsabilidad del programador
name?.length ?: 0  // Uso de elvis operator con fallback si es null