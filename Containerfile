FROM oven/bun:1 AS frontend-builder
ADD package.json /app/package.json
ADD bun.lockb /app/bun.lockb
ADD client /app/client/
ADD vite.config.ts /app/vite.config.ts
WORKDIR /app
RUN bun install --frozen-lockfile
RUN bun run build

FROM alpine:latest AS backend-builder
RUN apk add --no-cache leiningen
ADD project.clj /app/project.clj
ADD resources/index.html /app/resources/index.html
ADD server /app/server
COPY --from=frontend-builder /app/resources/dist /app/resources/dist
WORKDIR /app
RUN lein uberjar

FROM alpine:latest
RUN apk add --no-cache openjdk21-jre-headless
COPY --from=backend-builder /app/target/inert-1.0.0-standalone.jar /srv
ENV PORT=8080
ENV ENV=production
EXPOSE $PORT
CMD ["java", "-jar", "/srv/inert-1.0.0-standalone.jar"]
