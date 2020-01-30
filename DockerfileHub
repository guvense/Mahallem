FROM node:carbon

RUN npm install -g node-gyp
RUN npm install -g gulp-cli

WORKDIR /src/src/Hub
EXPOSE 3000
# EXPOSE 5050
# EXPOSE 80

COPY hub/ ./
RUN npm install --no-audit

RUN gulp build

#CMD ["sh", "-c", "tail -f /dev/null"]
CMD [ "npm", "start" ]
