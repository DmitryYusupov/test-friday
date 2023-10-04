# Details

### Bridge between compose and standard spring.datasource props:

* org.springframework.boot.docker.compose.service.connection.postgres.PostgresJdbcDockerComposeConnectionDetailsFactory.PostgresJdbcDockerComposeConnectionDetails

Include only basic props: url, password, user.

If got to the package
`org.springframework.boot.docker.compose.service.connection`

You won't find Kafka connector, so we should describe it as we always did it before in YAML file.
That's why i provided bootstrap servers property.