# How to run

`mvn clean package`

`java -jar target/org.obprado.mobimeo-1.0-SNAPSHOT-jar-with-dependencies.jar`

# Assumptions

 * The input data (CSV files) will always have negligible size and read time
 ** We can block the thread to read the data
 ** We can read it all at once and it will fit in memory
 ** The data doesn't change, thus we can read it once at startup
 
 * The delays are in minutes
 * The user of the service (FE, other BE app or mobile) will provide the current time (maybe sometimes it's not the current one?)
 * We always find a valid stop for the X, Y coordinates sent by the user (no sad cases handled)
 
# Considerations

 * Didn't have time to finish one of the endpoints
 * Given the time, I would unit test more
 * Given the time, I would rethink the architecture & class responsability