#Spring Boot SSL Redirection Bug

Ran into a problem where if Spring Boot and SSL.

If `server.ssl.enabled=true` and `server.port` is left as 8080, any redirect will go to 8443

This project was an attempt to prove the problem with a minimal configuration.

The bug is mentioned in [this issue](https://github.com/spring-projects/spring-boot/issues/6140)

The only real solution is to change the port to something other than 8443.


There are two test classes:

`ChangedPortTest`, which sets the port to 9000 and passes.


`DefaultPortTest`, which sets the port to 8080 and fails, with exception:
    
    org.apache.http.conn.HttpHostConnectException: Connect to localhost:8443 [localhost/127.0.0.1] failed: Connection refused (Connection refused)

due to a redirection to port 8443