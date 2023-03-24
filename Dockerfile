FROM node:lts-alpine AS frontend-builder
RUN mkdir -p /app/client
COPY package.json /app
COPY package-lock.json /app
COPY client /app/client
COPY vite.config.ts /app
WORKDIR /app
RUN npm ci
RUN npm run build

FROM alpine:latest AS backend-builder
RUN apk add --no-cache leiningen
RUN mkdir -p /app/resources
COPY project.clj /app
COPY resources/index.html /app/resources
COPY server /app/server
COPY --from=frontend-builder /app/resources/dist /app/resources/dist
WORKDIR /app
RUN lein uberjar

FROM alpine:latest
RUN apk add --no-cache openjdk17-jre-headless
COPY --from=backend-builder /app/target/inert-1.0.0-standalone.jar /srv
CMD ["java", "-jar", "/srv/inert-1.0.0-standalone.jar"]