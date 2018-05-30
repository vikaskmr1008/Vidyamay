/**
 * 
 */
package com.vidyamay.utils;

import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.validation.ObjectError;

/**
 * @author vikas.kumar3
 *
 */
public class Springi18nUtils {
	
	/** Spring Environment bean */
	@Autowired
	private Environment env;
	
    /** The application context. */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Instantiates a new springi 18 n utils.
     */
    public Springi18nUtils(){        
    }
    
    
    /**
     * Gets the message.
     *
     * @param code
     *            the code
     * @param args
     *            the args
     * @param locale
     *            the locale
     * @return the message
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        return this.getMessage(code, args, null, locale);
    }

    /**
     * Gets the message.
     *
     * @param code
     *            the code
     * @param args
     *            the args
     * @return the message
     */
    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, null, getLocale());
    }

    /**
     * Gets the message.
     *
     * @param code
     *            the code
     * @param args
     *            the args
     * @param defaultMessage
     *            the default message
     * @return the message
     */
    public String getMessage(String code, Object[] args,
            String defaultMessage) {
        return this.getMessage(code, args, defaultMessage, getLocale());
    }

    /**
     * Gets the message.
     *
     * @param code
     *            the code
     * @param args
     *            the args
     * @param defaultMessage
     *            the default message
     * @param locale
     *            the locale
     * @return the message
     */
    public String getMessage(String code, Object[] args, String defaultMessage,
            Locale locale) {
        Locale localParam = locale;
        if (localParam == null) {
            localParam = Locale.getDefault();
        }
       return this.applicationContext.getMessage(code, args, defaultMessage,
           localParam);
    }
    
    /**
     * Gets the locale from the request.
     * 
     * @return Locale
     */
    private Locale getLocale() {
      return LocaleContextHolder.getLocale();
    }
    
    /**
	 * Parse a given date object As List of Date according to locale. dateFormat
	 * is optional parameter as long,short,and medium.
	 * 
	 * @return List of String
	 */
	public static List<String> formatToLocalizedDate(List<Date> date, Locale locale, String... dateFormat) {
		final Locale locale1 = null == locale ? Locale.US : locale;
		List<String> convertedList = new ArrayList<>();
		date.forEach(
				dateToFormat -> convertedList.add(DateTimeFormatter.ofLocalizedDate(dateASShortLongMedium(dateFormat))
						.withZone(ZoneId.systemDefault()).withLocale(locale1).format(dateToFormat.toInstant())));
		System.out.println(convertedList);

		return convertedList;

	}

	/**
	 * Parse a given date object As date type of List of Map inside the Map
	 * according to locale.
	 * 
	 * @return formated localized Date as Map
	 */
	public static Map<String, List<Map<String, List<String>>>> formatToLocalizedDateAsMap(
			Map<String, List<Map<String, List<Date>>>> inputDateMap, Locale locale) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputDateMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = formatToLocalizedDate(innerList, locale);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * Parse a given date object As List of Date according to locale. dateFormat
	 * is optional parameter as long,short,and medium.
	 * 
	 * @return List of String based on optional parameter.
	 */
	public static List<String> formatToLocalizedTime(List<Date> date, Locale locale, String... dateFormat) {
		final Locale locale1 = null == locale ? Locale.US : locale;
		List<String> convertedList = new ArrayList<>();
		date.forEach(
				timeToFormat -> convertedList.add(DateTimeFormatter.ofLocalizedTime(dateASShortLongMedium(dateFormat))
						.withZone(ZoneId.systemDefault()).withLocale(locale1).format(timeToFormat.toInstant())));
		return convertedList;
	}

	/**
	 * Parse a given date object As date type of List of Map inside the Map
	 * according to locale.
	 * 
	 * @return formated localized Time as Map
	 */
	public static Map<String, List<Map<String, List<String>>>> formatToLocalizedTimeAsMap(
			Map<String, List<Map<String, List<Date>>>> inputDateMap, Locale locale) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputDateMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = formatToLocalizedTime(innerList, locale);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * Parse a given date object As List of DateTime according to locale.
	 * dateFormat is optional parameter as long,short,and medium.
	 * 
	 * @return List of String based on optional parameter.
	 * 
	 */
	public static List<String> formatToLocalizedDateTime(List<Date> date, Locale locale, String... dateFormat) {
		final Locale locale1 = null == locale ? Locale.US : locale;
		List<String> convertedList = new ArrayList<>();
		date.forEach(dateToFormat -> convertedList.add(DateTimeFormatter
				.ofLocalizedDateTime(dateASShortLongMedium(dateFormat), dateASShortLongMedium(dateFormat))
				.withZone(ZoneId.systemDefault()).withLocale(locale1).format(dateToFormat.toInstant())));
		return convertedList;
	}

	/**
	 * Parse a given date object As date type of List of Map inside the Map
	 * according to locale.
	 * 
	 * @return formated localized DateTime as Map
	 */
	public static Map<String, List<Map<String, List<String>>>> formatToLocalizedDateTimeAsMap(
			Map<String, List<Map<String, List<Date>>>> inputDateMap, Locale locale) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputDateMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = formatToLocalizedDateTime(innerList, locale);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * Parse a given number object As List of DateTime according to locale.
	 * 
	 * @return List of String.
	 */
	public static List<String> formatToLocalizedNumber(List<Number> number, Locale locale) {
		final Locale locale1 = null == locale ? Locale.US : locale;
		List<String> convertedList = new ArrayList<>();
		number.forEach(numberToFormat -> convertedList.add(NumberFormat.getInstance(locale1).format(numberToFormat)));
		return convertedList;
	}

	/**
	 * Parse a given number object As number type of List of Map inside the Map
	 * according to locale.
	 * 
	 * @return formated localized number as Map
	 */
	public static Map<String, List<Map<String, List<String>>>> formatToLocalizedNumberAsMap(
			Map<String, List<Map<String, List<Number>>>> inputNumberMap, Locale locale) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputNumberMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = formatToLocalizedNumber(innerList, locale);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * Parse a given amount object As List of amount according to locale.
	 * 
	 * @return List of String.
	 */
	public static List<String> formatToLocalizedCurrency(List<Double> amount, Locale locale) {
		final Locale locale1 = null == locale ? Locale.US : locale;
		List<String> convertedList = new ArrayList<>();
		amount.forEach(currencyToFormat -> convertedList
				.add(NumberFormat.getCurrencyInstance(locale1).format(currencyToFormat)));
		return convertedList;
	}

	/**
	 * Parse a given currency object As currency type of List of Map inside the
	 * Map according to locale.
	 * 
	 * @return formated localized currency as Map
	 */
	public static Map<String, List<Map<String, List<String>>>> formatToLocalizedCurrencyAsMap(
			Map<String, List<Map<String, List<Double>>>> inputAmountMap, Locale locale) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputAmountMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = formatToLocalizedCurrency(innerList, locale);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * Parse a given amount object As List of amount according to locale.
	 * currency conversion as from locale to to locale.
	 * 
	 * @return List of String.
	 */
	public static List<String> currencyConversion(List<Number> amount, Locale fromLocale, Locale toLocale) {
		final Locale locale1 = null == fromLocale ? Locale.US : fromLocale;
		if (null == toLocale)
			toLocale = Locale.US;
		List<String> convertedList = new ArrayList<>();
		CurrencyConversion toLocaleCurrencyConversion = MonetaryConversions
				.getConversion(Currency.getInstance(toLocale).getCurrencyCode());
		amount.forEach(currencyToFormat -> convertedList
				.add(Money.of(currencyToFormat, Currency.getInstance(locale1).getCurrencyCode())
						.with(toLocaleCurrencyConversion).toString()));

		return convertedList;
	}

	/**
	 * Parse a given currency object As currency type of List of Map inside the
	 * Map according to locale.
	 * 
	 * @return converted currency from locale to to locale as Map
	 *
	 */
	public static Map<String, List<Map<String, List<String>>>> currencyConversionAsMap(
			Map<String, List<Map<String, List<Number>>>> inputAmountMap, Locale fromLocale, Locale toLocale) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputAmountMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = currencyConversion(innerList, fromLocale, toLocale);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * Parse a given date object As List of date according to locale. convert a
	 * date on basis of time zone
	 * 
	 * @return List of String as date with full time zone.
	 */
	public static List<String> convertToLocalizedDateOnTimeZone(List<Date> date, Locale locale, String fullTimeZoneName,
			String... dateFormat) {
		final Locale locale1 = null == locale ? Locale.US : locale;
		List<String> convertedList = new ArrayList<>();
		date.forEach(dateToFormat -> convertedList
				.add(DateTimeFormatter.ofLocalizedDateTime(dateASShortLongMedium(dateFormat))
						.withZone(ZoneId.of(fullTimeZoneName)).withLocale(locale1).format(dateToFormat.toInstant())));
		return convertedList;
	}

	/**
	 * Parse a given date object As date type of List of Map inside the Map
	 * according to locale and full time zone.
	 * 
	 * @return converted date according to full time zone as Map
	 */
	public static Map<String, List<Map<String, List<String>>>> convertToLocalizedDateOnTimeZoneAsMap(
			Map<String, List<Map<String, List<Date>>>> inputDateMap, Locale locale, String fullTimeZoneName) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputDateMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = convertToLocalizedDateOnTimeZone(innerList, locale, fullTimeZoneName);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * Parse a given number object As List of number according to locale.
	 * convert number and display as percent format
	 * 
	 * @return List of String percent number.
	 */
	public static List<String> formatToLocalizedNumberPercent(List<Number> number, Locale locale) {
		final Locale locale1 = null == locale ? Locale.US : locale;
		List<String> convertedList = new ArrayList<>();
		number.forEach(
				numberToFormat -> convertedList.add(NumberFormat.getPercentInstance(locale1).format(numberToFormat)));
		return convertedList;
	}

	/**
	 * Parse a given number object As date type of List of Map inside the Map
	 * convert number and display as percent format
	 * 
	 * @return converted number in format of percent as Map
	 */
	public static Map<String, List<Map<String, List<String>>>> formatToLocalizedNumberPercentAsMap(
			Map<String, List<Map<String, List<Number>>>> inputNumberMap, Locale locale) {

		Map<String, List<Map<String, List<String>>>> resultMap = new HashMap<>();
		inputNumberMap.forEach((key, list) -> {
			List<Map<String, List<String>>> resultList = new ArrayList<>();
			list.forEach(innerMap -> {
				Map<String, List<String>> innerMapResult = new HashMap<>();
				innerMap.forEach((innerKey, innerList) -> {
					List<String> convertedList = formatToLocalizedNumberPercent(innerList, locale);
					innerMapResult.put(innerKey, convertedList);
				});
				resultList.add(innerMapResult);
			});
			resultMap.put(key, resultList);
		});

		return resultMap;
	}

	/**
	 * 
	 * @param dateFormat
	 * @return dateFormat on basis of short, medium and long parameter
	 */
	private static FormatStyle dateASShortLongMedium(String... dateFormat) {
		return dateFormat.length > 0
				? dateFormat[0].equalsIgnoreCase("short") ? FormatStyle.SHORT
						: dateFormat[0].equalsIgnoreCase("long") ? FormatStyle.LONG : FormatStyle.MEDIUM
				: FormatStyle.MEDIUM;
	}
	
	public String getMessage(ObjectError error) {
    try {
      for (String code : Arrays.asList(error.getCodes())) {
        if (env.getProperty(getLocale().getLanguage() + "." + code) != null)
          return env.getProperty(getLocale().getLanguage() + "." + code);
      }
      return error.getDefaultMessage();
    } catch (NoSuchMessageException ex) {
      //logger.error("Exception while getting error message.", ex);
      return "error msg not found";
    }
  }


}
