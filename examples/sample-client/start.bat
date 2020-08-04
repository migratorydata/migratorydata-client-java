REM !!! PLEASE EDIT THE PARAMETERS OF THE CLIENT SAMPLE !!!

REM The address of your MigratoryData server installation
set HOST=127.0.0.1

REM The port used by your MigratoryData server to listen for client connections
set PORT=8800

REM Subject to be used for subscribe / publish operations
set SUBJECT=/server/status

REM The memory in megabytes used by this client
set MEMORY=256

java -Xms%MEMORY%m -Xmx%MEMORY%m -jar sample-client.jar %HOST% %PORT% %SUBJECT% 
