# Multi-level, multi-level docker en source/javadoc generation

## Multi-level

- Parent is toegevoegd als parent in de root pom.
- Modules zijn gedefinieerd in de root pom.
- Child poms hebben de root pom als parent.

## Docker

- docker:build is gekoppeld aan install-phase.

## Source/javadoc generation

- mvn clean install source:jar javadoc:jar
