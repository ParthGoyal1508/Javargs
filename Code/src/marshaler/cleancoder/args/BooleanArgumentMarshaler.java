package marshaler.cleancoder.args;

import java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
  private boolean booleanValue = false;

  public static boolean getValue(ArgumentMarshaler marshaller) {
    boolean booleanValue = false;
    if (marshaller != null && marshaller instanceof BooleanArgumentMarshaler)
      booleanValue = ((BooleanArgumentMarshaler) marshaller).booleanValue;
    return booleanValue;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    booleanValue = true;
  }
 
}
