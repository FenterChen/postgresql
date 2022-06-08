FROM node:16-alpine as build-stage
WORKDIR /home/node/postgresql_client
COPY . /home/node/postgresql_client
RUN npm install && npm run build 

FROM nginx:alpine as production-stage
COPY --from=build-stage /home/node/postgresql_client/dist /usr/share/nginx/html
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
ENTRYPOINT ["nginx","-g","daemon off;"]

