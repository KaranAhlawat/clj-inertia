FROM clojure:tools-deps
COPY deps.edn /usr/src/deps.edn
COPY resources /usr/src/resources
COPY server /usr/src/server
WORKDIR /usr/src/
RUN clojure -P
CMD ["clojure", "-M", "-m", "inert.system"]
