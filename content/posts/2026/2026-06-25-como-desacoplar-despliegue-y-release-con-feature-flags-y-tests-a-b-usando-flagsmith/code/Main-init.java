private static FlagsmithClient flagsmith = FlagsmithClient
    .newBuilder()
    .setApiKey(System.getenv("0cff2ed56eae3ff3f0a663208a7ee49336c79ccd"))
    .build();