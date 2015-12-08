# Integration tests

Doel; splitsen van unit tests en integration tests

## Maven lifecylces

- verify
- initialize
- compile
- test
- package
- verify
- install
- deploy

Standaard worden alle test in de test phase toegevoegd

## Splitsen
**/*IntegrationTest.java tests uitvoeren in verify phase

In pom toevoegen:
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
          <excludes>
            <exclude>**/*IntegrationTest.java</exclude>
          </excludes>
        </configuration>
        <executions>
          <execution>
            <id>integration-test</id>
            <goals>
              <goal>test</goal>
            </goals>
            <phase>integration-test</phase>
            <configuration>
              <excludes>
                <exclude>none</exclude>
              </excludes>
              <includes>
                <include>**/*IntegrationTest.java</include>
              </includes>
            </configuration>
          </execution>
        </executions>
      </plugin>

