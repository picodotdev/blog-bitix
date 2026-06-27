Flags flags = flagsmith.getEnvironmentFlags();
Boolean enabled = flags.isFeatureEnabled("show-prices");
Object value = flags.getFeatureValue("show-prices");