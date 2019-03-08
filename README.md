# university-test

# Get Oracle Docker Image
`docker pull store/oracle/database-enterprise:12.2.0.1`

`docker run -d -p 1521:1521 -p 5500:5500 -it --name OraTest --shm-size="5g" store/oracle/database-enterprise:12.2.0.1`

# Run
`./mvnw clean install -s .mvn/wrapper/settings.xml`

# Coverage Report
.\target\site\jacoco\index.html
