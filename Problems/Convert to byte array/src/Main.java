import java.io.CharArrayWriter;

class Converter {
    public static char[] convert(String[] words) throws IOException {
        CharArrayWriter writer = new CharArrayWriter();
        for (String word : words) {
            writer.write(word);
        }
        return writer.toCharArray();
    }
}