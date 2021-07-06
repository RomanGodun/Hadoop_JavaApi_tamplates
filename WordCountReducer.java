static public class WordCountReducer  extends Reducer < Text,  IntWritable,  Text,  IntWritable > {
   @Override
   protected void reduce(Text key, Iterable < IntWritable > values, Context context)   throws IOException,  InterruptedException {
     //в редусер приходят все единицы от одного слова
    intsum = 0;
    for (IntWritable value: values) {
     sum += value.get();
    }
    context.write(key, new IntWritable(sum));
   }
 } 
