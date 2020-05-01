#!/bin/bash
wget -qO- https://toolbelt.heroku.com/install.sh | sh
heroku auth:token
heroku container:login
docker tag $IMAGE_NAME registry.heroku.com/mahallem/web
docker tag $IMAGE_NAME_HUB registry.heroku.com/mahallem/hub
docker push registry.heroku.com/mahallem/web
docker push registry.heroku.com/mahallem/hub
heroku container:release web -a mahallem
heroku container:release hub -a mahallem