# Resting Scalable Hamster

Base controller for build a rest API. Each controller is bound to a case class representing
  a known entity that can be stored in a MongoDB collection and serialized for
  consumers. The controller instance is passed the process' ActorSytem and
  MongoDB connection, both instantiated in ScalatraBootstrap.

  The basic GET collection, GET object, POST, PUT and DELETE routes are
  supported, and child classes can of course implement any additional routes
  as needed.

  Requests are handled asynchronously using akka.Actors. The RestActor receives
  a command corresponding to the HTTP route ("create", "update", etc), and
  whatever objects are required to complete the action - typically a case class
  instance of ObjectType constructed using JSON from the request body
