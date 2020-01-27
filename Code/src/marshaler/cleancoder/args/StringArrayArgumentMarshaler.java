package marshaler.cleancoder.args;

import static marshaler.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
  private List<String> strings = new ArrayList<String>();

  public static String[] getValue(ArgumentMarshaler marshaller) {
    String[] strValue = new String[0];
    if (marshaller != null && marshaller instanceof StringArrayArgumentMarshaler)
      strValue = ((StringArrayArgumentMarshaler) marshaller).strings.toArray(new String[0]);
    return strValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      strings.add(currentArgument.next());
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }
}
