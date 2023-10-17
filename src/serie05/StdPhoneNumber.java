package serie05;
import java.util.regex.*;

import util.Contract;

public class StdPhoneNumber implements PhoneNumber {
	
	public static String STD_NUMBER_PATTERN =  SEPARATORS
            + "(?:" + NATIONAL_PREFIX + "|\\" + INTERNATIONAL_PREFIX + ")?"
            + "((?:" + SEPARATORS + "\\d){" + DIGITS_NB + "})"
            + SEPARATORS + "(\\d*)"
            + SEPARATORS;
	
	
	private String digits = "";
	private String extension = "";
	public static Matcher stdNumMatcher = Pattern.compile(STD_NUMBER_PATTERN).matcher("");
	public static Matcher numMatcher = Pattern.compile(NUMBER_PATTERN).matcher("");
	
	public StdPhoneNumber(String n) {
		Contract.checkCondition(n != null);
		numMatcher.reset(n);
		Contract.checkCondition(numMatcher.matches());
		digits = getDigits(n);
		extension = getExtension(n);
	}

	@Override
	public char digit(int i) {
		Contract.checkCondition(1 <= i );
		Contract.checkCondition(i <= DIGITS_NB );
		return this.digits.charAt(i - 1);
	}

	@Override
	public String extension() {
		return this.extension;
	}


	@Override
	public String international() {
		String str = INTERNATIONAL_PREFIX  + " " + digits + ((this.extension.length() > 0) ?  "~" + this.extension : "");
		return str;
	}

	@Override
	public String national() {
		// TODO Auto-generated method stub
		String str = NATIONAL_PREFIX + digit(1);
		for (int i=2;i<=8;i = i+2) {
			str = str + " " + digit(i) + digit(i+1);
		}
		str =  str  + ((this.extension.length() > 0) ? " (" + this.extension + ")" : "");
		return str;
	}
	
	// outils
	private String getDigits(String n) {
		stdNumMatcher.reset(n).matches();
		Matcher m = Pattern.compile("(\\d+)").matcher(stdNumMatcher.group(1));
		String digits = "";
		while(m.find()) {
			digits += m.group();
		}
		return digits;
	}
	
	private String getExtension(String n) {
		stdNumMatcher.reset(n).matches();
		return stdNumMatcher.group(2);
	}
	
	@Override
	public String toString() {
		String str = "";
		str = str + digit(1);
		for (int i=2;i<=9;i++) {
			str = str + "," + digit(i);
		}
		
		str = str +  ((this.extension.length() > 0) ? ";" + this.extension  : "");
		return str;
		
	}

}
