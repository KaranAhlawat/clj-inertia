FROM clojure:tools-deps-alpine

COPY deps.edn /usr/src/deps.edn
COPY resources/index.html /usr/src/resources/index.html
COPY resources/env.edn /usr/src/resources/env.edn
COPY client /usr/src/client
COPY package.json /usr/src/package.json
COPY vite.config.ts /usr/src/vite.config.ts
COPY server /usr/src/server

WORKDIR /usr/src/

RUN apk update
RUN apk add nodejs npm

RUN npm install
RUN clojure -P
RUN npm run build

CMD ["clojure", "-M", "-m", "inert.system"]
