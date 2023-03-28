FROM node:lts-alpine AS frontend-builder
ADD package.json /app/package.json
ADD package-lock.json /app/package-lock.json
ADD client /app/client/
ADD vite.config.ts /app/vite.config.ts
WORKDIR /app
RUN npm ci
RUN npm run build

FROM alpine:latest AS backend-builder
RUN apk add --no-cache leiningen
ADD project.clj /app/project.clj
ADD resources/index.html /app/resources/index.html
ADD server /app/server
COPY --from=frontend-builder /app/resources/dist /app/resources/dist
WORKDIR /app
RUN lein uberjar

FROM alpine:latest
RUN apk add --no-cache openjdk17-jre-headless
COPY --from=backend-builder /app/target/inert-1.0.0-standalone.jar /srv
ARG PORT
ENV ENV production
EXPOSE $PORT
CMD ["java", "-jar", "/srv/inert-1.0.0-standalone.jar"]
