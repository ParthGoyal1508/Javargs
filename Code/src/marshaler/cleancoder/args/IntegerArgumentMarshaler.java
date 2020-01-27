package marshaler.cleancoder.args;

import static marshaler.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
  private int intValue = 0;

  public static int getValue(ArgumentMarshaler marshaller) {
    int intValue = 0;
    if (marshaller != null && marshaller instanceof IntegerArgumentMarshaler)
      intValue = ((IntegerArgumentMarshaler) marshaller).intValue;
    return intValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      intValue = Integer.parseInt(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_INTEGER);
    } catch (NumberFormatException e) {
      throw new ArgsException(INVALID_INTEGER, parameter);
    }
  }
}
