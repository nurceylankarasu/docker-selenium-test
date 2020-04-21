# docker-selenium-test
dockerize tests with selenium. chrome/firefox browsers, local/remote


docker pull selenium/hub
docker pull selenium/node-chrome
docker pull selenium/node-chrome-debug
docker pull selenium/node-firefox
docker pull selenium/node-firefox-debug

docker compose up -d
docker ps -a

mvn test -Dbrowser=chrome -Dtype=remote
