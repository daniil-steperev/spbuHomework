import java.io.*;
import java.util.*;

public final class String extends Object implements Serializable, Comparable, CharSequence {
	private final byte[] value = null;

	private final byte coder = 0;

	private int hash = 0;

	private static final long serialVersionUID = 0

	static final boolean COMPACT_STRINGS = true;

	private static final ObjectStreamField[] serialPersistentFields = null;

	public static final Comparator CASE_INSENSITIVE_ORDER = null;

	static final byte LATIN1 = 0;

	static final byte UTF16 = 0;

	
	public String(byte[] arg0) { }

	public String(byte[] arg0, int arg1, int arg2) { }

	public String(byte[] arg0, Charset arg1) { }

	public String(byte[] arg0, String arg1) { }

	public String(byte[] arg0, int arg1, int arg2, Charset arg3) { }

	String(char[] arg0, int arg1, int arg2, Void arg3) { }

	String(AbstractStringBuilder arg0, Void arg1) { }

	public String(StringBuilder arg0) { }

	public String(StringBuffer arg0) { }

	String(byte[] arg0, byte arg1) { }

	public String(char[] arg0, int arg1, int arg2) { }

	public String(char[] arg0) { }

	public String(String arg0) { }

	public String() { }

	public String(byte[] arg0, int arg1, int arg2, String arg3) { }

	public String(byte[] arg0, int arg1) { }

	public String(byte[] arg0, int arg1, int arg2, int arg3) { }

	public String(int[] arg0, int arg1, int arg2) { }

	byte[] value ()  {
		return null;
	}

	public boolean equals (Object arg0)  {
		return true;
	}

	public int length ()  {
		return 0;
	}

	public String toString ()  {
		return null;
	}

	public int hashCode ()  {
		return 0;
	}

	public void getChars (int arg0, int arg1, char[] arg2, int arg3)  { }

	public int compareTo (String arg0)  {
		return 0;
	}

	public volatile int compareTo (Object arg0)  {
		return 0;
	}

	public int indexOf (String arg0, int arg1)  {
		return 0;
	}

	public int indexOf (int arg0)  {
		return 0;
	}

	static int indexOf (byte[] arg0, byte arg1, int arg2, String arg3, int arg4)  {
		return 0;
	}

	public int indexOf (int arg0, int arg1)  {
		return 0;
	}

	public int indexOf (String arg0)  {
		return 0;
	}

	static void checkIndex (int arg0, int arg1)  { }

	public static String valueOf (int arg0)  {
		return null;
	}

	public static String valueOf (float arg0)  {
		return null;
	}

	public static String valueOf (boolean arg0)  {
		return null;
	}

	public static String valueOf (long arg0)  {
		return null;
	}

	public static String valueOf (double arg0)  {
		return null;
	}

	public static String valueOf (Object arg0)  {
		return null;
	}

	public static String valueOf (char arg0)  {
		return null;
	}

	public static String valueOf (char[] arg0)  {
		return null;
	}

	public static String valueOf (char[] arg0, int arg1, int arg2)  {
		return null;
	}

	byte coder ()  {
		return 0;
	}

	private static Void rangeCheck (char[] arg0, int arg1, int arg2)  {
		return null;
	}

	public IntStream codePoints ()  {
		return null;
	}

	public boolean isEmpty ()  {
		return true;
	}

	public char charAt (int arg0)  {
		return 'x';
	}

	public int codePointAt (int arg0)  {
		return 0;
	}

	public int codePointBefore (int arg0)  {
		return 0;
	}

	public int codePointCount (int arg0, int arg1)  {
		return 0;
	}

	public int offsetByCodePoints (int arg0, int arg1)  {
		return 0;
	}

	public byte[] getBytes (Charset arg0)  {
		return null;
	}

	public void getBytes (int arg0, int arg1, byte[] arg2, int arg3)  { }

	public byte[] getBytes (String arg0) throws UnsupportedEncodingException {
		return null;
	}

	public byte[] getBytes ()  {
		return null;
	}

	void getBytes (byte[] arg0, int arg1, byte arg2)  { }

	public boolean contentEquals (StringBuffer arg0)  {
		return true;
	}

	public boolean contentEquals (CharSequence arg0)  {
		return true;
	}

	private boolean nonSyncContentEquals (AbstractStringBuilder arg0)  {
		return true;
	}

	public boolean equalsIgnoreCase (String arg0)  {
		return true;
	}

	public int compareToIgnoreCase (String arg0)  {
		return 0;
	}

	public boolean regionMatches (boolean arg0, int arg1, String arg2, int arg3, int arg4)  {
		return true;
	}

	public boolean regionMatches (int arg0, String arg1, int arg2, int arg3)  {
		return true;
	}

	public boolean startsWith (String arg0)  {
		return true;
	}

	public boolean startsWith (String arg0, int arg1)  {
		return true;
	}

	public boolean endsWith (String arg0)  {
		return true;
	}

	public int lastIndexOf (int arg0)  {
		return 0;
	}

	static int lastIndexOf (byte[] arg0, byte arg1, int arg2, String arg3, int arg4)  {
		return 0;
	}

	public int lastIndexOf (String arg0, int arg1)  {
		return 0;
	}

	public int lastIndexOf (String arg0)  {
		return 0;
	}

	public int lastIndexOf (int arg0, int arg1)  {
		return 0;
	}

	public String substring (int arg0, int arg1)  {
		return null;
	}

	public String substring (int arg0)  {
		return null;
	}

	public CharSequence subSequence (int arg0, int arg1)  {
		return null;
	}

	public String concat (String arg0)  {
		return null;
	}

	public String replace (CharSequence arg0, CharSequence arg1)  {
		return null;
	}

	public String replace (char arg0, char arg1)  {
		return null;
	}

	public boolean matches (String arg0)  {
		return true;
	}

	public boolean contains (CharSequence arg0)  {
		return true;
	}

	public String replaceFirst (String arg0, String arg1)  {
		return null;
	}

	public String replaceAll (String arg0, String arg1)  {
		return null;
	}

	public String[] split (String arg0)  {
		return null;
	}

	public String[] split (String arg0, int arg1)  {
		return null;
	}

	public static transient String join (CharSequence arg0, CharSequence[] arg1)  {
		return null;
	}

	public static String join (CharSequence arg0, Iterable arg1)  {
		return null;
	}

	public String toLowerCase ()  {
		return null;
	}

	public String toLowerCase (Locale arg0)  {
		return null;
	}

	public String toUpperCase (Locale arg0)  {
		return null;
	}

	public String toUpperCase ()  {
		return null;
	}

	public String trim ()  {
		return null;
	}

	public String strip ()  {
		return null;
	}

	public String stripLeading ()  {
		return null;
	}

	public String stripTrailing ()  {
		return null;
	}

	public boolean isBlank ()  {
		return true;
	}

	private int indexOfNonWhitespace ()  {
		return 0;
	}

	public Stream lines ()  {
		return null;
	}

	public IntStream chars ()  {
		return null;
	}

	public char[] toCharArray ()  {
		return null;
	}

	public static transient String format (String arg0, Object[] arg1)  {
		return null;
	}

	public static transient String format (Locale arg0, String arg1, Object[] arg2)  {
		return null;
	}

	public static String copyValueOf (char[] arg0, int arg1, int arg2)  {
		return null;
	}

	public static String copyValueOf (char[] arg0)  {
		return null;
	}

	public native String intern ()  {
		return null;
	}

	public String repeat (int arg0)  {
		return null;
	}

	private boolean isLatin1 ()  {
		return true;
	}

	static void checkOffset (int arg0, int arg1)  { }

	static void checkBoundsOffCount (int arg0, int arg1, int arg2)  { }

	static void checkBoundsBeginEnd (int arg0, int arg1, int arg2)  { }

	static String valueOfCodePoint (int arg0)  {
		return null;
	}
	private static class CaseInsensitiveComparator extends Object implements Comparator, Serializable {
	private static final long serialVersionUID = 0

	
	private CaseInsensitiveComparator() { }

	public volatile int compare (Object arg0, Object arg1)  {
		return 0;
	}

	public int compare (String arg0, String arg1)  {
		return 0;
	}

	private Object readResolve ()  {
		return null;
	}
}

	
}

	