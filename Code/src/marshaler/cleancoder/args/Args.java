package marshaler.cleancoder.args;

import java.util.*;

import static marshaler.cleancoder.args.ArgsException.ErrorCode.*;

public class Args {
  private ListIterator<String> currentArgument;
  private Map<Character, ArgumentMarshaler> marshalers = new HashMap<Character, ArgumentMarshaler>();
  private Map<String, ArgumentMarshaler> schemaToMarshalerMap = new HashMap<String,ArgumentMarshaler>();
  private Set<Character> argsFound = new HashSet<Character>();
  private String schema;
  private String[] args;

  public Args(String schema, String[] args) throws ArgsException {
    this.schema = schema;
    this.args = args;
    initialzeSchemaToMarshalerMap();
    parseSchema();
    parseArgumentStrings(Arrays.asList(args));
  }

  private void initialzeSchemaToMarshalerMap() throws ArgsException {
    schemaToMarshalerMap.put("*",new StringArgumentMarshaler());
    schemaToMarshalerMap.put("#",new IntegerArgumentMarshaler());
    schemaToMarshalerMap.put("##",new DoubleArgumentMarshaler());
    schemaToMarshalerMap.put("[*]",new StringArrayArgumentMarshaler());
    schemaToMarshalerMap.put("&",new MapArgumentMarshaler());
  }

  private void parseArgumentStrings(List<String> argsList) throws ArgsException {
    for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
      String argString = currentArgument.next();
      if (argString.startsWith("-")) {
        parseArgumentCharacters(argString.substring(1));
      } else {
        currentArgument.previous();
        break;
      }
    }
  }

  private void parseArgumentCharacters(String argChars) throws ArgsException {
    for (int i = 0; i < argChars.length(); i++){
      char argChar = argChars.charAt(i);
      ArgumentMarshaler localMarshaller = marshalers.get(argChar);
      if (localMarshaller == null) {
        throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
      } else {
        argsFound.add(argChar);
        try {
          localMarshaller.set(currentArgument);
        } catch (ArgsException e) {
          e.setErrorArgumentId(argChar);
          throw e;
        }
      }
    }
  }

  private void parseSchema() throws ArgsException {
    for (String element : schema.split(","))
      if (element.length() > 0)
        parseSchemaElement(element.trim());
  }

  private void insertInMarshaler(char elementId,String elementTail) throws ArgsException {
    if (elementTail.length() == 0){
      marshalers.put(elementId, new BooleanArgumentMarshaler());
    }
    else if(schemaToMarshalerMap.containsKey(elementTail)){
      marshalers.put(elementId, schemaToMarshalerMap.get(elementTail));
    }
    else {
      throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }
  }

  private void parseSchemaElement(String element) throws ArgsException {
    char elementId = element.charAt(0);
    String elementTail = element.substring(1);
    validateSchemaElementId(elementId);
    insertInMarshaler(elementId, elementTail);
  }

  private void validateSchemaElementId(char elementId) throws ArgsException {
    if (!Character.isLetter(elementId))
      throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
  }

  public boolean getBoolean(char arg) {
    return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public double getDouble(char arg) {
    return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public int getInt(char arg) {
    return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public String getString(char arg) {
    return StringArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public String[] getStringArray(char arg) {
    return StringArrayArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public Map<String, String> getMap(char arg) {
    return MapArgumentMarshaler.getValue(marshalers.get(arg));
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  public int nextArgument() {
    return currentArgument.nextIndex();
  }
}



