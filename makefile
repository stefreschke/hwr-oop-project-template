.PHONY: default build mutation-testing setup-maven-wrapper reset-maven-wrapper

default:
	@echo "Available targets:"
	@echo "  build                - Build the project"
	@echo "  mutation-testing     - Run mutation tests"
	@echo "  setup-maven-wrapper  - Make Maven wrapper executable"
	@echo "  reset-maven-wrapper  - Remove executable permission from Maven wrapper"

build:
	./mvnw clean package

mutation-testing:
	./mvnw package pitest:mutationCoverage

setup-maven-wrapper:
	chmod +x ./mvnw

reset-maven-wrapper:
	chmod -x ./mvnw
