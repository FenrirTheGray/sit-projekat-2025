start cmd /k "cd /d app && docker-compose -f docker-compose.env-dev.yml up"
sleep 15
start cmd /k "cd /d app/servelogic-api && mvnw spring-boot:run"
sleep 15
start cmd /k "cd /d app/cms && mvnw spring-boot:run"
start cmd /k "cd /d app/ordering && mvnw spring-boot:run"