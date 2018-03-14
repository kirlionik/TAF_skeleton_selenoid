#!/usr/bin/env bash
echo "============================== Check and Download last Selenoid release =============================="
grep -v "^#" docker-compose.yml|grep "image:"|awk 'BEGIN{FS="image:"};{print $2}'|xargs -I{} docker pull {}
docker pull selenoid/video-recorder:latest
echo "============================== Check browser images ================================"
cat browsers.json | jq -r '..|.image?|strings' | xargs -I{} docker pull {}
echo "============================== Start docker-compose ================================"
docker-compose up