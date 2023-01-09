#!/bin/bash

echo "Begin sleep for $2 seconds..."
sleep $2
echo "Waking up!"
java -jar $1
