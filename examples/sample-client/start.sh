#!/bin/bash

# !!! PLEASE EDIT THE PARAMETERS OF THE CLIENT SAMPLE !!!

# The address of your MigratoryData server installation
HOST=localhost

# The port used by your MigratoryData server to listen for client connections
PORT=8800

# Subject to be used for subscribe / publish operations
SUBJECT=/server/status

# The memory in megabytes used by this client
MEMORY=256

java -Xms${MEMORY}m -Xmx${MEMORY}m -jar sample-client.jar $HOST $PORT $SUBJECT
