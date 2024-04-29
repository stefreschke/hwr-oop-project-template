
default:
  just --list

build:
  ./mvnw clean package

mutation-testing:
  ./mvnw package pitest:mutationCoverage

setup-maven-wrapper:
  chmod +x ./mvnw

reset-maven-wrapper:
  chmod -x ./mvnw
