# How to

### to build the docker image
`docker build -t demo-project .
`

### to run the automated tests
`docker run --rm -e ENVIRONMENT_NAME={$ENVIRONMENT_NAME} demo-project`

| where $ENVIRONMENT_NAME is 'staging' or 'production'

