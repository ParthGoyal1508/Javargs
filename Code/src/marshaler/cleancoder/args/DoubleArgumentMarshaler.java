package marshaler.cleancoder.args;

import static marshaler.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
  private double doubleValue = 0.0;

  public static double getValue(ArgumentMarshaler marshaller) {
    double doubleValue = 0.0;
    if (marshaller != null && marshaller instanceof DoubleArgumentMarshaler)
      doubleValue = ((DoubleArgumentMarshaler) marshaller).doubleValue;
    return doubleValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      doubleValue = Double.parseDouble(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_DOUBLE);
    } catch (NumberFormatException e) {
      throw new ArgsException(INVALID_DOUBLE, parameter);
    }
  }
}
