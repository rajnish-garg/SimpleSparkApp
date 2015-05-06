Simple Spark Application. 
To count the null column in each column efficently

(This project gives a simple description, on how to create a Spark Application, here it covers the following:
1. Logger
2. Loading properties form file.
3. Spark Assembly plugin.
4. Scala Test
)

Input:
1. It expectes a text file specified by some delimeter.


#Step to run
spark-submit --class  my.simple.app.CountNullForEachColumn \
--master local[*]  \
--conf spark.serializer=org.apache.spark.serializer.JavaSerializer \
--verbose \
<JAR_PATH>/<JAR_NAME> <INPUT_FILE>  


#Output:
it will print the number of null values in each column.
