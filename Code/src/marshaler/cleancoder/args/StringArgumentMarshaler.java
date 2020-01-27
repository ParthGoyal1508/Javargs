package marshaler.cleancoder.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static marshaler.cleancoder.args.ArgsException.ErrorCode.MISSING_STRING;

public class StringArgumentMarshaler implements ArgumentMarshaler {
  private String stringValue = "";

  public static String getValue(ArgumentMarshaler marshaller) {
    String stringValue = "";
    if (marshaller != null && marshaller instanceof StringArgumentMarshaler)
      stringValue = ((StringArgumentMarshaler) marshaller).stringValue;
    return stringValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      stringValue = currentArgument.next();
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

 
}
