public class WordCountMapper
        extends Mapper<LongWritable, Text, Text, Intwritable> {
  
    private final static IntWritable one = new IntWritable (1);
    private final Text word = new Text();
  
    @Override
    protected void map(LongWritable key, Text value, Context context)
          throws IOException, InterruptedException {
        StringTokenizer tokenizer =
                            = new StringTokenizer (value. toString ());
  
        while (tokenizer. hasMoreTokens() ) {
            word.set(tokenizer.nextToken() );
            context.write(word, one);
        }
    }
}
