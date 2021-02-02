docker="./gradlew mysqlbinlogCompose"
dockercdc="./gradlew mysqlbinlogcdcCompose"

${docker}Down
${dockercdc}Up
${docker}Up
