package marshaler.cleancoder.args;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static marshaler.cleancoder.args.ArgsException.ErrorCode.*;

public class MapArgumentMarshaler implements ArgumentMarshaler {
  private Map<String, String> map = new HashMap<>();

  public static Map<String, String> getValue(ArgumentMarshaler marshaller) {
    Map<String, String> mapArgValues = new HashMap<>();
    if (marshaller != null && marshaller instanceof MapArgumentMarshaler)
      mapArgValues = ((MapArgumentMarshaler) marshaller).map;
    return mapArgValues;
  }

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      String[] mapEntries = currentArgument.next().split(",");
      for (String entry : mapEntries) {
        String[] entryComponents = entry.split(":");
        if (entryComponents.length != 2)
          throw new ArgsException(MALFORMED_MAP);
        map.put(entryComponents[0], entryComponents[1]);
      }
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_MAP);
    }
  }
}
