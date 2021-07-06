public class WordCountJob extends Configured implements Tool{
        @Override
        public int run(String[] args) throws Exception{
                Job job = Job.getInstance(getConf(), "WordCount");
                job.setJarByClass(getClass ());

                Text InputFormat.addInputPath (job, new Path(args[0]));
                job.setInputFormatClass(TextInputFormat.class);

                job.setMapperClass(WordCountMapper.class);
                job.setReducerClass(WordCountReducer.class);
                job.setCombinerClass(WordCountReducer.class);

                TextOutputFormat.setOutputPath(job,new Path(args[1]));
                job.setOutpurFormatClass(TextOutputFormat.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass (IntWritable.class);
                
                return job.waitForCompletion(true) ? 0 : 1;
        }
        
        public static void main(String[] args) throws Exception {
                int exitCode = ToolRunner.run( 
                        new WordCountJob(), args);
                System. exit(exitCode);
        }
}
