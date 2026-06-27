String identifier = "d4cb38f6-9945-4730-b779-e699039592f0"
Map<String, Object> traits = new HashMap<String, Object>();
traits.put("country", "spain");

Flags flags = flagsmith.getIdentityFlags(identifier, traits);
Boolean enabled = flags.isFeatureEnabled("show-prices");
Object value = flags.getFeatureValue("show-prices");