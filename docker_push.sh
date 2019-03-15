#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push alexthelion34/yadroclient
docker push alexthelion34/yadroservice