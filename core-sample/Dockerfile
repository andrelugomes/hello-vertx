# Extend vert.x image
FROM vertx/vertx3

ENV VERTICLE_NAME VertX
ENV VERTICLE_FILE target/core-sample-1.0.jar

# Set the location of the verticle
ENV VERTICLE_HOME /usr/verticle

EXPOSE 8080

# Copy your verticle to the container
COPY $VERTICLE_FILE $VERTICLE_HOME/

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]