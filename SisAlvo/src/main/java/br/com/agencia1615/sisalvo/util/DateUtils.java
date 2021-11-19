package br.com.agencia1615.sisalvo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public enum PATTERN {
		DDMM_SLASH_SEPARATED_PATTERN("dd/MM"), DDMMYYYY_SLASH_SEPARATED_PATTERN("dd/MM/yyyy"),
		DDMMYYYY_HYPHEN_SEPARETED_PATTERN("dd-MM-yyyy"), MMYYYY_SLASH_SEPARATED_PATTERN("MM/yyyy"),
		HOUR_MINUTE_PATTERN("HH:mm"), DDMMYYYYHHMM_PATTERN("ddMMyyyyHHmm"),
		DDMMYYYYHHMM_SLASH_SEPARATED_PATTERN("dd/MM/yyyy HH:mm");

		private String pattern;

		private PATTERN(String pattern) {
			this.pattern = pattern;
		}
	}

	public static final long MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
	/* Formato: dd/MM/yyyy */
	@Deprecated
	public static final String DDMMYYYY_SLASH_SEPARATED_PATTERN = "dd/MM/yyyy";

	/* Formato: dd-MM-yyyy */
	@Deprecated
	public static final String DDMMYYYY_HYPHEN_SEPARETED_PATTERN = "dd-MM-yyyy";

	public static final String HOUR_MINUTE_PATTERN = "HH:mm";

	/* Formato padrï¿½o ï¿½ dd/MM/yyyy */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
			DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN.pattern);

	/**
	 * Calculates the number of days between start and end dates, taking into
	 * consideration leap years, year boundaries etc.
	 *
	 * @param start the start date
	 * @param end   the end date, must be later than the start date
	 * @return the number of days between the start and end dates
	 */
	public static long countDaysBetween(Date start, Date end) {
		// reset all hours mins and secs to zero on start date
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		long startTime = startCal.getTimeInMillis();

		// reset all hours mins and secs to zero on end date
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.set(Calendar.MINUTE, 0);
		endCal.set(Calendar.SECOND, 0);
		long endTime = endCal.getTimeInMillis();

		return (endTime - startTime) / DateUtils.MILLISECONDS_IN_DAY;
	}

	public static String format(Calendar cal) {
		return DateUtils.formatDate(cal.getTime());
	}

	public static String format(Calendar data, PATTERN pattern) {
		String dataRetorno = "";
		if (data != null) {
			dataRetorno = DateUtils.format(data, pattern.pattern);
		}
		return dataRetorno;
	}

	public static String format(Calendar calendar, String pattern) {
		String data = "";

		if (calendar != null) {
			data = DateUtils.formatDate(calendar.getTime(), pattern);
		}
		return data;
	}

	public static String format(Date date) {
		return DateUtils.formatDate(date);
	}

	public static String formataData(String text) {
		String retorno = text;

		if (text.length() == 2) {
			retorno = text + "/";
		} else if (text.length() == 5) {
			retorno = text + "/";
		}
		return retorno;
	}

	/**
	 * Formata data usando o padrï¿½o dd/MM/yyyy.
	 *
	 * @see #DateUtils .DDMMYYYY_SLASH_SEPARATED_PATTERN
	 * @param date data a ser formatada.
	 * @return String com a data formatada.
	 */
	public static String formatDate(Date date) {
		return DateUtils.doFormat(date);
	}

	/**
	 * Formata data usando um padrï¿½o qualquer.
	 *
	 * @param date    Data a ser formatada.
	 * @param pattern padrï¿½o a ser utilizado.
	 * @return String com a data formatada.
	 */
	public static String formatDate(Date date, String pattern) {
		try {
			DateUtils.DATE_FORMATTER.applyPattern(pattern);
			String formattedDate = DateUtils.doFormat(date);

			return formattedDate;
		} finally {
			DateUtils.voltaFormatoDefault();
		}
	}

	public static boolean isDatasValidas(String dataInicio, String dataFinal) {

		boolean retorno = true;

		if (!dataInicio.equals("") && !dataFinal.equals("")) {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setLenient(false);

			try {
				System.out.println(format.parse(dataInicio));
				System.out.println(format.parse(dataFinal));
			} catch (Exception e) {
				e.printStackTrace();
				retorno = false;
			}
		}
		return retorno;
	}

	/**
	 * Verifica se a tecla digitada Ã© um nÃºmero.
	 *
	 * @param key
	 * @return boolean
	 */
	public static boolean isNumber(java.awt.event.KeyEvent key) {

		if ((key.getKeyCode() >= 48) && (key.getKeyCode() <= 57)) {
			return true;
		}
		if ((key.getKeyCode() >= 96) && (key.getKeyCode() <= 105)) {
			return true;
		}
		if (key.getKeyCode() == 8) {
			return true;
		}

		return false;
	}

	public static Date parse(String date) throws ParseException {
		return DateUtils.doParse(date, true, null);
	}

	public static Date parse(String date, boolean lenient) throws ParseException {
		return DateUtils.doParse(date, lenient, null);
	}

	public static Date parse(String date, String pattern) throws ParseException {
		try {
			DateUtils.DATE_FORMATTER.applyPattern(pattern);
			Date parsedDate = DateUtils.doParse(date);

			return parsedDate;
		} finally {
			DateUtils.voltaFormatoDefault();
		}
	}

	public static Date parse(String date, String pattern, boolean lenient) throws ParseException {
		return DateUtils.doParse(date, lenient, pattern);
	}

	public static Calendar parseToCalendar(String data, boolean ignoreExceptions) {
		Calendar calendar = null;
		System.out.println("data: " + MeuStringUtils.isNotEmpty(data));
		if ((MeuStringUtils.isNotEmpty(data)) && (data.length() == 10)) {
			calendar = Calendar.getInstance();
			calendar = DateUtils.parseToCalendar(data, ignoreExceptions, true);
		}

		return calendar;
	}

	public static Calendar parseToCalendar(String data, boolean ignoreExceptions, boolean lenient) {
		Calendar cal = null;

		try {
			Date d = DateUtils.parse(data, lenient);
			cal = Calendar.getInstance();
			cal.setLenient(lenient);
			cal.setTime(d);
		} catch (Exception e) {
			if (!ignoreExceptions) {
				throw new RuntimeException(e);
			}
		}

		return cal;
	}

	public static Calendar parseToCalendar(String data, PATTERN pattern, boolean ignoreExceptions, boolean lenient) {
		return DateUtils.parseToCalendar(data, pattern.pattern, ignoreExceptions, lenient);
	}

	public static Calendar parseToCalendar(String data, String pattern, boolean ignoreExceptions, boolean lenient) {
		Calendar cal = null;

		try {
			Date d = DateUtils.parse(data, pattern, lenient);
			cal = Calendar.getInstance();
			cal.setLenient(lenient);
			cal.setTime(d);
		} catch (Exception e) {
			if (!ignoreExceptions) {
				throw new RuntimeException(e);
			}
		}

		return cal;
	}

	public static void teste(String dataInicio, String dataFinal) {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);

		try {
			System.out.println(format.parse(dataInicio));
			System.out.println(format.parse(dataFinal));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void validaData(String data) {
		if (MeuStringUtils.isNotEmpty(data)) {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setLenient(false);
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String doFormat(Date date) {
		return DateUtils.DATE_FORMATTER.format(date);
	}

	private static Date doParse(String date) throws ParseException {
		return DateUtils.doParse(date, true, null);
	}

	private static Date doParse(String date, boolean lenient, String formato) throws ParseException {
		SimpleDateFormat clone = (SimpleDateFormat) DateUtils.DATE_FORMATTER.clone();
		clone.setLenient(lenient);
		if (formato != null) {
			clone.applyPattern(formato);
		}

		return clone.parse(date);
	}

	private static void voltaFormatoDefault() {
		DateUtils.DATE_FORMATTER.applyPattern(DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN.pattern);
	}
}
