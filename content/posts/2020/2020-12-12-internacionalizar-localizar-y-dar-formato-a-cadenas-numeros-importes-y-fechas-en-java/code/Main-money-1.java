import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
...
System.out.println();
System.out.println("# Money");

Money millionUSD = Money.of(new BigDecimal("1000000"), "USD");
Money millionEUR = Money.of(new BigDecimal("1000000"), "EUR");
Money decimalUSD = Money.of(new BigDecimal("1000.35"), "USD");
Money decimalEUR = Money.of(new BigDecimal("1000.35"), "EUR");

MonetaryAmountFormat enMonetaryFormat = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(enLocale).set(CurrencyStyle.SYMBOL).build());
MonetaryAmountFormat esMonetaryFormat = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(esLocale).set(CurrencyStyle.SYMBOL).build());

System.out.println("Million USD (en): " + enMonetaryFormat.format(millionUSD));
System.out.println("Million EUR (es): " + esMonetaryFormat.format(millionEUR));
System.out.println("Decimal USD (en): " + enMonetaryFormat.format(decimalUSD));
System.out.println("Decimal EUR (es): " + esMonetaryFormat.format(decimalEUR));