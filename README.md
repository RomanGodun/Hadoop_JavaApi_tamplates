Шаблоны классов для реальзации задач map-reduce в фреймворке Hadoop через JavaApi 

<h1>Класс Job</h1>
Содержит описание MapReduce задачи:

- input/output пути
- Формат input/output данных
- Указания классов для mapper, reducer, combiner
и partitioner
- Типы значений пар key/value
- Количество редьюсеров

<h2>Описание</h2>

- Создание объекта Job:

<code>Job job = Job. getInstance(getConf(),"WordCount")</code>

- Определение jar для задачи, в каком классе будет функциональность ( в данном случае в том же классе):

<code>job.setJarByClass(getclass());</code>

- Определение input:

  • в качестве пути может быть файл, директория,
  шаблон пути (/path/to/dir/test_*)
  
  • TextInputFormat читает входные данные как
  текстовый файл (key - LongWritable, value - Text)
  
<code>TextInputFormat.addInputPath(job, new Path(args[0]));</code><br>
<code>job. setInputFormatClass (Text InputFormat.class);</code>

- Определение класса для Mapper и Reducer:

<code>job. setMapperClass(WordCountMapper.class);</code><br>
<code>job. setReducerClass (WordCountReducer.class);</code><br>

- Определение класса для Combiner(в данном случае используем класс редьюсера):

<code>job. setCombinerClass(WordCountReducer.class);</code><br>

- Определение output:

<code>TextOutputFormatFormat.setOutputPath(job, new Path(args[1]));</code><br>
<code>job.setOutputFormatClass(TextOutputFormat.class);</code><br>
<code>job.setOutputKeyClass(Text.class);</code><br>
<code>job.setOutputValueClass (IntWritable.class);</code>
  
 • Если типы для mар и reduce отличаются, то:

<code>job.setMapOutputKeyClass(Text. class) ;</code><br>
<code>job.setMapOutputValueClass(LongWritable.class);</code>

- Далее строчка запускает задачу и ждет ее окончания:

<code>return job.waitForCompletion(true) ? 0 : 1;</code>

   • true в случае успеха, false в случае ошибки
  
   • в данном случе функция будет возвращать 0 или 1
   
 - Реализация main для запуска задачи 
 
<code>public static void main(String[] args) </code>

Передаем в ToolRunner.run объект нашего класса, наш класс возвращает exitecode, который в свою очередь возвращается из всей программы в строчке

<code>System. exit(exitCode);</code>

<h1>Класс Mapper</h1>
• Наследник класса:

<code>public class Mapper <keyin, valuein, keyout, valueout></code>
  
• Должен быть реализован метод map():
  
<code>void map(KEYIN key, value, Context context)</code> - Вызывается для каждой пары key/value из input split
  
• Типы key / value (из org. apache. hadoop. io):
  
  + IntWritable
  + Text
  + ImmutableBytesWritable

• void setup(Mapper. Context context) - Вызывается один раз при запуске таска
  
• void cleanup(Mapper. Context context) - Вызывается один раз при завершении таска 

 <h1>Класс Reducer/Combiner</h1>

• void setup(Reducer. Context context) - Вызывается один раз при запуске таска

• void reduce(K key, Iterable<V> values,Reducer. Context context) - Вызывается для каждого key
  
• void cleanup(Reducer. Context context) - Вызывается один раз при завершении таска
  
<h1>Класс Partitioner</h1>
  
int getPartition(K key, V value, int numPartitions) - Возвращает номер reducer для ключа К ( по умолчанию номер редьюсера = hash(k)mod n )

