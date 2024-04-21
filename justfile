
default:
  just --list

build:
  ./mvnw clean package

mutation-testing:
  ./mvnw package pitest:mutationCoverage
